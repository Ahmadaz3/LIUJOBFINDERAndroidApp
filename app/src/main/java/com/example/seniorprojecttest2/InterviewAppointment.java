package com.example.seniorprojecttest2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class InterviewAppointment extends AppCompatActivity {
    private  StudentData1 studentData1;
    private TextView JobName;
    private Button confirm,checkstudentProfile,interviewDone;
    private EditText interLoc,interDate;
    private TextView t1,t2,t3,interstatus;
    String url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_appointment);

        // Get reference to the ActionBar
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Interview Appointment";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }


        JobName=  findViewById(R.id.interJobName);
        confirm = findViewById(R.id.btnConfirmInterview);
        interLoc = findViewById(R.id.edtInterviewLocation);
        interDate = findViewById(R.id.edtDateAndTime);
        interstatus = findViewById(R.id.interViewStatus);
        interviewDone = findViewById(R.id.btnIbterviewDone);
        interviewDone.setVisibility(View.GONE);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        checkstudentProfile=findViewById(R.id.btncheckStudentProfile);
        Intent intent = getIntent();
        int buss_id = intent.getIntExtra("bsId",0);
        String business_id=  String.valueOf(buss_id);
       // Toast.makeText(this, business_id, Toast.LENGTH_SHORT).show();
        studentData1 = (StudentData1)intent.getSerializableExtra("data");
        String stdName = studentData1.getF_name();
        String jobName = studentData1.getJobName();
        JobName.setText(jobName);
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/SetInterview.php";

        // url2="https://seniorprojectahmadazimeh.000webhostapp.com/getJobForStudents2.php?std_major=" + major + "&std_id=" + stdId;
        url2 = "https://seniorprojectahmadazimeh.000webhostapp.com/getInterviewStatus.php?std_id="+studentData1.getStudent_id()+"&job_id="+studentData1.getJob_id();
        getStudentData();
        checkstudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InterviewAppointment.this,StudentJobProfile.class);
                intent.putExtra("std_id",studentData1.getStudent_id());
                startActivity(intent);
            }
        });
        confirm.setOnClickListener(view -> {

            if(interLoc.getText().toString().equals("") || interDate.getText().toString().equals("")){
                Toast.makeText(InterviewAppointment.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
              }
            else{
                confirm.setEnabled(false);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(InterviewAppointment.this,"Interview set successfully", Toast.LENGTH_LONG).show();
                        confirm.setText("Already Confirmed");
                        // The button will be Enabled after the response accrued
                       // interviewStatus.setInterview="true";
                        interLoc.setText("");
                        interDate.setText("");
                       // confirm.setEnabled(true);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InterviewAppointment.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("student_id", studentData1.getStudent_id());
                        params.put("job_id",studentData1.getJob_id());
                        params.put("business_id", business_id);
                        params.put("Interview_location",interLoc.getText().toString());
                        params.put("interview_DateAndTime",interDate.getText().toString());
                     //   params.put("interview_status", "Pending");
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        String urlDone = "https://seniorprojectahmadazimeh.000webhostapp.com/DoneInterview.php";
        interviewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,urlDone,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response from the server
                                // Display success or error message
                                Toast.makeText(InterviewAppointment.this,"Interview Marked as Done", Toast.LENGTH_SHORT).show();
                                interviewDone.setEnabled(false);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                System.out.println("Error deleting interview appointment: " + error.getMessage());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Pass the std_id and job_id as parameters to the PHP script
                        Map<String, String> params = new HashMap<>();
                        params.put("std_id", studentData1.getStudent_id());
                        params.put("job_id", studentData1.getJob_id());
                        return params;
                    }
                };

                // Add the request to the queue
                queue.add(stringRequest);
            }

        });
    }
    public void getStudentData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                response -> {
                    if(response.equals(null)){
                        Toast.makeText(this, "still not set", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                String interviewStatus = row.getString("interview_status");
                             //   Toast.makeText(this, "status: " + interviewStatus, Toast.LENGTH_LONG).show();
                                JobName.setVisibility(View.GONE);
                                confirm.setVisibility(View.GONE);
                                interLoc.setVisibility(View.GONE);
                                interDate.setVisibility(View.GONE);
                                t1.setVisibility(View.GONE);
                                t2.setVisibility(View.GONE);
                                t3.setVisibility(View.GONE);
                                checkstudentProfile.setVisibility((View.GONE));
                                interstatus.setText("The interview status is : "+interviewStatus);
                                interviewDone.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}