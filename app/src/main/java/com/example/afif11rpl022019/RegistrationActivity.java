package com.example.afif11rpl022019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private long backpress;
    private Toast backToast;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText txtuser, txtpass;
    Button btnMasuk, btnBack;

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
        setContentView(R.layout.activity_registration);
        btnMasuk = findViewById(R.id.btnmsk);
        btnBack = findViewById(R.id.btnBack);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        txtuser = findViewById(R.id.txtusername);
        txtpass = findViewById(R.id.txtpassword);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = pref.edit();
                editor.putString("username", txtuser.getText().toString());
                editor.putString("password", txtpass.getText().toString());
                editor.apply();
                if (txtuser.getText().toString().isEmpty() || txtpass.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "data belum diisi", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences getName = getSharedPreferences("login", MODE_PRIVATE);
                    String Name = getName.getString("username", "");
                    Intent in = new Intent(RegistrationActivity.this, MainMenuActivity.class);
                    Toast.makeText(RegistrationActivity.this, "Welcome " + Name, Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
