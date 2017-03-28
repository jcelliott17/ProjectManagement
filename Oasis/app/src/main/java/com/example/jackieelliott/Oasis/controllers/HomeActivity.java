package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.jackieelliott.team60application.GoogleMapsActivity;
import com.example.jackieelliott.Oasis.controllers.SelectReportTypeActivity;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");
        logoutButton = (Button) findViewById(R.id.logout_button);
        reportButton = (Button) findViewById(R.id.report_button);
        qualityListButton = (Button) findViewById(R.id.qualitylist_button);
        graphButton = (Button) findViewById(R.id.graph_button);
        tempmap = (Button) findViewById(R.id.tempmap);
        reportsList = (ListView) findViewById(R.id.reports_list);
        addListenerOnButtonLogout();

        String[] reports = new String[reportList.size()];
        for (int i = 0; i < reportList.size(); i++) {
            reports[i] = reportList.get(i).toString();
        }

        if (currentUser.getPermission() < 3) {
            qualityListButton.setVisibility(View.GONE);
            graphButton.setVisibility(View.GONE);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, reports);
        reportsList.setAdapter(adapter);

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


        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(currentUser.getUsername())
                    && currentUser.getPassword().equals(userList.get(i)
                    .getPassword())) {
                userList.remove(i);
                userList.add(i, currentUser); // replaces the user with the
                // updated current user
                // This is necessary because of pass by value
            }
        }


        //logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });

        profileButton = (Button) findViewById(R.id.button2);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });

        reportButton = (Button) findViewById(R.id.report_button);

        reportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (currentUser.getPermission() == 2 || currentUser.getPermission() == 3) {
                    Intent intent = new Intent(context, SelectReportTypeActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                }
            }

        });

        tempmap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GoogleMapsActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

        qualityListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, QualityListActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

        graphButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateGraphActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });

    }



}
