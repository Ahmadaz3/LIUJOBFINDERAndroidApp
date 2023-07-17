package com.example.seniorprojecttest2;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesAdapterVh> {
    public List<ResourcesData> resourcesData = new ArrayList<>();
    public Context context;

    public ResourcesAdapter(List<ResourcesData> resourcesData, Context context) {
        this.resourcesData = resourcesData;
        this.context = context;
    }

    @NonNull
    @Override
    public ResourcesAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.resourcesforstudents,parent,false);
        return new ResourcesAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesAdapterVh holder, int position) {
         ResourcesData res = resourcesData.get(position);
         holder.txtResName.setText(res.getResource_name()+" :");
         holder.txtResContant.setText(res.getResource_contant());
         holder.txtResContant.setAutoLinkMask(Linkify.WEB_URLS);
        holder.txtResContant.setMovementMethod(LinkMovementMethod.getInstance());

        // tvWeb.setAutoLinkMask(Linkify.WEB_URLS);
        //tvWeb.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return resourcesData.size();
    }

    public static class ResourcesAdapterVh extends RecyclerView.ViewHolder{
       private TextView txtResName,txtResContant;
        public ResourcesAdapterVh(@NonNull View itemView) {
            super(itemView);
            txtResName = itemView.findViewById(R.id.ResName);
            txtResContant = itemView.findViewById(R.id.ResContant);
        }
    }
}
