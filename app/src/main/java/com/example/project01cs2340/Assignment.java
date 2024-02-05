package com.example.project01cs2340;

public class Assignment {
    private String course;
    private String assign;
    private String date;

    public Assignment(String course, String assign, String date) {
        this.course = course;
        this.assign = assign;
        this.date = date;

    }

    @Override
    public String toString() {
        return "Assignment{" +
                "course='" + course + '\'' +
                ", assign='" + assign + '\'' +
                ", date='" + date +
                '}';
    }

    public String getID() {return course; }
    public void setID(String course) {this.course = course;}
    public String getAssignment() { return assign; }
    public void setAssignment(String assign) {this.assign = assign;}
    public String getDueDate() {return date; }
    public void setDate(String date) {this.date = date;}
}
