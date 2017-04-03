package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;

import java.util.ArrayList;


import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class LoginActivity extends Activity {

    private Button login;
    private Button cancel;
    private EditText loginField;
    private EditText passField;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        reportList  = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");
        qualityList = b.getParcelableArrayList("QualityList");

    }

    /**
     * adds functionality to login button
     */
    public final void addListenerOnButtonLogin() {

        final Context context = this;

        login = (Button) findViewById(R.id.login_button);
        loginField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText2);
        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity
                .this).create();
        //Ignore this issue, portability issues
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Wrong Username/Password");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /*
                Searches for login username. Warning should pop up if the
                user name does not exist. If the user name and password
                combination exist then the current user is set the user
                found.
                 */
                boolean login = false;
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUsername().equals(loginField
                            .getText().toString()) && passField.getText()
                            .toString().equals(userList.get(i).getPassword())) {
                        login = true;
                        currentUser = userList.get(i);
                    }
                }
                if (login) {

                    // Passed information among activities

                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("ReportList",
                            reportList);
                    intent.putExtra("CurrentUser", currentUser);
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);
                } else {
                    alertDialog.show();
                }

            }

        });

    }

    /**
     * adds functionality to cancel button
     */
    public final void addListenerOnButtonCancel() {

        final Context context = this;

        cancel = (Button) findViewById(R.id.login_Cancel);

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Passes information amount activities
                Intent intent = new Intent(context, WelcomePageActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);

            }

        });

    }

}
