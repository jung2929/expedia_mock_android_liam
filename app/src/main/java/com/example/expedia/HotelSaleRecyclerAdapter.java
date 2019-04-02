package com.example.expedia;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelSaleRecyclerAdapter extends RecyclerView.Adapter<HotelSaleRecyclerAdapter.HotelSaleViewHolder> {

    private ArrayList<HotelSaleRecyclerView> mList;

    public class HotelSaleViewHolder extends  RecyclerView.ViewHolder{
        protected ImageView hotel_sale_image;
        protected TextView hotel_sale_percent;
        protected TextView hotel_sale_name;
        protected TextView hotel_sale_loc;
        protected TextView hotel_sale_date;
        protected TextView hotel_sale_price;

        public HotelSaleViewHolder(@NonNull View view) {
            super(view);
            final View mView = view;
            this.hotel_sale_image = view.findViewById(R.id.hotel_sale_image);
            this.hotel_sale_percent = view.findViewById(R.id.hotel_sale_percent);
            this.hotel_sale_name = view.findViewById(R.id.hotel_sale_name);
            this.hotel_sale_loc = view.findViewById(R.id.hotel_sale_loc);
            this.hotel_sale_date = view.findViewById(R.id.hotel_sale_date);
            this.hotel_sale_price = view.findViewById(R.id.hotel_sale_price);
        }
    }
    public HotelSaleRecyclerAdapter(ArrayList<HotelSaleRecyclerView> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public HotelSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_main1, viewGroup, false);

        HotelSaleViewHolder viewHolder = new HotelSaleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelSaleViewHolder viewHolder, int position) {
        HotelSaleRecyclerView item = mList.get(position);

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