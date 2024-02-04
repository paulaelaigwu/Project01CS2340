package com.example.project01cs2340;

public class ExamModel {
    private String courseCode;
    private String examName;
    private String date;
    private String time;
    private String location;

    public ExamModel(String courseCode, String examName, String date, String time, String location) {
        this.courseCode = courseCode;
        this.examName = examName;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
