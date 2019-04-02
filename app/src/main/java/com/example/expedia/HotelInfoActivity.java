package com.example.expedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HotelInfoActivity extends AppCompatActivity {


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

    }

}