package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class HomeActivity extends Activity {

    Button logoutButton;
    Button profileButton;
    Button reportButton;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        addListenerOnButtonLogout();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
    }

    public void addListenerOnButtonLogout() {

        final Context context = this;

        logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);

            }

        });

        profileButton = (Button) findViewById(R.id.button);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
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
                startActivity(intent);

            }

        });

    }



}
