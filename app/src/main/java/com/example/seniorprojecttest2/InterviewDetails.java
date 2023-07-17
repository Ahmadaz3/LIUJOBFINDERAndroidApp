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

public class InterviewDetails extends AppCompatActivity {
    private InterviewData interviewData;
    private TextView interviewFromAndAt;
    private TextView InterViewLocAndTime;

    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frame_color)));
        getSupportActionBar().setTitle("InterView Details");
        interviewFromAndAt = findViewById(R.id.InterviewFromAt);
        InterViewLocAndTime = findViewById(R.id.InterViewLocAndTime);
        btnUpdate = findViewById(R.id.btnConfrimInterview);
        Intent intent = getIntent();
        if(intent != null) {
            interviewData = (InterviewData) intent.getSerializableExtra("data");
          //  Toast.makeText(this,interviewData.getJobName(), Toast.LENGTH_SHORT).show();
            interviewFromAndAt.setText("Interview From: "+interviewData.getBusinessName()+"\n\n" +
                    "" +
                                        "Interview For Job: "+interviewData.getJobName());
            InterViewLocAndTime.setText("Interview Location:\n\n"+interviewData.getInterviewLoc()+"\n\n" +
                                         "Interview Date And Time:\n\n"+interviewData.getInterviewDateAndTime());
            if(interviewData.getInterViewStatus().equals("confirmed")){
                btnUpdate.setEnabled(false);
                btnUpdate.setText("Already Confirmed");
            }
            final RequestQueue queue = Volley.newRequestQueue(this);
            String Updateurl ="https://seniorprojectahmadazimeh.000webhostapp.com/UpdateInterviewStatus.php";
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // setEnabled as false since the user cannot send manyRequest at the same time before responses
                    btnUpdate.setEnabled(false);
                    // create a StringRequest
                    StringRequest request = new StringRequest(Request.Method.POST,Updateurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // on responses all fields will be empty and A Toast Your request added will appear
                            Toast.makeText(InterviewDetails.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(InterviewDetails.this, "Post Already Approved and Posted\n" +
                                    " You can not Edit it.", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            // create an Object of hashMap called params to send the data we get it
                            // from the Edits texts and insert it as there Keys to the database
                            Map<String, String> params = new HashMap<>();
                            params.put("std_id",interviewData.getStd_id());
                            return params;
                        }
                    };
                    queue.add(request);

                }
            });
        }
    }
}