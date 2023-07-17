package com.example.seniorprojecttest2;

import java.io.Serializable;

public class RecommendData implements Serializable {
    private String job_id;
    private String job_Name;
    private String job_Location;
    private String job_type;
    private  String WorkPlace_Type;
    private String job_Req;
    private String job_Desc;
    private String Instructor_Name;

    public RecommendData(String job_id, String job_Name, String job_Location, String job_type, String workPlace_Type, String job_Req, String job_Desc, String instructor_Name) {
        this.job_id = job_id;
        this.job_Name = job_Name;
        this.job_Location = job_Location;
        this.job_type = job_type;
        WorkPlace_Type = workPlace_Type;
        this.job_Req = job_Req;
        this.job_Desc = job_Desc;
        Instructor_Name = instructor_Name;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_Name() {
        return job_Name;
    }

    public void setJob_Name(String job_Name) {
        this.job_Name = job_Name;
    }

    public String getJob_Location() {
        return job_Location;
    }

    public void setJob_Location(String job_Location) {
        this.job_Location = job_Location;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getWorkPlace_Type() {
        return WorkPlace_Type;
    }

    public void setWorkPlace_Type(String workPlace_Type) {
        WorkPlace_Type = workPlace_Type;
    }

    public String getJob_Req() {
        return job_Req;
    }

    public void setJob_Req(String job_Req) {
        this.job_Req = job_Req;
    }

    public String getJob_Desc() {
        return job_Desc;
    }

    public void setJob_Desc(String job_Desc) {
        this.job_Desc = job_Desc;
    }

    public String getInstructor_Name() {
        return Instructor_Name;
    }

    public void setInstructor_Name(String instructor_Name) {
        Instructor_Name = instructor_Name;
    }
}
