package com.example.afif11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieFavoriteActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    ItemFavoriteAdapter mahasiswaAdapter;
    List<MovieModelRealm> mahasiswaModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_favorite);

        recyclerView = findViewById(R.id.recyclerView2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getDefaultInstance();

        realmHelper = new RealmHelper(realm);
        mahasiswaModels = new ArrayList<>();

        mahasiswaModels = realmHelper.getAllMahasiswa();

        show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mahasiswaAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        mahasiswaAdapter = new ItemFavoriteAdapter(this, mahasiswaModels);
        recyclerView.setAdapter(mahasiswaAdapter);
    }
}