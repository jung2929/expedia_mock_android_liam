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
import com.example.expedia.activity.HotelSaleActivity;
import com.example.expedia.entities.HotelSaleRecyclerViewItem;
import com.example.expedia.R;

import java.util.ArrayList;

public class HotelSaleRecyclerAdapter extends RecyclerView.Adapter<HotelSaleRecyclerAdapter.HotelSaleViewHolder> {

    private ArrayList<HotelSaleRecyclerViewItem> mList;
    private final Context context;

    public class HotelSaleViewHolder extends  RecyclerView.ViewHolder{
        protected ImageView hotel_sale_image;
        protected TextView hotel_sale_percent;
        protected TextView hotel_sale_name;
        protected TextView hotel_sale_loc;
        protected TextView hotel_sale_date;
        protected TextView hotel_sale_price;

        public HotelSaleViewHolder(@NonNull View view) {
            super(view);
            this.hotel_sale_image = view.findViewById(R.id.hotel_sale_image);
            this.hotel_sale_percent = view.findViewById(R.id.hotel_sale_percent);
            this.hotel_sale_name = view.findViewById(R.id.hotel_sale_name);
            this.hotel_sale_loc = view.findViewById(R.id.hotel_sale_loc);
            this.hotel_sale_date = view.findViewById(R.id.hotel_sale_date);
            this.hotel_sale_price = view.findViewById(R.id.hotel_sale_price);
            final View mView = view;
        }
    }
    public HotelSaleRecyclerAdapter(Context context, ArrayList<HotelSaleRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public HotelSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_main2, viewGroup, false);

        HotelSaleViewHolder viewHolder = new HotelSaleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HotelSaleViewHolder viewHolder, final int position) {

        Glide.with(context).asDrawable().load(mList.get(position).getHotel_sale_image()).into(viewHolder.hotel_sale_image);
        //viewHolder.hotel_sale_image.setImageDrawable(mList.get(position).getHotel_sale_image());
        viewHolder.hotel_sale_percent.setText(mList.get(position).getHotel_sale_percent());
        viewHolder.hotel_sale_name.setText(mList.get(position).getHotel_sale_name());
        viewHolder.hotel_sale_loc.setText(mList.get(position).getHotel_sale_loc());
        viewHolder.hotel_sale_date.setText(mList.get(position).getHotel_sale_date());
        viewHolder.hotel_sale_price.setText(mList.get(position).getHotel_sale_price());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, HotelInfoActivity.class);
                intent.putExtra("hotelName", viewHolder.hotel_sale_name.getText().toString());
                intent.putExtra("hotelPercentage", viewHolder.hotel_sale_percent.getText().toString());
                intent.putExtra("hotelLoc", viewHolder.hotel_sale_loc.getText().toString());
                intent.putExtra("hotelDate", viewHolder.hotel_sale_date.getText().toString());
                intent.putExtra("hotelPrice", viewHolder.hotel_sale_price.getText().toString());
                intent.putExtra("hotelLat", mList.get(position).getHotel_sale_lat());
                intent.putExtra("hotelLng", mList.get(position).getHotel_sale_lng());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}