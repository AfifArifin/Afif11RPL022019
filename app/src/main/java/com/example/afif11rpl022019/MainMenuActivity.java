package com.example.afif11rpl022019;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainMenuActivity extends AppCompatActivity {

    private long backpress;
    private Toast backToast;
    private CardView cvfav,cvmovie;

    @Override
    public void onBackPressed() {
        if (backpress + 3000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backpress = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        cvfav = findViewById(R.id.cvfav);
        cvmovie = findViewById(R.id.cvmovie);

        cvmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListData.class));
            }
        });
        cvfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MovieFavoriteActivity.class));
            }
        });

    }


}
