package com.example.seniorprojecttest2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentHome extends AppCompatActivity implements UserAdapter.UserClickListener {
    private RequestQueue requestQueue;
    private ArrayList<StudentData> stdData;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<JobData2> jobData = new ArrayList<>();
    List<JobData2> jobData1 = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    public static String major;
  //  String m ="Computer Science";
    public static String url2 ;
    private int std_id;
    String stdId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        // Get reference to the ActionBar
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Search For Job";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }


       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frame_color)));
       // getSupportActionBar().setTitle("Search For Job");


        // Initialize the request queue inside the onCreate() method
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        std_id = intent.getIntExtra("id", 0);
         String stdId = String.valueOf(std_id);
        userAdapter = new UserAdapter(jobData, this, this::SelectedUser);
        //  Toast.makeText(this,""+std_id, Toast.LENGTH_SHORT).show();
        String url = "https://seniorprojectahmadazimeh.000webhostapp.com/getStudentInfo.php?std_id=" + std_id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Loop through the JSON array and add the names to the ArrayList
                    stdData = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String Student_id = row.getString("Student_id");
                            String First_name = row.getString("First_name");
                            String Last_name = row.getString("Last_name");
                            String Student_Email = row.getString("Student_Email");
                            String Major = row.getString("Major");
                            String Campus = row.getString("Campus");
                         //   Toast.makeText(this, First_name, Toast.LENGTH_SHORT).show();
                            stdData.add(new StudentData(Student_id, First_name, Last_name, Student_Email, Major, Campus));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < stdData.size(); i++) {
                        StudentData student = stdData.get(i);
                        major = student.getMajor();
                        // Do something with the student data
                    }

                    //url2="https://seniorprojectahmadazimeh.000webhostapp.com/getJobsForStudent.php?std_major=" + major + "&std_id=" + stdId;
                    url2="https://seniorprojectahmadazimeh.000webhostapp.com/getJobForStudents2.php?std_major=" + major + "&std_id=" + stdId;
                 //   Toast.makeText(this,"Your major is"+ major, Toast.LENGTH_SHORT).show();
                    recyclerView = findViewById(R.id.stdRc);
                    recyclerView.setHasFixedSize(true);
                    jobData = new ArrayList<>();
                    jobData1 = new ArrayList<>();
                    userAdapter = new UserAdapter(jobData1,this,this::SelectedUser);
                    linearLayoutManager = new LinearLayoutManager(this); // Set span count to 2
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(userAdapter);
                    getData();
                    jobData.clear();
                    jobData1.clear();
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonArrayRequest);
      //  url2 = "https://seniorprojectahmadazimeh.000webhostapp.com/getJobsForStudent.php?std_major=" + major;

    //    Toast.makeText(this, major+""+stdId, Toast.LENGTH_SHORT).show();
    }
    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                response -> {
                    // Loop through the JSON array and add the names to the ArrayList
                        if (jobData.size() >= 0){
                            jobData.clear();
                        }
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
                            String business_name = row.getString("Business_name");
                            jobData.add(new JobData2(id, name, Loc, jobType, workP_Type, job_req, job_desc,business_name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    jobData1.clear();
                    for (int i = jobData.size() - 1; i >= 0; i--) {
                        jobData1.add(jobData.get(i));
                    }
                    userAdapter.notifyDataSetChanged();
                },
                error -> {
                 //   Toast.makeText(this, "wait for response", Toast.LENGTH_SHORT).show();
                });
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    @Override
    public void SelectedUser(JobData2 userModel) {
        Intent intent = new Intent(StudentHome.this,ApplyForJobStudent.class);
        intent.putExtra("data",userModel);
        intent.putExtra("YourStudentId",String.valueOf(std_id));
        //Toast.makeText(this, String.valueOf(std_id), Toast.LENGTH_SHORT).show();
     //   Toast.makeText(this, userModel.getBusiness_name(), Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this,SelectedUserActivity.class));
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.searchView){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searchStr = newText;
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }





   /* @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(StudentHome.this,ApplyForJobStudent.class);
        intent.putExtra("id",jobData.get(pos).getJobId());
        intent.putExtra("job_name",jobData.get(pos).getJobname());
       // Toast.makeText(this,jobData.get(pos).getJobname() , Toast.LENGTH_SHORT).show();
        intent.putExtra("job_loc",jobData.get(pos).getJobLoc());
        intent.putExtra("job_type",jobData.get(pos).getJob_Type());
        intent.putExtra("job_workplacetype",jobData.get(pos).getWorkPlace_Type());
        intent.putExtra("job_desc",jobData.get(pos).getJob_desc());
        intent.putExtra("job_req",jobData.get(pos).getJob_req());
        intent.putExtra("std_id",std_id);
       // Toast.makeText(this, ""+std_id, Toast.LENGTH_SHORT).show();
       startActivity(intent);
    }
*/


}