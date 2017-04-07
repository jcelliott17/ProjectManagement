package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jackieelliott.Oasis.Model.CurrentUser;
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

    private EditText email;
    private EditText homeAddress;
    private TextView username;
    private TextView accountType;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * Creates the Profile activity which has the necessary information
     * transferred with it.
     */
    @SuppressWarnings("FeatureEnvy")
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        addListenerOnButtonBack();

        User current = CurrentUser.getUser();
        //noinspection LawOfDemeter
        this.username.setText(current.getUsername());
        this.email = (EditText) findViewById(R.id.emailText);
        this.homeAddress = (EditText) findViewById(R.id.addressText);
        //noinspection LawOfDemeter
        this.accountType.setText(current.getAccountType());
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        // Gets the information of the current user if the email/ home
        // address already exists.
        if (CurrentUser.getUser() != null) {
            //noinspection LawOfDemeter
            if (current.getEmailAddress() != null){
                //noinspection LawOfDemeter
                this.email.setText(current.getEmailAddress());
            }
        }
        if (CurrentUser.getUser() != null) {
            //noinspection LawOfDemeter
            if (current.getHomeAddress() != null) {
                //noinspection LawOfDemeter
                this.homeAddress.setText(current.getHomeAddress());
            }
        }
    }

    /**
     * Adds functionality to the back button on the the profile page.
     */
    @SuppressWarnings("FeatureEnvy")
    private void addListenerOnButtonBack() {

        final Context context = this;

        Button backButton = (Button) findViewById(R.id.backButton);
        this.email = (EditText) findViewById(R.id.emailText);
        this.homeAddress = (EditText) findViewById(R.id.addressText);
        this.username = (TextView) findViewById(R.id.usernameText);
        this.accountType = (TextView) findViewById(R.id.accountText);

        //noinspection FeatureEnvy
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("TESTING", "Entered Onclick");

                User current = CurrentUser.getUser();
                Editable e1 = email.getText();
                Editable e2 = homeAddress.getText();
                //noinspection UnqualifiedFieldAccess
                if ((email.getText() != null) && (CurrentUser.getUser() != null)) {
                    //noinspection UnqualifiedFieldAccess,LawOfDemeter
                    current.setEmailAddress(e1.toString());
                }
                //noinspection UnqualifiedFieldAccess
                if ((homeAddress.getText() != null) && (CurrentUser.getUser() != null)) {
                    //noinspection UnqualifiedFieldAccess,LawOfDemeter
                    current.setHomeAddress(e2.toString());
                }

                Intent intent = new Intent(context, HomeActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });

    }

}
