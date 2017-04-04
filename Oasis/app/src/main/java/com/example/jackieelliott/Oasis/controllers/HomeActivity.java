package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;
import com.example.jackieelliott.Oasis.controllers.GoogleMapsActivity;
import com.example.jackieelliott.Oasis.controllers.SelectReportTypeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class HomeActivity extends Activity {

    private Button logoutButton;
    private Button reportButton;
    private Button tempMap;
    private Button qualityListButton;
    private static final String TAG = "HomeActivity-TAG";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User _user;


    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        logoutButton = (Button) findViewById(R.id.logout_button);
        reportButton = (Button) findViewById(R.id.report_button);
        qualityListButton = (Button) findViewById(R.id.qualitylist_button);
        tempMap = (Button) findViewById(R.id.tempmap);
        //reportsList = (ListView) findViewById(R.id.reports_list);
        //util = new Util();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("user");
        addButtonListeners();

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

    }

    private void addButtonListeners() {
        addListenerOnButtonLogout();
        addListenerOnButtonProfile();
        addListenerOnButtonReport();
        addListenerOnButtontempmap();
        addListenerOnButtonQualityList();
    }

    /**
     * Adds functionality to the logout button
     */
    private void addListenerOnButtonLogout() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        //logoutButton = (Button) findViewById(R.id.logout_button);

        this.logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
                startActivity(intent);
            }

        });
    }

    /**
     * Adds functionality to the profile button
     */
    public void addListenerOnButtonProfile() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        Button profileButton = (Button) findViewById(R.id.button2);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
            }

        });
    }

    /**
     * Adds functionality to the report button
     */
    public void addListenerOnButtonReport() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        //reportButton = (Button) findViewById(R.id.report_button);
        //reportButton.setOnClickListener(new View.OnClickListener() {

        this.reportButton = (Button) findViewById(R.id.report_button);

        this.reportButton.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("UnqualifiedFieldAccess")
            @Override
            public void onClick(View arg0) {
                if (_user.getPermission() >= 2) {
                    Intent intent = new Intent(context, SelectReportTypeActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    startActivity(intent);
                }
            }

        });
    }

        /**
         * Adds functionality to the tempmap button
         */
    public void addListenerOnButtontempmap() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        this.tempMap.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GoogleMapsActivity.class);
                startActivity(intent);

            }

        });
    }

        /**
         * Adds functionality to the quality list button
         */
    public void addListenerOnButtonQualityList() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        this.qualityListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, QualityListActivity.class);
                startActivity(intent);

            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Loop through children for current user
                Iterable<DataSnapshot> userlist = dataSnapshot.getChildren();
                for (DataSnapshot user : userlist) {
                    User candidate = user.getValue(User.class);
                    Log.d(TAG, "looping!");
                    Log.d(TAG, "Username: " + candidate.getUsername());
                    Log.d(TAG, "User ID: " + candidate.getUserID());
                    Log.d(TAG, "FB ID: " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                    if (candidate.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        _user = candidate;
                        Log.d(TAG, "resetting user!");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mUserReference.addValueEventListener(userListener);

        /*if (_user.getPermission() <= 2) {
            qualityListButton.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        //if (mAuthListener != null) {
        //    mAuth.removeAuthStateListener(mAuthListener);
        //}
    }

}
