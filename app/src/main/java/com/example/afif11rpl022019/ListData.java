package com.example.afif11rpl022019;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList;
    private ProgressBar pgsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = findViewById(R.id.rvdata);
        pgsBar = findViewById(R.id.pb1);
        pgsBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        addOnlineData();
    }

    public void addOnlineData() {
        DataArrayList = new ArrayList<>();

        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=cffbf8a5f4d5b346c7aaff34e31b9b43&language=en-US&page=1")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        Model modelku;
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setVote_average(jsonObject.getString("vote_average"));
                                modelku.setPopularity(jsonObject.getString("popularity"));
                                modelku.setLang(jsonObject.getString("original_language"));
                                modelku.setId(Integer.parseInt(jsonObject.getString("id")));

                                DataArrayList.add(modelku);
                            }

                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model movie = DataArrayList.get(position);

                                    Intent intent = new Intent(ListData.this, DetailActivity.class);
                                    intent.putExtra("judul", movie.getOriginal_title());
                                    intent.putExtra("gambar", movie.getPoster_path());
                                    intent.putExtra("date", movie.getRelease_date());
                                    intent.putExtra("desc", movie.getOverview());
                                    intent.putExtra("popularity", movie.getPopularity());
                                    intent.putExtra("voteaverage", movie.getVote_average());
                                    intent.putExtra("adult", movie.getAdult());
                                    intent.putExtra("lang", movie.getLang());
                                    intent.putExtra("idfilm", String.valueOf(movie.getId()));

                                    startActivity(intent);
                                }

                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
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
        recyclerView.setVisibility(View.VISIBLE);
    }

}



