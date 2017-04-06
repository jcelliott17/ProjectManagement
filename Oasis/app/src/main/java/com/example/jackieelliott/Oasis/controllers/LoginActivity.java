package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Toast;

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

/**
 * Created by JackieElliott on 2/8/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class LoginActivity extends Activity {

    private EditText loginField;
    private EditText passField;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    private static final String TAG = "LoginActivity-TAG";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ValueEventListener mUserListener;
    private DatabaseReference mUserReference;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState saved instance state
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
        Bundle b = getIntent().getExtras();
        this.reportList  = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        mAuth = FirebaseAuth.getInstance();
        mUserReference = FirebaseDatabase.getInstance().getReference().child("user");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    mUserReference.addValueEventListener(mUserListener);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Loop through children for current user
                Iterable<DataSnapshot> userlist = dataSnapshot.getChildren();
                for (DataSnapshot user : userlist) {
                    User candidate = user.getValue(User.class);
                    Log.d(TAG, "looping!");
                    if (candidate.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
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
     * adds functionality to login button
     */
    private void addListenerOnButtonLogin() {

        final Context context = this;

        Button login = (Button) findViewById(R.id.login_button);
        this.loginField = (EditText) findViewById(R.id.username_text);
        this.passField = (EditText) findViewById(R.id.editText2);
        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity
                .this).create();
        //Ignore this issue, portability issues
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Wrong Username/Password");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (validateForm()) {
                    signIn();
                } else {
                    alertDialog.show();
                }
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
                        } else {
                            Toast.makeText(LoginActivity.this, "You're in!",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Cur Perm: " + CurrentUser.getUser().getPermission());
                            goToHome();
                        }

                    }
                });
    }

    /**
     * adds functionality to cancel button
     */
    private void addListenerOnButtonCancel() {

        final Context context = this;

        Button cancel = (Button) findViewById(R.id.login_Cancel);

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Passes information amount activities
                Intent intent = new Intent(context, WelcomePageActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });

    }

    private void goToHome() {
        // Passed information among activities

        final Context context = this;

        Intent intent = new Intent(context, HomeActivity.class);
        //noinspection UnqualifiedFieldAccess
        intent.putParcelableArrayListExtra("QualityList", qualityList);
        //noinspection UnqualifiedFieldAccess
        intent.putParcelableArrayListExtra("ReportList", reportList);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = loginField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            loginField.setError("Required.");
            valid = false;
        } else {
            loginField.setError(null);
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
