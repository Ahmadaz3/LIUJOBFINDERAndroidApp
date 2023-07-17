package com.example.seniorprojecttest2;

import java.io.Serializable;

public class JobData implements Serializable{
    private String jobId;
   // private String Post_Time;
    private String jobname;
    private String jobLoc;

    private String Job_Type;
    private  String WorkPlace_Type;
    private String job_req;
    private String job_desc;

    private String job_status;
    public JobData(String jobId, String jobname, String jobLoc, String job_Type, String workPlace_Type, String job_req, String job_desc) {
        this.jobId = jobId;

        this.jobname = jobname;
        this.jobLoc = jobLoc;
        Job_Type = job_Type;
        WorkPlace_Type = workPlace_Type;
        this.job_req = job_req;
        this.job_desc = job_desc;
    }
    public JobData(String jobId, String jobname, String jobLoc, String job_Type, String workPlace_Type, String job_req, String job_desc,String job_status) {
        this.jobId = jobId;
        this.jobname = jobname;
        this.jobLoc = jobLoc;
        Job_Type = job_Type;
        WorkPlace_Type = workPlace_Type;
        this.job_req = job_req;
        this.job_desc = job_desc;
        this.job_status = job_status;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJob_Type() {
        return Job_Type;
    }

    public void setJob_Type(String job_Type) {
        Job_Type = job_Type;
    }

    public String getWorkPlace_Type() {
        return WorkPlace_Type;
    }

    public void setWorkPlace_Type(String workPlace_Type) {
        WorkPlace_Type = workPlace_Type;
    }

    public String getJob_req() {
        return job_req;
    }

    public void setJob_req(String job_req) {
        this.job_req = job_req;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobLoc() {
        return jobLoc;
    }

    public void setJobLoc(String jobLoc) {
        this.jobLoc = jobLoc;
    }
}
