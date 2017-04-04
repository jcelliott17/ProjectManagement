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
<<<<<<< HEAD
import com.example.jackieelliott.Oasis.controllers.GoogleMapsActivity;
=======

>>>>>>> master

import java.util.ArrayList;

/**
 * Created by Alon on 3/15/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class QualityListActivity extends Activity {
    private Button backButton;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    /**
     * On the creation of the Home activity this
     * information is uploaded and updated.
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quality_list);
        Bundle b = getIntent().getExtras();
<<<<<<< HEAD
        /*
        userList = b.getParcelableArrayList("UserList");
        _user = b.getParcelable("CurrentUser");
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
=======
        this.userList = b.getParcelableArrayList("UserList");
        this._user = b.getParcelable("CurrentUser");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");
        this.backButton = (Button) findViewById(R.id.back_button);
        ListView qualityReportList = (ListView) findViewById(R.id.quality_list);
        addListenerOnButtonBack();


        String[] qualityReports = new String[this.qualityList.size()];
        for (int i = 0; i < this.qualityList.size(); i++) {
            qualityReports[i] = this.qualityList.get(i).toString();
>>>>>>> master
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.
                R.layout.simple_list_item_1, qualityReports);
        qualityReportList.setAdapter(adapter);
        */
    }

    /**
     * Adds functionality to the logout button
     */
    private void addListenerOnButtonBack() {

        final Context context = this;

        this.backButton = (Button) findViewById(R.id.back_button);

        this.backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, HomeActivity.class);
<<<<<<< HEAD
                /*
=======
                //noinspection UnqualifiedFieldAccess
>>>>>>> master
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
                intent.putExtra("CurrentUser", _user);
                */
                startActivity(intent);
            }

        });
    }
}
