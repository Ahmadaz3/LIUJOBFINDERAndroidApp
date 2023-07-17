package com.example.seniorprojecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentPostsRecyclerView extends RecyclerView.Adapter<StudentPostsRecyclerView.RCViewHolder> {
    private final SRecyclerViewInterface sRecyclerViewInterface;
    Context context;
    ArrayList<JobData> modelArrayList;
   // public List<JobData> getUserModelFilter = new ArrayList<>();

    public StudentPostsRecyclerView(Context context, ArrayList<JobData> modelArrayList, SRecyclerViewInterface sRecyclerViewInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
       // this.getUserModelFilter = modelArrayList;
        this.sRecyclerViewInterface = sRecyclerViewInterface;
    }
    @NonNull
    @Override
    public StudentPostsRecyclerView.RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.raw_des, parent, false);
        return new StudentPostsRecyclerView.RCViewHolder(view,sRecyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull StudentPostsRecyclerView.RCViewHolder holder, int position) {
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

        public RCViewHolder(@NonNull View itemView,SRecyclerViewInterface sRecyclerViewInterface) {
            super(itemView);
            JobName = itemView.findViewById(R.id.JobName);
            //  JobLoc = itemView.findViewById(R.id.JT);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sRecyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            sRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
