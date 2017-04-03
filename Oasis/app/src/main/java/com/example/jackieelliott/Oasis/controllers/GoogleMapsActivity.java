package com.example.jackieelliott.Oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import com.example.jackieelliott.Oasis.Model.Report;
import com.example.jackieelliott.Oasis.Model.User;
import com.example.jackieelliott.Oasis.controllers.HomeActivity;
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

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button backButton;
    private ListView reportsList;
    private ArrayList<User> userList;
    private ArrayList<Report> reportList;
    private ArrayList<QualityReport> qualityList;
    private User currentUser;

    /**
     * sets up activity when it is first created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.userList = b.getParcelableArrayList("UserList");
        this.currentUser = b.getParcelable("CurrentUser");
        this.reportList = b.getParcelableArrayList("ReportList");
        this.qualityList = b.getParcelableArrayList("QualityList");
        this.reportsList = (ListView) findViewById(R.id.reports_list);
        this.backButton = (Button) findViewById(R.id.backButton);
        setContentView(R.layout.activity_google_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addListenerOnButtonBack();
    }

    /**
     * Adds functionality to the Back button.
     */
    public void addListenerOnButtonBack() {

        final Context context = this;

        this.backButton = (Button)findViewById(R.id.backButton);

        this.backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0a) {

                Intent intent = new Intent(context, HomeActivity.class);
                //do not use this.variable as it cannot find the symbol
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
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        this.mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);


                //do not use this.variable as it cannot find the symbol
                Report a = new Report("newly added", latLng.latitude, latLng.longitude);
                //noinspection UnqualifiedFieldAccess
                a.setReportNumber(reportList.size() + 1);
                //noinspection UnqualifiedFieldAccess
                reportList.add(a);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                //noinspection UnqualifiedFieldAccess
                markerOptions.title(reportList.get(reportList.size() - 1).getReportName());
                //noinspection UnqualifiedFieldAccess
                markerOptions.snippet(reportList.get(reportList.size() - 1).toString());

                // Animating to the touched position
                //noinspection UnqualifiedFieldAccess
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                //noinspection UnqualifiedFieldAccess
                mMap.addMarker(markerOptions);
            }
        });

        for (Report r : this.reportList) {
            LatLng loc = new LatLng(r.getLatitude(), r.getLongitude());
            this.mMap.addMarker(new MarkerOptions().position(loc).title(r.getReportName()).snippet(r.toString()));
            this.mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }


}

