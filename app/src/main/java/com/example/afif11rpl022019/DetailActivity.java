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
    String title,desc,date,path,popularity,votecount;
    Boolean adult;
    ArrayList<MyGenre>genreku;


    TextView tvjudul,tvdate,tvadult,tvpopularity,tvrate,tvgenre;
    ImageView ivposter,imgback;
    TextView tvdesc;
    Button btnbookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
getGenreku();

        tvjudul = findViewById(R.id.tvjudul);
        imgback = findViewById(R.id.imgback);
        tvgenre = findViewById(R.id.tvgnre);
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


    public void getGenreku() {
        genreku = new ArrayList<>();

        AndroidNetworking.get("https://api.themoviedb.org/3/genre/movie/list?api_key=cffbf8a5f4d5b346c7aaff34e31b9b43&language=en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());

                        //jika sudah berhasil debugm lanjutkan code dibawah ini

                        MyGenre modelku;
                        try {
                            JSONArray jsonArray= response.getJSONArray("genres");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                modelku = new MyGenre();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setGenre(jsonObject.getString("name"));

                                genreku.add(modelku);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });

    }




}