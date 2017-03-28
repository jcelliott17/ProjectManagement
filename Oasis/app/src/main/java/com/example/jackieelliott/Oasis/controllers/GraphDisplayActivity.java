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
    private ArrayList<HistoryGraph> historyGraphList;
    private User currentUser;
    private LinkedList<QualityReport>[] monthlyQualityList;

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
        historyGraphList = b.getParcelableArrayList("GraphList");

        //double y,x;
        //x = -5.0;
        //Creates the graph view
        scatterPlot = (GraphView) findViewById(R.id.graph);
        //Sets labels on axises
        GridLabelRenderer gridLabel = scatterPlot.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setVerticalAxisTitle(historyGraphList.get(0).getyAxis() + "PPM");


        series = new PointsGraphSeries<DataPoint>();

        getData(historyGraphList.get(0).getYear(), historyGraphList.get(0).getLatitude(),
                historyGraphList.get(0).getLongitude(), historyGraphList.get(0).getyAxis());
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

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                historyGraphList.clear();
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
    //datatype is virus or contaminant
    private void getData(int year, int latitude, int longitude, String dataType) {
        LinkedList<QualityReport>[] reportsByYear = sortReports(year, latitude, longitude, qualityList);
        int month = 1;
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
                series.appendData(new DataPoint(month, average), true, 12);
            }
            month++;
        }
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


    private LinkedList<QualityReport>[] sortReports(int year, int latitude, int longitude,
                                                         ArrayList<QualityReport> qualityList) {
        monthlyQualityList = (LinkedList<QualityReport>[]) new LinkedList[12];
        if (qualityList == null) {
            return monthlyQualityList;
        }

        for (QualityReport report: qualityList) {
            if (report.getTimeAndDate().getYear() == year && report.getLatitude() == latitude && report.getLongitude() == longitude) {
                if (monthlyQualityList[report.getTimeAndDate().getMonth()] == null) {
                    monthlyQualityList[report.getTimeAndDate().getMonth()] = new LinkedList<QualityReport>();
                }
                monthlyQualityList[report.getTimeAndDate().getMonth()].add(report);
            }
        }

        return monthlyQualityList;
    }
}
