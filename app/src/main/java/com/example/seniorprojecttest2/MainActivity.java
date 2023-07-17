package com.example.seniorprojecttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set the the Screen As A full Screen since its a Splash Screen Page
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Create a Handler to Handle a delay for Moving from Splash Screen To Login Activity. with 2.5 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this,LoginPage.class);
            startActivity(intent);
            finish();
        },2500);
    }
}