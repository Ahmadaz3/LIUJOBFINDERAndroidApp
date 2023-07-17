package com.example.seniorprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstructorHome extends AppCompatActivity implements InstructorAdapter.UserClickListeners {
    private ArrayList<InstructorData> insData;
    private ArrayList<JobData> jobData = new ArrayList<>();
    private RequestQueue requestQueue;
    public static String major;
    RecyclerView recyclerView;
    InstructorAdapter instructorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frame_color)));
        getSupportActionBar().setTitle("Available Jobs");
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.InsJobs);
        jobData = new ArrayList<>();
        String url = "https://seniorprojectahmadazimeh.000webhostapp.com/getInstructorInfo.php?Ins_id=" + id;
        /*
        <?php
        header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
        header("Cache-Control: post-check=0, pre-check=0", false);
        header("Pragma: no-cache");
        $conn = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
        // Check connection
        if (mysqli_connect_errno())
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
         // Retrieve the buss_id parameter from the HTTP request
        $Ins_id = $_GET['Ins_id'];

        // Select all the employees with the same JSSNF
       //$sql = "SELECT job_name,job_location FROM Job WHERE buss_id = $buss_id";
       //$result = mysqli_query($conn, $sql);
        $sql = "SELECT * FROM Instructor WHERE Instructor_id = '$Ins_id'";
        $result = mysqli_query($conn, $sql);
        // Create an array to store the names
        $names = array();
        // Loop through the result set and store the names in the array
        while($row = mysqli_fetch_assoc($result)) {
            $names[] = $row;
        }
        echo(json_encode($names));
        // Free result set
        mysqli_free_result($result);
        mysqli_close($conn);
         ?>
       */
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Initialize the insData ArrayList
                    insData = new ArrayList<>();

                    // Loop through the JSON array and add the instructor data to the ArrayList
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);

                            String insId = row.getString("Instructor_id");
                            String firstName = row.getString("First_name");
                            String lastName = row.getString("Last_name");
                            String email = row.getString("Instructort_Email");
                            major = row.getString("Major");
                            String campus = row.getString("Campus");

                            insData.add(new InstructorData(insId, firstName, lastName, email, major, campus));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Fetch jobs for instructor using the retrieved major
                    fetchJobsForInstructor(major);
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonArrayRequest);
    }


    private void fetchJobsForInstructor(String major) {
        String url = "https://seniorprojectahmadazimeh.000webhostapp.com/getJobsForInstructor.php?Ins_major=" + major;
        /*
        <?php
        header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
        header("Cache-Control: post-check=0, pre-check=0", false);
        header("Pragma: no-cache");
        $conn = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
        // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
          // Retrieve the Ins_major parameter from the HTTP request
        $Ins_major = $_GET['Ins_major'];
          // Select all rows from the Job table where status is Accepted, Domain is equal to $Ins_major,
         // and the job ID is not present in the Recommendation table
        $sql = "SELECT *
        FROM Job
        WHERE status = 'Accepted'
        AND Domain = '$Ins_major'
        AND job_id NOT IN (SELECT job_id FROM Recommendation)";
        $result = mysqli_query($conn, $sql);
         // Create an array to store the rows
        $rows = array();
        // Loop through the result set and store the rows in the array
        while ($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
        echo (json_encode($rows));
        // Free result set
        mysqli_free_result($result);
        mysqli_close($conn);
        ?>
        */

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Initialize the jobData ArrayList
                    jobData = new ArrayList<>();

                    // Loop through the JSON array and add the job data to the ArrayList
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String id = row.getString("job_id");
                            String name = row.getString("job_name");
                            String Loc = row.getString("job_location");
                            String jobType = row.getString("job_type");
                            String workP_Type = row.getString("workplace_type");
                            String job_req = row.getString("job_requirements");
                            String job_desc = row.getString("job_description");
                            jobData.add(new JobData(id, name, Loc, jobType, workP_Type, job_req, job_desc));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Perform the operations that depend on the jobData ArrayList here
                    // For example, update UI, set adapter, etc.
                    prepareRecyclerView();
                    prepareAdapter();
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonArrayRequest);
    }

    public void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void prepareAdapter() {
        instructorAdapter = new InstructorAdapter(jobData, this, this::selectUser);
        recyclerView.setAdapter(instructorAdapter);
    }
    @Override
   protected void onResume() {
        super.onResume();
        fetchJobsForInstructor(major);
    }

    @Override
    public void selectUser(JobData jobData) {
        Intent intent = new Intent(InstructorHome.this,RecommendJob.class);
        intent.putExtra("data",jobData);
        intent.putExtra("ins_id",insData.get(0).getInstructor_id());
        startActivity(intent);
    }
}
