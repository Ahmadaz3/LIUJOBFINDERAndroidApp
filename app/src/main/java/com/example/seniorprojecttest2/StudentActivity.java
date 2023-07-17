package com.example.seniorprojecttest2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
public class StudentActivity extends AppCompatActivity {
    // Declare the dataFields needed
     private EditText f_name , l_name,uni_Email,CreatePass,VerPass;
      private Button SignUp;
      private Spinner spMajor,SpCampus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        // set the Screen as Full Screen
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // created a request queue of type volley
        // the add job url of the php script that will add a job
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://seniorprojectahmadazimeh.000webhostapp.com/studentSignUp.php";
        /*
         <?php
             $First_name = addslashes(strip_tags($_POST['First_name']));
             $Last_name = addslashes(strip_tags($_POST['Last_name']));
             $Student_Email = addslashes(strip_tags($_POST['Student_Email']));
             $Password = addslashes(strip_tags($_POST['Password']));
             $Major = addslashes(strip_tags($_POST['Major']));
             $Campus= addslashes(strip_tags($_POST['Campus']));
             $con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
              // Check connection
              if (mysqli_connect_errno()) {
               echo "Failed to connect to MySQL: ". mysqli_connect_error();
               }
               // check information
                 $query_1 = "SELECT * FROM Student WHERE Student_Email='$Student_Email'";
                 $result = mysqli_query($con,$query_1);
                 if (mysqli_num_rows($result)<1) {
                 // Insert data into the Student table
                 $sql1 = "INSERT INTO Student(First_name,Last_name,Student_Email,Password,Major,Campus)
                 VALUES ('$First_name','$Last_name','$Student_Email','$Password','$Major','$Campus')";
                  mysqli_query($con, $sql1) or die("Can't add record to the Student table");
                  // Insert data into the user table
                 $sql2 = "INSERT INTO users(id,email,password)
                 VALUES (LAST_INSERT_ID(),'$Student_Email','$Password')";
                 mysqli_query($con, $sql2) or die("Can't add record to the users table");
                 echo "Record added successfully!";
                 } else {
                 echo "Record falied to add";
                }
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

        // we Create a TextChange Listener for passwords
        VerPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // if the Verify Password Text Not Match the Create Password Text
                if(!VerPass.getText().toString().equals(CreatePass.getText().toString())){
                    VerPass.setError("Password not match");
                }else{
                    // That's mean No Error......
                    VerPass.setError(null);
                }
            }
        });
        // we Create a TextChange Listener for Email to check if the Student Write the Email in Correct Format.........
        uni_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            // That is will check always When textChanges
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // we get the Email we enter
                String email = charSequence.toString().trim();
                // Check if the email contains 8 digits followed by @students.liu.edu.lb
                // we Use Regex to check
                if (!email.matches("^\\d{8}@students\\.liu\\.edu\\.lb$")) {
                    uni_Email.setError("Invalid email format");
                } else {
                    uni_Email.setError(null);
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
               Toast.makeText(this, "University Email not Correct", Toast.LENGTH_SHORT).show();
           }else if(VerPass.getError() != null) {
               Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
           }else if(!VerPass.getText().toString().equals(CreatePass.getText().toString())){
               Toast.makeText(this, "Passwords not match", Toast.LENGTH_SHORT).show();
           }else{
               // setEnabled as false since the user cannot send manyRequest at the same time before responses
               SignUp.setEnabled(false);
               // create a StringRequest
               StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // on responses all fields will be empty and A Toast Your request added will appear
                       if(response.toString().equals("Record falied to add")){
                           SignUp.setEnabled(true);
                           Toast.makeText(StudentActivity.this, "Sign Up error try Again", Toast.LENGTH_SHORT).show();
                           SignUp.setEnabled(true);
                       }else{
                           Toast.makeText(StudentActivity.this,"Sign Up Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(StudentActivity.this, LoginPage.class);
                           startActivity(intent);
                           finish();
                       }
                       // The button will be Enabled after the response accrued
                   }
               }
               , new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(StudentActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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
                       params.put("Student_Email", uni_Email.getText().toString());
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