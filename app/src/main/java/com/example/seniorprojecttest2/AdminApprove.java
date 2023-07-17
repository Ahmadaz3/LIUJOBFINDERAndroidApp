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

public class AdminApprove extends AppCompatActivity {
    private Button Dpost,Dbusiness,Baprrove,Bdecline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approve);
        // get the Job id so we Keep Tracking with the Id of the User from the Posts RequestClass
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.white));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "follow the request";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        Intent intent = getIntent();
        String job_Id = intent.getStringExtra("job_id");
        String jobName = intent.getStringExtra("job_name");
        String jobLoc = intent.getStringExtra("job_location");
        String jobType = intent.getStringExtra("jobType");
        String jobWPlaceType = intent.getStringExtra("jobWorkPlaceType");
        String JobReqier = intent.getStringExtra("job_requirements");
        String JobDesc = intent.getStringExtra("job_description");
        String buss_id = intent.getStringExtra("buss_id");

      //  Toast.makeText(this, "Your job id is:"+job_Id, Toast.LENGTH_SHORT).show();


        Baprrove = findViewById(R.id.ApprovePost);
        Bdecline = findViewById(R.id.RejectPost);
        Dpost = findViewById(R.id.PostDetalisbtn);
        Dbusiness = findViewById(R.id.BusinessDetalisbtn);

        final RequestQueue queue = Volley.newRequestQueue(this);


        String ApproveUrl="https://seniorprojectahmadazimeh.000webhostapp.com/UpdateStatusToApproved.php";
        /*
        <?php
                $job_id = addslashes(strip_tags($_POST['job_id']));
        $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
        // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        $sql = "UPDATE Job SET status='Accepted' WHERE job_id='$job_id'";

        if (mysqli_query($con, $sql)) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . mysqli_error($con);
        }

        mysqli_close($con);
          ?>
          */
        String DeclineUrl="https://seniorprojectahmadazimeh.000webhostapp.com/UpdateStatusToDecline.php";
        /*
        <?php
                $job_id = addslashes(strip_tags($_POST['job_id']));
        $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
         // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        $sql = "UPDATE Job SET status='Rejected' WHERE job_id='$job_id'";

        if (mysqli_query($con, $sql)) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . mysqli_error($con);
        }

        mysqli_close($con);
         ?>
         */
        // get the Job id from the PostRequests Class
        Baprrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                Baprrove.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST,ApproveUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(AdminApprove.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //  Intent i = new Intent(BusinessEditDeleteJob.this,BusinessPostsFragment.class);
                        // BusinessPostsFragment ps = new BusinessPostsFragment();
                        // ps.getData();
                        //   startActivity(i);

                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminApprove.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("job_id",job_Id);
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        Bdecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                Bdecline.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST,DeclineUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(AdminApprove.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //  Intent i = new Intent(BusinessEditDeleteJob.this,BusinessPostsFragment.class);
                        // BusinessPostsFragment ps = new BusinessPostsFragment();
                        // ps.getData();
                        //   startActivity(i);

                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminApprove.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("job_id",job_Id);
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        Dpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(AdminApprove.this,JobDetalis.class);
              intent.putExtra("Jname",jobName);
              intent.putExtra("jLoc",jobLoc);
              intent.putExtra("jtype",jobType);
              intent.putExtra("jworkType",jobWPlaceType);
              intent.putExtra("jReq",JobReqier);
              intent.putExtra("jDesc",JobDesc);
              startActivity(intent);
            }
        });
        Dbusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(AdminApprove.this,BusinessDetails.class);
            intent.putExtra("business_id",buss_id);
            startActivity(intent);
            }
        });
    }
}