package com.example.expedia.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.adapter.HotelInfoViewPagerAdapter;
import com.example.expedia.R;
import com.example.expedia.adapter.RoomRecyclerAdapter;
import com.example.expedia.entities.RoomRecyclerViewItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tmall.ultraviewpager.UltraViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageButton backButton;
    private Geocoder geocoder;
    private TextView hotelInfoName;
    private TextView hotelInfoDate;
    private TextView hotelInfoPerson;
    private TextView hotelInfoPrice;
    private TextView hotelInfoPercentage;
    private TextView hotelInfoLoc;

    private RecyclerView roomRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<RoomRecyclerViewItem> roomArrayList;
    private RoomRecyclerAdapter roomRecyclerAdapter;

    private String lat;
    private String lng;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        Intent intent = getIntent();

        UltraViewPager viewPager = findViewById(R.id.hotel_info_image_viewPager);
        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        HotelInfoViewPagerAdapter viewPagerAdapter = new HotelInfoViewPagerAdapter(HotelInfoActivity.this);

        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image0));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image1));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image2));
        viewPagerAdapter.addItem(getResources().getDrawable(R.drawable.hotel_image3));

        viewPager.setAdapter(viewPagerAdapter);

        roomRecyclerView = findViewById(R.id.rvRoom);
        mLinearLayoutManager = new LinearLayoutManager(this);
        roomRecyclerView.setLayoutManager(mLinearLayoutManager);

        roomArrayList = new ArrayList<>();

        ArrayList<Drawable> roomImage = new ArrayList<>();
        roomImage.add(getResources().getDrawable(R.drawable.hotel_image0));
        roomImage.add(getResources().getDrawable(R.drawable.hotel_image1));
        roomImage.add(getResources().getDrawable(R.drawable.hotel_image3));
        for(int i=0;i<3;i++){
            roomArrayList.add(new RoomRecyclerViewItem(roomImage.get(i), "스탠다드 더블 룸"));
        }
        roomRecyclerAdapter = new RoomRecyclerAdapter(roomArrayList);
        roomRecyclerView.setAdapter(roomRecyclerAdapter);

        hotelInfoName = findViewById(R.id.hotel_info_name);
        hotelInfoDate = findViewById(R.id.hotel_info_date);
        hotelInfoLoc = findViewById(R.id.hotel_info_loc);
        hotelInfoPrice = findViewById(R.id.hotel_info_price);
        hotelInfoPercentage = findViewById(R.id.hotel_info_percentage);
        hotelInfoPerson = findViewById(R.id.hotel_info_person);
        backButton = findViewById(R.id.backButton);

        hotelInfoName.setText(intent.getStringExtra("hotelName"));
        hotelInfoDate.setText(intent.getStringExtra("hotelDate"));
        hotelInfoLoc.setText(intent.getStringExtra("hotelLoc"));
        hotelInfoPrice.setText(intent.getStringExtra("hotelPrice") + "/1박");
        hotelInfoPercentage.setText(intent.getStringExtra("hotelPercentage"));

        lat = intent.getStringExtra("hotelLat");
        lng = intent.getStringExtra("hotelLng");

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapfragment = (MapFragment) fragmentManager.findFragmentById(R.id.hotel_info_map);
        mapfragment.getMapAsync(this);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HotelInfoActivity.this, SearchActivity.class) ;
                intent.putExtra("hotelName", hotelInfoName.getText().toString());
                intent.putExtra("hotelDate", hotelInfoDate.getText().toString());
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        geocoder = new Geocoder(this);

        String hotelLoc = hotelInfoLoc.getText().toString();

        final LatLng point = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(hotelInfoName.getText().toString());
        //markerOptions.snippet(address);
        markerOptions.position(point);

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(HotelInfoActivity.this, MapActivity.class);
                intent.putExtra("hotelName", hotelInfoName.getText().toString());
                intent.putExtra("hotelLoc", hotelInfoLoc.getText().toString());
                intent.putExtra("hotelLat", point.latitude);
                intent.putExtra("hotelLng", point.longitude);
                startActivity(intent);
            }
        });
    }
    /*@Override
    public void onBackPressed(){
        Intent intent = new Intent(HotelInfoActivity.this, SearchActivity.class) ;
        intent.putExtra("hotelName", hotelInfoName.getText().toString());
        intent.putExtra("hotelDate", hotelInfoDate.getText().toString());
        finish();
        startActivity(intent);
    }*/
}