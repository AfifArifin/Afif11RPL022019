package com.example.afif11rpl022019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder > {
    private ArrayList<ItemActivity> mExampleList;

    public static class  ViewHolder extends  RecyclerView.ViewHolder {
        public  ImageView mImageView;
        public  TextView mTextView1;
        public  TextView mTextView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView =itemView.findViewById(R.id.imgView);
            mTextView1 =itemView.findViewById(R.id.tv1);
            mTextView2 =itemView.findViewById(R.id.tv2);
        }
    }
    public Adapter(ArrayList<ItemActivity> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder evh = new ViewHolder(v);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemActivity currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
