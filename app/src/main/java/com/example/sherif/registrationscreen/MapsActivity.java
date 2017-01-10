package com.example.sherif.registrationscreen;

import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.sherif.registrationscreen.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean markerClicked = true;
    private  Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

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
        mMap = googleMap;
        Bundle b = getIntent().getExtras();
        double lat = b.getDouble("lat");
        double lon = b.getDouble("lon");
        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(lat, lon);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(loc,18);
        mMap.animateCamera(cu);
        marker =  mMap.addMarker(new MarkerOptions().position(loc).draggable(true));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

//                Toast.makeText(MapsActivity.this,"Text!",Toast.LENGTH_SHORT).show();
                double newlat = point.latitude;
                double newLon = point.longitude;
                if (markerClicked == true) {
                    marker.remove();
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(point));
                    marker =  mMap.addMarker(new MarkerOptions().position(point).title(String.valueOf(newlat) + "------" + String.valueOf(newLon)).draggable(true));
                    markerClicked = false;
                }
                else{
                    marker.remove();
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(point));
                    marker =  mMap.addMarker(new MarkerOptions().position(point).title(String.valueOf(newlat) + "------" + String.valueOf(newLon)).draggable(true));
                }


            }
        });

//        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(LatLng latLng) {
//                mMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .draggable(true));
//
//                markerClicked = false;
//            }
//        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(MapsActivity.this,marker.getPosition().toString()+"on markend",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
