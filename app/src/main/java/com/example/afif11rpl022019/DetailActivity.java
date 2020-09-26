package com.example.afif11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    Bundle extras;
    String title,desc,date,path,popularity,votecount,lang;
    Boolean adult;


    TextView tvjudul,tvdate,tvadult,tvpopularity,tvrate,tvlang;
    ImageView ivposter,imgback;
    TextView tvdesc;
    Button btnbookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvjudul = findViewById(R.id.tvjudul);
        imgback = findViewById(R.id.imgback);
        tvlang = findViewById(R.id.tvlang);
        tvdate = findViewById(R.id.tvdate);
        ivposter = findViewById(R.id.ivposter);
        tvdesc = findViewById(R.id.txtdeskripsi);
        tvadult = findViewById(R.id.tvadultt);
        tvrate = findViewById(R.id.tvratee);
        tvpopularity = findViewById(R.id.tvpopuler);

imgback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),ListData.class));
    }
});

        extras = getIntent().getExtras();
        if(extras != null){
            title = extras.getString("judul");
            date = extras.getString("date");
            path = extras.getString("gambar");
            popularity = extras.getString("popularity");
            votecount = extras.getString("votecount");
            adult = extras.getBoolean("adult");
            desc = extras.getString("desc");
            lang = extras.getString("lang");


            tvlang.setText(lang);
            tvjudul.setText(title);
            tvrate.setText(votecount);
            tvpopularity.setText(popularity);
            tvdate.setText(date);
            tvdesc.setText(desc);
            Glide.with(this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            if (adult == true){
                tvadult.setText("Yes");
            }else{
                tvadult.setText("No");
            }

        }
    }

}