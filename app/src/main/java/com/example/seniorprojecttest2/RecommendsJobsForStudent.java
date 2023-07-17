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
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendsJobsForStudent extends AppCompatActivity implements RecommendationAdapter.UserClickListeners {
   public String url2;
    private ArrayList<RecommendData> recommendData = new ArrayList<>();
    private ArrayList<RecommendData> recommendData1 = new ArrayList<>();
    private RequestQueue requestQueue;
    RecommendationAdapter recommendationAdapter;
    RecyclerView recyclerView;
     public String student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommends_jobs_for_student);
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Recommends Jobs";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");
        //Toast.makeText(this, student_id, Toast.LENGTH_SHORT).show();
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.RecoJobs);
        url2 = "https://seniorprojectahmadazimeh.000webhostapp.com/getRecommendedJobs.php?std_major=" + MainActivityStudent.major + "&std_id=" + student_id;
        getData();
    }
        public void getData() {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url2,
                    null,
                    response -> {
                        // Loop through the JSON array and add the names to the ArrayList
                        if (recommendData.size() >= 0) {
                            recommendData.clear();
                        }
                        recommendData = new ArrayList<>();
                        // new data to sort in updated sorting jobs recommended
                        recommendData1 = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                String job_id = row.getString("job_id");
                                String job_Name = row.getString("job_name");
                                String job_location = row.getString("job_location");
                                String job_type = row.getString("job_type");
                                String workplace_type = row.getString("workplace_type");
                                String job_requirements = row.getString("job_requirements");
                                String job_description = row.getString("job_description");
                                String Ins_name = row.getString("Instructor_Name");
                                Log.d("RecommendJobs", "Job Name: " + job_Name);
                                recommendData.add(new RecommendData(job_id, job_Name, job_location, job_type, workplace_type, job_requirements, job_description, Ins_name));
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
        recommendData1.clear();
        for (int i = recommendData.size() - 1; i >= 0; i--) {
            recommendData1.add(recommendData.get(i));
        }
        recommendationAdapter = new RecommendationAdapter(recommendData1,this,this::selectUser);
        recyclerView.setAdapter(recommendationAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    @Override
    public void selectUser(RecommendData recData) {
        Intent intent = new Intent(RecommendsJobsForStudent.this,ApplyForJobStudent.class);
        intent.putExtra("MyStudentId",student_id);
        intent.putExtra("newData",recData);
       // Toast.makeText(this, student_id, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
