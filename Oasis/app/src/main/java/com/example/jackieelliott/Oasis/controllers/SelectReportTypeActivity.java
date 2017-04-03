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
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");

        this.chooseReportTypeSpinner = (Spinner) findViewById(R.id.select_report_type);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ReportType.values());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.chooseReportTypeSpinner.setAdapter(adapter1);

        addListenerOnButtonBack();
        addListenerOnButtonNext();
    }

    /**
     * Adds functionality to the Back button.
     */
    public void addListenerOnButtonBack() {

        final Context context = this;


        this.backButton = (Button) findViewById(R.id.back_button);

        this.backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

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
     * adds functionality to the next button
     */
    public void addListenerOnButtonNext() {

        final Context context = this;

        this.nextButton = (Button) findViewById(R.id.next_button);

        this.nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //noinspection UnqualifiedFieldAccess
                if (chooseReportTypeSpinner.getSelectedItem().toString() == "Quality") {
                    Intent intent = new Intent(context, QualityReportActivity.class);
                    //noinspection UnqualifiedFieldAccess,UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("UserList", userList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putExtra("CurrentUser", currentUser);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
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
            }

        });
    }
}
