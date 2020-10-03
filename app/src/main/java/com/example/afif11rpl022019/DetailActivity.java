package com.example.afif11rpl022019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    MovieModelRealm mahasiswaModel;
    Bundle extras;
    String title, desc, date, path, popularity, voteaverage, lang;
    String title2, date2, path2, popularity2, voteaverage2;
    Boolean adult;


    TextView tvjudul, tvdate, tvadult, tvpopularity, tvrate, tvlang;
    ImageView ivposter, imgback;
    TextView tvdesc;
    Button btnbookmark;
    ShineButton fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fav = findViewById(R.id.fav);
        tvjudul = findViewById(R.id.tvjudul);
        imgback = findViewById(R.id.imgback);
        tvlang = findViewById(R.id.tvlang);
        tvdate = findViewById(R.id.tvdate);
        ivposter = findViewById(R.id.ivposter);
        tvdesc = findViewById(R.id.txtdeskripsi);
        tvadult = findViewById(R.id.tvadultt);
        tvrate = findViewById(R.id.tvratee);
        tvpopularity = findViewById(R.id.tvpopuler);

        fav.setChecked(update("save"));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListData.class));
            }
        });

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

        if (realm != null) {
        }

        fav.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {

                SaveFav("save",checked);

                //Set up Realm
                Realm.init(DetailActivity.this);
                realm = Realm.getDefaultInstance();

                title2 = extras.getString("judul");
                date2 = extras.getString("date");
                path2 = extras.getString("gambar");
                popularity2 = extras.getString("popularity");
                voteaverage2 = extras.getString("voteaverage");

                mahasiswaModel = new MovieModelRealm();
                mahasiswaModel.setRelease_date(date2);
                mahasiswaModel.setPoster_path(path2);
                mahasiswaModel.setOriginal_title(title2);
                mahasiswaModel.setPopularity(popularity2);
                mahasiswaModel.setVote_average(voteaverage2);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(mahasiswaModel);

                Toast.makeText(DetailActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();

                if (!fav.isChecked()){

                    Toast.makeText(DetailActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SaveFav(String key,Boolean value){
        SharedPreferences pref = getSharedPreferences("Save",MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public  boolean update(String key){
        SharedPreferences pref = getSharedPreferences("Save",MODE_PRIVATE);
        return pref.getBoolean(key,false);
    }
}
