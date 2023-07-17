package com.example.seniorprojecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusinessPostsRecyclerViewAdapter extends RecyclerView.Adapter<BusinessPostsRecyclerViewAdapter.RCViewHolder>{
    private final BRecyclerViewInterface bRecyclerViewInterface;
    Context context;
    ArrayList<JobData> modelArrayList;



    public BusinessPostsRecyclerViewAdapter(Context context, ArrayList<JobData> modelArrayList, BRecyclerViewInterface bRecyclerViewInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.bRecyclerViewInterface = bRecyclerViewInterface;
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.raw_des, parent, false);
        return new RCViewHolder(view,bRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        JobData model = modelArrayList.get(position);
        holder.JobName.setText(model.getJobname());
     //   holder.JobLoc.setText(model.getJobLoc());
    }
    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
    public static class RCViewHolder extends RecyclerView.ViewHolder {
        TextView JobName;

        public RCViewHolder(@NonNull View itemView,BRecyclerViewInterface bRecyclerViewInterface) {
            super(itemView);
            JobName = itemView.findViewById(R.id.JobName);
          //  JobLoc = itemView.findViewById(R.id.JT);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(bRecyclerViewInterface != null){
                       int pos = getAdapterPosition();
                       if(pos != RecyclerView.NO_POSITION){
                          bRecyclerViewInterface.onItemClick(pos);
                       }
                    }
                }
            });

        }
    }
}