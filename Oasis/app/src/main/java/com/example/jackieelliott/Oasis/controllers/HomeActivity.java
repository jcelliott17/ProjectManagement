package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    User currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        currentUser = b.getParcelable("CurrentUser");
        addListenerOnButtonLogout();

    }


    public void addListenerOnButtonLogout() {

        /*
        Sets the user that you originally used to create current user to the updated current user
        which could potentially contain new address/ home email.
         */
        final Context context = this;


        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(currentUser.getUsername())
                    && currentUser.getPassword().equals(userList.get(i).getPassword())) {
                userList.remove(i);
                userList.add(i, currentUser); // replaces the user with the updated current user
                // This is necessary because of pass by value
            }
        }
            for (int i = 0; i < workerList.size(); i++) {
                if (workerList.get(i).getUsername().equals(currentUser.getUsername())
                        && currentUser.getPassword().equals(workerList.get(i).getPassword())) {
                    workerList.remove(i);
                    workerList.add(i, (Worker) currentUser);
                }
            }


            for (int i = 0; i < managerList.size(); i++) {
                if (managerList.get(i).getUsername().equals(currentUser.getUsername())
                        && currentUser.getPassword().equals(managerList.get(i).getPassword())) {
                    managerList.remove(i);
                    managerList.add(i, (Manager) currentUser);
                }
            }

            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).getUsername().equals(currentUser.getUsername())
                        && currentUser.getPassword().equals(adminList.get(i).getPassword())) {
                    adminList.remove(i);
                    adminList.add(i, (Admin) currentUser);
                }
            }


        logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                intent.putExtra("CurrentUser", currentUser);
                Log.d("TESomh", currentUser.getEmailAddress());
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
                startActivity(intent);

            }

        });

    }



}
