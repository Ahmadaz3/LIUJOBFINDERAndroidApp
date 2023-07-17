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

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.InterviewAdapterVh> {
    public List<InterviewData> interData = new ArrayList<>();
    public Context context;
    public InterviewAdapter.UserClickListeners userClickListeners;

    @NonNull
    @Override
    public InterviewAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rawinterdesign,parent,false);
        return new InterviewAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterviewAdapterVh holder, int position) {
        InterviewData interviewData =interData.get(position);
        String interviewDet = "You have an InterView Appointment" +"\n"+
                               "From: "+interviewData.getBusinessName()+"\n" +
                               "For The Job: "+interviewData.getJobName();
        holder.interDetails.setText(interviewDet);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListeners.selectUser(interviewData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return interData.size();
    }

    public interface UserClickListeners{
        void selectUser(InterviewData interviewData);
    }

    public InterviewAdapter(List<InterviewData> interData, Context context, UserClickListeners userClickListeners) {
        this.interData = interData;
        this.context = context;
        this.userClickListeners = userClickListeners;
    }

    public static class InterviewAdapterVh extends  RecyclerView.ViewHolder{
        private TextView interDetails;
        public InterviewAdapterVh(@NonNull View itemView) {
            super(itemView);
            interDetails = itemView.findViewById(R.id.InterViewDetalis);
        }
    }



}
