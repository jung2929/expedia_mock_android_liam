package com.example.expedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.activity.HotelInfoActivity;
import com.example.expedia.entities.HotelSaleRecyclerViewItem;
import com.example.expedia.entities.RoomOptionRecyclerViewItem;
import com.example.expedia.entities.RoomRecyclerViewItem;

import java.util.ArrayList;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.RoomViewHolder> {

    private ArrayList<RoomRecyclerViewItem> mList;
    private Context context;

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        protected ImageView roomImage;
        protected TextView roomName;
        protected RecyclerView roomOption;

        public RoomViewHolder(@NonNull View view) {
            super(view);
            this.roomImage = view.findViewById(R.id.roomImage);
            this.roomName = view.findViewById(R.id.roomName);
            this.roomOption = view.findViewById(R.id.rvRoomOption);
            final View mView = view;
        }
    }
    public RoomRecyclerAdapter(ArrayList<RoomRecyclerViewItem> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_room, viewGroup, false);

        RoomViewHolder viewHolder = new RoomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomViewHolder viewHolder, int position) {
        viewHolder.roomImage.setImageDrawable(mList.get(position).getRoomImage());
        viewHolder.roomName.setText(mList.get(position).getRoomName());

        ArrayList<RoomOptionRecyclerViewItem> roomArrayList = new ArrayList<>();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(context);
        viewHolder.roomOption.setLayoutManager(mLinearLayoutManager);

        for(int i=0;i<3;i++){
            roomArrayList.add(new RoomOptionRecyclerViewItem( i+1, String.valueOf((i+1)*220), String.valueOf((i+1)*30), String.valueOf((i+1)*12300)));
        }
        RoomOptionRecyclerAdapter roomOptionRecyclerAdapter = new RoomOptionRecyclerAdapter(roomArrayList);
        viewHolder.roomOption.setAdapter(roomOptionRecyclerAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}