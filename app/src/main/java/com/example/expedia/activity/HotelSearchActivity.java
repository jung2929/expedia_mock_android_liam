package com.example.expedia.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.expedia.adapter.HotelSearchRecyclerAdapter;
import com.example.expedia.entities.HotelSearchRecyclerViewItem;
import com.example.expedia.R;

import java.util.ArrayList;

public class HotelSearchActivity extends AppCompatActivity {

    private ArrayList<HotelSearchRecyclerViewItem> hotelSearchArrayList;
    private HotelSearchRecyclerAdapter hotelSearchAdapter;
    private RecyclerView hotelRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        hotelRecyclerView = findViewById(R.id.hotel_search_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        hotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        hotelSearchArrayList = new ArrayList<>();

        for(int i=0;i<6;i++){
            Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image3);
            hotelSearchArrayList.add(new HotelSearchRecyclerViewItem(hotel_image, "-" + String.valueOf(i*15)+"%",
                    "Hotel Example", "Hotel Location Example", "Example Rate",
                    "\\" + String.valueOf(i*18700)));
        }

        hotelSearchAdapter = new HotelSearchRecyclerAdapter(hotelSearchArrayList);
        hotelRecyclerView.setAdapter(hotelSearchAdapter);

        backButton = findViewById(R.id.hotel_search_backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });


    }

}