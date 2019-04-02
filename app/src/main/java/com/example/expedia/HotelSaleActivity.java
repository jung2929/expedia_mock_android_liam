package com.example.expedia;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class HotelSaleActivity extends AppCompatActivity {

    private ArrayList<HotelSaleRecyclerView> hotelSaleArrayList;
    private HotelSaleRecyclerAdapter hotelSaleAdapter;
    private RecyclerView hotelRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_sale);

        hotelRecyclerView = findViewById(R.id.recyclerview_hotel);
        mLinearLayoutManager = new LinearLayoutManager(this);
        hotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        hotelSaleArrayList = new ArrayList<>();

        for(int i=0;i<6;i++){
            Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image);
            hotelSaleArrayList.add(new HotelSaleRecyclerView(hotel_image, "-" + String.valueOf(i*15)+"%", "Hotel Example",
                    "Hotel Location Example", "Example Date1 ~ Example Date2", "\\" + String.valueOf(i*19280)));
        }

        hotelSaleAdapter = new HotelSaleRecyclerAdapter(hotelSaleArrayList);
        hotelRecyclerView.setAdapter(hotelSaleAdapter);

        exitButton = findViewById(R.id.hotel_sale_exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });


    }

}