package com.example.expedia.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.example.expedia.entities.HotelSaleData;
import com.example.expedia.entities.HotelSaleRecyclerViewItem;
import com.example.expedia.R;
import com.example.expedia.fragment.DailySaleFragment;
import com.example.expedia.fragment.HotelSaleFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

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
    private Integer DISCOUNTED_80000 = 0;
    private Integer DISCOUNTED_TODAY = 1;
    private Integer DISCOUNTED_FIN = 2;
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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        hotelListAsyncTask mAsyncTask = new hotelListAsyncTask();
        mAsyncTask.execute();

        exitButton = findViewById(R.id.hotel_sale_exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
    public class hotelListAsyncTask extends AsyncTask<String, Void, Response>{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Response doInBackground(String... strings) {
            String url;
            if(listType==DISCOUNTED_80000){
                url = "http://www.kaca5.com/expedia/discounted_80000";
            }
            else if(listType==DISCOUNTED_TODAY){
                url = "http://www.kaca5.com/expedia/discounted_today";
            }
            else{
                url = "http://www.kaca5.com/expedia/discounted_fin";
            }
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Response response){
            super.onPostExecute(response);
            Gson gson = new GsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonElement rootObject = parser.parse(response.body().charStream())
                    .getAsJsonObject().get("result");

            Log.e("JSONDATA : ", String.valueOf(rootObject));

            HotelSaleData[] result = gson.fromJson(rootObject, HotelSaleData[].class);
            for(HotelSaleData data : result){
                //Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image0);
                hotelSaleArrayList.add(new HotelSaleRecyclerViewItem(data.getImage(), "-" + String.valueOf(data.getPercentage())+"%",
                        String.valueOf(data.getName()), String.valueOf(data.getLocation()),
                        String.valueOf(data.getSdate()) + " ~ " + String.valueOf(data.getEdate()),
                        "￦" + String.valueOf(data.getPrice()), data.getLat(), data.getLng()));
            }
            hotelSaleAdapter = new HotelSaleRecyclerAdapter(HotelSaleActivity.this, hotelSaleArrayList);
            hotelRecyclerView.setAdapter(hotelSaleAdapter);
        }
    }
}