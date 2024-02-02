package com.example.project01cs2340;

public class Assignment implements Comparable<Assignment>{
    private String courseID;
    private String courseName;
    private String dateDue;

    public Assignment(String courseID, String courseName, String dateDue) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.dateDue = dateDue;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDateDue() {
        return dateDue;
    }
    @Override
    public int compareTo(Assignment other) {

        return this.dateDue.compareTo(other.dateDue);
    }
}
}
