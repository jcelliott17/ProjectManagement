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
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.R;
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

public class RegisterUserActivity extends Activity{

    Button registerBotton;
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
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;


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
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
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
                            if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
                                User user = new User(emailField.getText().toString(), passField.getText().toString());
                                userList.add(user);
                            } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
                                Worker worker = new Worker(emailField.getText().toString(), passField.getText().toString());
                                workerList.add(worker);
                            } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
                                Manager manager = new Manager(emailField.getText().toString(), passField.getText().toString());
                                managerList.add(manager);
                            } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
                                Admin admin = new Admin(emailField.getText().toString(), passField.getText().toString());
                                adminList.add(admin);
                            }
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.putParcelableArrayListExtra("UserList", userList);
                            intent.putParcelableArrayListExtra("WorkerList", workerList);
                            intent.putParcelableArrayListExtra("ManagerList", managerList);
                            intent.putParcelableArrayListExtra("AdminList", adminList);
                            startActivity(intent);
                        }
                        //task.
                    }
                });
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

    public void addListenerOnButtonRegister() {

        context = this;

        registerBotton = (Button) findViewById(R.id.registerOnRegisterPage);
        emailField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText3);

        registerBotton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (validateForm()) {
                    createAccount(emailField.getText().toString(), passField.getText().toString());
                } else {
                    Log.d(TAG, "Failed validation");
                }

            }

        });
    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);

            }

        });
    }


}
