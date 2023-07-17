package com.example.seniorprojecttest2;

import java.io.Serializable;

public class InstructorData implements Serializable {
    private  String instructor_id;
    private String First_name;
    private String Last_name;
    private  String instructor_Email;
    private  String Major;
    private String Campus;

    public InstructorData(String instructor_id, String first_name, String last_name, String instructor_Email, String major, String campus) {
        this.instructor_id = instructor_id;
        First_name = first_name;
        Last_name = last_name;
        this.instructor_Email = instructor_Email;
        Major = major;
        Campus = campus;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getInstructor_Email() {
        return instructor_Email;
    }

    public void setInstructor_Email(String instructor_Email) {
        this.instructor_Email = instructor_Email;
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
}
