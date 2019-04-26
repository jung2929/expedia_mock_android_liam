package com.example.expedia.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.entities.RoomOptionRecyclerViewItem;

import java.util.ArrayList;

public class RoomOptionRecyclerAdapter extends RecyclerView.Adapter<RoomOptionRecyclerAdapter.RoomOptionViewHolder> {

    private ArrayList<RoomOptionRecyclerViewItem> mList;

    public class RoomOptionViewHolder extends RecyclerView.ViewHolder{
        protected TextView optionNum;
        protected TextView optionPoint;
        protected TextView optionPercentage;
        protected TextView optionPrice;

        public RoomOptionViewHolder(@NonNull View view) {
            super(view);
            optionNum = view.findViewById(R.id.tvOptionNum);
            optionPoint = view.findViewById(R.id.tvRoomPoint);
            optionPercentage = view.findViewById(R.id.tvRoomPercentage);
            optionPrice = view.findViewById(R.id.tvRoomPrice);
            final View mView = view;
        }
    }
    public RoomOptionRecyclerAdapter(ArrayList<RoomOptionRecyclerViewItem> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public RoomOptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_room_option, viewGroup, false);

        RoomOptionViewHolder viewHolder = new RoomOptionViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomOptionViewHolder viewHolder, int position) {
        viewHolder.optionNum.setText("옵션"+mList.get(position).getOptionNum());
        viewHolder.optionPercentage.setText("-"+mList.get(position).getRoomPercentage()+"%");
        viewHolder.optionPoint.setText(mList.get(position).getRoomPoint()+ " 포인트 획득!");
        viewHolder.optionPrice.setText("￦"+mList.get(position).getRoomPrice());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}