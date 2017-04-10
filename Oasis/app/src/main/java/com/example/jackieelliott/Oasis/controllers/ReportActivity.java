package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.WaterCondition;
import com.example.jackieelliott.Oasis.Model.WaterType;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Report Activity class
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("CyclicClassDependency")

public class ReportActivity extends Activity {

    private EditText reportTitle;
    private EditText reportLatitude;
    private EditText reportLongitude;
    private Spinner typeWaterSpinner;
    private Spinner conditionWaterSpinner;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * Creates the Report Activity with the necessary information.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.report_page);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        this.reportTitle = (EditText) findViewById(R.id.report_title_textEdit);
        this.reportLatitude = (EditText) findViewById(R.id.latitude_text);
        this.reportLongitude = (EditText) findViewById(R.id.longitude_text);

        this.typeWaterSpinner = (Spinner) findViewById(R.id.type_water_spinner);

        @SuppressWarnings("unchecked") ArrayAdapter<String> adapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        WaterType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.typeWaterSpinner.setAdapter(adapter);

        this.conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);

        @SuppressWarnings("unchecked") ArrayAdapter<String> adapter2 =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        WaterCondition.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.conditionWaterSpinner.setAdapter(adapter2);
        addListenerOnButtonBack();
        addListenerOnButtonCreateReport();
    }

    /**
     * Adds functionality to the Back button.
     */
    private void addListenerOnButtonBack() {

        final Context context = this;

        Button backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(context, HomeActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);

                }

        });

    }

    /**
     * adds functionality to create report button
     */
    @SuppressWarnings("FeatureEnvy")
    private void addListenerOnButtonCreateReport() {

        final Context context = this;

        Button createReportButton = (Button) findViewById(R.id.create_report_button);

        //noinspection FeatureEnvy
        createReportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Editable repT = reportTitle.getText();
                Editable repLa = reportLatitude.getText();
                Editable repLo = reportLongitude.getText();
                Object condition = conditionWaterSpinner.getSelectedItem();
                Object type = typeWaterSpinner.getSelectedItem();

                @SuppressWarnings("UnqualifiedFieldAccess") Report newReport =
                        new Report(repT.toString());
                //noinspection UnqualifiedFieldAccess
                newReport.setLatitude((Double.parseDouble(repLa.toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setLongitude((Double.parseDouble(repLo.toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setCondition(condition.toString());
                //noinspection UnqualifiedFieldAccess
                newReport.setTypeOfWater(type.toString());
                //noinspection UnqualifiedFieldAccess
                newReport.setReportNumber(reportList.size() + 1);
                //noinspection UnqualifiedFieldAccess
                reportList.add(newReport);

                Intent intent = new Intent(context, HomeActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                startActivity(intent);
            }

        });
    }
}
