package com.example.seniorprojecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminRequestsRecyclerViewAdapter extends RecyclerView.Adapter<AdminRequestsRecyclerViewAdapter.RCViewHolder> {

    private final ARecyclerViewInterface aRecyclerViewInterface;
    private Context context;
    private ArrayList<JobData1> jobData;

    public AdminRequestsRecyclerViewAdapter(Context context, ArrayList<JobData1> jobData, ARecyclerViewInterface aRecyclerViewInterface) {
        this.context = context;
        this.jobData = jobData;
        this.aRecyclerViewInterface = aRecyclerViewInterface;
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.raw_des, parent, false);
        return new RCViewHolder(view, aRecyclerViewInterface);
    }



    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        JobData1 model = jobData.get(position);
        holder.jobName.setText(model.getJobname());

    }

    @Override
    public int getItemCount() {
        return jobData.size();
    }

    public static class RCViewHolder extends RecyclerView.ViewHolder {

        TextView jobName;
        public RCViewHolder(@NonNull View itemView, ARecyclerViewInterface aRecyclerViewInterface) {
            super(itemView);
            jobName = itemView.findViewById(R.id.JobName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (aRecyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            aRecyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

