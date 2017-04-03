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
import java.util.Date;

import com.example.jackieelliott.Oasis.Model.PurityType;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.Model.WaterCondition;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 3/13/17.
 */

public class QualityReportActivity extends Activity {
    Button backButton;
    Button createReportButton;
    Button back_button;
    Button next_button;
    TextView reportText;
    TextView virusView;
    EditText virusEdit;
    TextView contaminantView;
    EditText contaminantEdit;
    EditText reportTitle;
    EditText reportLatitude;
    EditText reportLongitude;
    Spinner conditionWaterSpinner;
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

        setContentView(R.layout.quality_report_page);

        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");


        this.reportTitle = (EditText) findViewById(R.id.report_title_textedit);
        this.reportLatitude = (EditText) findViewById(R.id.latitude_text);
        this.reportLongitude = (EditText) findViewById(R.id.longitude_text);
        this.virusEdit = (EditText) findViewById(R.id.virusPPMText);
        this.contaminantEdit = (EditText) findViewById(R.id.contaminantPPMText);


        this.conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PurityType.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.conditionWaterSpinner.setAdapter(adapter2);
        addListenerOnButtonBack();
        addListenerOnButtonCreateReport();
    }

    /**
     * adds functionality to back button
     */
    public void addListenerOnButtonBack() {

        final Context context = this;


        this.backButton = (Button) findViewById(R.id.backButton);
        this.reportText = (TextView) findViewById(R.id.report_textview);
        this.reportTitle = (EditText) findViewById(R.id.report_title_textedit);
        this.conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);


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
     * adds functionality to create report button
     */
    public void addListenerOnButtonCreateReport() {

        final Context context = this;

        this.createReportButton = (Button) findViewById(R.id.create_report_button);

        this.createReportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                @SuppressWarnings("UnqualifiedFieldAccess") QualityReport newReport = new QualityReport(reportTitle.getText().toString());
                newReport.setLatitude((Double.parseDouble(reportLatitude.getText().toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setLongitude((Double.parseDouble(reportLongitude.getText().toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setCondition(conditionWaterSpinner.getSelectedItem().toString());
                //noinspection UnqualifiedFieldAccess
                newReport.setVirus(Double.parseDouble(virusEdit.getText().toString()));
                //noinspection UnqualifiedFieldAccess
                newReport.setContanminant(Double.parseDouble(contaminantEdit.getText().toString()));
                //noinspection UnqualifiedFieldAccess
                newReport.setReportNumber(reportList.size() + 1);
                newReport.setTimeAndDate(new Date());
                //noinspection UnqualifiedFieldAccess
                qualityList.add(newReport);

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
}
