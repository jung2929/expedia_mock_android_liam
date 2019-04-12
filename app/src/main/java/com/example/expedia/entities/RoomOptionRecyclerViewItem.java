package com.example.expedia.entities;

import android.graphics.drawable.Drawable;

public class RoomOptionRecyclerViewItem {
    private int optionNum;
    private String roomPoint;
    private String roomPercentage;
    private String roomPrice;

    public RoomOptionRecyclerViewItem(int optionNum, String roomPoint, String roomPercentage, String roomPrice) {
        this.optionNum = optionNum;
        this.roomPoint = roomPoint;
        this.roomPercentage = roomPercentage;
        this.roomPrice = roomPrice;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public String getRoomPoint() {
        return roomPoint;
    }

    public void setRoomPoint(String roomPoint) {
        this.roomPoint = roomPoint;
    }

    public String getRoomPercentage() {
        return roomPercentage;
    }

    public void setRoomPercentage(String roomPercentage) {
        this.roomPercentage = roomPercentage;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }
}