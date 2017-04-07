package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jackieelliott.Oasis.Model.AccountTypes;
import com.example.jackieelliott.Oasis.Model.CurrentUser;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressWarnings("CyclicClassDependency")
/**
 * Register User activity class
 */
public class RegisterUserActivity extends Activity {

    private Button registerButton;
    private Spinner accountTypeSpinner;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ValueEventListener mUserListener;
    private static final String TAG = "RegUserActivity-TAG";
    private DatabaseReference mUserReference;
    private Context context;
    private EditText emailField;
    private EditText passField;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * Creates the report activity page.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        addListenerOnButtonRegister();
        addListenerOnButtonCancel();

        this.accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                        AccountTypes.AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.accountTypeSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        //noinspection ChainedMethodCall
        mUserReference = FirebaseDatabase.getInstance().getReference().child("user");
        mAuth = FirebaseAuth.getInstance();

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

            }
        };

        mUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Loop through children for current user
                FirebaseAuth fa = FirebaseAuth.getInstance();
                Iterable<DataSnapshot> userList = dataSnapshot.getChildren();
                for (DataSnapshot user : userList) {
                    User candidate = user.getValue(User.class);
                    Log.d(TAG, "looping!");
                    String id = candidate.getUserID();
                    FirebaseUser cu = fa.getCurrentUser();
                    if (id.equals(cu != null ? cu.getUid() : null)) {
                        CurrentUser.updateUser(candidate);
                        Log.d(TAG, "Updating user!");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    /**
     * Adds functionality to the register button.
     */
    private void addListenerOnButtonRegister() {

        context = this;

        registerButton = (Button) findViewById(R.id.registerOnRegisterPage);
        this.emailField = (EditText) findViewById(R.id.username_text);
        this.passField = (EditText) findViewById(R.id.editText3);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Here we did not account for if that user already exists
                // A warning should pop up here and let them know if that username already
                // exists?

                Editable em = emailField.getText();
                Editable pass = passField.getText();
                if (validateForm()) {
                    createAccount(em.toString(), pass.toString());
                } else {
                    Log.d(TAG, "Failed validation");
                }

            }

        });

    }

    /**
     * Added functionality to the cancel button on the register page.
     */
    private void addListenerOnButtonCancel() {

        final Context context = this;

        Button cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });
    }

    private void createAccount(String email, String password) {
        //noinspection ChainedMethodCall
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            @SuppressWarnings("ThrowableResultOfMethodCallIgnored") Exception e =
                                    task.getException();
                            Toast t =  Toast.makeText(RegisterUserActivity.this,
                                    "Authentication failed: " + (e != null ? e.toString() : null),
                                    Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            Toast t = Toast.makeText(RegisterUserActivity.this, "You're in!",
                                    Toast.LENGTH_SHORT);
                            t.show();
                            makeNewUser();
                            goToHome();
                        }
                    }
                });
    }

    /*
      Determines what type of user to create based on selection.
      Then sets the current user to the user
      created. Adds new user to Database.
    */
    @SuppressWarnings("FeatureEnvy")
    private void makeNewUser() {
        Editable em = emailField.getText();
        Editable pass = passField.getText();
        FirebaseUser cu = mAuth.getCurrentUser();
        User newUser = new User(em.toString(),
                            pass.toString(),
                cu != null ? cu.getUid() : null
        );

        int permission = 1;
        if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
            newUser.setAccountType("User");
        } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
            permission = 2;
            newUser.setAccountType("Worker");
        } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
            permission = 3;
            newUser.setAccountType("Manager");
        } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
            permission = 4;
            newUser.setAccountType("Admin");
        }
        newUser.setPermission(permission);
        @SuppressWarnings("ConstantConditions") DatabaseReference child =
                mUserReference.child(cu != null ? cu.getUid() : null);
        child.setValue(newUser);
        CurrentUser.updateUser(newUser);
    }

    private void goToHome() {
        Intent intent = new Intent(context, HomeActivity.class);
        //noinspection UnqualifiedFieldAccess
        intent.putParcelableArrayListExtra("ReportList", reportList);
        //noinspection UnqualifiedFieldAccess
        intent.putParcelableArrayListExtra("QualityList", qualityList);
        startActivity(intent);
    }

    private boolean validateForm() {

        boolean valid = true;

        Editable em = emailField.getText();
        Editable pass = passField.getText();
        String email = em.toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = pass.toString();
        if (TextUtils.isEmpty(password)) {
            passField.setError("Required.");
            valid = false;
        } else {
            passField.setError(null);
        }
        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        mUserReference.addValueEventListener(mUserListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
