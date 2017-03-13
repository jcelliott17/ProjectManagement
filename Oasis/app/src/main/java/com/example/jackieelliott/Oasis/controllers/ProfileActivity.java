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

import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class ProfileActivity extends Activity {

    Button backButton;
    EditText email;
    EditText homeAddress;
    TextView username;
    TextView accountType;
    ArrayList<User> userList;
    ArrayList<Report> reportList;
    User currentUser;

    /**
     * Creates the Profile activity which has the necessary information
     * transfered with it.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        addListenerOnButtonBack();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");

        username.setText(currentUser.getUsername());
        email = (EditText) findViewById(R.id.emailText);
        homeAddress = (EditText) findViewById(R.id.addressText);
        accountType.setText(currentUser.getAccountType());

        // Gets the information of the current user if the email/ home
        // address already exists.
        if (currentUser != null) {
            if (currentUser.getEmailAddress() != null){
                email.setText(currentUser.getEmailAddress());
            } else {
                email.setText("y u null");
            }
        }
        if (currentUser != null) {
            if (currentUser.getHomeAddress() != null) {
                homeAddress.setText(currentUser.getHomeAddress());
            }
        }
    }

    /**
     * Adds functionality to the back button on the the profile page.
     */
    public void addListenerOnButtonBack() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.backButton);
        email = (EditText) findViewById(R.id.emailText);
        homeAddress = (EditText) findViewById(R.id.addressText);
        username = (TextView) findViewById(R.id.usernameText);
        accountType = (TextView) findViewById(R.id.accountText);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("TESTING", "Entered Onclick");

                if (email.getText() != null && currentUser != null) {
                    Log.d("Input",email.getText().toString());
                    currentUser.setEmailAddress(email.getText().toString());
                }
                if (homeAddress.getText() != null && currentUser != null) {
                    currentUser.setHomeAddress(homeAddress.getText().toString());
                }

                Intent intent = new Intent(context, HomeActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

    }



}
