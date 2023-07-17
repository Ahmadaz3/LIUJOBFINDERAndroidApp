package com.example.seniorprojecttest2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class AppliedFragment extends Fragment implements StudentRecyclerViewAdapter.UserClickListeners {
    String url="https://seniorprojectahmadazimeh.000webhostapp.com/AppliedForYourJob2.php?business_id="+BusinessHome.id;
    /*
    <?php
      // Include your database connection file
     // $connection = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
    // Get the business ID from the Android application
    $businessId = $_GET['business_id'];
    // Prepare the SQL query to fetch the students who applied for jobs for the specific business
    $query = "SELECT s.Student_id, s.First_name, s.Last_name, s.Student_Email, s.Major, s.Campus,
    j.job_id, j.job_name, j.job_location, j.job_type, j.workplace_type, j.job_requirements, j.job_description, j.status
    FROM Student s
    INNER JOIN Applied_job a ON s.Student_id = a.std_id
    INNER JOIN Job j ON a.job_id = j.job_id
    WHERE j.buss_id = $businessId
    AND a.applied = false"; // Add the condition to select only where applied is false
      // Execute the query
    $result = mysqli_query($connection, $query);
      // Check if there are any results
         if (mysqli_num_rows($result) > 0) {
        $students = array();
        // Loop through the result set and fetch student and job data
        while ($row = mysqli_fetch_assoc($result)) {
            $student = array(
                    'Student_id' => $row['Student_id'],
                    'First_name' => $row['First_name'],
                    'Last_name' => $row['Last_name'],
                    'Student_Email' => $row['Student_Email'],
                    'Major' => $row['Major'],
                    'Campus' => $row['Campus'],
                    'Job_id' => $row['job_id'],
                    'Job_name' => $row['job_name']
        );
            // Add the student to the students array
            $students[] = $student;
        }
        // Convert the students array to JSON format
        $response = json_encode($students);
        // Send the JSON response back to the Android application
        echo $response;
    } else {
        // If no students found, send an empty response
        echo "[]";
    }
    // Close the database connection
    mysqli_close($connection);
    ?>
     */
    //ArrayList<StudentData1> stdData;
    RecyclerView rvStd;
    StudentRecyclerViewAdapter studentRecyclerViewAdapter;
    List<StudentData1> stdData = new ArrayList<>();
  //TextView  emptyTextView1;
  LottieAnimationView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_applied, container, false);
        //tv = v.findViewById(R.id.tvID);
      //  Toast.makeText(getContext(),"Your business id"+BusinessHome.id, Toast.LENGTH_SHORT).show();
        rvStd= v.findViewById(R.id.AppliedRV);
      //  emptyTextView1 = v.findViewById(R.id.emptyTextView1);
       lv = v.findViewById(R.id.waiting);
        // Initialize the ArrayList
        stdData = new ArrayList<>();
        getStudentData();
        prepareRecyclerView();
        return v;
    }

    public void getStudentData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    stdData.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String Student_id = row.getString("Student_id");
                            String First_name = row.getString("First_name");
                            String Last_name = row.getString("Last_name");
                            String Student_Email = row.getString("Student_Email");
                            String Major = row.getString("Major");
                            String Campus = row.getString("Campus");
                            String jobId = row.getString("Job_id");
                            String jobname = row.getString("Job_name");
                            stdData.add(new StudentData1(Student_id, First_name, Last_name, Student_Email, Major, Campus,jobname,jobId));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    studentRecyclerViewAdapter.notifyDataSetChanged();
                    if (stdData.isEmpty()) {
                       // emptyTextView1.setVisibility(View.VISIBLE);
                       lv.setVisibility(View.VISIBLE);
                    } else {
                        //emptyTextView1.setVisibility(View.GONE);
                       lv.setVisibility(View.GONE);
                    }
                    // Display the first student's first name in the TextView
                   // if (!stdData.isEmpty()) {
                      //  tv.setText(stdData.get(0).getJobName());
                   // }else{
                   //     Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
                  //  }
                },
                error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        getStudentData();
    }
    public void prepareRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvStd.setLayoutManager(linearLayoutManager);
        prepareAdapter();
    }
    public void prepareAdapter(){
        studentRecyclerViewAdapter = new StudentRecyclerViewAdapter(stdData,getContext(),this::selectUser);
        rvStd.setAdapter(studentRecyclerViewAdapter);
    }
    @Override
    public void selectUser(StudentData1 studentData1) {
      //  Toast.makeText(getContext(), "Student Name: "+studentData1.getF_name()+" "+studentData1.getL_name(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),InterviewAppointment.class);
        intent.putExtra("data",studentData1);
        intent.putExtra("bsId",BusinessHome.id);
        startActivity(intent);
    }

















}
