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

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

/**
 * Created by JackieElliott on 3/27/17.
 */

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
        year = (EditText) findViewById(R.id.year_editText);
        latitude = (EditText) findViewById(R.id.lat_editText);
        longitude = (EditText) findViewById(R.id.long_editText);
        createGraph = (Button) findViewById(R.id.create_graph_button);
        back = (Button) findViewById(R.id.back_button);
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
                Intent intent = new Intent(context, GraphDisplayActivity.class);
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
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
