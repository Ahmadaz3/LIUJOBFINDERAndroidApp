package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
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
import java.util.Objects;

public class BusinessActivity extends AppCompatActivity {
    // Declare
    private EditText BName,Bemail,Bpass,Blocation,Bwebsite;
    private Spinner Btype,Bsize;
    private Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BName = findViewById(R.id.BnameEd);
        Bemail = findViewById(R.id.BemailEd);
        Bpass = findViewById(R.id.BpassEd);
        Blocation = findViewById(R.id.BLocation);
        Bwebsite = findViewById(R.id.Bweb);
        Btype = findViewById(R.id.Btype);
        Bsize = findViewById(R.id.BSize);
        SignUp = findViewById(R.id.BsignUp);
        final RequestQueue queue = Volley.newRequestQueue(this);
        Bemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString().trim();
                if (email.isEmpty()) {
                    Bemail.setError("Email is required");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Bemail.setError("Invalid email format");
                } else {
                    Bemail.setError(null);  // Clear any previous error
                }
            }
        });
        Bwebsite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String website = s.toString().trim();
                if (website.isEmpty()) {
                    Bwebsite.setError("Website is required");
                } else if (!Patterns.WEB_URL.matcher(website).matches()) {
                    Bwebsite.setError("Invalid website format");
                } else {
                    Bwebsite.setError(null);  // Clear any previous error
                }
            }
        });

        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/BusinessSignUp.php";
        /*
        <?php
        $Business_name = addslashes(strip_tags($_POST['Business_name']));
        $Business_Email = addslashes(strip_tags($_POST['Business_Email']));
        $password = addslashes(strip_tags($_POST['password']));
        $Business_Size = addslashes(strip_tags($_POST['Business_Size']));
        $Business_Type = addslashes(strip_tags($_POST['Business_Type']));
        $Business_Location = addslashes(strip_tags($_POST['Business_Location']));
        $Business_website = addslashes(strip_tags($_POST['Business_website']));
        $con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
        // Check connection
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: ". mysqli_connect_error();
        }
        // check information
        $query_1 = "SELECT * FROM Business WHERE Business_Email ='$Business_Email'";
        $result = mysqli_query($con,$query_1);

        if (mysqli_num_rows($result)<1) {
            $sql1 = "INSERT INTO Business (Business_name,Business_Email,password,Business_Size,Business_Type,Business_Location,Business_website)
            VALUES ('$Business_name','$Business_Email','$password','$Business_Size','$Business_Type','$Business_Location','$Business_website')";
            mysqli_query($con, $sql1) or die("Can't add record to the Business table");

            // Insert data into the user table
            $sql2 = "INSERT INTO users(id,email, password)
            VALUES (LAST_INSERT_ID(),'$Business_Email','$password')";
            mysqli_query($con, $sql2) or die("Can't add record to the user table");

            echo "Sign Up successfully!";
        } else {
            echo "SignUp failed";
        }
        mysqli_close($con);
        ?>
         */
        SignUp.setOnClickListener(view -> {
            if(BName.getText().toString().equals("") || Bemail.getText().toString().equals("") || Bpass.getText().toString().equals("")
                    || Blocation .getText().toString().equals("") || Bwebsite.getText().toString().equals("")){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            } else if (Bemail.getError() != null) {
                // Email has an error (invalid format)
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            } else if (Bwebsite.getError() != null) {
                // Website has an error (invalid format)
                Toast.makeText(this, "Invalid website format", Toast.LENGTH_SHORT).show();
            } else {
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                SignUp.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(BusinessActivity.this,
                                response.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BusinessActivity.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                        // The button will be Enabled after the response accrued
                        SignUp.setEnabled(true);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BusinessActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("Business_name", BName.getText().toString());
                        params.put("Business_Email", Bemail.getText().toString());
                        params.put("password", Bpass.getText().toString());
                        params.put("Business_Size",Btype.getSelectedItem().toString() );
                        params.put("Business_Type", Bsize.getSelectedItem().toString());
                        params.put("Business_Location", Blocation.getText().toString());
                        params.put("Business_website", Bwebsite.getText().toString());
                        return params;
                    }
                };
                queue.add(request);
            }
        });

    }
}