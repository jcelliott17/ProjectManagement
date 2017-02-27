package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class ReportActivity extends Activity {

    Button backButton;
    TextView reportText;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;

    @Override
    /**
     * Creates the Report Acticity with the necessary information.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_page);
        addListenerOnButtonLogout();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
    }

    /**
     * Adds functionality to the logout button.
     */
    public void addListenerOnButtonLogout() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.backButton);
        reportText = (TextView) findViewById(R.id.reportText);

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
