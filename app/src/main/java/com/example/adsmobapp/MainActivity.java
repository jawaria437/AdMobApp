package com.example.adsmobapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnScreen2, btnScreen3, btnScreen4, btnScreen5, btnLogout;
    private AdView adViewMain;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Views
        tvWelcome = findViewById(R.id.welcomeText);
        btnScreen2 = findViewById(R.id.btnScreen2);
        btnScreen3 = findViewById(R.id.btnScreen3);
        btnScreen4 = findViewById(R.id.btnScreen4);
        btnScreen5 = findViewById(R.id.btnScreen5);
        btnLogout = findViewById(R.id.btnLogout);
        adViewMain = findViewById(R.id.adViewMain);

        // Set welcome text with user email
        if (mAuth.getCurrentUser() != null) {
            tvWelcome.setText("Welcome " + mAuth.getCurrentUser().getEmail());
        }

        // Load Banner Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewMain.loadAd(adRequest);

        // Navigate to Screen 2
        btnScreen2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Screen_2.class));
        });

        // Navigate to Screen 3
        btnScreen3.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Screen_3.class));
        });

        // Navigate to Screen 4
        btnScreen4.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Screen_4.class));
        });

        // Navigate to Screen 5
        btnScreen5.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Screen_5.class));
        });

        // Logout Button
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        });
    }
}
