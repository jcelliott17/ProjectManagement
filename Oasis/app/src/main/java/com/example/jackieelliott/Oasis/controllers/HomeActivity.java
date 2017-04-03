package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;


import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class HomeActivity extends Activity {

    private Button logoutButton;
    private Button profileButton;
    private Button reportButton;
    private Button tempMap;
    private Button qualityListButton;
    private Button graphButton;
    private ListView reportsList;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");
        this.logoutButton = (Button) findViewById(R.id.logout_button);
        this.reportButton = (Button) findViewById(R.id.report_button);
        this.qualityListButton = (Button) findViewById(R.id.qualitylist_button);
        this.graphButton = (Button) findViewById(R.id.graph_button);
        this.tempMap = (Button) findViewById(R.id.tempmap);
        this.reportsList = (ListView) findViewById(R.id.reports_list);
        addListenerOnButtonLogout();

        String[] reports = new String[this.reportList.size()];
        for (int i = 0; i < this.reportList.size(); i++) {
            reports[i] = this.reportList.get(i).toString();
        }

        if (this.currentUser.getPermission() < 3) {
            this.qualityListButton.setVisibility(View.GONE);
            this.graphButton.setVisibility(View.GONE);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, reports);
        this.reportsList.setAdapter(adapter);

    }

    /**
     * Adds functionality to the logout button
     */
    public final void addListenerOnButtonLogout() {

        /*
        Sets the user that you originally used to create
        current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;


        for (int i = 0; i < this.userList.size(); i++) {
            if (this.userList.get(i).getUsername().equals(this.currentUser.getUsername())
                    && this.currentUser.getPassword().equals(this.userList.get(i)
                    .getPassword())) {
                this.userList.remove(i);
                this.userList.add(i, this.currentUser); // replaces the user with the
                // updated current user
                // This is necessary because of pass by value
            }
        }


        //logoutButton = (Button) findViewById(R.id.logout_button);

        this.logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
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

        this.profileButton = (Button) findViewById(R.id.button2);

        this.profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ProfileActivity.class);
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

        this.reportButton = (Button) findViewById(R.id.report_button);

        this.reportButton.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("UnqualifiedFieldAccess")
            @Override
            public void onClick(View arg0) {

                //noinspection UnqualifiedFieldAccess
                if (currentUser.getPermission() == 2 || currentUser.getPermission() == 3) {
                    Intent intent = new Intent(context, SelectReportTypeActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("UserList", userList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
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
            }

        });

        this.tempMap.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GoogleMapsActivity.class);
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

        this.qualityListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, QualityListActivity.class);
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

        this.graphButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateGraphActivity.class);
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
