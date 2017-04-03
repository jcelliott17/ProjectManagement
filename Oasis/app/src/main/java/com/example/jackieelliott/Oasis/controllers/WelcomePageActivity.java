package com.example.jackieelliott.Oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/8/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("ALL")
public class WelcomePageActivity extends AppCompatActivity {

    Button button;
    Button button2;
    //These lists allow for information to persist. Eventually will be replaced by data base
    ArrayList<User> userList;
    ArrayList<Report> reportList;
    ArrayList<QualityReport> qualityList;

    /**
     * Creates the Welcome using the passed information.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        addListenerOnButton();
        addListenerOnButtonLogin();
        if (getIntent().getExtras() == null) {
            this.userList = new ArrayList<>();
            this.reportList = new ArrayList<>();
            this.qualityList = new ArrayList<>();
        } else {
            Bundle b = getIntent().getExtras();
            this.userList = b.getParcelableArrayList("UserList");
            this.reportList = b.getParcelableArrayList("ReportList");
            this.qualityList = b.getParcelableArrayList("QualityList");
        }
    }

    /**
     * Adds functionality to the register button.
     */
    public final void addListenerOnButton() {

        final Context context = this;

        this.button = (Button) findViewById(R.id.register_button);

        this.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RegisterUserActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });

    }

    /**
     * Adds functionality to the login button.
     */
    public final void addListenerOnButtonLogin() {

        final Context context = this;

        this.button2 = (Button) findViewById(R.id.login_button);

        this.button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, LoginActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });

    }

}
