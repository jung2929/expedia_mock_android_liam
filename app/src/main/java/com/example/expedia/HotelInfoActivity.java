package com.example.expedia;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HotelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageButton backButton;
    private FloatingActionButton chooseRoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        chooseRoomButton = findViewById(R.id.hotel_info_floating_btn);
        ViewPager viewPager = findViewById(R.id.hotel_info_image_viewPager);
        HotelInfoViewPagerAdapter viewPagerAdapter = new HotelInfoViewPagerAdapter(HotelInfoActivity.this);
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image0));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image1));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image2));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image3));

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment fragment = (MapFragment) fragmentManager.findFragmentById(R.id.hotel_info_map);
        fragment.getMapAsync(this);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        chooseRoomButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        googleMap.addMarker(markerOptions);
    }
}