package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jackieelliott.team60application.Model.Admin;
import com.example.jackieelliott.team60application.Model.Manager;
import com.example.jackieelliott.team60application.Model.User;
import com.example.jackieelliott.team60application.Model.Worker;
import com.example.jackieelliott.team60application.R;

import org.w3c.dom.Text;

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
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        addListenerOnButtonLogout();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        //username.setText();
    }

    public void addListenerOnButtonLogout() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.backButton);
        email = (EditText) findViewById(R.id.emailText);
        homeAddress = (EditText) findViewById(R.id.addressText);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(context, HomeActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);

            }

        });

    }



}
