package com.example.afif11rpl022019;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.sackcentury.shinebuttonlib.ShineButton;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    Model mahasiswaModel;
    Bundle extras;
    String title, desc, date, path, popularity, voteaverage, lang;
    Boolean adult;
    int idfilm;

    TextView tvjudul, tvdate, tvadult, tvpopularity, tvrate, tvlang;
    ImageView ivposter;
    TextView tvdesc;
    ShineButton fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fav = findViewById(R.id.fav);
        tvjudul = findViewById(R.id.tvjudul);
        tvlang = findViewById(R.id.tvlang);
        tvdate = findViewById(R.id.tvdate);
        ivposter = findViewById(R.id.ivposter);
        tvdesc = findViewById(R.id.txtdeskripsi);
        tvadult = findViewById(R.id.tvadultt);
        tvrate = findViewById(R.id.tvratee);
        tvpopularity = findViewById(R.id.tvpopuler);

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
            idfilm = Integer.parseInt(extras.getString("idfilm"));

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

        fav.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {

                //Set up Realm
                Realm.init(DetailActivity.this);
                RealmConfiguration configuration = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(configuration);

                mahasiswaModel = new Model();
                mahasiswaModel.setAdult(adult);
                mahasiswaModel.setOverview(desc);
                mahasiswaModel.setLang(lang);
                mahasiswaModel.setRelease_date(date);
                mahasiswaModel.setPoster_path(path);
                mahasiswaModel.setOriginal_title(title);
                mahasiswaModel.setPopularity(popularity);
                mahasiswaModel.setVote_average(voteaverage);
                mahasiswaModel.setId(idfilm);

                realmHelper = new RealmHelper(realm);

                if (checked == true){
                    realmHelper.save(mahasiswaModel);
                    Toast.makeText(DetailActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }else{
                    realmHelper.delete(idfilm);
                    Toast.makeText(DetailActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
