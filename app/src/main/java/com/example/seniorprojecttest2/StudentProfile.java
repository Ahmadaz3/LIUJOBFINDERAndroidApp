package com.example.seniorprojecttest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class StudentProfile extends AppCompatActivity {
    ImageView img;
    Uri selecteduri;
    Bitmap bitmp;
    String encodeImage;
    RequestQueue queue;
    private Button updatePro;
    private EditText perEmail,perEducation,perLoc,perExperience;
    public  String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        img = findViewById(R.id.chooseImage);
        perEmail=findViewById(R.id.Personal_Email);
        perEducation = findViewById(R.id.Education);
        perLoc = findViewById(R.id.Location);
        perExperience = findViewById(R.id.Experience);
        updatePro= findViewById(R.id.UpdateProfle);
         queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String stdID = intent.getStringExtra("std_id");
        url ="https://seniorprojectahmadazimeh.000webhostapp.com/getProfileData.php?std_id="+stdID;
        //String StudentImage = intent.getStringExtra("std_img");
        // Decode the image string to a byte array
        if(ImageDataHolder.studentImage != null) {
         byte[] imageBytes = Base64.decode(ImageDataHolder.studentImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if(bitmap!= null){
                img.setImageBitmap(bitmap);
            }
           }
        perEmail.addTextChangedListener(new TextWatcher() {
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
                    perEmail.setError("Email is required");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    perEmail.setError("Invalid email format");
                } else {
                    perEmail.setError(null);  // Clear any previous error
                }
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });
      //  String url;
       // JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url, new Response.Listener<String>()){}
        updatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(encodeImage == ""){
                    Toast.makeText(StudentProfile.this, "Please Upload an Image", Toast.LENGTH_SHORT).show();
                }else if (perEmail.getError() != null) {
                    // Email has an error (invalid format)
                    Toast.makeText(StudentProfile.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                }else{
                    // Upload the image to the server

                    String uploadImage ="https://seniorprojectahmadazimeh.000webhostapp.com/StudentProfile2.php";
                    StringRequest request = new StringRequest(Request.Method.POST, uploadImage, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // on responses all fields will be empty and A Toast Your request added will appear
                            Toast.makeText(StudentProfile.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(StudentProfile.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            // create an Object of hashMap called params to send the data we get it
                            // from the Edits texts and insert it as there Keys to the database
                            Map<String, String> params = new HashMap<>();
                            params.put("perEmail",perEmail.getText().toString());
                            params.put("Edu",perEducation.getText().toString());
                            params.put("Loc",perLoc.getText().toString());
                            params.put("Exp",perExperience.getText().toString());
                            params.put("std",stdID);
                            if(encodeImage == null){
                                params.put("imageUpload",ImageDataHolder.studentImage);
                            }else{
                                params.put("imageUpload",encodeImage);
                            }


                            return params;
                        }
                    };
                    queue.add(request);
                }
            }
        });
        getData();
    }

    private void ChooseImage() {
        ImagePicker.with(this)
                .cropSquare()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            selecteduri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(selecteduri);
                bitmp = BitmapFactory.decodeStream(inputStream);
                img.setImageURI(selecteduri);
                ImageStore(bitmp);

                // You now have the bitmap and the encoded string
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private void ImageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte [] imagebyte = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
        // Upload the image to the server
    }
    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject row = response.getJSONObject(i);
                            String Personal_Email = row.getString("Personal_Email");
                            String Education = row.getString("Education");
                            //  String post_Time = row.getString("post_time");
                            String Loc = row.getString("Location");
                            String Exp = row.getString("Experience");
                            String image = row.getString("Image");
                            perEmail.setText(Personal_Email);
                            perEducation.setText(Education);
                            perLoc.setText(Loc);
                            perExperience.setText(Exp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());
       // RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        queue.add(jsonArrayRequest);
    }
}