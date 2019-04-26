package com.example.expedia.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.expedia.adapter.HotelSearchRecyclerAdapter;
import com.example.expedia.cluster.MarkerClusterRenderer;
import com.example.expedia.entities.HotelClusteringItem;
import com.example.expedia.entities.HotelSaleData;
import com.example.expedia.entities.HotelSearchRecyclerViewItem;
import com.example.expedia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HotelSearchActivity extends AppCompatActivity {

    private ArrayList<HotelClusteringItem> hotelClusteringArrayList;
    private ClusterManager<HotelClusteringItem> clusterManager;
    private ArrayList<HotelSearchRecyclerViewItem> hotelSearchArrayList;
    private HotelSearchRecyclerAdapter hotelSearchAdapter;
    private RecyclerView hotelRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageButton backButton;

    private String hotelLoc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        context = getApplicationContext();
        hotelLoc = getIntent().getStringExtra("hotelLoc");

        hotelRecyclerView = findViewById(R.id.hotel_search_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        hotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        hotelSearchArrayList = new ArrayList<>();
        hotelClusteringArrayList = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HotelSearchListAsyncTask hotelListAsyncTask = new HotelSearchListAsyncTask();
        hotelListAsyncTask.execute();

        backButton = findViewById(R.id.hotel_search_backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
    public class HotelSearchListAsyncTask extends AsyncTask<String, Void, Response> implements OnMapReadyCallback{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Response doInBackground(String... strings) {
            String url;
            url = "http://www.kaca5.com/expedia/hotel";

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
            JsonElement rootObject = null;
            if (response.body() != null) {
                rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("result");
            }
            Log.e("JSONDATA : ", String.valueOf(rootObject));

            HotelSaleData[] result = gson.fromJson(rootObject, HotelSaleData[].class);
            for(HotelSaleData data : result){
                if(data.getLocation().contains(hotelLoc) || data.getName().contains(hotelLoc)){
                    hotelSearchArrayList.add(new HotelSearchRecyclerViewItem(data.getImage(), "-"+data.getPercentage()+"%", data.getName(),
                            data.getLocation(), "★★★★", "￦"+data.getPrice(), data.getLat(), data.getLng()));
                    hotelClusteringArrayList.add(new HotelClusteringItem(data.getName(), data.getLat(), data.getLng()));
                }
            }
            hotelSearchAdapter = new HotelSearchRecyclerAdapter(HotelSearchActivity.this, hotelSearchArrayList);
            hotelRecyclerView.setAdapter(hotelSearchAdapter);

            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapfragment = (MapFragment) fragmentManager.findFragmentById(R.id.hotel_search_map);
            mapfragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(final GoogleMap googleMap) {

            clusterManager = new ClusterManager<>(context, googleMap);

            for(int i=0;i<hotelClusteringArrayList.size();i++){
                clusterManager.addItem(hotelClusteringArrayList.get(i));
                LatLng latLng = hotelClusteringArrayList.get(i).getPosition();
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).
                       title(hotelClusteringArrayList.get(i).getName());
            }

            MarkerClusterRenderer<HotelClusteringItem> clusterRenderer = new MarkerClusterRenderer<>(context, googleMap, clusterManager);
            clusterManager.setRenderer(clusterRenderer);
            //clusterManager.cluster();
            final CameraPosition[] mPreviousCameraPosition = {null};
            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    CameraPosition position = googleMap.getCameraPosition();
                    if(mPreviousCameraPosition[0] == null || mPreviousCameraPosition[0].zoom != position.zoom) {
                        mPreviousCameraPosition[0] = googleMap.getCameraPosition();
                        clusterManager.cluster();
                    }
                }
            });

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hotelClusteringArrayList.get(0).getPosition(), 9.0f));

            /*LatLng point = new LatLng(lat, lng);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(mapName.getText().toString());
            markerOptions.position(point);

            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));*/
        }
    }


}