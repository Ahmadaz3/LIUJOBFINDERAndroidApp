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

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationAdapterVh> {
    public List<RecommendData> recommendData = new ArrayList<>();
    public Context context;
    public RecommendationAdapter.UserClickListeners userClickListeners;
    public RecommendationAdapter(List<RecommendData> recommendData, Context context,UserClickListeners userClickListeners) {
        this.recommendData = recommendData;
        this.context = context;
        this.userClickListeners = userClickListeners;
    }
    public interface UserClickListeners{
        void selectUser(RecommendData recData);
    }
    @NonNull
    @Override
    public RecommendationAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recojobs,parent,false);
        return new RecommendationAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationAdapterVh holder, int position) {
        RecommendData rec =recommendData.get(position);
        holder.job.setText(rec.getJob_Name()+"\nRecommend By: "+rec.getInstructor_Name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListeners.selectUser(rec);
            }
        });

    }
    @Override
    public int getItemCount() {
        return recommendData.size();
    }

    public static class RecommendationAdapterVh extends  RecyclerView.ViewHolder{
        private TextView job;
        public RecommendationAdapterVh(@NonNull View itemView) {
            super(itemView);
            job = itemView.findViewById(R.id.JobNameAndRecommendation);
        }
    }

}
