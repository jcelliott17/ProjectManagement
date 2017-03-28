package com.example.jackieelliott.Oasis.controllers;

/**
 * Created by JackieElliott on 3/27/17.
 */
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class GraphDisplayActivity extends Activity {

    private Button back;
    private PointsGraphSeries<DataPoint> series;
    private GraphView scatterPlot;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        Bundle b = getIntent().getExtras();
        back = (Button) findViewById(R.id.back_button);

        //To be removed
        userList = b.getParcelableArrayList("UserList");
        currentUser = b.getParcelable("CurrentUser");
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");

        double y,x;
        x = -5.0;
        //Creates the graph view
        scatterPlot = (GraphView) findViewById(R.id.graph);
        //Sets labels on axises
        GridLabelRenderer gridLabel = scatterPlot.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setVerticalAxisTitle("PPM");
        series = new PointsGraphSeries<DataPoint>();
        for (int i = 0; i < 10; i++) {
            x = x + 10;
            y = x;
            series.appendData(new DataPoint(x, y), true, 10);
        }
        scatterPlot.addSeries(series);
        addListenerOnButtonBack();
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

    /**
     *
     * @param year
     * @param dataType
     */
    //datatype is virus or contaminant
    private void getData(int year, String dataType) {
        List<QualityReport> reportsByYear = QualityReport.getReportsByYear(year, qualityList);
        if (reportsByYear.size() != 0) {
            for (QualityReport report : qualityList) {
                series.appendData(new DataPoint(report.getTimeAndDate(), report.getContaminant()), true, 12);
            }

        }
        scatterPlot.addSeries(series);
    }
}
