package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class LoginActivity extends Activity {

    Button login;
    Button cancel;
    EditText loginField;
    EditText passField;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    private static final String TAG = "LoginActivity-TAG";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private DatabaseReference accountEndPoint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        accountEndPoint = mDatabase.child("accounts");

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
                // ...
            }
        };
    }

    public void addListenerOnButtonLogin() {

        final Context context = this;

        login = (Button) findViewById(R.id.login_button);
        loginField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText2);
        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Wrong Username/Password");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //boolean login = false;
                if (validateForm()) {
                    signIn();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("WorkerList", workerList);
                    intent.putParcelableArrayListExtra("ManagerList", managerList);
                    intent.putParcelableArrayListExtra("AdminList", adminList);
                    startActivity(intent);
                } else {
                    alertDialog.show();
                }
                /*
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUsername().equals(loginField.getText().toString())
                            && passField.getText().toString().equals(userList.get(i).getPassword())) {
                        login = true;
                    }
                }
                if (!login) {
                    for (int i = 0; i < workerList.size(); i++) {
                        if (workerList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(workerList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }
                if (!login) {
                    for (int i = 0; i < managerList.size(); i++) {
                        if (managerList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(managerList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }
                if (!login) {
                    for (int i = 0; i < adminList.size(); i++) {
                        if (adminList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(adminList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }

                if (login) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("WorkerList", workerList);
                    intent.putParcelableArrayListExtra("ManagerList", managerList);
                    intent.putParcelableArrayListExtra("AdminList", adminList);
                    startActivity(intent);
                } else {
                    alertDialog.show();
                }
                */

            }

        });

    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancel = (Button) findViewById(R.id.login_Cancel);

        cancel.setOnClickListener(new View.OnClickListener() {

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

    private void signIn() {
        String email = loginField.getText().toString();
        String password = passField.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private boolean validateForm() {
        return (isEmailValid(loginField.getText().toString())
            && isPasswordValid(passField.getText().toString()));
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
