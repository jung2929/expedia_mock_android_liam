package com.example.expedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.activity.HotelInfoActivity;
import com.example.expedia.activity.HotelSaleActivity;
import com.example.expedia.entities.MainRecyclerViewItem;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private ArrayList<MainRecyclerViewItem> mList;

    public class MainViewHolder extends  RecyclerView.ViewHolder{
        protected ImageView imageBtnMain;
        protected TextView tvMain1;
        protected TextView tvMain2;

        public MainViewHolder(@NonNull View view) {
            super(view);
            this.imageBtnMain = view.findViewById(R.id.main_btn);
            this.tvMain1 = view.findViewById(R.id.main_tx1);
            this.tvMain2 = view.findViewById(R.id.main_tx2);
            final View mView = view;
        }
    }
    public MainRecyclerAdapter(ArrayList<MainRecyclerViewItem> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_main0, viewGroup, false);

        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder viewHolder, final int position) {
        viewHolder.imageBtnMain.setImageDrawable(mList.get(position).getMain_image());
        viewHolder.tvMain1.setText(mList.get(position).getMain_tx1());
        viewHolder.tvMain2.setText(mList.get(position).getMain_tx2());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, HotelSaleActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}