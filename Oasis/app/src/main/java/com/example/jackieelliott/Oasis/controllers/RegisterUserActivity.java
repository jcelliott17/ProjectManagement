package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;

import com.example.jackieelliott.Oasis.Model.AccountTypes;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.R;
import com.example.jackieelliott.Oasis.Model.Profile;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class RegisterUserActivity extends Activity{

    Button registerBotton;
    Button cancelButton;
    private Spinner accountTypeSpinner;
    EditText userNameField;
    EditText passField;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;
    ArrayList<Report> reportList;
    //currentUser created to keep track of who is using the application
    //this will be removed once the database starts working
    User currentUser;

    @Override
    /**
     * Creates the report activity page.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        addListenerOnButtonRegister();
        addListenerOnButtonCancel();

        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountTypes.AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");
    }

    /**
     * Adds functionality to the register button.
     */
    public void addListenerOnButtonRegister() {

        final Context context = this;

        registerBotton = (Button) findViewById(R.id.registerOnRegisterPage);
        userNameField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText3);


        registerBotton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Here we did not account for if that user already exists
                // A warning should pop up here and let them know if that username already
                // exists?

                /*
                Determines what type of user to create based on selection. Then sets the current user to the user
                created.
                 */
                if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
                    User user = new User(userNameField.getText().toString(), passField.getText().toString());
                    userList.add(user);
                    currentUser = user;
                    currentUser.setAccountType("User");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
                    Worker worker = new Worker(userNameField.getText().toString(), passField.getText().toString());
                    workerList.add(worker);
                    currentUser = worker;
                    currentUser.setAccountType("Worker");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
                    Manager manager = new Manager(userNameField.getText().toString(), passField.getText().toString());
                    managerList.add(manager);
                    currentUser = manager;
                    currentUser.setAccountType("Manager");
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
                    Admin admin = new Admin(userNameField.getText().toString(), passField.getText().toString());
                    adminList.add(admin);
                    currentUser = admin;
                    currentUser.setAccountType("Admin");
                }
                // Passes information amoung models
                Intent intent = new Intent(context, HomeActivity.class);
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

    /**
     * Added functionality to the cancel button on the register page.
     */
    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

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
    }


}
