package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jackieelliott.Oasis.Model.AccountTypes;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.HistoryGraph;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 3/27/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class CreateGraphActivity extends Activity{

    private EditText year;
    private EditText latitude;
    private EditText longitude;
    private Spinner dataType;
    private Button createGraph;
    private Button back;

    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private HistoryGraph historyGraphList;
    private User currentUser;

    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_setup_page);
        Bundle b = getIntent().getExtras();

        userList = b.getParcelableArrayList("UserList");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");
        currentUser = b.getParcelable("CurrentUser");

        year = (EditText) findViewById(R.id.year_editText);
        latitude = (EditText) findViewById(R.id.lat_editText);
        longitude = (EditText) findViewById(R.id.long_editText);
        createGraph = (Button) findViewById(R.id.create_graph_button);
        back = (Button) findViewById(R.id.back_button);

        //Creates the functionality for the spinner
        dataType = (Spinner) findViewById(R.id.data_type_spinner);

        String[] spinnerItems = {"Contaminant","Virus"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataType.setAdapter(adapter);

        userList = b.getParcelableArrayList("UserList");
        currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");


        addListenerOnButtonCreateGraph();
        addListenerOnButtonBack();

    }

    public void addListenerOnButtonCreateGraph() {
        final Context context = this;

        createGraph = (Button) findViewById(R.id.create_graph_button);

        createGraph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                    //HistoryGraph graph = new HistoryGraph(Integer.parseInt(year.getText().toString()),
                            //Integer.parseInt(latitude.getText().toString()), Integer.parseInt(longitude
                            //.getText().toString()), dataType.getSelectedItem().toString());
                Log.d("myTag", dataType.getSelectedItem().toString());
                    historyGraphList = (new HistoryGraph(Integer.parseInt(year.getText().toString()),
                            Integer.parseInt(latitude.getText().toString()), Integer.parseInt(longitude
                            .getText().toString()), dataType.getSelectedItem().toString()));

                Log.d("myTags", year.getText().toString());
                Intent intent = new Intent(context, GraphDisplayActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("Graph", historyGraphList);

                intent.putExtra("CurrentUser", currentUser);
                startActivity(intent);
            }

        });
    }

    public void addListenerOnButtonBack() {
        final Context context = this;

        back.setOnClickListener(new View.OnClickListener() {

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

}
