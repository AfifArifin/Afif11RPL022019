package com.example.afif11rpl022019;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class ItemFavoriteAdapter extends RecyclerView.Adapter<ItemFavoriteAdapter.MyViewHolder> {
    private List<MovieModelRealm> mahasiswaModels;
    RealmHelper realmHelper;
    Context context;

    public ItemFavoriteAdapter(Context context, List<MovieModelRealm> mahasiswaModels){
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @Override
    public ItemFavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_favorite,parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemFavoriteAdapter.MyViewHolder holder,  int position) {
        final MovieModelRealm model = mahasiswaModels.get(position);

        holder.txtNama2.setText(String.valueOf(model.getOriginal_title()));
        holder.tvrate2.setText(String.valueOf(model.getVote_average()));
        holder.tvpopularity2.setText(String.valueOf(model.getPopularity()));
        holder.tvdate2.setText(String.valueOf(model.getRelease_date()));
        Log.d("cover", "onBindViewHolder: " + model.getPoster_path());
        Glide.with(holder.itemView)
                .load(model.getPoster_path())
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivprofile2);

    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama2,  tvdate2, tvpopularity2, tvrate2;
        CardView card;
        ImageView ivprofile2;
private Button remove;

        public MyViewHolder(View itemView){
            super(itemView);
            card =  itemView.findViewById(R.id.cardku2);
            ivprofile2 =  itemView.findViewById(R.id.ivprofile2);
            txtNama2 =  itemView.findViewById(R.id.tvname2);
            tvpopularity2=  itemView.findViewById(R.id.tvpopularity2);
            tvdate2 = itemView.findViewById(R.id.tvdate2);
            tvrate2 = itemView.findViewById(R.id.tvrate2);
        }
    }
}
