package com.example.expedia.entities;

import android.graphics.drawable.Drawable;

public class RoomRecyclerViewItem {
    private Drawable roomImage;
    private String roomName;

    public RoomRecyclerViewItem(Drawable roomImage, String roomName) {
        this.roomImage = roomImage;
        this.roomName = roomName;
    }

    public Drawable getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(Drawable roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}