package com.example.seniorprojecttest2;

import java.io.Serializable;

public class StudentData1 implements Serializable {
    private String Student_id;
    private String F_name;
    private String L_name;
    private String Std_Email;
    private String Major;
    private String Campus;
    private  String jobName;
    private String job_id;

    public StudentData1(String student_id, String f_name, String l_name, String std_Email, String major, String campus, String jobName, String job_id) {
        Student_id = student_id;
        F_name = f_name;
        L_name = l_name;
        Std_Email = std_Email;
        Major = major;
        Campus = campus;
        this.jobName = jobName;
        this.job_id = job_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public String getL_name() {
        return L_name;
    }

    public void setL_name(String l_name) {
        L_name = l_name;
    }

    public String getStd_Email() {
        return Std_Email;
    }

    public void setStd_Email(String std_Email) {
        Std_Email = std_Email;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String campus) {
        Campus = campus;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
