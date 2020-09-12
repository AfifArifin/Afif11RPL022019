package com.example.afif11rpl022019;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
import java.util.Arrays;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
//        addData();
        addOnlineData();

    }
//
//    void addData() {
//        //offline, isi data offline dulu
//        DataArrayList = new ArrayList<>();
//        Model data1 = new Model();
//        data1.setOriginal_title("Judul Film");
//        data1.setPoster_path("https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
//        data1.setAdult(false);
//        data1.setOverview("Deskripsi Film disini");
//        data1.setVote_count(100);
//        data1.setRelease_date("01-01-2020");
//        DataArrayList.add(data1);
//        Model data2 = new Model();
//        data2.setOriginal_title("Judul Film");
//        data2.setPoster_path("https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
//        data2.setAdult(false);
//        data2.setOverview("Deskripsi Film disini");
//        data2.setVote_count(100);
//        data2.setRelease_date("01-01-2020");
//        DataArrayList.add(data2);


//        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
//            @Override
//            public void onClick(int position) {
//
//            }
//
//            @Override
//            public void test() {
//
//            }
//        });
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//        //get data online
//
//
//    }


    private void addOnlineData() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=cffbf8a5f4d5b346c7aaff34e31b9b43&language=en-US&page=1")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini

                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setVote_average(jsonObject.getString("vote_count"));
                                modelku.setPopularity(jsonObject.getString("popularity"));
//                                String Allgenre[] = {"Action","Adventure","Animation","Comedy","Crime","Documentary","Drama","Family","Fantasy","History","Horror","Music","Mystery","Romance","Science Fiction","TV Movie","Thriller","War","Western"};
//                                String genre = jsonObject.getString("genre_ids");
//
//
////                                switch (genre){
////                                    case "[28]":
////                                        genre = Allgenre[0];
////                                }
//                                modelku.setGenres(genre);
                                DataArrayList.add(modelku);
                            }

                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {

                                }

                                @Override
                                public void test() {

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
    }


}
