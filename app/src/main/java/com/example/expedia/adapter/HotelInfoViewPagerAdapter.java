package com.example.expedia.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.expedia.entities.HotelInfoViewPagerItem;
import com.example.expedia.R;

import java.util.ArrayList;

public class HotelInfoViewPagerAdapter extends PagerAdapter {

    private Context mContext = null;
    private ArrayList<HotelInfoViewPagerItem> mList = new ArrayList<>();

    public HotelInfoViewPagerAdapter(){

    }
    public HotelInfoViewPagerAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        if(mContext != null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.viewpager_hotelimage_item, container, false);
            ImageView imageView = view.findViewById(R.id.viewPager_image);

            HotelInfoViewPagerItem viewPagerItem = mList.get(position);

            imageView.setImageDrawable(viewPagerItem.getHotel_image());

            container.addView(view);

        }
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
    public void addItem(Drawable image){
        HotelInfoViewPagerItem item = new HotelInfoViewPagerItem();

        item.setHotel_image(image);
        mList.add(item);
    }
}