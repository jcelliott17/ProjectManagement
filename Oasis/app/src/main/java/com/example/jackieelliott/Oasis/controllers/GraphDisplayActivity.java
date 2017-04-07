package com.example.jackieelliott.Oasis.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.Oasis.Model.HistoryGraph;
import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

//Overriding the toString() method
//we do not want to override the toString method in this class

/**
 * Graph display activity controller
 */
@SuppressWarnings("CyclicClassDependency")
public class GraphDisplayActivity extends Activity {

    private Button back;
    private PointsGraphSeries<DataPoint> series;
    private GraphView scatterPlot;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    @SuppressWarnings("FeatureEnvy")
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        back = (Button) findViewById(R.id.back_button);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");
        HistoryGraph historyGraph = b.getParcelable("Graph");

        //Creates the graph view
        scatterPlot = (GraphView) findViewById(R.id.graph);

        //Sets labels on axises

        GridLabelRenderer gridLabel = scatterPlot.getGridLabelRenderer();
        if ((historyGraph != null ? historyGraph.getYAxis() : null) != null) {
            gridLabel.setVerticalAxisTitle(historyGraph.getYAxis() + " PPM");
        }

        this.series = new PointsGraphSeries<>();

        getData(historyGraph != null ? historyGraph.getYear() : 0,
                historyGraph != null ? historyGraph.getLatitude() : 0,
                historyGraph != null ? historyGraph.getLongitude() : 0,
                historyGraph != null ? historyGraph.getYAxis() : null);

        addListenerOnButtonBack();
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

    /**
     * Gets the data values for a given year and plots the points on the graph
     *
     * @param year specified year for reports
     * @param dataType virus or contaminant
     */
    //dataType is virus or contaminant
    @SuppressWarnings({"FeatureEnvy", "MagicNumber"})
    private void getData(int year, double latitude, double longitude, String dataType) {
        LinkedList<QualityReport>[] reportsByYear =
                QualityReport.sortReports(year, latitude, longitude, qualityList);
        int month = 1;
        int max = 0;
        for (Deque<QualityReport> reportsByMonth: reportsByYear) {
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
                series.appendData(new DataPoint(month + 1, average), true, 12);
            }
            month++;
        }
        Viewport vP = scatterPlot.getViewport();
        vP.setScrollable(true);
        vP.setMinX(1);
        vP.setMaxX(13);

        vP.setScrollableY(true);
        vP.setMinY(0);
        vP.setMaxY(max + (.2 * max));

        vP.setYAxisBoundsManual(true);
        vP.setXAxisBoundsManual(true);

        scatterPlot.addSeries(this.series);
    }
}
