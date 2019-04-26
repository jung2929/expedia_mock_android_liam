package com.example.expedia.entities;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class HotelClusteringItem implements ClusterItem {

    private String Name;
    private String lat;
    private String lng;

    public HotelClusteringItem(String Name, String lat, String lng) {
        this.Name = Name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName(){
        return Name;
    }

    public void setName(){
        this.Name = Name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public LatLng getPosition() {
        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        return location;
    }
}
