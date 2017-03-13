package com.example.jackieelliott.Oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Report;
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
    ArrayList<Report> reportList;

    @Override
    /**
     * Creates the Welcome using the passed information.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        addListenerOnButton();
        addListenerOnButtonLogin();
        if (getIntent().getExtras() == null) {
            userList = new ArrayList<>();
            reportList = new ArrayList<>();
        } else {
            Bundle b = getIntent().getExtras();
            userList = b.getParcelableArrayList("UserList");
            reportList = b.getParcelableArrayList("ReportList");
        }
    }

    /**
     * Adds functionality to the register button.
     */
    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.register_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RegisterUserActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                startActivity(intent);

            }

        });

    }

    /**
     * Adds functionality to the login button.
     */
    public void addListenerOnButtonLogin() {

        final Context context = this;

        button2 = (Button) findViewById(R.id.login_button);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, LoginActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                startActivity(intent);

            }

        });

    }

}
