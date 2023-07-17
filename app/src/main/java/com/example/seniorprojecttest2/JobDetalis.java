package com.example.seniorprojecttest2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class JobDetalis extends AppCompatActivity {
   private TextView tvNameAndLoc,tvJtyepandWtype,jobdesc,jobReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detalis);
        ActionBar actionBar = getSupportActionBar();
        // Change the background color
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.frame_color));
            actionBar.setBackgroundDrawable(colorDrawable);

            // Change the title
            String title = "Job Details";
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.action_color)),
                    0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(spannableString);
        }
        tvNameAndLoc = findViewById(R.id.txtJNameAndJLoc);
        tvJtyepandWtype = findViewById(R.id.txtTypeandWorkType);
        jobdesc = findViewById(R.id.Jdescview);
        jobReq = findViewById(R.id.jReqview);
        Intent intent = getIntent();
        String jobName = intent.getStringExtra("Jname");
        String jobLoc = intent.getStringExtra("jLoc");
        String jobType = intent.getStringExtra("jtype");
        String jobWPlaceType = intent.getStringExtra("jworkType");
        String JobReqier = intent.getStringExtra("jReq");
        String JobDesc = intent.getStringExtra("jDesc");
       tvNameAndLoc.setText(jobName+"\n"+jobLoc);
        tvJtyepandWtype.setText(jobType+" - "+jobWPlaceType);
        jobdesc.setText(" - "+JobDesc);
        jobReq.setText(JobReqier);

    }
}