package com.example.expedia.entities;

import android.graphics.drawable.Drawable;

public class HotelSearchRecyclerViewItem {
    private String hotel_search_image;
    private String hotel_search_percent;
    private String hotel_search_name;
    private String hotel_search_loc_detail;
    private String hotel_search_rate;
    private String hotel_search_price;
    private String hotel_search_lat;
    private String hotel_search_lng;

    public HotelSearchRecyclerViewItem(String hotel_search_image, String hotel_search_percent, String hotel_search_name,
                                       String hotel_search_loc_detail, String hotel_search_rate, String hotel_search_price, String hotel_search_lat,
                                       String hotel_search_lng) {
        this.hotel_search_image = hotel_search_image;
        this.hotel_search_percent = hotel_search_percent;
        this.hotel_search_name = hotel_search_name;
        this.hotel_search_loc_detail = hotel_search_loc_detail;
        this.hotel_search_rate = hotel_search_rate;
        this.hotel_search_price = hotel_search_price;
        this.hotel_search_lat = hotel_search_lat;
        this.hotel_search_lng = hotel_search_lng;
    }

    public String getHotel_search_image() {
        return hotel_search_image;
    }

    public void setHotel_search_image(String hotel_search_image) {
        this.hotel_search_image = hotel_search_image;
    }

    public String getHotel_search_percent() {
        return hotel_search_percent;
    }

    public void setHotel_search_percent(String hotel_search_percent) {
        this.hotel_search_percent = hotel_search_percent;
    }

    public String getHotel_search_name() {
        return hotel_search_name;
    }

    public void setHotel_search_name(String hotel_search_name) {
        this.hotel_search_name = hotel_search_name;
    }

    public String getHotel_search_loc_detail() {
        return hotel_search_loc_detail;
    }

    public void setHotel_search_loc_detail(String hotel_sale_loc_detail) {
        this.hotel_search_loc_detail = hotel_sale_loc_detail;
    }

    public String getHotel_search_rate() {
        return hotel_search_rate;
    }

    public void setHotel_search_rate(String hotel_search_rate) {
        this.hotel_search_rate = hotel_search_rate;
    }

    public String getHotel_search_price() {
        return hotel_search_price;
    }

    public void setHotel_search_price(String hotel_search_price) {
        this.hotel_search_price = hotel_search_price;
    }

    public String getHotel_search_lat() {
        return hotel_search_lat;
    }

    public String getHotel_search_lng() {
        return hotel_search_lng;
    }
}