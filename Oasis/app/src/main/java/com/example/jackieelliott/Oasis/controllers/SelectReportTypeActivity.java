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

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.ReportType;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.WaterCondition;
import com.example.jackieelliott.Oasis.Model.WaterType;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 3/13/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class SelectReportTypeActivity extends Activity {

    Button backButton;
    Button nextButton;
    Spinner chooseReportTypeSpinner;
    ArrayList<User> userList;
    ArrayList<Report> reportList;
    ArrayList<QualityReport> qualityList;
    User currentUser;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_report_type_page);

        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");
        qualityList = b.getParcelableArrayList("QualityList");

        chooseReportTypeSpinner = (Spinner) findViewById(R.id.select_report_type);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ReportType.values());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseReportTypeSpinner.setAdapter(adapter1);

        addListenerOnButtonBack();
        addListenerOnButtonNext();
    }

    /**
     * Adds functionality to the Back button.
     */
    public void addListenerOnButtonBack() {

        final Context context = this;


        backButton = (Button) findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HomeActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);

            }

        });

    }

    /**
     * adds functionality to the next button
     */
    public void addListenerOnButtonNext() {

        final Context context = this;

        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (chooseReportTypeSpinner.getSelectedItem().toString() == "Quality") {
                    Intent intent = new Intent(context, QualityReportActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    intent.putParcelableArrayListExtra("UserList", userList);
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                }
            }

        });
    }
}