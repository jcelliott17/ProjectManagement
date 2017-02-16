package com.example.jackieelliott.team60application.controllers;

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

import com.example.jackieelliott.team60application.Model.AccountTypes;
import com.example.jackieelliott.team60application.Model.User;
import com.example.jackieelliott.team60application.Model.Worker;
import com.example.jackieelliott.team60application.Model.Manager;
import com.example.jackieelliott.team60application.Model.Admin;
import com.example.jackieelliott.team60application.R;

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

    @Override
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

    }

    public void addListenerOnButtonRegister() {

        final Context context = this;

        registerBotton = (Button) findViewById(R.id.registerOnRegisterPage);
        userNameField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText3);


        registerBotton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
                    User user = new User(userNameField.getText().toString(), passField.getText().toString());
                    userList.add(user);
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
                    Worker worker = new Worker(userNameField.getText().toString(), passField.getText().toString());
                    workerList.add(worker);
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
                    Manager manager = new Manager(userNameField.getText().toString(), passField.getText().toString());
                    managerList.add(manager);
                } else if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
                    Admin admin = new Admin(userNameField.getText().toString(), passField.getText().toString());
                    adminList.add(admin);
                }
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("WorkerList", workerList);
                intent.putParcelableArrayListExtra("ManagerList", managerList);
                intent.putParcelableArrayListExtra("AdminList", adminList);
                startActivity(intent);
            }

        });

    }

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
                startActivity(intent);

            }

        });
    }


}
