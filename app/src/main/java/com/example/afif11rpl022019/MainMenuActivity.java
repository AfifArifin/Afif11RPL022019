package com.example.afif11rpl022019;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private long backpress;
    private Toast backToast;
    TextView btnAction, btnAdventure, btnMagic, btnIsekai, btnsport, btnSuperPower, btnMartialArts, btnGame,btnMilitary,btnMecha;

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
        btnAction = findViewById(R.id.btnAction);
        btnAdventure = findViewById(R.id.btnAdventure);
        btnMagic = findViewById(R.id.btnMagic);
        btnIsekai = findViewById(R.id.btnIsekai);
        btnsport = findViewById(R.id.btnSport);
        btnSuperPower = findViewById(R.id.btnSuperPower);
        btnMartialArts = findViewById(R.id.btnMartialArts);
        btnGame = findViewById(R.id.btnGame);
        btnMecha = findViewById(R.id.btnMecha);
        btnMilitary = findViewById(R.id.btnMilitary);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListData.class));
            }
        });
        btnAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MovieFavoriteActivity.class));
            }
        });
        btnMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnSuperPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnIsekai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnMartialArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnMecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnMilitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
