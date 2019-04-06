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

import com.example.expedia.activity.HotelInfoActivity;
import com.example.expedia.entities.HotelSearchRecyclerViewItem;
import com.example.expedia.R;

import java.util.ArrayList;

public class HotelSearchRecyclerAdapter extends RecyclerView.Adapter<HotelSearchRecyclerAdapter.HotelSearchViewHolder> {

    private ArrayList<HotelSearchRecyclerViewItem> mList;

    public class HotelSearchViewHolder extends  RecyclerView.ViewHolder{
        protected ImageView hotel_search_image;
        protected TextView hotel_search_percent;
        protected TextView hotel_search_name;
        protected TextView hotel_search_loc_detail;
        protected TextView hotel_search_rate;
        protected TextView hotel_search_price;

        public HotelSearchViewHolder(@NonNull View view) {
            super(view);
            this.hotel_search_image = view.findViewById(R.id.hotel_search_image);
            this.hotel_search_percent = view.findViewById(R.id.hotel_search_percent);
            this.hotel_search_name = view.findViewById(R.id.hotel_search_name);
            this.hotel_search_loc_detail = view.findViewById(R.id.hotel_search_loc_detail);
            this.hotel_search_rate = view.findViewById(R.id.hotel_search_rate);
            this.hotel_search_price = view.findViewById(R.id.hotel_search_price);
            final View mView = view;
        }
    }
    public HotelSearchRecyclerAdapter(ArrayList<HotelSearchRecyclerViewItem> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public HotelSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_hotel1, viewGroup, false);

        HotelSearchViewHolder viewHolder = new HotelSearchViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelSearchViewHolder viewHolder, int position) {
        viewHolder.hotel_search_image.setImageDrawable(mList.get(position).getHotel_search_image());
        viewHolder.hotel_search_percent.setText(mList.get(position).getHotel_search_percent());
        viewHolder.hotel_search_name.setText(mList.get(position).getHotel_search_name());
        viewHolder.hotel_search_loc_detail.setText(mList.get(position).getHotel_search_loc_detail());
        viewHolder.hotel_search_rate.setText(mList.get(position).getHotel_search_rate());
        viewHolder.hotel_search_price.setText(mList.get(position).getHotel_search_price());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, HotelInfoActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}