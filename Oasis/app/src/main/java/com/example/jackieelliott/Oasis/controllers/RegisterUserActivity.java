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
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class RegisterUserActivity extends Activity{

    private Spinner accountTypeSpinner;
    private EditText userNameField;
    private EditText passField;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    //currentUser created to keep track of who is using the application
    //this will be removed once the database starts working
    private User currentUser;

    /**
     * Creates the report activity page.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        addListenerOnButtonRegister();
        addListenerOnButtonCancel();

        this.accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountTypes.AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.accountTypeSpinner.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");
    }

    /**
     * Adds functionality to the register button.
     */
    private void addListenerOnButtonRegister() {

        final Context context = this;

        Button registerButton = (Button) findViewById(R.id.registerOnRegisterPage);
        this.userNameField = (EditText) findViewById(R.id.username_text);
        this.passField = (EditText) findViewById(R.id.editText3);


        registerButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // Here we did not account for if that user already exists
                // A warning should pop up here and let them know if that username already
                // exists?

                /*
                Determines what type of user to create based on selection. Then sets the current user to the user
                created.
                 */
                //noinspection UnqualifiedFieldAccess
                if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.User) {
                    @SuppressWarnings("UnqualifiedFieldAccess") User user = new User(userNameField.getText().toString(), passField.getText().toString(), 1);
                    //noinspection UnqualifiedFieldAccess
                    userList.add(user);
                    //noinspection UnqualifiedFieldAccess
                    currentUser = user;
                    //noinspection UnqualifiedFieldAccess
                    currentUser.setAccountType("User");
                } else //noinspection UnqualifiedFieldAccess
                    if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Worker) {
                    @SuppressWarnings("UnqualifiedFieldAccess") User user = new User(userNameField.getText().toString(), passField.getText().toString(), 2);
                        //noinspection UnqualifiedFieldAccess
                        userList.add(user);
                        //noinspection UnqualifiedFieldAccess
                        currentUser = user;
                        //noinspection UnqualifiedFieldAccess
                        currentUser.setAccountType("Worker");
                } else //noinspection UnqualifiedFieldAccess
                        if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Manager) {
                    @SuppressWarnings("UnqualifiedFieldAccess") User user = new User(userNameField.getText().toString(), passField.getText().toString(), 3);
                            //noinspection UnqualifiedFieldAccess
                            userList.add(user);
                            //noinspection UnqualifiedFieldAccess
                            currentUser = user;
                            //noinspection UnqualifiedFieldAccess
                            currentUser.setAccountType("Manager");
                } else //noinspection UnqualifiedFieldAccess
                            if (accountTypeSpinner.getSelectedItem() == AccountTypes.AccountType.Admin) {
                    @SuppressWarnings("UnqualifiedFieldAccess") User user = new User(userNameField.getText().toString(), passField.getText().toString(), 3);
                                //noinspection UnqualifiedFieldAccess
                                userList.add(user);
                                //noinspection UnqualifiedFieldAccess
                                currentUser = user;
                                //noinspection UnqualifiedFieldAccess
                                currentUser.setAccountType("Admin");
                }
                // Passes information among models
                Intent intent = new Intent(context, HomeActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });

    }

    /**
     * Added functionality to the cancel button on the register page.
     */
    private void addListenerOnButtonCancel() {

        final Context context = this;

        Button cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });
    }


}
