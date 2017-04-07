package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.ReportType;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("CyclicClassDependency")
/**
 * Select Report Type Activity
 */
public class SelectReportTypeActivity extends Activity {

    private Spinner chooseReportTypeSpinner;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState saved instance state
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_report_type_page);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");

        this.chooseReportTypeSpinner = (Spinner) findViewById(R.id.select_report_type);

        ArrayAdapter<String> adapter1 =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        ReportType.values());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.chooseReportTypeSpinner.setAdapter(adapter1);

        addListenerOnButtonBack();
        addListenerOnButtonNext();
    }

    /**
     * Adds functionality to the Back button.
     */
    private void addListenerOnButtonBack() {

        final Context context = this;


        Button backButton = (Button) findViewById(R.id.back_button);

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
     * adds functionality to the next button
     */
    private void addListenerOnButtonNext() {

        final Context context = this;

        Button nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Object type = chooseReportTypeSpinner.getSelectedItem();
                String typeS = type.toString();
                //noinspection UnqualifiedFieldAccess
                if ("Quality".equals(typeS)) {
                    Intent intent = new Intent(context, QualityReportActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ReportActivity.class);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("ReportList", reportList);
                    //noinspection UnqualifiedFieldAccess
                    intent.putParcelableArrayListExtra("QualityList", qualityList);
                    startActivity(intent);
                }
            }

        });
    }
}
