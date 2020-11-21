package com.example.afif11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItemFavoriteAdapterOnClick extends AppCompatActivity {

    Bundle extras;
    String title, desc, date, path, popularity, voteaverage, lang;
    Boolean adult;
    double Rating;
    int idfilm;

    String id;
    ProgressDialog progressDialog;
    ArrayList<ModelTrailer> modelTrailer = new ArrayList<>();
    TrailerAdapter trailerAdapter;

    RecyclerView rvTrailer;
    TextView tvjudul, tvdate,tvpopularity,tvRating;
    ImageView imgcover, imgphoto;
    TextView tvdesc;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_favorite_adapter_on_click);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan trailer");

        tvjudul = findViewById(R.id.tvName);
        tvdate = findViewById(R.id.tvRelease);
        imgcover = findViewById(R.id.imgCover);
        imgphoto = findViewById(R.id.imgPhoto);
        tvdesc = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        tvpopularity = findViewById(R.id.tvPopularity);
        rvTrailer = findViewById(R.id.rvTrailer);
        tvRating = findViewById(R.id.tvRating);


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
            id = extras.getString("idfilm");
            Rating = Double.parseDouble(extras.getString("voteaverage"));

            tvjudul.setText(title);
            tvpopularity.setText(popularity);
            tvdate.setText(date);
            tvdesc.setText(desc);
            tvRating.setText(voteaverage + "/10");

            float newValue = (float)Rating;
            ratingBar.setNumStars(5);
            ratingBar.setStepSize((float) 0.5);
            ratingBar.setRating(newValue / 2);


            Glide.with(this)
                    .load(path)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgcover);
            Glide.with(this)
                    .load(path)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgphoto);

            rvTrailer.setHasFixedSize(true);
            rvTrailer.setLayoutManager(new LinearLayoutManager(this));

            getTrailer(id);

        }

    }

    private void getTrailer(String id) {
        progressDialog.show();
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=cffbf8a5f4d5b346c7aaff34e31b9b43&language=en-US")
                .addPathParameter("id", id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            Log.d("getTrailer",response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelTrailer dataApi = new ModelTrailer();
                                dataApi.setKey(jsonObject.getString("key"));
                                dataApi.setType(jsonObject.getString("type"));
                                modelTrailer.add(dataApi);
                                showTrailer();
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(ItemFavoriteAdapterOnClick.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                        Toast.makeText(ItemFavoriteAdapterOnClick.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showTrailer() {
        trailerAdapter = new TrailerAdapter(ItemFavoriteAdapterOnClick.this, modelTrailer);
        rvTrailer.setAdapter(trailerAdapter);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
