package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import com.example.jackieelliott.team60application.Model.AccountTypes;
import com.example.jackieelliott.team60application.Model.Admin;
import com.example.jackieelliott.team60application.Model.Manager;
import com.example.jackieelliott.team60application.Model.User;
import com.example.jackieelliott.team60application.Model.Worker;
import com.example.jackieelliott.team60application.R;

import java.util.List;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class LoginActivity extends Activity {

    Button login;
    Button cancel;
    EditText loginField;
    EditText passField;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");

    }

    public void addListenerOnButtonLogin() {

        final Context context = this;

        login = (Button) findViewById(R.id.login_button);
        loginField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText2);
        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Wrong Username/Password");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                boolean login = false;
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUsername().equals(loginField.getText().toString())
                            && passField.getText().toString().equals(userList.get(i).getPassword())) {
                        login = true;
                    }
                }
                if (!login) {
                    for (int i = 0; i < workerList.size(); i++) {
                        if (workerList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(workerList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }
                if (!login) {
                    for (int i = 0; i < managerList.size(); i++) {
                        if (managerList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(managerList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }
                if (!login) {
                    for (int i = 0; i < adminList.size(); i++) {
                        if (adminList.get(i).getUsername().equals(loginField.getText().toString())
                                && passField.getText().toString().equals(adminList.get(i).getPassword())) {
                            login = true;
                        }
                    }
                }
                if (login) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("WorkerList", workerList);
                    intent.putParcelableArrayListExtra("ManagerList", managerList);
                    intent.putParcelableArrayListExtra("AdminList", adminList);
                    startActivity(intent);
                } else {
                    alertDialog.show();
                }

            }

        });

    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancel = (Button) findViewById(R.id.login_Cancel);

        cancel.setOnClickListener(new View.OnClickListener() {

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

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
