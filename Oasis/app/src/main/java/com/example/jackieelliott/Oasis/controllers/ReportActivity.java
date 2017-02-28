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
import android.widget.TextView;

import com.example.jackieelliott.Oasis.Model.AccountTypes;
import com.example.jackieelliott.Oasis.Model.Admin;
import com.example.jackieelliott.Oasis.Model.Manager;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.WaterCondition;
import com.example.jackieelliott.Oasis.Model.WaterType;
import com.example.jackieelliott.Oasis.Model.Worker;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class ReportActivity extends Activity {

    Button backButton;
    Button createReportButton;
    TextView reportText;
    EditText reportTitle;
    EditText reportLocation;
    Spinner typeWaterSpinner;
    Spinner conditionWaterSpinner;
    ArrayList<User> userList;
    ArrayList<Worker> workerList;
    ArrayList<Manager> managerList;
    ArrayList<Admin> adminList;
    ArrayList<Report> reportList;
    User currentUser;

    @Override
    /**
     * Creates the Report Acticity with the necessary information.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_page);
        addListenerOnButtonBack();
        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        workerList = b.getParcelableArrayList("WorkerList");
        managerList = b.getParcelableArrayList("ManagerList");
        adminList = b.getParcelableArrayList("AdminList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");

        reportTitle = (EditText) findViewById(R.id.report_title_textedit);
        reportLocation = (EditText) findViewById(R.id.location_textedit);

        typeWaterSpinner = (Spinner) findViewById(R.id.type_water_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, WaterType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeWaterSpinner.setAdapter(adapter);

        conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, WaterCondition.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionWaterSpinner.setAdapter(adapter2);

        addListenerOnButtonCreateReport();
    }

    /**
     * Adds functionality to the Back button.
     */
    public void addListenerOnButtonBack() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.backButton);
        /*
        reportText = (TextView) findViewById(R.id.reportText);
        reportTitle = (EditText) findViewById(R.id.report_title_textedit);
        reportLocation = (EditText) findViewById(R.id.location_textedit);
        typeWaterSpinner = (Spinner) findViewById(R.id.type_water_spinner);
        conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);*/


        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

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

    public void addListenerOnButtonCreateReport() {

        final Context context = this;

        createReportButton = (Button) findViewById(R.id.create_report_button);

        /*backButton = (Button) findViewById(R.id.backButton);
        reportText = (TextView) findViewById(R.id.reportText);
        reportTitle = (EditText) findViewById(R.id.report_title_textedit);
        reportLocation = (EditText) findViewById(R.id.location_textedit);
        typeWaterSpinner = (Spinner) findViewById(R.id.type_water_spinner);
        conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);*/

        Report newReport = new Report(reportTitle.getText().toString(), reportLocation.getText().toString());
        newReport.setCondition(conditionWaterSpinner.getSelectedItem().toString());
        newReport.setTypeOfWater(typeWaterSpinner.getSelectedItem().toString());
        reportList.add(newReport);

        createReportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

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





}