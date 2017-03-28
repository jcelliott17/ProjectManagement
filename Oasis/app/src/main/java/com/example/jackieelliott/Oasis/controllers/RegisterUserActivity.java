package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.jackieelliott.Oasis.Model.AccountTypes;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;
import com.example.jackieelliott.Oasis.Model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class RegisterUserActivity extends Activity {

    Button registerButton;
    Button cancelButton;
    private Spinner accountTypeSpinner;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "RegUserActivity-TAG";
    private DatabaseReference mDatabase;
    private DatabaseReference accountEndPoint;
    private Context context;
    EditText emailField;
    EditText passField;
    ArrayList<User> userList;
    ArrayList<Report> reportList;
    ArrayList<QualityReport> qualityList;
    //currentUser created to keep track of who is using the application
    //this will be removed once the database starts working
    User currentUser;

    /**
     * Creates the report activity page.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        addListenerOnButtonRegister();
        addListenerOnButtonCancel();

        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountTypes.AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");
        qualityList = b.getParcelableArrayList("QualityList");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        accountEndPoint = mDatabase.child("accounts");
        mAuth = FirebaseAuth.getInstance();

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                // updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }


    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

    private void createAccount(String email, String password) {
        accountEndPoint.setValue(email + " - " + password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterUserActivity.this, "Authentication failed: " + task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterUserActivity.this, "You're in!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.putParcelableArrayListExtra("UserList", userList);
                            startActivity(intent);
                            makeNewUser();
                            goToHome();
                        }
                        //task.
                    }
                });
    }

    private void makeNewUser() {
        Object userObject;
        userObject = new User(emailField.getText().toString(), passField.getText().toString(), 1);
        //userList.add(user);
        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userObject);
    }

    private void goToHome() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putParcelableArrayListExtra("UserList", userList);
        startActivity(intent);
    }

    private boolean validateForm() {

        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = passField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passField.setError("Required.");
            valid = false;
        } else {
            passField.setError(null);
        }
        return valid;
    }

    /**
     * Adds functionality to the register button.
     */
    public void addListenerOnButtonRegister() {

        context = this;

        registerButton = (Button) findViewById(R.id.registerOnRegisterPage);
        emailField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText3);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Here we did not account for if that user already exists
                // A warning should pop up here and let them know if that username already
                // exists?

                /*
                Determines what type of user to create based on selection. Then sets the current user to the user
                created.
                 */
                if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
                    User user = new User(emailField.getText().toString(), passField.getText().toString(), 1);
                    userList.add(user);
                    currentUser = user;
                    currentUser.setAccountType("User");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
                    User user = new User(emailField.getText().toString(), passField.getText().toString(), 2);
                    userList.add(user);
                    currentUser = user;
                    currentUser.setAccountType("Worker");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
                    User user = new User(emailField.getText().toString(), passField.getText().toString(), 3);
                    userList.add(user);
                    currentUser = user;
                    currentUser.setAccountType("Manager");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
                    User user = new User(emailField.getText().toString(), passField.getText().toString(), 3);
                    userList.add(user);
                    currentUser = user;
                    currentUser.setAccountType("Admin");
                }
                // Passes information amoung models
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

                if (validateForm()) {
                    createAccount(emailField.getText().toString(), passField.getText().toString());
                } else {
                    Log.d(TAG, "Failed validation");
                }

            }

        });
    }

    /**
     * Added functionality to the cancel button on the register page.
     */
    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });
    }

}
