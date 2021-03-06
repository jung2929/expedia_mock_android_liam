package com.example.expedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.expedia.activity.HotelInfoActivity;
import com.example.expedia.entities.HotelSearchRecyclerViewItem;
import com.example.expedia.R;

import java.util.ArrayList;

public class HotelSearchRecyclerAdapter extends RecyclerView.Adapter<HotelSearchRecyclerAdapter.HotelSearchViewHolder> {

    private ArrayList<HotelSearchRecyclerViewItem> mList;
    private final Context context;

    public class HotelSearchViewHolder extends  RecyclerView.ViewHolder{
        protected ImageView hotel_search_image;
        protected TextView hotel_search_percent;
        protected TextView hotel_search_name;
        protected TextView hotel_search_loc;
        protected TextView hotel_search_rate;
        protected TextView hotel_search_price;

        public HotelSearchViewHolder(@NonNull View view) {
            super(view);
            this.hotel_search_image = view.findViewById(R.id.hotel_search_image);
            this.hotel_search_percent = view.findViewById(R.id.hotel_search_percent);
            this.hotel_search_name = view.findViewById(R.id.hotel_search_name);
            this.hotel_search_loc = view.findViewById(R.id.hotel_search_loc_detail);
            this.hotel_search_rate = view.findViewById(R.id.hotel_search_rate);
            this.hotel_search_price = view.findViewById(R.id.hotel_search_price);
            final View mView = view;
        }
    }
    public HotelSearchRecyclerAdapter(Context context, ArrayList<HotelSearchRecyclerViewItem> list){
        this.mList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public HotelSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_hotel1, viewGroup, false);

        HotelSearchViewHolder viewHolder = new HotelSearchViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HotelSearchViewHolder viewHolder, final int position) {
        //viewHolder.hotel_search_image.setImageDrawable(mList.get(position).getHotel_search_image());
        Glide.with(context).asDrawable().load(mList.get(position).getHotel_search_image()).into(viewHolder.hotel_search_image);
        viewHolder.hotel_search_percent.setText(mList.get(position).getHotel_search_percent());
        viewHolder.hotel_search_name.setText(mList.get(position).getHotel_search_name());
        viewHolder.hotel_search_loc.setText(mList.get(position).getHotel_search_loc_detail());
        viewHolder.hotel_search_rate.setText(mList.get(position).getHotel_search_rate());
        viewHolder.hotel_search_price.setText(mList.get(position).getHotel_search_price());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, HotelInfoActivity.class);
                intent.putExtra("hotelName", viewHolder.hotel_search_name.getText().toString());
                intent.putExtra("hotelPercentage", viewHolder.hotel_search_percent.getText().toString());
                intent.putExtra("hotelLoc", viewHolder.hotel_search_loc.getText().toString());
                //intent.putExtra("hotelDate", viewHolder.hotel_search_date.getText().toString());
                intent.putExtra("hotelPrice", viewHolder.hotel_search_price.getText().toString());
                intent.putExtra("hotelLat", mList.get(position).getHotel_search_lat());
                intent.putExtra("hotelLng", mList.get(position).getHotel_search_lng());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}