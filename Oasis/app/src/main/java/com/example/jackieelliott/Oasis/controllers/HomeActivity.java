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
import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;
import com.example.jackieelliott.team60application.GoogleMapsActivity;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class HomeActivity extends Activity {

    private Button logoutButton;
    private Button profileButton;
    private Button reportButton;
    private Button tempmap;
    private ListView reportsList;
    private ArrayList<User> userList;
    private ArrayList<Worker> workerList;
    private ArrayList<Manager> managerList;
    private ArrayList<Admin> adminList;
    private ArrayList<Report> reportList;
    private User currentUser;

    @Override
    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        logoutButton = (Button) findViewById(R.id.logout_button);
        reportButton = (Button) findViewById(R.id.report_button);
        tempmap = (Button) findViewById(R.id.tempmap);
        reportsList = (ListView) findViewById(R.id.reports_list);
        addListenerOnButtonLogout();


        String[] reports = new String[reportList.size()];
        for (int i = 0; i < reportList.size(); i++) {
            reports[i] = reportList.get(i).toString();
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
        for (int i = 0; i < workerList.size(); i++) {
            if (workerList.get(i).getUsername().equals(currentUser
                    .getUsername())
                    && currentUser.getPassword().equals(workerList.get(i)
                    .getPassword())) {
                workerList.remove(i);
                workerList.add(i, (Worker) currentUser);
            }
        }


        for (int i = 0; i < managerList.size(); i++) {
            if (managerList.get(i).getUsername().equals(currentUser
                    .getUsername())
                    && currentUser.getPassword().equals(managerList.get(i)
                    .getPassword())) {
                managerList.remove(i);
                managerList.add(i, (Manager) currentUser);
            }
        }

        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getUsername().equals(currentUser.getUsername())
                    && currentUser.getPassword().equals(adminList.get(i)
                    .getPassword())) {
                adminList.remove(i);
                adminList.add(i, (Admin) currentUser);
            }
        }


        //logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
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
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });

        reportButton = (Button) findViewById(R.id.report_button);

        reportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ReportActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

        tempmap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GoogleMapsActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

    }



}
