package com.example.seniorprojecttest2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class AddPostFragment extends Fragment {
    // The edit Texts job name and job location and Job Description and Job Requirements
    EditText Jname ,JLoc , JDesc ,JReq;
    // The button Add Job
    Button AddJob;
    // The spinners for job type work place type and the Job Domain that it must match the student Major
    Spinner Jtype , JWork,JDomain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        // findViewById for all this attributes
        Jname = view.findViewById(R.id.updateJname);
       // JTime = view.findViewById(R.id.JTime);
        JLoc = view.findViewById(R.id.updateJobLocation);
        JDesc = view.findViewById(R.id.updateJobDescription);
        JReq = view.findViewById(R.id.updateJobReq);
        AddJob = view.findViewById(R.id.JAdd);
        Jtype = view.findViewById(R.id.updateJobType);
        JWork = view.findViewById(R.id.updateWorkPlaceType);
        JDomain =view.findViewById(R.id.UpdateDomianType);
        // get the id from the BusinessHome for the Business
        int id = BusinessHome.id;
       // Toast.makeText(getActivity(), "Your id is"+id, Toast.LENGTH_LONG).show();
        // Create a Requests to add Requests to a Queue in this Fragment
        final RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/AddJob.php";
        // php code for AddJob
        /*
        <?php
                $job_name = addslashes(strip_tags($_POST['job_name']));
        $job_location = addslashes(strip_tags($_POST['job_location']));
        $job_type = addslashes(strip_tags($_POST['job_type']));
        $workplace_type = addslashes(strip_tags($_POST['workplace_type']));
        $job_requirements = addslashes(strip_tags($_POST['job_requirements']));
        $job_description= addslashes(strip_tags($_POST['job_description']));
        $status = 'Pending'; // Default status value
        $buss_id = addslashes(strip_tags($_POST['buss_id']));
        $Domain= addslashes(strip_tags($_POST['Domain']));

        $con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
        // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: ". mysqli_connect_error();
        }

        $sql1 = "INSERT INTO Job(job_name, job_location, job_type, workplace_type, job_requirements, job_description, status, buss_id, Domain)
        VALUES ('$job_name', '$job_location', '$job_type', '$workplace_type', '$job_requirements', '$job_description', '$status', '$buss_id', '$Domain')";

        mysqli_query($con, $sql1) or die("Can't add record to the Job table: ".mysqli_error($con));

        echo "Record Added Successfully";
        mysqli_close($con);
          ?>
         */
       // add the data to the database
        AddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if the user enter all fields
                if(Jname.getText().toString().equals("")  || JLoc.getText().toString().equals("")
                        || JDesc .getText().toString().equals("") || JReq.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    // setEnabled as false since the user cannot send manyRequest at the same time before responses
                    AddJob.setEnabled(false);
                    // create a StringRequest
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // on responses all fields will be empty and A Toast Your request added will appear
                            Toast.makeText(getActivity(), "Post Added successfully\n  " +
                                    "Waiting for Admin Confirmation", Toast.LENGTH_LONG).show();
                            Jname.setText("");
                            JLoc.setText("");
                            JDesc.setText("");
                            JReq.setText("");
                            // The button will be Enabled after the response accrued
                            AddJob.setEnabled(true);
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            // create an Object of hashMap called params to send the data we get it
                            // from the Edits texts and insert it as there Keys to the database
                            Map<String, String> params = new HashMap<>();
                            params.put("job_name", Jname.getText().toString());
                           // params.put("post_time", JTime.getText().toString());
                            params.put("job_location", JLoc.getText().toString());
                            params.put("job_type", Jtype.getSelectedItem().toString());
                            params.put("workplace_type", JWork.getSelectedItem().toString());
                            params.put("Domain", JDomain.getSelectedItem().toString());
                            params.put("job_requirements", JReq.getText().toString());
                            params.put("job_description",JDesc.getText().toString());
                            params.put("buss_id",String.valueOf(id));
                            return params;
                        }
                    };
                    queue.add(request);
                }
            }
        });
        return view;
    }
}