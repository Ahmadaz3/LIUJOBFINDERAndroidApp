package com.example.seniorprojecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class feedbackAdapter extends RecyclerView.Adapter<feedbackAdapter.FeedbackViewHolder> {
    private List<feedbackdata> feedbackDataList;
    private Context context;

    public feedbackAdapter(List<feedbackdata> feedbackDataList, Context context) {
        this.feedbackDataList = feedbackDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.raw_des, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        feedbackdata feeddata = feedbackDataList.get(position);
        holder.feedbackNameAndFrom.setText("FeedBack Message: \n"+feeddata.getFeedText()+"\n" +
                "From: "+feeddata.getStudentEmail());
    }

    @Override
    public int getItemCount() {
        return feedbackDataList.size();
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        private TextView feedbackNameAndFrom;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackNameAndFrom = itemView.findViewById(R.id.JobName);
        }
    }
}
