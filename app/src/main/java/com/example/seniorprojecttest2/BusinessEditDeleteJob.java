package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.Objects;

public class BusinessEditDeleteJob extends AppCompatActivity {
     EditText jobName,jobLocation, jobDescription, jobRequirements,jobPostTime;
     Spinner jobType,jobWorkPlaceType,jobDomainType;
     Button btnUpdate,btnDelete;
     private ImageView back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_edit_delete_job);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        jobName = findViewById(R.id.updateJname);
      //  jobPostTime = findViewById(R.id.updatePostTime);
        jobLocation = findViewById(R.id.updateJobLocation);
        jobDescription = findViewById(R.id.updateJobDescription);
        jobRequirements = findViewById(R.id.updateJobReq);
        jobType = findViewById(R.id.updateJobType);
        jobWorkPlaceType = findViewById(R.id.updateWorkPlaceType);
        //jobDomainType = findViewById(R.id.UpdateDomianType);
        btnUpdate = findViewById(R.id.updatePostBtn);
        btnDelete = findViewById(R.id.DeletePostbtn);
        back = findViewById(R.id.backarrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String job_Id = getIntent().getStringExtra("job_id");
      //  Toast.makeText(this,job_Id, Toast.LENGTH_SHORT).show();
        String job_Name = getIntent().getStringExtra("job_name");
       // String job_posttime = getIntent().getStringExtra("job_postTime");
        String job_Loc = getIntent().getStringExtra("job_Location");
        String job_req = getIntent().getStringExtra("job_requirements");
        String job_desc = getIntent().getStringExtra("job_Description");
        String jobstatus = getIntent().getStringExtra("job_status");
        if(jobstatus.trim().equals("Rejected")){
            btnUpdate.setEnabled(false);
        }
        jobName.setText(job_Name);
     //   jobPostTime.setText(job_posttime);
        jobLocation.setText(job_Loc);
        jobDescription.setText(job_desc);
        jobRequirements.setText(job_req);
        final RequestQueue queue = Volley.newRequestQueue(this);
        String Updateurl ="https://seniorprojectahmadazimeh.000webhostapp.com/UpdatePost2.php";
        /*
        <?php
            $job_id = addslashes(strip_tags($_POST['job_id']));
            $job_name = addslashes(strip_tags($_POST['job_name']));
            $job_location = addslashes(strip_tags($_POST['job_location']));
            $job_type = addslashes(strip_tags($_POST['job_type']));
            $workplace_type = addslashes(strip_tags($_POST['workplace_type']));
            $job_requirements = addslashes(strip_tags($_POST['job_requirements']));
            $job_description = addslashes(strip_tags($_POST['job_description']));
            $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
            // Check connection
            if (mysqli_connect_errno()) {
             echo "Failed to connect to MySQL: " . mysqli_connect_error();
             }
             $sql = "UPDATE Job SET job_name='$job_name',job_location='$job_location', job_type='$job_type', workplace_type='$workplace_type', job_requirements='$job_requirements', job_description='$job_description' WHERE job_id=$job_id";
             if (mysqli_query($con, $sql)) {
             echo "Record updated successfully";
             }  else {
             echo "Error updating record: " . mysqli_error($con);
             }
             mysqli_close($con);
              ?>
               */
        String Deleteurl ="https://seniorprojectahmadazimeh.000webhostapp.com/DeletePost.php";
        /*
        <?php
        $job_id = addslashes(strip_tags($_POST['job_id']));
        $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
          // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        // Check if the job exists in the Applied_job table
        $sql_check_applied = "SELECT * FROM Applied_job WHERE job_id='$job_id'";
        $result_check_applied = mysqli_query($con, $sql_check_applied);

        if (mysqli_num_rows($result_check_applied) > 0) {
            // Delete corresponding records from the Applied_job table
            $sql_delete_applied = "DELETE FROM Applied_job WHERE job_id='$job_id'";
            if (mysqli_query($con, $sql_delete_applied)) {
                echo "Applied_job records deleted successfully";
            } else {
                echo "Error deleting Applied_job records: " . mysqli_error($con);
            }
        }

         // Delete the job from the Job table
        $sql_delete_job = "DELETE FROM Job WHERE job_id='$job_id'";

        if (mysqli_query($con, $sql_delete_job)) {
            echo "Record deleted successfully";
        } else {
            echo "Error deleting record: " . mysqli_error($con);
        }

        mysqli_close($con);
         ?>
       */


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
                        Toast.makeText(BusinessEditDeleteJob.this, response.toString(), Toast.LENGTH_SHORT).show();
                        btnUpdate.setEnabled(true);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BusinessEditDeleteJob.this, "Post Already Approved and Posted\n" +
                                " You can not Edit it.", Toast.LENGTH_LONG).show();
                        btnUpdate.setEnabled(true);
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("job_id",job_Id);
                        params.put("job_name",jobName.getText().toString());
                       // params.put("post_time",jobPostTime.getText().toString());
                        params.put("job_location",jobLocation.getText().toString());
                        params.put("job_type", jobType.getSelectedItem().toString());
                        params.put("workplace_type", jobWorkPlaceType.getSelectedItem().toString());
                        params.put("job_requirements", jobRequirements.getText().toString());
                        params.put("job_description",jobDescription.getText().toString());
                        return params;
                    }
                };
                queue.add(request);
            }
             });
        btnDelete.setOnClickListener(view -> {
            // setEnabled as false since the user cannot send manyRequest at the same time before responses


            // create a StringRequest
            StringRequest request = new StringRequest(Request.Method.POST,Deleteurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.toString().contains("Error deleting record")){
                        Toast.makeText(BusinessEditDeleteJob.this, "Can not delete the Post already published" +
                                "\n You should Contact Admin", Toast.LENGTH_SHORT).show();
                        btnDelete.setEnabled(true);
                        btnUpdate.setEnabled(true);
                    }else{
                        btnDelete.setEnabled(false);
                        Toast.makeText(BusinessEditDeleteJob.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                    btnUpdate.setEnabled(false);
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(BusinessEditDeleteJob.this, error.toString(), Toast.LENGTH_LONG).show();
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
        });
    }

}