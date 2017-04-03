package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList  = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");

    }

    /**
     * adds functionality to login button
     */
    public void addListenerOnButtonLogin() {

        final Context context = this;

        this.login = (Button) findViewById(R.id.login_button);
        this.loginField = (EditText) findViewById(R.id.username_text);
        this.passField = (EditText) findViewById(R.id.editText2);
        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity
                .this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Wrong Username/Password");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        this.login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /*
                Searches for login username. Warning should pop up if the
                user name does not exist. If the user name and password
                combination exist then the current user is set the user
                found.
                 */
                boolean login = false;
                //noinspection UnqualifiedFieldAccess
                for (int i = 0; i < userList.size(); i++) {
                    //noinspection UnqualifiedFieldAccess
                    if (userList.get(i).getUsername().equals(loginField
                            .getText().toString()) && passField.getText()
                            .toString().equals(userList.get(i).getPassword())) {
                        login = true;
                        //noinspection UnqualifiedFieldAccess
                        currentUser = userList.get(i);
                    }
                }
                if (login) {

                    // Passed information amoung activities

                    Intent intent = new Intent(context, HomeActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("UserList", userList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList",
                            reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putExtra("CurrentUser", currentUser);
                    //noinspection UnqualifiedFieldAccess
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
    public void addListenerOnButtonCancel() {

        final Context context = this;

        this.cancel = (Button) findViewById(R.id.login_Cancel);

        this.cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Passes information amount activities
                Intent intent = new Intent(context, WelcomePageActivity.class);
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
     * checks that user email is valid
     * @param email String email addresss
     * @return boolean
     */
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
     * checks that user password is valid
     * @param password String password
     * @return boolean
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
