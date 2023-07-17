package com.example.seniorprojecttest2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.util.Linkify;
import android.widget.TextView;
import android.widget.Toast;

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

public class BusinessDetails extends AppCompatActivity {
    private TextView tvNameAndLoc,tvEmail,tvTypeAndSize,tvWeb;
   // private String buss_id;
    ArrayList<BussData> BusData;
   // String url="https://seniorprojectahmadazimeh.000webhostapp.com/getBusinessDetalisByid.php?buss_id="+buss_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.white));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the titles
            String title = "Business Details";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        tvNameAndLoc = findViewById(R.id.BussNameAndLoc);
        tvEmail = findViewById(R.id.BussEmail);
        tvTypeAndSize = findViewById(R.id.BusstypeandSize);
        tvWeb = findViewById(R.id.bussWebsite);
        Intent intent = getIntent();
        String buss_id =  intent.getStringExtra("business_id");
        String url="https://seniorprojectahmadazimeh.000webhostapp.com/getBusinessDetalisByid.php?buss_id="+buss_id;
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
        $buss_id = $_GET['buss_id'];
        // Select all the employees with the same JSSNF
        //$sql = "SELECT job_name,job_location FROM Job WHERE buss_id = $buss_id";
        //$result = mysqli_query($conn, $sql);
        $sql = "SELECT * FROM Business WHERE Business_id = $buss_id";
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
        // Toast.makeText(this, buss_id, Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Loop through the JSON array and add the names to the ArrayList
                    // BusData.clear();
                    BusData = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String id = row.getString("Business_id");
                            String name = row.getString("Business_name");
                        //    Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                            //  String post_Time = row.getString("post_time");
                            String Loc = row.getString("Business_Location");
                            String BusSize = row.getString("Business_Size");
                            String BusType = row.getString("Business_Type");
                            String BusWeb = row.getString("Business_website");
                            String Email = row.getString("Business_Email");
                            BusData.add(new BussData(id,name,Loc,BusSize,BusType,BusWeb,Email));
                            //Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //  adapt.notifyDataSetChanged();
                    // Loop through the BusData ArrayList and set the data to the textViews
                    for (BussData bussData : BusData) {
                       // Toast.makeText(this,bussData.getName() , Toast.LENGTH_SHORT).show();
                        tvNameAndLoc.setText("Business Name: "+bussData.getName() + "\n\nBusiness Location : " +
                                "\n" + bussData.getLocation()+"");
                        tvEmail.setText("Business Email: \n"+bussData.getEmail());
                        tvTypeAndSize.setText("Business Size: \n"+bussData.getType() + "\n\nBusiness Type: \n" + bussData.getSize()+"\n");
                        tvWeb.setText("Business Website: \n"+bussData.getWebsite());
                       // tvWeb.setText("https://www.google.com/search?q=google&oq=google&aqs=chrome.0.0i131i355i433i512j46i131i199i433i465i512j69i59i131i433i512j69i60l3j5j69i65.2731j0j7&sourceid=chrome&ie=UTF-8");
                        tvWeb.setAutoLinkMask(Linkify.WEB_URLS);
                        tvWeb.setMovementMethod(LinkMovementMethod.getInstance());
                    }

                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }


    }
