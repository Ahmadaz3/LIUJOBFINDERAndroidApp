package com.example.seniorprojecttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    // Declare an Lottie AnimationVIEW TextView 2 Edit Texts and Button
    private LottieAnimationView animationViewLoading;
    private TextView tv;
    private EditText email;
    private EditText pass;
    private Button Loginbtn;
    private static final String LOGIN_URL = "https://seniorprojectahmadazimeh.000webhostapp.com/Login.php";
    // Login Url to communicate with Server
    // PHP CODE
    /*
        <?php
         $con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
         $email = $_POST['email'];
         $password = $_POST['password'];
         $query = "SELECT * FROM users WHERE email='$email' AND password='$password'";
         $result = mysqli_query($con, $query);
         if (mysqli_num_rows($result) == 1) {
         $user = mysqli_fetch_assoc($result);
         $response = array(
         'error' => false,
         'user' => $user
         );
         } else {
         $response = array(
        'error' => true,
        'message' => 'Invalid username or password'
         );
         }
         echo json_encode($response);
          ?>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // initialize The Variables
        animationViewLoading = findViewById(R.id.AnimLoadingLogin);
        animationViewLoading.setVisibility(View.GONE);
        tv = findViewById(R.id.signUpTV);
        email = findViewById(R.id.LoginEmail);
        pass= findViewById(R.id.LoginPass);
        Loginbtn = findViewById(R.id.LoginButton);

        // if user click Register Now TextView it will move him to preSignUp Page
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,PreSignUp.class);
                startActivity(intent);
            }
        });
        // Click on LoginButton will Move Us to call the Login Method
        Loginbtn.setOnClickListener(view -> {
             login();
        });
    }
    // Login method that's Responsible for Mange Users Login.....
    private void login() {
        // get the Email and password we get from The User
        final String Email = email.getText().toString();
        final String Password = pass.getText().toString();
        // we set the Login button as unVisible and we set the Loading Animation As visible
        Loginbtn.setVisibility(View.GONE);
        animationViewLoading.setVisibility(View.VISIBLE);
        // we Create A String Request With Post Request Method
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Loginbtn.setVisibility(View.VISIBLE);
                        animationViewLoading.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean error = jsonObject.getBoolean("error");
                            if (!error) {
                                JSONObject user = jsonObject.getJSONObject("user");
                                String userEmail = user.getString("email");
                                String userPassword = user.getString("password");
                                int id = user.getInt("id");
                                if(id>= 1000 && id< 100000){
                                     Intent intent = new Intent(LoginPage.this,MainActivityStudent.class);
                                     intent.putExtra("id",id);
                                     startActivity(intent);
                                     finish();
                                } else if (id>= 100000) {
                                    Intent intent = new Intent(LoginPage.this,BusinessHome.class);
                                    intent.putExtra("id",id);
                                    startActivity(intent);
                                    finish();
                                }else if(id>=2 && id<1000){
                                    Intent intent = new Intent(LoginPage.this,InstructorHome.class);
                                    intent.putExtra("id",id);
                                    startActivity(intent);
                                    finish();
                                } else if (id==1) {
                                    Intent intent = new Intent(LoginPage.this,AdminHome.class);
                                    intent.putExtra("id",id);
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
                                String message = jsonObject.getString("message");
                                Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Loginbtn.setVisibility(View.VISIBLE);
                        animationViewLoading.setVisibility(View.GONE);
                        Toast.makeText(LoginPage.this, "Please Check Your internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
            // we send the Email and Password to Server to check if they match......
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", Email);
                params.put("password",Password);
                return params;
            }
        };
        // Create A RequestQueue and add Our String Request To it....................
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}