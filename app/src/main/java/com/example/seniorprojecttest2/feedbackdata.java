package com.example.seniorprojecttest2;

public class feedbackdata {
    private String feedText;
    private String StudentEmail;

    public feedbackdata(String feedText, String studentEmail) {
        this.feedText = feedText;
        StudentEmail = studentEmail;
    }
    public String getFeedText() {
        return feedText;
    }

    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }
}
