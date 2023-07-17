package com.example.seniorprojecttest2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class feedbacks extends AppCompatActivity {
    private RequestQueue requestQueue;
    private Button feedbackSubmit;
    private EditText edtFeeds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);
        requestQueue = Volley.newRequestQueue(this);
        edtFeeds = findViewById(R.id.feedbacktext);
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Send FeedBack";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        feedbackSubmit= findViewById(R.id.btnFeedBack);
        Intent intent = getIntent();
        String id =intent.getStringExtra("std_id");
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/getFeedbacks.php";
        /*
        <?php
        $std_id = addslashes(strip_tags($_POST['std_id']));
        $feedback_text = addslashes(strip_tags($_POST['feedback_text']));
        $con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
         // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        $sql = "INSERT INTO feedback (std_id, feedback_text)
        VALUES ('$std_id', '$feedback_text')";

        mysqli_query($con, $sql) or die("Can't add record to the feedback table: " . mysqli_error($con));

        echo "Record Added Successfully";
        mysqli_close($con);
        ?>
         */

        feedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(feedbacks.this,"For any Response we will contact you\nBy Email", Toast.LENGTH_SHORT).show();
                                edtFeeds.setText("");
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle any errors that occur during the request
                                Toast.makeText(feedbacks.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("std_id",id);
                        params.put("feedback_text",edtFeeds.getText().toString());
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });
    }
}