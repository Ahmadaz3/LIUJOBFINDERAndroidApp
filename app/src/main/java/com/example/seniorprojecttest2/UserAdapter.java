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


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterVh> implements Filterable {
    public List<JobData2> userModelList= new ArrayList<>();
    public List<JobData2> getUserModelListFilter = new ArrayList<>();
    public Context context;
    UserClickListener userClickListener;
    public UserAdapter(List<JobData2> userModels, Context context, UserClickListener userClickListener) {
        this.userModelList = userModels;
        this.context = context;
        this.getUserModelListFilter = userModels;
        this.userClickListener = userClickListener;
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length()==0){
                    filterResults.values =getUserModelListFilter;
                    filterResults.count =getUserModelListFilter.size();
                }else{
                     String searchStr =charSequence.toString().toLowerCase();
                     List<JobData2> userModles = new ArrayList<>();
                     for(JobData2 data:getUserModelListFilter){
                         if(data.getJobname().toLowerCase().contains(searchStr)||data.getJobLoc().toLowerCase().contains(searchStr)||
                             data.getJob_Type().toLowerCase().contains(searchStr) ||data.getJob_Type().toLowerCase().contains(searchStr)
                             || data.getWorkPlace_Type().toLowerCase().contains(searchStr) ||
                              data.getBusiness_name().toLowerCase().contains(searchStr)){
                             userModles.add(data);
                         }
                     }
                     filterResults.values = userModles;
                     filterResults.count = userModles.size();

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userModelList = (List<JobData2>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface UserClickListener{
       void SelectedUser(JobData2 userModel);
   }



    @NonNull
    @Override
    public UserAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.raw_design,parent,false);
        return new UserAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVh holder, int position) {
        JobData2 user = userModelList.get(position);
       // String firstName = user.getJobname();
       // String lastName = user.getJob_Type();
       // String Userphone = user.getJobId();
        //String prefix = firstName.charAt(0)+" "+lastName.charAt(0);
        holder.JobName.setText(user.getJobname());
        holder.JOBlOC.setText(user.getJobLoc());
        holder.Buss_name.setText(user.getBusiness_name());
        holder.jobTAndW.setText("("+user.getJob_Type()+")"+" , ("+user.getWorkPlace_Type()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userClickListener.SelectedUser(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public static  class UserAdapterVh extends RecyclerView.ViewHolder {
        private TextView JobName;
        private TextView JOBlOC;
        private TextView jobTAndW;
        private TextView Buss_name;

        public UserAdapterVh(@NonNull View itemView) {
            super(itemView);
            JobName = itemView.findViewById(R.id.InterViewDetalis);
            Buss_name = itemView.findViewById(R.id.StudentCampusInterview);
            JOBlOC = itemView.findViewById(R.id.StudentMajorInterview);
            jobTAndW = itemView.findViewById(R.id.StudentAplliedFor);
        }

    }

}
