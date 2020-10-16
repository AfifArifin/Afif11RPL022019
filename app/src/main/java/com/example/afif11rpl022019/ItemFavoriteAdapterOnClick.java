package com.example.afif11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class ItemFavoriteAdapterOnClick extends AppCompatActivity {

    Bundle extras;
    String title, desc, date, path, popularity, voteaverage, lang;
    Boolean adult;
    
    TextView tvjudul, tvdate, tvadult, tvpopularity, tvrate, tvlang;
    ImageView ivposter;
    TextView tvdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_favorite_adapter_on_click);

        tvjudul = findViewById(R.id.tvjudul2);
        tvlang = findViewById(R.id.tvlang2);
        tvdate = findViewById(R.id.tvdate2);
        ivposter = findViewById(R.id.ivposter2);
        tvdesc = findViewById(R.id.txtdeskripsi2);
        tvadult = findViewById(R.id.tvadultt2);
        tvrate = findViewById(R.id.tvratee2);
        tvpopularity = findViewById(R.id.tvpopuler2);

        extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            path = extras.getString("gambar");
            popularity = extras.getString("popularity");
            voteaverage = extras.getString("voteaverage");
            adult = extras.getBoolean("adult");
            desc = extras.getString("desc");
            lang = extras.getString("lang");

            tvlang.setText(lang);
            tvjudul.setText(title);
            tvrate.setText(voteaverage);
            tvpopularity.setText(popularity);
            tvdate.setText(date);
            tvdesc.setText(desc);
            Glide.with(this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            if (adult == true) {
                tvadult.setText("Yes");
            } else {
                tvadult.setText("No");
            }

        }

    }
}