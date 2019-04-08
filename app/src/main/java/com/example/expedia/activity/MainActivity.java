package com.example.expedia.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.adapter.MainRecyclerAdapter;
import com.example.expedia.entities.MainRecyclerViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button hotelReservebtn;
    private ImageButton btnDeadline;
    private Button hotelAndPlaneReservebtn;
    private TextView tvLogin;

    private ArrayList<MainRecyclerViewItem> mainArrayList;
    private MainRecyclerAdapter mainAdapter;
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelReservebtn = findViewById(R.id.hotel_reserve_btn);
        hotelAndPlaneReservebtn = findViewById(R.id.hotel_plane_reserve_btn);

        mainRecyclerView = findViewById(R.id.recyclerview_main);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager);

        mainArrayList = new ArrayList<>();

        Drawable mainImage1 = getResources().getDrawable(R.drawable.hotel_image0);
        Drawable mainImage2 = getResources().getDrawable(R.drawable.hotel_image1);
        Drawable mainImage3 = getResources().getDrawable(R.drawable.hotel_image3);
        mainArrayList.add(new MainRecyclerViewItem(mainImage1,  "\\80,000 이하 특가 호텔", "호텔이 1박에 \\80,000 이하!"));
        mainArrayList.add(new MainRecyclerViewItem(mainImage2,  "일일 특가", "매일 일부 호텔을 40% 이상 할인해 드려요. " +
                "매일 밤 자정 특가 상품이 업데이트 됩니다."));

        mainAdapter = new MainRecyclerAdapter(mainArrayList);
        mainRecyclerView.setAdapter(mainAdapter);

        btnDeadline = findViewById(R.id.btnDeadline);

        hotelReservebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        btnDeadline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, HotelSaleActivity.class);
                startActivity(intent);
            }
        });
        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}