package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.R;
import com.example.jackieelliott.Oasis.controllers.GoogleMapsActivity;

import java.util.ArrayList;

/**
 * Created by Alon on 3/15/17.
 */

public class QualityListActivity extends Activity {
    private Button backButton;
    private ListView qualityReportList;
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
        setContentView(R.layout.quality_list);
        Bundle b = getIntent().getExtras();
        /*
        userList = b.getParcelableArrayList("UserList");
        currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");
        */
        backButton = (Button) findViewById(R.id.back_button);
        qualityReportList = (ListView) findViewById(R.id.quality_list);
        addListenerOnButtonBack();

        /*
        String[] qualityReports = new String[qualityList.size()];
        for (int i = 0; i < qualityList.size(); i++) {
            qualityReports[i] = qualityList.get(i).toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, qualityReports);
        qualityReportList.setAdapter(adapter);
        */
    }

    /**
     * Adds functionality to the logout button
     */
    public void addListenerOnButtonBack() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, HomeActivity.class);
                /*
                intent.putParcelableArrayListExtra("UserList", userList);
                intent.putParcelableArrayListExtra("ReportList", reportList);
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                intent.putExtra("CurrentUser", currentUser);
                */
                startActivity(intent);
            }

        });
    }
}
