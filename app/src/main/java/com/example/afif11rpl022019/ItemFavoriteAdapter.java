package com.example.afif11rpl022019;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItemFavoriteAdapter extends RecyclerView.Adapter<ItemFavoriteAdapter.MyViewHolder> {

    private List<Model> mahasiswaModels;
    Realm realm;
    RealmHelper realmHelper;
    Context context;

    public ItemFavoriteAdapter(Context context, List<Model> mahasiswaModels) {
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @Override
    public ItemFavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_favorite, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemFavoriteAdapter.MyViewHolder holder, int position) {
        final Model model = mahasiswaModels.get(position);

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

        holder.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.init(context);
                RealmConfiguration configuration = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(configuration);
                realmHelper = new RealmHelper(realm);

                final int id = model.getId();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure ?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                        realmHelper.delete(id);
                        ItemFavoriteAdapter.this.notifyItemRemoved(id);
                        ItemFavoriteAdapter.this.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemFavoriteAdapterOnClick.class);
                intent.putExtra("judul", model.getOriginal_title());
                intent.putExtra("gambar", model.getPoster_path());
                intent.putExtra("date", model.getRelease_date());
                intent.putExtra("desc", model.getOverview());
                intent.putExtra("popularity", model.getPopularity());
                intent.putExtra("voteaverage", model.getVote_average());
                intent.putExtra("adult", model.getAdult());
                intent.putExtra("lang", model.getLang());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama2, tvdate2, tvpopularity2, tvrate2;
        CardView card;
        ImageView ivprofile2, btnremove;

        public MyViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardku2);
            btnremove = itemView.findViewById(R.id.btnremove);
            ivprofile2 = itemView.findViewById(R.id.ivprofile2);
            txtNama2 = itemView.findViewById(R.id.tvname2);
            tvpopularity2 = itemView.findViewById(R.id.tvpopularity2);
            tvdate2 = itemView.findViewById(R.id.tvdate2);
            tvrate2 = itemView.findViewById(R.id.tvrate2);

        }

    }

}
