package com.example.seniorprojecttest2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Resources extends AppCompatActivity {
    private ArrayList<ResourcesData> resData = new ArrayList<>();
    private RequestQueue requestQueue;
    ResourcesAdapter resourcesAdapter;
    RecyclerView recyclerView;
    public  String url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        String major = intent.getStringExtra("major");
        //Toast.makeText(this,major, Toast.LENGTH_SHORT).show();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = major+" Resources";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.ResourcesRecyler);
        url2 ="https://seniorprojectahmadazimeh.000webhostapp.com/getResourcesForStudents.php?major="+major;
        getData();
    }
    public void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                response -> {
                    // Loop through the JSON array and add the names to the ArrayList
                    if (resData.size() >= 0) {
                        resData.clear();
                    }
                    resData = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String resName = row.getString("Resource_name");
                            String resContant = row.getString("res_content");
                            //  Log.d("RecommendJobs", "Job Name: " + job_Name);
                            resData.add(new ResourcesData(resName,resContant));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // Call the RecyclerView setup and adapter preparation methods
                    prepareRecyclerView();
                    prepareAdapter();
                },
                error -> {
                    // Handle error case
                });
        requestQueue.add(jsonArrayRequest);
    }
    public void prepareRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // prepareAdapter();
    }

    public void prepareAdapter(){
        resourcesAdapter = new ResourcesAdapter(resData,this);
        recyclerView.setAdapter(resourcesAdapter);
    }
}