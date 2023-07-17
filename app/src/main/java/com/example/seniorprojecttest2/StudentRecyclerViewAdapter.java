package com.example.seniorprojecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.StudentRecyclerViewAdapterVh> {
    public List<StudentData1> stdData = new ArrayList<>();
    public Context context;
    public UserClickListeners userClickListeners;
    public interface UserClickListeners{
        void selectUser(StudentData1 studentData1);
    }
    public StudentRecyclerViewAdapter(List<StudentData1> stdData, Context context,UserClickListeners userClickListeners) {
        this.stdData = stdData;
        this.context = context;
        this.userClickListeners = userClickListeners;
    }

    @NonNull
    @Override
    public StudentRecyclerViewAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.raw_designinterviews,parent,false);
        return new StudentRecyclerViewAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRecyclerViewAdapterVh holder, int position) {
     StudentData1 studentData1 = stdData.get(position);
     String StudentName = studentData1.getF_name()+" "+studentData1.getL_name();
     String StudentCampus = studentData1.getCampus();
     String StudentMajor = studentData1.getMajor();
     String JobName = studentData1.getJobName();
     holder.stdName.setText("Candidate Name: "+StudentName);
     holder.stdCampus.setText("Base Campus: "+StudentCampus);
     holder.stdMajor.setText("Major: "+StudentMajor);
     holder.jbName.setText("Applied For: "+JobName);
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             userClickListeners.selectUser(studentData1);
         }
     });

    }

    @Override
    public int getItemCount() {
        return stdData.size();
    }

    public static class StudentRecyclerViewAdapterVh extends RecyclerView.ViewHolder {
          private TextView stdName,stdCampus,stdMajor,jbName;
        public StudentRecyclerViewAdapterVh(@NonNull View itemView) {
            super(itemView);
            stdName = itemView.findViewById(R.id.InterViewDetalis);
            stdCampus = itemView.findViewById(R.id.StudentCampusInterview);
            stdMajor = itemView.findViewById(R.id.StudentMajorInterview);
            jbName = itemView.findViewById(R.id.StudentAplliedFor);
        }
    }































}
