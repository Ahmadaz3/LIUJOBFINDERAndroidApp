package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RecommendJob extends AppCompatActivity {
    private JobData jobdata;
    private TextView tvNameAndLoc,tvJtyepandWtype,jobdesc,jobReq;
    private Button btnRecommend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_job);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frame_color)));
        getSupportActionBar().setTitle("Recommend Job");
        tvNameAndLoc = findViewById(R.id.JNameAndJLoc);
        tvJtyepandWtype = findViewById(R.id.TypeandWorkType);
        jobdesc = findViewById(R.id.descview);
        jobReq = findViewById(R.id.Reqview);
        btnRecommend = findViewById(R.id.btnRecommend);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ins_id");
        final RequestQueue queue = Volley.newRequestQueue(this);
        if(intent != null) {
            jobdata = (JobData) intent.getSerializableExtra("data");
            tvNameAndLoc.setText(jobdata.getJobname()+"\n"+jobdata.getJobLoc());
            tvJtyepandWtype.setText(jobdata.getJob_Type()+"-"+jobdata.getWorkPlace_Type()+"\n");
            jobdesc.setText(jobdata.getJob_desc());
            jobReq.setText(jobdata.getJob_req());
        }
        String Updateurl ="https://seniorprojectahmadazimeh.000webhostapp.com/insertRecommendation.php";
      //  Toast.makeText(this, "Your id is "+id, Toast.LENGTH_SHORT).show();
        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                btnRecommend.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST,Updateurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btnRecommend.setEnabled(false);
                        btnRecommend.setText("Recommend Recorded");
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(RecommendJob.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecommendJob.this, "Post Already Approved and Posted\n" +
                                " You can not Edit it.", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("job_id",jobdata.getJobId());
                        params.put("Ins_id",id);
                        return params;
                    }
                };
                queue.add(request);
            }
        });
    }
}