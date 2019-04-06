package com.example.expedia.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.adapter.HotelInfoViewPagerAdapter;
import com.example.expedia.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HotelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageButton backButton;
    private TextView hotelInfoName;
    private TextView hotelInfoDate;
    private TextView hotelInfoPerson;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

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

        hotelInfoName = findViewById(R.id.hotel_info_name);
        hotelInfoDate = findViewById(R.id.hotel_info_date);
        hotelInfoPerson = findViewById(R.id.hotel_info_person);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HotelInfoActivity.this, SearchActivity.class) ;
                intent.putExtra("hotel_name", hotelInfoName.getText().toString());
                intent.putExtra("hotel_date", hotelInfoDate.getText().toString());
                intent.putExtra("hotel_person", hotelInfoPerson.getText().toString());
                finish();
                startActivity(intent);
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
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(HotelInfoActivity.this, SearchActivity.class) ;
        intent.putExtra("hotel_name", hotelInfoName.getText().toString());
        intent.putExtra("hotel_date", hotelInfoDate.getText().toString());
        intent.putExtra("hotel_person", hotelInfoPerson.getText().toString());
        finish();
        startActivity(intent);
    }
}