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

import com.example.jackieelliott.Oasis.Model.AccountTypes;

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

public class HomeActivity extends Activity {

    private Button logoutButton;
    private Button profileButton;
    private Button reportButton;
    private Button tempmap;
    private Button qualityListButton;
    /*
    private ListView reportsList;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;
    */
    private static final String TAG = "HomeActivity-TAG";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User _user;
    private Util util;


    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        //Bundle b = getIntent().getExtras();
        /*
        userList = b.getParcelableArrayList("UserList");
        //currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");
        */
        logoutButton = (Button) findViewById(R.id.logout_button);
        reportButton = (Button) findViewById(R.id.report_button);
        qualityListButton = (Button) findViewById(R.id.qualitylist_button);
        tempmap = (Button) findViewById(R.id.tempmap);
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


        /*
        String[] reports = new String[reportList.size()];
        for (int i = 0; i < reportList.size(); i++) {
            reports[i] = reportList.get(i).toString();
        }
        */

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, reports);
        reportsList.setAdapter(adapter);*/

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
    public void addListenerOnButtonLogout() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;

        //logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
        //        intent.putParcelableArrayListExtra("UserList", userList);
        //        intent.putParcelableArrayListExtra("ReportList", reportList);
        //        intent.putParcelableArrayListExtra("QualityList", qualityList);
        //        intent.putExtra("CurrentUser", currentUser);
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

        profileButton = (Button) findViewById(R.id.button2);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ProfileActivity.class);
        //        intent.putParcelableArrayListExtra("UserList", userList);
        //        intent.putParcelableArrayListExtra("ReportList", reportList);
        //        intent.putParcelableArrayListExtra("QualityList", qualityList);
        //        intent.putExtra("CurrentUser", currentUser);
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

        reportButton = (Button) findViewById(R.id.report_button);
        reportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (_user.getPermission() >= 2) {
                    Intent intent = new Intent(context, SelectReportTypeActivity.class);
                    //intent.putParcelableArrayListExtra("UserList", userList);
                    //intent.putParcelableArrayListExtra("ReportList", reportList);
                    //intent.putParcelableArrayListExtra("QualityList", qualityList);
                    //intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    //intent.putParcelableArrayListExtra("UserList", userList);
                    //intent.putParcelableArrayListExtra("ReportList", reportList);
                    //intent.putParcelableArrayListExtra("QualityList", qualityList);
                    //intent.putExtra("CurrentUser", currentUser);
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

        tempmap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GoogleMapsActivity.class);
                //intent.putParcelableArrayListExtra("UserList", userList);
                //intent.putParcelableArrayListExtra("ReportList", reportList);
                //intent.putParcelableArrayListExtra("QualityList", qualityList);
                //intent.putExtra("CurrentUser", currentUser);
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

        qualityListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, QualityListActivity.class);
                //intent.putParcelableArrayListExtra("UserList", userList);
                //intent.putParcelableArrayListExtra("ReportList", reportList);
                //intent.putParcelableArrayListExtra("QualityList", qualityList);
                //intent.putExtra("CurrentUser", currentUser);
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
