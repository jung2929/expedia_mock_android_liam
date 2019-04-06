package com.example.expedia.entities;

import android.graphics.drawable.Drawable;

public class MainRecyclerViewItem {
    private Drawable main_image;
    private String main_tx1;
    private String main_tx2;

    public MainRecyclerViewItem(Drawable main_image, String main_tx1, String main_tx2) {
        this.main_image = main_image;
        this.main_tx1 = main_tx1;
        this.main_tx2 = main_tx2;
    }

    public Drawable getMain_image() {
        return main_image;
    }

    public void setMain_image(Drawable main_image) {
        this.main_image = main_image;
    }

    public String getMain_tx1() {
        return main_tx1;
    }

    public void setMain_tx1(String main_tx1) {
        this.main_tx1 = main_tx1;
    }

    public String getMain_tx2() {
        return main_tx2;
    }

    public void setMain_tx2(String main_tx2) {
        this.main_tx2 = main_tx2;
    }
}