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

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorAdapterVh> {
    public List<JobData> jobData = new ArrayList<>();
    public Context context;
    public InstructorAdapter.UserClickListeners userClickListeners;
    public InstructorAdapter(List<JobData> jobData, Context context,UserClickListeners userClickListeners) {
        this.jobData = jobData;
        this.context = context;
        this.userClickListeners = userClickListeners;
    }
    public interface UserClickListeners{
        void selectUser(JobData jobData);
    }
    @NonNull
    @Override
    public InstructorAdapter.InstructorAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view  = LayoutInflater.from(context).inflate(R.layout.raw_des,parent,false);
        return new InstructorAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorAdapter.InstructorAdapterVh holder, int position) {
      JobData data = jobData.get(position);
      //int id = InstructorHome.id;
      holder.JobName.setText(data.getJobname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListeners.selectUser(data);
            }
        });
    }
    @Override
    public int getItemCount() {
        return jobData.size();
    }
    public static class InstructorAdapterVh extends  RecyclerView.ViewHolder{
        private TextView JobName;
        public InstructorAdapterVh(@NonNull View itemView) {
            super(itemView);
            JobName = itemView.findViewById(R.id.JobName);
        }
    }
}
