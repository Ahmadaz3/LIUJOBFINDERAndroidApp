package com.example.seniorprojecttest2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostRequests extends Fragment implements ARecyclerViewInterface{
   private RecyclerView recyclerView;
   private ArrayList<JobData1> jobData;
   private AdminRequestsRecyclerViewAdapter adapter;
   private LinearLayoutManager linearLayoutManager;
   private String url="https://seniorprojectahmadazimeh.000webhostapp.com/getJobRequests.php";
   LottieAnimationView lvanim;
   // public String buss_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_requests, container, false);
        lvanim = view.findViewById(R.id.empty);
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        jobData = new ArrayList<>();
        adapter = new AdminRequestsRecyclerViewAdapter(getContext(),jobData,this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Loop through the JSON array and add the names to the ArrayList
                        jobData.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                String id = row.getString("job_id");
                                String name = row.getString("job_name");
                                //String post_Time = row.getString("post_time");
                                String Loc = row.getString("job_location");
                                String jobType = row.getString("job_type");
                                String workP_Type = row.getString("workplace_type");
                                String job_req = row.getString("job_requirements");
                                String job_desc = row.getString("job_description");
                                String bussid = row.getString("buss_id");

                                jobData.add(new JobData1(id,name,Loc,jobType,workP_Type,job_req,job_desc,bussid));
                               // Toast.makeText(getContext(),bussid, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                       if(jobData.isEmpty()){
                           lvanim.setVisibility(View.VISIBLE);
                       }else{
                           lvanim.setVisibility(View.GONE);
                       }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onResume() {
        super.onResume();
        // Reload the data in the onResume() method
        getData();
    }
    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(getActivity(),AdminApprove.class);
        //Toast.makeText(getContext(),jobData.get(pos).getBuss_id(), Toast.LENGTH_SHORT).show();
        intent.putExtra("job_id", jobData.get(pos).getJobId());
        intent.putExtra("job_name", jobData.get(pos).getJobname());
        intent.putExtra("job_location", jobData.get(pos).getJobLoc());
        intent.putExtra("jobType", jobData.get(pos).getJob_Type());
        intent.putExtra("jobWorkPlaceType", jobData.get(pos).getWorkPlace_Type());
        intent.putExtra("job_requirements", jobData.get(pos).getJob_req());
        intent.putExtra("job_description", jobData.get(pos).getJob_desc());
        intent.putExtra("buss_id",jobData.get(pos).getBuss_id());
        startActivity(intent);

    }
}