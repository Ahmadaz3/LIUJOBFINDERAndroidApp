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
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentInterViews extends AppCompatActivity implements InterviewAdapter.UserClickListeners {
    String id;
    String url;
    RecyclerView rvInterView;
    InterviewAdapter interviewAdapter;
    List<InterviewData>  interviewData = new ArrayList<>();
    LottieAnimationView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_inter_views);
        lv = findViewById(R.id.waitingforinterview);
      //  getSupportActionBar().setTitle("Your Interviews");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frame_color)));
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(R.color.white));
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Your Interviews";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        rvInterView = findViewById(R.id.stdInter);
        Intent intent = getIntent();
        id = intent.getStringExtra("std_id");
       // Toast.makeText(this,"Your id is : "+ id, Toast.LENGTH_SHORT).show();
        interviewData = new ArrayList<>();
        url ="https://seniorprojectahmadazimeh.000webhostapp.com/getInterViewDetalisByStudentID.php?student_id="+id;
        getStudentData();
        prepareRecyclerView();
    }
    public void getStudentData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    interviewData.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String Student_id = row.getString("std_id");
                            String Job_id = row.getString("job_id");
                            String Business_id = row.getString("bus_id");
                            String Interview_location = row.getString("Interview_location");
                            String interview_DateAndTime = row.getString("interview_DateAndTime");
                            String interview_status = row.getString("interview_status");
                            String job_name = row.getString("job_name");
                            String Business_name = row.getString("business_name");
                            interviewData.add(new InterviewData(Student_id, Job_id, Business_id, Interview_location, interview_DateAndTime, interview_status,job_name,Business_name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                   // Log.d("InterviewData", interviewData.toString());
                    interviewAdapter.notifyDataSetChanged();
                    if(interviewData.isEmpty()){
                        lv.setVisibility(View.VISIBLE);
                    }else{
                        lv.setVisibility(View.GONE);
                    }
                    // Display the first student's first name in the TextView
                    // if (!stdData.isEmpty()) {
                    //  tv.setText(stdData.get(0).getJobName());
                    // }else{
                    //     Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
                    //  }
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void prepareRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvInterView.setLayoutManager(linearLayoutManager);
        prepareAdapter();
    }

    public void prepareAdapter(){
        interviewAdapter = new InterviewAdapter(interviewData,this,this::selectUser);
        rvInterView.setAdapter(interviewAdapter);
    }
    @Override
    public void selectUser(InterviewData interviewData) {
        Intent intent = new Intent(StudentInterViews.this,InterviewDetails.class);
        intent.putExtra("data",interviewData);
        startActivity(intent);

    }
    @Override
    public void onResume() {
        super.onResume();
        getStudentData();
    }
}