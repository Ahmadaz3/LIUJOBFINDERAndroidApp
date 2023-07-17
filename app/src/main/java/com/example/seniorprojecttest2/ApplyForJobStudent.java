package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
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

public class ApplyForJobStudent extends AppCompatActivity {
    private TextView tvNameAndLoc,tvJtyepandWtype,jobdesc,jobReq;
    private Button applybtn;
    private JobData2 userModel;
    private RecommendData recommendData;
  //  private JobData userModel;
    Intent intent ;
    Intent intent2;
  public String id;
  public String std_id;
  public String url;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_job_student);
        tvNameAndLoc = findViewById(R.id.txtJNameAndJLoc);
        tvJtyepandWtype = findViewById(R.id.txtTypeandWorkType);
        jobdesc = findViewById(R.id.Jdescview);
        jobReq = findViewById(R.id.jReqview);
        applybtn = findViewById(R.id.ApplyForJobBtn);
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Apply For Job";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        intent = getIntent();
        intent2 = getIntent();
        if (intent2 != null && intent2.hasExtra("newData")) {
            recommendData = (RecommendData) intent2.getSerializableExtra("newData");
            if (recommendData != null) {
                tvNameAndLoc.setText(recommendData.getJob_Name().trim() + "-" + recommendData.getJob_Location().trim());
                tvJtyepandWtype.setText(recommendData.getJob_type().trim() + "-" + recommendData.getWorkPlace_Type().trim());
                jobdesc.setText(recommendData.getJob_Desc().trim());
                jobReq.setText(recommendData.getJob_Req().trim());
                std_id = intent2.getStringExtra("MyStudentId");
              //  Toast.makeText(this,"Your rec id : " +std_id, Toast.LENGTH_SHORT).show();
            }
        } else if (intent != null && intent.hasExtra("data")) {
            userModel = (JobData2) intent.getSerializableExtra("data");
            if (userModel != null) {
                tvNameAndLoc.setText(userModel.getJobname() + "-" + userModel.getJobLoc());
                tvJtyepandWtype.setText(userModel.getJob_Type() + "-" + userModel.getWorkPlace_Type());
                jobdesc.setText(userModel.getJob_desc().trim());
                jobReq.setText(userModel.getJob_req().trim());
                id = intent.getStringExtra("YourStudentId");
            //    Toast.makeText(this, "Your search id: "+id, Toast.LENGTH_SHORT).show();
            }
        }

      queue = Volley.newRequestQueue(this);
        url ="https://seniorprojectahmadazimeh.000webhostapp.com/ApplyforJobStudent.php";

        /*
        <?php
                $std_id = addslashes(strip_tags($_POST['stdId']));
        $job_id = addslashes(strip_tags($_POST['jobId']));

        $con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");

          // Check connection
         if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: ". mysqli_connect_error();
        }

        // Check if the user has already applied for the job
        $sql_check = "SELECT * FROM Applied_job WHERE std_id = '$std_id' AND job_id = '$job_id'";
        $result_check = mysqli_query($con, $sql_check);
        if(mysqli_num_rows($result_check) > 0) {
            echo "true";
        } else {
            $sql_insert = "INSERT INTO Applied_job (std_id, job_id) VALUES ('$std_id', '$job_id')";
            mysqli_query($con, $sql_insert) or die("Can't add record to the AppliedJob table: ".mysqli_error($con));
            echo "Applied success";
        }

        mysqli_close($con);
          ?>
         */
        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSucessDialog();
            }

        });
    }
    private void showSucessDialog(){
        ConstraintLayout sucessConistraintLayout = findViewById(R.id.sucessConistraintLayout);
        View view = LayoutInflater.from(ApplyForJobStudent.this).inflate(R.layout.sucessdialog,sucessConistraintLayout);
        Button sucessDone = view.findViewById(R.id.sucessDone);
        Button sucessDone2 = view.findViewById(R.id.sucessDone2);
        AlertDialog.Builder builder= new AlertDialog.Builder(ApplyForJobStudent.this);
        builder.setView(view);
        final AlertDialog alretDialog = builder.create();
        sucessDone2.findViewById(R.id.sucessDone2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alretDialog.dismiss();
                Toast.makeText(ApplyForJobStudent.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
        sucessDone.findViewById(R.id.sucessDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                applybtn.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("true")){
                            Toast.makeText(ApplyForJobStudent.this, "You are Already applied for this job and waiting for job owner response", Toast.LENGTH_LONG).show();
                        }
                        applybtn.setEnabled(false);
                        applybtn.setText("Already Applied");
                        alretDialog.dismiss();
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApplyForJobStudent.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        if(intent != null && intent.hasExtra("data") ){
                            params.put("stdId",id);
                            params.put("jobId",userModel.getJobId());
                        }else if(intent2 != null && intent2.hasExtra("newData")){
                            params.put("stdId",std_id);
                            params.put("jobId",recommendData.getJob_id());
                        }
                        return params;
                    }
                };
                queue.add(request);


        }
        });
        if (alretDialog.getWindow() != null) {
            alretDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alretDialog.show();
    }
}