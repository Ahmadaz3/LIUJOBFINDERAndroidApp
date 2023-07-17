package com.example.seniorprojecttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class PreSignUp extends AppCompatActivity {
    //Declare 3 Buttons for the 3 users should SignUp for Application
     private Button btStd,btBus,insButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize The Buttons
        btStd = findViewById(R.id.StdBtn);
        btBus = findViewById(R.id.busbtn);
        insButton = findViewById(R.id.InsBtn);


        // if The Student Click His Button The Application will Move Him to StudentActivity That's his SignUpPage....
        btStd.setOnClickListener(view -> {
          Intent intent = new Intent(PreSignUp.this,StudentActivity.class);
          startActivity(intent);
        });


        //if The Business Owner  Click His Button The Application will Move Him to BusinessActivity That's his SignUpPage....
        btBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreSignUp.this,BusinessActivity.class);
                startActivity(intent);
            }
        });

        //if The Instructor Click His Button The Application will Move Him to InstructorActivity That's his SignUpPage....
        insButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreSignUp.this,InstructorActivity.class);
                startActivity(intent);
            }
        });
    }
}