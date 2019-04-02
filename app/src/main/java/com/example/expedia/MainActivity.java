package com.example.expedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button hotelReservebtn;
    private Button planeReservebtn;
    private Button hotelAndPlaneReservebtn;
    private ImageButton hotelSalebtn;
    private ImageButton dailySalebtn;
    private ImageButton deadlineSalebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelReservebtn = findViewById(R.id.hotel_reserve_btn);
        planeReservebtn = findViewById(R.id.plane_reserve_btn);
        hotelAndPlaneReservebtn = findViewById(R.id.hotel_plane_reserve_btn);
        hotelSalebtn = findViewById(R.id.hotel_sale_btn);
        dailySalebtn = findViewById(R.id.daily_sale_btn);
        deadlineSalebtn = findViewById(R.id.deadline_sale_btn);

        hotelSalebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, HotelSaleActivity.class);
                startActivity(intent);
            }
        });
    }

}