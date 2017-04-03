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

import com.example.jackieelliott.Oasis.Model.HistoryGraph;
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
import java.util.LinkedList;
import java.util.List;

//Overriding the toString() method
//we do not want to override the toString method in this class

public class GraphDisplayActivity extends Activity {

    private Button back;
    private PointsGraphSeries<DataPoint> series;
    private GraphView scatterPlot;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private HistoryGraph historyGraph;
    private User currentUser;
    private LinkedList<QualityReport>[] monthlyQualityList;

    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        back = (Button) findViewById(R.id.back_button);

        Bundle b = getIntent().getExtras();
        userList = b.getParcelableArrayList("UserList");
        reportList = b.getParcelableArrayList("ReportList");
        currentUser = b.getParcelable("CurrentUser");
        qualityList = b.getParcelableArrayList("QualityList");
        historyGraph = b.getParcelable("Graph");

        //Creates the graph view

        scatterPlot = (GraphView) findViewById(R.id.graph);
        //Sets labels on axises

        GridLabelRenderer gridLabel = scatterPlot.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setVerticalAxisTitle(historyGraph.getYAxis() + " PPM");



        series = new PointsGraphSeries<DataPoint>();

        getData(historyGraph.getYear(), historyGraph.getLatitude(),
                historyGraph.getLongitude(), historyGraph.getYAxis());

        addListenerOnButtonBack();
    }

    public final void addListenerOnButtonBack() {
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
     * Gets the data values for a given year and plots the points on the graph
     *
     * @param year specified year for reports
     * @param dataType virus or contaminant
     */
    //dataType is virus or contaminant
    private void getData(int year, double latitude, double longitude, String dataType) {
        LinkedList<QualityReport>[] reportsByYear = sortReports(year, latitude, longitude, qualityList);
        int month = 1;
        int max = 0;
        for (LinkedList<QualityReport> reportsByMonth: reportsByYear) {
            if (reportsByMonth != null) {
                int average = 0;
                for (QualityReport report: reportsByMonth) {
                    if (dataType.equals("Virus")) {
                        average += report.getVirus();
                    } else {
                        average += report.getContaminant();
                    }
                }
                average = average/ reportsByMonth.size();
                if (average > max) {
                    max = average;
                }
                series.appendData(new DataPoint(month + 1, average), true, 12);
            }
            month++;
        }
        Log.d("myTag", Integer.toString(max));
        scatterPlot.getViewport().setScrollable(true);
        scatterPlot.getViewport().setMinX(1);
        scatterPlot.getViewport().setMaxX(13);

        scatterPlot.getViewport().setScrollableY(true);
        scatterPlot.getViewport().setMinY(0);
        scatterPlot.getViewport().setMaxY(max + (.2 * max));

        scatterPlot.getViewport().setYAxisBoundsManual(true);
        scatterPlot.getViewport().setXAxisBoundsManual(true);

        scatterPlot.addSeries(series);
    }

    //Returns a list of quality reports in a given year
    //Use deprecated Date code because android wouldn't support localDateTime
    /**
     * Gets the reports from a specified year and sorts them by month
     *
     *
     * @param year specified year for reports
     * @param qualityList list of reports
     * @return An array of linked lists
     */


    private LinkedList<QualityReport>[] sortReports(int year, double latitude, double longitude,
                                                         ArrayList<QualityReport> qualityList) {
        monthlyQualityList = (LinkedList<QualityReport>[]) new LinkedList[12];
        if (qualityList == null) {
            return monthlyQualityList;
        }
        Log.d("myTag", Double.toString(latitude));
        Log.d("myTag", Double.toString(longitude));
        for (QualityReport report: qualityList) {
            Log.d("myTag", Integer.toString(report.getTimeAndDate().getMonth()));
            Log.d("myTag", Integer.toString(report.getTimeAndDate().getYear()));
            Log.d("myTag", Double.toString(report.getLatitude()));
            Log.d("myTag", Double.toString(report.getLongitude()));
            if (report.getTimeAndDate().getYear() == (year - 2000 + 100) && report.getLatitude() == latitude && report.getLongitude() == longitude) {
                if (monthlyQualityList[report.getTimeAndDate().getMonth()] == null) {
                    monthlyQualityList[report.getTimeAndDate().getMonth()] = new LinkedList<QualityReport>();
                }
                monthlyQualityList[report.getTimeAndDate().getMonth()].add(report);
                Log.d("myTag", Integer.toString(report.getTimeAndDate().getMonth()));
            }
        }
        return monthlyQualityList;
    }
}
