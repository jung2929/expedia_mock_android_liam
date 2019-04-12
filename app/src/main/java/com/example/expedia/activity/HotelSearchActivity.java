package com.example.expedia.activity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.expedia.adapter.HotelSearchRecyclerAdapter;
import com.example.expedia.entities.HotelSaleData;
import com.example.expedia.entities.HotelSearchRecyclerViewItem;
import com.example.expedia.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HotelSearchActivity extends AppCompatActivity {

    private ArrayList<HotelSearchRecyclerViewItem> hotelSearchArrayList;
    private HotelSearchRecyclerAdapter hotelSearchAdapter;
    private RecyclerView hotelRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageButton backButton;

    private String hotelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        hotelLoc = getIntent().getStringExtra("hotelLoc");

        hotelRecyclerView = findViewById(R.id.hotel_search_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        hotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        hotelSearchArrayList = new ArrayList<>();

        HotelSearchListAsyncTask hotelListAsyncTask = new HotelSearchListAsyncTask();
        hotelListAsyncTask.execute();

        /*for(int i=0;i<6;i++){
            Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image3);
            hotelSearchArrayList.add(new HotelSearchRecyclerViewItem(hotel_image, "-" + String.valueOf(i*15)+"%",
                    "Hotel Example", "Hotel Location Example", "Example Rate",
                    "\\" + String.valueOf(i*18700)));
        }*/

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
    public class HotelSearchListAsyncTask extends AsyncTask<String, Void, Response> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Response doInBackground(String... strings) {
            String url;
            url = "http://www.kaca5.com/expedia/discounted_80000";

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
                    .getAsJsonObject().get("result").getAsJsonArray();

            Log.e("JSONDATA : ", String.valueOf(rootObject));

            HotelSaleData[] result = gson.fromJson(rootObject, HotelSaleData[].class);
            for(HotelSaleData data : result){
                if(data.getShortL().contains(hotelLoc) || data.getName().contains(hotelLoc)){
                    Drawable hotel_image = getResources().getDrawable(R.drawable.hotel_image0);
                    hotelSearchArrayList.add(new HotelSearchRecyclerViewItem(hotel_image, "-"+data.getPercentage()+"%", data.getName(),
                            data.getShortL(), "★★★★", "￦"+data.getPriced()));
                }
            }
            hotelSearchAdapter = new HotelSearchRecyclerAdapter(hotelSearchArrayList);
            hotelRecyclerView.setAdapter(hotelSearchAdapter);
        }
    }

}