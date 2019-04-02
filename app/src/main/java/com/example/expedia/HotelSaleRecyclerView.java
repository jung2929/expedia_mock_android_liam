package com.example.expedia;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelSaleRecyclerView {
    private Drawable hotel_sale_image;
    private String hotel_sale_percent;
    private String hotel_sale_name;
    private String hotel_sale_loc;
    private String hotel_sale_date;
    private String hotel_sale_price;

    public HotelSaleRecyclerView(Drawable hotel_sale_image, String hotel_sale_percent, String hotel_sale_name,
                                 String hotel_sale_loc, String hotel_sale_date, String hotel_sale_price) {
        this.hotel_sale_image = hotel_sale_image;
        this.hotel_sale_percent = hotel_sale_percent;
        this.hotel_sale_name = hotel_sale_name;
        this.hotel_sale_loc = hotel_sale_loc;
        this.hotel_sale_date = hotel_sale_date;
        this.hotel_sale_price = hotel_sale_price;
    }

    public Drawable getHotel_sale_image() {
        return hotel_sale_image;
    }

    public void setHotel_sale_image(Drawable hotel_sale_image) {
        this.hotel_sale_image = hotel_sale_image;
    }

    public String getHotel_sale_percent() {
        return hotel_sale_percent;
    }

    public void setHotel_sale_percent(String hotel_sale_percent) {
        this.hotel_sale_percent = hotel_sale_percent;
    }

    public String getHotel_sale_name() {
        return hotel_sale_name;
    }

    public void setHotel_sale_name(String hotel_sale_name) {
        this.hotel_sale_name = hotel_sale_name;
    }

    public String getHotel_sale_loc() {
        return hotel_sale_loc;
    }

    public void setHotel_sale_loc(String hotel_sale_loc) {
        this.hotel_sale_loc = hotel_sale_loc;
    }

    public String getHotel_sale_date() {
        return hotel_sale_date;
    }

    public void setHotel_sale_date(String hotel_sale_date) {
        this.hotel_sale_date = hotel_sale_date;
    }

    public String getHotel_sale_price() {
        return hotel_sale_price;
    }

    public void setHotel_sale_price(String hotel_sale_price) {
        this.hotel_sale_price = hotel_sale_price;
    }
}