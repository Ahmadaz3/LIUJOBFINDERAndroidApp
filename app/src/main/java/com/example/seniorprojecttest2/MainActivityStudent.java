package com.example.seniorprojecttest2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;
public class MainActivityStudent extends AppCompatActivity {
   private int std_id;
   private CardView studentJobs,Logout,accInterViews,RecommendJobs,feedback,res;
   private RequestQueue requestQueue;
   private ArrayList<StudentData> stdData;
   private TextView StudentName;
   private TextView StudentMajor;
   private ImageView img;
   public static String major;
   //public static  String StudentImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageDataHolder.studentImage =null;
        Intent intent = getIntent();
        std_id = intent.getIntExtra("id", 0);
        studentJobs = findViewById(R.id.StudentJobs);
        requestQueue = Volley.newRequestQueue(this);
        StudentName = findViewById(R.id.StudentName);
        StudentMajor = findViewById(R.id.StudentMajor);
        Logout =findViewById(R.id.LogoutCard);
        accInterViews= findViewById(R.id.AcceptedInterviews);
        RecommendJobs=findViewById(R.id.RecommendJobsCard);
        feedback=findViewById(R.id.feedbackCard);
        res = findViewById(R.id.ResourcesCard);
        img = findViewById(R.id.StudentProfileImage);
        SetImage();
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityStudent.this,Resources.class);
                intent.putExtra("major",major);
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityStudent.this,feedbacks.class);
                intent.putExtra("std_id",String.valueOf(std_id));
                startActivity(intent);

            }
        });
            Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivityStudent.this,LoginPage.class);
                startActivity(intent1);
                finish();
            }
        });
        accInterViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityStudent.this,StudentInterViews.class);
                intent.putExtra("std_id",String.valueOf(std_id));
                startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent2 = new Intent(MainActivityStudent.this,StudentProfile.class);
              intent2.putExtra("std_id",String.valueOf(std_id));
              //intent2.putExtra("std_img",StudentImage);
              startActivity(intent2);
            }
        });

        studentJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivityStudent.this,StudentHome.class);
                intent1.putExtra("id",std_id);
                startActivity(intent1);
            }
        });
        RecommendJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivityStudent.this,RecommendsJobsForStudent.class);
             intent.putExtra("id",String.valueOf(std_id));
             startActivity(intent);
            }
        });
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
                            major = row.getString("Major");
                            String Campus = row.getString("Campus");
                            StudentName.setText(First_name+" "+Last_name);
                            StudentMajor.setText(major);
                            //   Toast.makeText(this, First_name, Toast.LENGTH_SHORT).show();
                            stdData.add(new StudentData(Student_id, First_name, Last_name, Student_Email, major, Campus));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonArrayRequest);
    }
    private void SetImage(){
        String url1 = "https://seniorprojectahmadazimeh.000webhostapp.com/getStudentImage.php?std_id=" + std_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url1,
                null,
                response -> {
                    try {
                        String imageData = response.getString("image");
                        String[] parts = imageData.split(",");
                        String mimeType = parts[0].split(":")[1].split(";")[0];
                        String base64Image = parts[1];
                        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        img.setImageBitmap(decodedByte);
                        Bitmap bitmap = decodedByte; // Replace with your actual Bitmap

                         // Convert the Bitmap to a byte array
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                          // Convert the byte array to a String
                      ImageDataHolder.studentImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SetImage();
    }
}
