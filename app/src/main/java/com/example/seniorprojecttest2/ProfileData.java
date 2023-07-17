package com.example.seniorprojecttest2;

public class ProfileData {
    private String stdName;
    private String stdEmail;
    private String stdMajor;
    private String stdCampus;
    private String stdEducation;
    private String stdSkills;
    private String stdImage;

    public ProfileData(String stdName, String stdEmail, String stdMajor, String stdCampus, String stdEducation, String stdSkills, String stdImage) {
        this.stdName = stdName;
        this.stdEmail = stdEmail;
        this.stdMajor = stdMajor;
        this.stdCampus = stdCampus;
        this.stdEducation = stdEducation;
        this.stdSkills = stdSkills;
        this.stdImage = stdImage;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdEmail() {
        return stdEmail;
    }

    public void setStdEmail(String stdEmail) {
        this.stdEmail = stdEmail;
    }

    public String getStdMajor() {
        return stdMajor;
    }

    public void setStdMajor(String stdMajor) {
        this.stdMajor = stdMajor;
    }

    public String getStdCampus() {
        return stdCampus;
    }

    public void setStdCampus(String stdCampus) {
        this.stdCampus = stdCampus;
    }

    public String getStdEducation() {
        return stdEducation;
    }

    public void setStdEducation(String stdEducation) {
        this.stdEducation = stdEducation;
    }

    public String getStdSkills() {
        return stdSkills;
    }

    public void setStdSkills(String stdSkills) {
        this.stdSkills = stdSkills;
    }

    public String getStdImage() {
        return stdImage;
    }

    public void setStdImage(String stdImage) {
        this.stdImage = stdImage;
    }
}
