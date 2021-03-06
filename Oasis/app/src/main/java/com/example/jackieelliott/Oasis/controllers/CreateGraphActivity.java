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

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.HistoryGraph;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;
/**
 * Create Graph Activity class
 * creates the page with the graph creating information
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("CyclicClassDependency")

public class CreateGraphActivity extends Activity{
    /**
     * Create Graph Activity class
     * creates the page with the graph creating information
     */

    private Button createGraph;
    private Button back;
    private EditText year;
    private EditText latitude;
    private EditText longitude;
    private Spinner dataType;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private HistoryGraph historyGraphList;

    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_setup_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        year = (EditText) findViewById(R.id.year_editText);
        latitude = (EditText) findViewById(R.id.lat_editText);
        longitude = (EditText) findViewById(R.id.long_editText);
        createGraph = (Button) findViewById(R.id.create_graph_button);
        back = (Button) findViewById(R.id.back_button);

        //Creates the functionality for the spinner
        dataType = (Spinner) findViewById(R.id.data_type_spinner);

        String[] spinnerItems = {"Contaminant","Virus"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataType.setAdapter(adapter);

        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");

        addListenerOnButtonCreateGraph();
        addListenerOnButtonBack();

    }

    /**
     * Adds functionality to the Create Graph button
     */
    private void addListenerOnButtonCreateGraph() {
        final Context context = this;

        createGraph = (Button) findViewById(R.id.create_graph_button);

        createGraph.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("ChainedMethodCall")
            @Override
            public void onClick(View arg0) {
                //noinspection ChainedMethodCall
                historyGraphList = (new HistoryGraph(Integer.parseInt(year.getText().toString()),
                        Double.parseDouble(latitude.getText().toString()),
                        Double.parseDouble(longitude
                        .getText().toString()), dataType.getSelectedItem().toString()));
                Intent intent = new Intent(context, GraphDisplayActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
                intent.putExtra("Graph", historyGraphList);
                startActivity(intent);
            }

        });
    }

    /**
     * Adds functionality to the back button
     */
    private void addListenerOnButtonBack() {
        final Context context = this;

        back.setOnClickListener(new View.OnClickListener() {

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

}
