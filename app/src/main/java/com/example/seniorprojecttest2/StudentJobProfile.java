package com.example.seniorprojecttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class StudentJobProfile extends AppCompatActivity {
    private TextView stdNameAndMajor,StudentuniAndcampus,stdPersonalEmail,stdPersonalLoc,stdPersonalEducation,stdPersonalSkills;
    private ImageView profileImage;

    public String url;
    private RequestQueue requestQueue;
    private ArrayList<ProfileData> profileData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_job_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        String id = intent.getStringExtra("std_id");
     //   Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        stdNameAndMajor = findViewById(R.id.profileNameAndMajor);
        stdPersonalEmail = findViewById(R.id.ProfilepersonalEmail);
        stdPersonalLoc = findViewById(R.id.ProfileLocation);
        stdPersonalEducation = findViewById(R.id.ProfileEducation);
        stdPersonalSkills = findViewById(R.id.ProfileSkills);
        StudentuniAndcampus =findViewById(R.id.StudentUniAndCampus);
        profileImage = findViewById(R.id.ProfileImage);
        requestQueue = Volley.newRequestQueue(this);
        url = "https://seniorprojectahmadazimeh.000webhostapp.com/getProfileInformations.php?student_id=" + id;
        getData();
    }
    public void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Process the JSON object and extract the data
                        try {
                            String name = response.getString("name");
                            String email = response.getString("email");
                            String major = response.getString("major");
                            String location = response.getString("location");
                            String campus = response.getString("campus");
                            String education = response.getString("education");
                            String experience = response.getString("experience");
                            String image = response.getString("image");
                            stdNameAndMajor.setText(name+"\n-"+major);
                            StudentuniAndcampus.setText("Lebanese International University at "+campus+" Campus");
                            stdPersonalEmail.setText(email);
                            if(location.trim().equals("")){
                                stdPersonalLoc.setText("Not set Yet");
                            }else{
                                stdPersonalLoc.setText(location);
                            }
                            if(education.trim().equals("")){
                                stdPersonalEducation.setText("Not set Yet");
                            }else{
                                stdPersonalEducation.setText(education);
                            }
                            if(experience.trim().equals("")){
                                stdPersonalSkills.setText("Not set Yet");
                            }else{
                                stdPersonalSkills.setText(experience);
                            }
                            setImage(image);
                            profileData = new ArrayList<>();
                        //    profileData.add(new ProfileData(name, email, major, campus, education, experience, image));

                            // Update your UI or perform other operations with the retrieved data
                        } catch (JSONException e) {
                            //Toast.makeText(StudentJobProfile.this, "No profile Created Yet", Toast.LENGTH_SHORT).show();
                           // e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    private void setImage(String imageData) {
        String[] parts = imageData.split(",");
        String mimeType = parts[0].split(":")[1].split(";")[0];
        String base64Image = parts[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        profileImage.setImageBitmap(decodedByte);
    }
}