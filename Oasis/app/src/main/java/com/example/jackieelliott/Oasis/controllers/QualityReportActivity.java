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
import java.util.Date;

import com.example.jackieelliott.Oasis.Model.PurityType;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("CyclicClassDependency")
public class QualityReportActivity extends Activity {

    private EditText virusEdit;
    private EditText contaminantEdit;
    private EditText reportTitle;
    private EditText reportLatitude;
    private EditText reportLongitude;
    private Spinner conditionWaterSpinner;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState saved instance tag
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quality_report_page);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        this.reportTitle = (EditText) findViewById(R.id.report_title_textEdit);
        this.reportLatitude = (EditText) findViewById(R.id.latitude_text);
        this.reportLongitude = (EditText) findViewById(R.id.longitude_text);
        this.virusEdit = (EditText) findViewById(R.id.virusPPMText);
        this.contaminantEdit = (EditText) findViewById(R.id.contaminantPPMText);


        this.conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);

        ArrayAdapter<String> adapter2 =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        PurityType.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.conditionWaterSpinner.setAdapter(adapter2);
        addListenerOnButtonBack();
        addListenerOnButtonCreateReport();
    }

    /**
     * adds functionality to back button
     */
    private void addListenerOnButtonBack() {

        final Context context = this;

        Button backButton = (Button) findViewById(R.id.backButton);
        this.reportTitle = (EditText) findViewById(R.id.report_title_textEdit);
        this.conditionWaterSpinner = (Spinner) findViewById(R.id.water_condition_spinner);


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
                Editable virE = virusEdit.getText();
                Editable contE = contaminantEdit.getText();
                @SuppressWarnings("UnqualifiedFieldAccess") QualityReport newReport =
                        new QualityReport(repT.toString());
                newReport.setLatitude((Double.parseDouble(repLa.toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setLongitude((Double.parseDouble(repLo.toString())));
                //noinspection UnqualifiedFieldAccess
                newReport.setCondition(condition.toString());
                //noinspection UnqualifiedFieldAccess
                newReport.setVirus(Double.parseDouble(virE.toString()));
                //noinspection UnqualifiedFieldAccess
                newReport.setContaminant(Double.parseDouble(contE.toString()));
                //noinspection UnqualifiedFieldAccess
                newReport.setReportNumber(reportList.size() + 1);
                newReport.setTimeAndDate(new Date());
                //noinspection UnqualifiedFieldAccess
                qualityList.add(newReport);

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
