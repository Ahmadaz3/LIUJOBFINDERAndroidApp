package com.example.seniorprojecttest2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminMessagesFragment extends Fragment {
    private ArrayList<feedbackdata> feeddata = new ArrayList<>();
    feedbackAdapter feedAdapter;
    RecyclerView recyclerView;
    private RequestQueue requestQueue;
   public  String url2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_messages, container, false);
        requestQueue= Volley.newRequestQueue(getContext());
        recyclerView=view.findViewById(R.id.recylerFeedbacks);
        url2 = "https://seniorprojectahmadazimeh.000webhostapp.com/getFeedbacksFromStudent.php";
        /*
        <?php
                $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        $sql = "SELECT f.feedback_text, s.Student_email
        FROM feedback f
        INNER JOIN Student s ON f.std_id = s.Student_id";

        $result = mysqli_query($con, $sql);

        $response = array();

        if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
                $feedbackText = $row['feedback_text'];
                $studentEmail = $row['Student_email'];

                $feedback = array(
                        'feedback_text' => $feedbackText,
                        'student_email' => $studentEmail
         );

                $response[] = $feedback;
            }
        }
        mysqli_free_result($result);
        mysqli_close($con);
        echo json_encode($response);
         ?>
         */
        getData();
        return view;
    }
    public void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                response -> {
                    // Loop through the JSON array and add the names to the ArrayList
                    if (feeddata.size() >= 0) {
                        feeddata.clear();
                    }
                    feeddata = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String feedText = row.getString("feedback_text");
                            String stdEmail = row.getString("student_email");
                          //  Log.d("RecommendJobs", "Job Name: " + job_Name);
                            feeddata.add(new feedbackdata(feedText,stdEmail));
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // prepareAdapter();
    }

    public void prepareAdapter(){
        feedAdapter = new feedbackAdapter(feeddata,getContext());
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}