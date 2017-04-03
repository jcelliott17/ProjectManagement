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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        this.back = (Button) findViewById(R.id.back_button);

        Bundle b = getIntent().getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.qualityList = b.getParcelableArrayList("QualityList");
        this.historyGraph = b.getParcelable("Graph");

        //double y,x;
        //x = -5.0;
        //Creates the graph view
        this.scatterPlot = (GraphView) findViewById(R.id.graph);
        //Sets labels on axises

        GridLabelRenderer gridLabel = this.scatterPlot.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setVerticalAxisTitle(this.historyGraph.getyAxis() + " PPM");


        this.series = new PointsGraphSeries<DataPoint>();

        getData(this.historyGraph.getYear(), this.historyGraph.getLatitude(),
                this.historyGraph.getLongitude(), this.historyGraph.getyAxis());
        //for (int i = 0; i < 10; i++) {
           // x = x + 10;
            //y = x;
            //series.appendData(new DataPoint(x, y), true, 10);
        //}
        //scatterPlot.addSeries(series);
        addListenerOnButtonBack();
    }

    public void addListenerOnButtonBack() {
        final Context context = this;

        this.back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, HomeActivity.class);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("UserList", userList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("ReportList", reportList);
                //noinspection UnqualifiedFieldAccess
                intent.putParcelableArrayListExtra("QualityList", qualityList);
                //noinspection UnqualifiedFieldAccess
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
    //datatype is virus or contaminant
    private void getData(int year, double latitude, double longitude, String dataType) {
        LinkedList<QualityReport>[] reportsByYear = sortReports(year, latitude, longitude, this.qualityList);
        int month = 1;
        int max = 0;
        for (LinkedList<QualityReport> reportsByMonth: reportsByYear) {
            if (reportsByMonth != null) {
                int average = 0;
                for (QualityReport report: reportsByMonth) {
                    if ("Virus".equals(dataType)) {
                        average += report.getVirus();
                    } else {
                        average += report.getContaminant();
                    }
                }
                average = average/ reportsByMonth.size();
                if (average > max) {
                    max = average;
                }
                this.series.appendData(new DataPoint(month + 1, average), true, 12);
            }
            month++;
        }
        Log.d("myTag", Integer.toString(max));
        this.scatterPlot.getViewport().setScrollable(true);
        this.scatterPlot.getViewport().setMinX(1);
        this.scatterPlot.getViewport().setMaxX(13);

        this.scatterPlot.getViewport().setScrollableY(true);
        this.scatterPlot.getViewport().setMinY(0);
        this.scatterPlot.getViewport().setMaxY(max + (.2 * max));

        this.scatterPlot.getViewport().setYAxisBoundsManual(true);
        this.scatterPlot.getViewport().setXAxisBoundsManual(true);

        this.scatterPlot.addSeries(this.series);
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
        this.monthlyQualityList = (LinkedList<QualityReport>[]) new LinkedList[12];
        if (qualityList == null) {
            return this.monthlyQualityList;
        }
        Log.d("myTag", Double.toString(latitude));
        Log.d("myTag", Double.toString(longitude));
        for (QualityReport report: qualityList) {
            Log.d("myTag", Integer.toString(report.getTimeAndDate().getMonth()));
            Log.d("myTag", Integer.toString(report.getTimeAndDate().getYear()));
            Log.d("myTag", Double.toString(report.getLatitude()));
            Log.d("myTag", Double.toString(report.getLongitude()));
            if (report.getTimeAndDate().getYear() == (year - 2000 + 100) && report.getLatitude() == latitude && report.getLongitude() == longitude) {
                if (this.monthlyQualityList[report.getTimeAndDate().getMonth()] == null) {
                    this.monthlyQualityList[report.getTimeAndDate().getMonth()] = new LinkedList<QualityReport>();
                }
                this.monthlyQualityList[report.getTimeAndDate().getMonth()].add(report);
                Log.d("myTag", Integer.toString(report.getTimeAndDate().getMonth()));
            }
        }
        return this.monthlyQualityList;
    }
}
