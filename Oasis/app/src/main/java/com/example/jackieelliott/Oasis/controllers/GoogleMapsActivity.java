package com.example.jackieelliott.Oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.jackieelliott.Oasis.R;

import java.util.ArrayList;

//Overriding the toString() method
//we do not want to override the toString method in this class

/**
 * Google maps activity controller
 */
@SuppressWarnings("CyclicClassDependency")
public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button backButton2;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState saved instance state
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        backButton2 = (Button) findViewById(R.id.backButton2);
        setContentView(R.layout.activity_google_maps);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        reportList = b.getParcelableArrayList("ReportList");
        qualityList = b.getParcelableArrayList("QualityList");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager mF = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) mF.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addListenerOnButtonBack();
    }

    /**
     * Adds functionality to the Back button.
     */
    private void addListenerOnButtonBack() {

        final Context context = this;

        backButton2 = (Button)findViewById(R.id.backButton2);

        backButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0a) {

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
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressWarnings("FeatureEnvy")
    @Override

    public final void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @SuppressWarnings("FeatureEnvy")
            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);


                //do not use this.variable as it cannot find the symbol
                Report a = new Report(latLng.latitude, latLng.longitude);
                //noinspection UnqualifiedFieldAccess
                a.setReportNumber(reportList.size() + 1);
                //noinspection UnqualifiedFieldAccess
                reportList.add(a);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                Report report = reportList.get(reportList.size() - 1);
                //noinspection UnqualifiedFieldAccess
                markerOptions.title(report.getReportName());
                //noinspection UnqualifiedFieldAccess
                markerOptions.snippet(report.toString());

                // Animating to the touched position
                //noinspection UnqualifiedFieldAccess
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                //noinspection UnqualifiedFieldAccess
                mMap.addMarker(markerOptions);
            }
        });

        for (Report r : reportList) {
            LatLng loc = new LatLng(r.getLatitude(), r.getLongitude());
            MarkerOptions mO = new MarkerOptions();
            //noinspection ChainedMethodCall     need to chain method here
            mMap.addMarker(mO.position(loc).title(r.getReportName()).snippet(r.toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }


}

