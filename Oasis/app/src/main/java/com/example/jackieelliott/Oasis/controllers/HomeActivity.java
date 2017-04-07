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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jackieelliott.Oasis.Model.CurrentUser;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Home Activity class
 * loads the information and display for the homepage
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings({"ClassWithTooManyDependencies", "CyclicClassDependency",
        "ClassWithTooManyDependencies"})

public class HomeActivity extends Activity {

    private Button logoutButton;
    private Button reportButton;
    private Button tempMap;
    private Button qualityListButton;
    private Button graphButton;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private static final String TAG = "HomeActivity-TAG";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ValueEventListener mUserListener;


    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");
        logoutButton = (Button) findViewById(R.id.logout_button);
        reportButton = (Button) findViewById(R.id.report_button);
        qualityListButton = (Button) findViewById(R.id.qualityList_button);
        tempMap = (Button) findViewById(R.id.tempMap);
        graphButton = (Button) findViewById(R.id.graph_button);
        ListView reportsList = (ListView) findViewById(R.id.reports_list);
        mAuth = FirebaseAuth.getInstance();
        //noinspection ChainedMethodCall
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //noinspection ChainedMethodCall
        mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("user");

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
                Iterable<DataSnapshot> userList = dataSnapshot.getChildren();
                for (DataSnapshot user : userList) {
                    User candidate = user.getValue(User.class);
                    Log.d(TAG, "looping!");
                    FirebaseAuth fa = FirebaseAuth.getInstance();
                    if (fa != null) {
                        FirebaseUser cu = fa.getCurrentUser();
                        if (cu != null) {
                            String id = cu.getUid();
                            String uId = candidate.getUserID();
                            if (uId.equals(id)) {
                                CurrentUser.updateUser(candidate);
                                Log.d(TAG, "Updating user!");
                                }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        addButtonListeners();

        String[] reports = new String[this.reportList.size()];
        for (int i = 0; i < this.reportList.size(); i++) {
            Report r = reportList.get(i);
            reports[i] = r.toString();
        }

        ListAdapter adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, reports);
        reportsList.setAdapter(adapter);


        User current = CurrentUser.getUser();
        //noinspection LawOfDemeter
        if (current.getPermission() <= 2) {
            qualityListButton.setVisibility(View.GONE);
            graphButton.setVisibility(View.GONE);
        }

    }

    private void addButtonListeners() {
        addListenerOnButtonLogout();
        addListenerOnButtonProfile();
        addListenerOnButtonReport();
        addListenerOnButtonTempMap();
        addListenerOnButtonQualityList();
        addListenerOnButtonGraph();
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
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);
            }

        });
    }

    /**
     * Adds functionality to the profile button
     */
    private void addListenerOnButtonProfile() {

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
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);
            }

        });
    }

    /**
     * Adds functionality to the report button
     */
    private void addListenerOnButtonReport() {

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
                User current = CurrentUser.getUser();
                //noinspection LawOfDemeter
                if (current.getPermission() >= 2) {
                    Intent intent = new Intent(context, SelectReportTypeActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);
                }
            }

        });
    }

        /**
         * Adds functionality to the tempMap button
         */
        private void addListenerOnButtonTempMap() {

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
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });
    }

        /**
         * Adds functionality to the quality list button
         */
        private void addListenerOnButtonQualityList() {

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
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });
    }

    private void addListenerOnButtonGraph() {
        final Context context = this;

        this.graphButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateGraphActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });
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
