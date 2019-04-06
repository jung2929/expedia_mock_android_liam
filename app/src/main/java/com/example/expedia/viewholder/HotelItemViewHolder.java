package com.example.expedia.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expedia.R;

public class HotelItemViewHolder extends  RecyclerView.ViewHolder{

    protected ImageView hotel_sale_image;
    protected TextView hotel_sale_percent;
    protected TextView hotel_sale_name;
    protected TextView hotel_sale_loc;
    protected TextView hotel_sale_date;
    protected TextView hotel_sale_price;

    public HotelItemViewHolder(@NonNull View view) {
        super(view);
        this.hotel_sale_image = view.findViewById(R.id.hotel_sale_image);
        this.hotel_sale_percent = view.findViewById(R.id.hotel_sale_percent);
        this.hotel_sale_name = view.findViewById(R.id.hotel_sale_name);
        this.hotel_sale_loc = view.findViewById(R.id.hotel_sale_loc);

        //--
        try{ this.hotel_sale_date = view.findViewById(R.id.hotel_sale_date);}catch(Exception e){}
        try{ this.hotel_sale_price = view.findViewById(R.id.hotel_sale_price);}catch(Exception e){}
        //--
        final View mView = view;
    }


}