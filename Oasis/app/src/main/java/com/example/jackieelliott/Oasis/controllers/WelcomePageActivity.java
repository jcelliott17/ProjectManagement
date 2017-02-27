package com.example.jackieelliott.Oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class WelcomePageActivity extends AppCompatActivity {

    Button button;
    Button button2;
    //These lists allow for information to persist. Eventually will be replaced by data base
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        addListenerOnButton();
        addListenerOnButtonLogin();
        if (getIntent().getExtras() == null) {
            userList = new ArrayList<>();
            workerList = new ArrayList<>();
            managerList = new ArrayList<>();
            adminList = new ArrayList<>();
        } else {
            Bundle b = getIntent().getExtras();
            userList = b.getParcelableArrayList("UserList");
            workerList = b.getParcelableArrayList("WorkerList");
            managerList = b.getParcelableArrayList("ManagerList");
            adminList = b.getParcelableArrayList("AdminList");
        }
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.register_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RegisterUserActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);

            }

        });

    }

    public void addListenerOnButtonLogin() {

        final Context context = this;

        button2 = (Button) findViewById(R.id.login_button);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, LoginActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);

            }

        });

    }

}
