package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class InstructorActivity extends AppCompatActivity {
   private EditText f_name , l_name,uni_Email,CreatePass,VerPass;
    private Button SignUp;
    private Spinner spMajor,SpCampus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        // set the Screen as Full Screen
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // created a request queue of type volley
        // the add job url of the php script that will add a job
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/InstructorSignUp.php";
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
        // get the items
        f_name = findViewById(R.id.insFname);
        l_name = findViewById(R.id.insLastname);
        uni_Email = findViewById(R.id.InsUniEmail);
        CreatePass = findViewById(R.id.InsCPass);
        VerPass = findViewById(R.id.InsVpass);
        spMajor = findViewById(R.id.insSpMajor);
        SpCampus = findViewById(R.id.insSpCampus);
        SignUp = findViewById(R.id.SpInsBt);
        VerPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!VerPass.getText().toString().equals(CreatePass.getText().toString())){
                    VerPass.setError("Password not match");
                }else{
                    VerPass.setError(null);
                }
            }
        });
        uni_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String emailPattern = "^[a-zA-Z]+(\\.[a-zA-Z]+)?@liu\\.edu\\.lb$";
                String email = uni_Email.getText().toString().trim();

                if (!email.matches(emailPattern)) {
                    // display an error message to the user
                    uni_Email.setError("Invalid email format. Please enter an email in the format 'khaled.khalil@liu.edu.lb'");
                } else {
                    // the email is valid
                    // continue with your code
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // when the user click the button
        SignUp.setOnClickListener(view -> {
            if(f_name.getText().toString().equals("") || l_name.getText().toString().equals("") || uni_Email.getText().toString().equals("")
                    || CreatePass .getText().toString().equals("") || VerPass.getText().toString().equals("")){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }else if(uni_Email.getError() != null) {
                Toast.makeText(this, "Uni Email not Correct", Toast.LENGTH_SHORT).show();
            }else if(VerPass.getError() != null) {
                Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }else if(!VerPass.getText().toString().equals(CreatePass.getText().toString())){
                Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }else{
                // setEnabled as false since the user cannot send manyRequest at the same time before responses
                SignUp.setEnabled(false);
                // create a StringRequest
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // on responses all fields will be empty and A Toast Your request added will appear
                        Toast.makeText(InstructorActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InstructorActivity.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                        // The button will be Enabled after the response accrued
                        SignUp.setEnabled(true);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InstructorActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // create an Object of hashMap called params to send the data we get it
                        // from the Edits texts and insert it as there Keys to the database
                        Map<String, String> params = new HashMap<>();
                        params.put("First_name", f_name.getText().toString());
                        params.put("Last_name", l_name.getText().toString());
                        params.put("Instructort_Email", uni_Email.getText().toString());
                        params.put("Password", CreatePass.getText().toString());
                        params.put("Major", spMajor.getSelectedItem().toString());
                        params.put("Campus", SpCampus.getSelectedItem().toString());
                        return params;
                    }
                };
                queue.add(request);
            }
        });
    }
}