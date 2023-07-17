package com.example.seniorprojecttest2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class BusinessPostsFragment extends Fragment implements BRecyclerViewInterface {
        String url="https://seniorprojectahmadazimeh.000webhostapp.com/getYourJobById.php?buss_id="+BusinessHome.id;
        RecyclerView recyclerView;
        ArrayList<JobData> jobData;
        BusinessPostsRecyclerViewAdapter adapt;
        //JobData Prod;
        LinearLayoutManager linearLayoutManager;
        TextView emptyTextView;
      //  public String job_req;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_business_posts, container, false);
            recyclerView=v.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            jobData = new ArrayList<>();
            emptyTextView = v.findViewById(R.id.emptyTextView);
            adapt = new BusinessPostsRecyclerViewAdapter(getContext(), jobData,this);
            //make 2 in each row
            linearLayoutManager = new LinearLayoutManager(getActivity()); // Set span count to 2
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapt);
            getData();
            // Update the adapter and notifyDataSetChanged() method
            adapt = new BusinessPostsRecyclerViewAdapter(getContext(), jobData,this);
            recyclerView.setAdapter(adapt);
          //  adapt.notifyDataSetChanged();
            return v;
        }
        private void getData() {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        // Loop through the JSON array and add the names to the ArrayList
                        jobData.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                String id = row.getString("job_id");
                                String name = row.getString("job_name");
                              //  String post_Time = row.getString("post_time");
                                String Loc = row.getString("job_location");
                                String jobType = row.getString("job_type");
                                String workP_Type = row.getString("workplace_type");
                                String job_req = row.getString("job_requirements");
                                String job_desc = row.getString("job_description");
                                String job_status = row.getString("status");
                               jobData.add(new JobData(id,name,Loc,jobType,workP_Type,job_req,job_desc,job_status));
                                //Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapt.notifyDataSetChanged();
                        if (jobData.isEmpty()) {
                            emptyTextView.setVisibility(View.VISIBLE);
                        } else {
                            emptyTextView.setVisibility(View.GONE);
                        }

                    },
                    error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show());
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
        Intent intent = new Intent(getActivity(),BusinessEditDeleteJob.class);
        intent.putExtra("job_id", jobData.get(pos).getJobId());
        intent.putExtra("job_name", jobData.get(pos).getJobname());
       // intent.putExtra("job_postTime", jobData.get(pos).getPost_Time());
        intent.putExtra("job_Location", jobData.get(pos).getJobLoc());
        intent.putExtra("job_requirements", jobData.get(pos).getJob_req());
        intent.putExtra("job_Description", jobData.get(pos).getJob_desc());
        intent.putExtra("job_status",jobData.get(pos).getJob_status());
        Toast.makeText(getContext(),"Note Your job status is : "+jobData.get(pos).getJob_status()+"" +
                "\n You can not edit or delete if your job is Accepted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
    //  public void restartFragment() {
      //  FragmentTransaction transaction = getFragmentManager().beginTransaction();
       // transaction.detach(this).attach(this).commit();
  //  }
    }


