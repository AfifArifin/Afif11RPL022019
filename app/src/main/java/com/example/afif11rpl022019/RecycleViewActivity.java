package com.example.afif11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        ArrayList<ItemActivity> exampleList = new ArrayList<>();
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 1","Line 2"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 3","Line 4"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 5","Line 6"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 7","Line 8"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 9","Line 10"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 11","Line 12"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 13","Line 14"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 15","Line 16"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 17","Line 18"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 19","Line 20"));
        exampleList.add(new ItemActivity(R.drawable.profile_account,"Line 21","Line 22"));

        mRecycleView = findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(exampleList);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}
