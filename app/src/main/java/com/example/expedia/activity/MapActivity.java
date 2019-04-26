package com.example.expedia.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.adapter.HotelInfoViewPagerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageButton backButton;
    private Geocoder geocoder;
    private TextView mapName;
    private TextView mapRate;
    private String hotelLoc;
    private Double lat;
    private Double lng;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        backButton = findViewById(R.id.btnMapBack);
        mapName = findViewById(R.id.tvMapName);
        mapRate = findViewById(R.id.tvMapRate);

        Intent intent = getIntent();

        mapName.setText(intent.getStringExtra("hotelName"));
        mapRate.setText("★★★★");

        hotelLoc = intent.getStringExtra("hotelLoc");
        lat = intent.getDoubleExtra("hotelLat", 126.5);
        lng = intent.getDoubleExtra("hotelLng", 37.5);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapfragment = (MapFragment) fragmentManager.findFragmentById(R.id.mapMap);
        mapfragment.getMapAsync(this);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        LatLng point = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(mapName.getText().toString());
        markerOptions.position(point);

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));

    }
}