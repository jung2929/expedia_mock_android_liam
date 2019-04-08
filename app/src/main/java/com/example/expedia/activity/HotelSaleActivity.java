package com.example.expedia.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.adapter.HotelSaleRecyclerAdapter;
import com.example.expedia.entities.HotelSaleRecyclerViewItem;
import com.example.expedia.R;
import com.example.expedia.fragment.DailySaleFragment;
import com.example.expedia.fragment.HotelSaleFragment;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HotelSaleActivity extends AppCompatActivity {

    private ArrayList<HotelSaleRecyclerViewItem> hotelSaleArrayList;
    private HotelSaleRecyclerAdapter hotelSaleAdapter;
    private RecyclerView hotelRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageButton exitButton;
    private TextView tvDeadline;
    int listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_sale);

        tvDeadline = findViewById(R.id.tvDeadline);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Intent intent = getIntent();
        listType = intent.getIntExtra("position", -1);

        if(listType==0){
            tvDeadline.setVisibility(View.GONE);
            fragmentTransaction.replace(R.id.hotel_description_fragment, new HotelSaleFragment()).commit();
        }
        else if(listType==1){
            tvDeadline.setVisibility(View.GONE);
            fragmentTransaction.replace(R.id.hotel_description_fragment, new DailySaleFragment()).commit();
        }
        else{
            tvDeadline.setVisibility(View.VISIBLE);
        }

        hotelRecyclerView = findViewById(R.id.recyclerview_hotel);
        mLinearLayoutManager = new LinearLayoutManager(this);
        hotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        hotelSaleArrayList = new ArrayList<>();

        for(int i=0;i<6;i++){
            Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image0);
            hotelSaleArrayList.add(new HotelSaleRecyclerViewItem(hotel_image, "-" + String.valueOf(i*15)+"%", "Hotel Example",
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
    public void getHotelList(int position){
        OkHttpClient client = new OkHttpClient();
        String url;
        if(position == 0){
            url = "http://www.kaca5.com/expedia/discounted_80000";
        }
        else if(position == 1){
            url = "http://www.kaca5.com/expedia/discounted_today";
        }
        else{
            url = "http://www.kaca5.com/expedia/discounted_fin";
        }
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(hotelListCallback);
    }
    private Callback hotelListCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("Error", "Message : " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String responseData = response.body().string();
            Log.e("Success", "responseData : " + responseData);
        }
    };
}