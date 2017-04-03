package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class ProfileActivity extends Activity {

    private Button backButton;
    private EditText email;
    private EditText homeAddress;
    private TextView username;
    private TextView accountType;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    /**
     * Creates the Profile activity which has the necessary information
     * transferred with it.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        addListenerOnButtonBack();
        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");

        this.username.setText(this.currentUser.getUsername());
        this.email = (EditText) findViewById(R.id.emailText);
        this.homeAddress = (EditText) findViewById(R.id.addressText);
        this.accountType.setText(this.currentUser.getAccountType());

        // Gets the information of the current user if the email/ home
        // address already exists.
        if (this.currentUser != null) {
            if (this.currentUser.getEmailAddress() != null){
                this.email.setText(this.currentUser.getEmailAddress());
            } else {
                this.email.setText("y u null");
            }
        }
        if (this.currentUser != null) {
            if (this.currentUser.getHomeAddress() != null) {
                this.homeAddress.setText(this.currentUser.getHomeAddress());
            }
        }
    }

    /**
     * Adds functionality to the back button on the the profile page.
     */
    public final void addListenerOnButtonBack() {

        final Context context = this;

        this.backButton = (Button) findViewById(R.id.backButton);
        this.email = (EditText) findViewById(R.id.emailText);
        this.homeAddress = (EditText) findViewById(R.id.addressText);
        this.username = (TextView) findViewById(R.id.usernameText);
        this.accountType = (TextView) findViewById(R.id.accountText);

        this.backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("TESTING", "Entered Onclick");

                //noinspection UnqualifiedFieldAccess
                if (email.getText() != null && currentUser != null) {
                    //noinspection UnqualifiedFieldAccess
                    currentUser.setEmailAddress(email.getText().toString());
                }
                //noinspection UnqualifiedFieldAccess
                if (homeAddress.getText() != null && currentUser != null) {
                    //noinspection UnqualifiedFieldAccess
                    currentUser.setHomeAddress(homeAddress.getText().toString());
                }

                Intent intent = new Intent(context, HomeActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

    }



}
