package com.example.seniorprojecttest2;

import java.io.Serializable;

public class InterviewData  implements Serializable {

    private String std_id;
    private String job_id;
    private String bus_id;
    private String interviewLoc;
    private String interviewDateAndTime;
    private String interViewStatus;
    private String jobName;
    private String BusinessName;

    public InterviewData(String std_id, String job_id, String bus_id, String interviewLoc, String interviewDateAndTime, String interViewStatus, String jobName, String businessName) {
        this.std_id = std_id;
        this.job_id = job_id;
        this.bus_id = bus_id;
        this.interviewLoc = interviewLoc;
        this.interviewDateAndTime = interviewDateAndTime;
        this.interViewStatus = interViewStatus;
        this.jobName = jobName;
        BusinessName = businessName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getInterviewLoc() {
        return interviewLoc;
    }

    public void setInterviewLoc(String interviewLoc) {
        this.interviewLoc = interviewLoc;
    }

    public String getInterviewDateAndTime() {
        return interviewDateAndTime;
    }

    public void setInterviewDateAndTime(String interviewDateAndTime) {
        this.interviewDateAndTime = interviewDateAndTime;
    }

    public String getInterViewStatus() {
        return interViewStatus;
    }

    public void setInterViewStatus(String interViewStatus) {
        this.interViewStatus = interViewStatus;
    }
}
