package com.example.seniorprojecttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class BusinessHome extends AppCompatActivity {
    ViewPager2 viewPager2;
    BusinessViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    public static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        id = getIntent().getIntExtra("id", 0);
        bottomNavigationView = findViewById(R.id.BottomNav);
        viewPager2 = findViewById(R.id.viewPager);
        viewPagerAdapter = new BusinessViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case  R.id.posted_jobs:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.Add_Job:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.appliedToYourJob:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.posted_jobs).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.Add_Job).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.appliedToYourJob).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        // Get the root view of the activity
        View rootView = getWindow().getDecorView().getRootView();

        // Set up a listener to detect changes in the layout
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Determine the height difference between the root view and the visible window
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);

                // If the height difference is greater than a certain threshold, assume the keyboard is showing
                int threshold = 200;
                if (heightDiff > threshold) {
                    // Hide the bottom navigation
                    View bottomNavigation = findViewById(R.id.BottomNav);
                    bottomNavigation.setVisibility(View.GONE);
                } else {
                    // Show the bottom navigation
                    View bottomNavigation = findViewById(R.id.BottomNav);
                    bottomNavigation.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}