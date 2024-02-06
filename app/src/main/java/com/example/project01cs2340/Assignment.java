package com.example.project01cs2340;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Assignment {
    private String courseId;
    private String assign;
    private String date;

    public Assignment(String course, String assign, String date) {
        this.courseId = course;
        this.assign = assign;
        this.date = date;

    }

    public static Comparator<Assignment> AssignmentCourseIdComparator = new Comparator<Assignment>() {
        @Override
        public int compare(Assignment a1, Assignment a2) {
            return a1.getID().compareTo(a2.getID());
        }
    };
    public static Comparator<Assignment> AssignmentDueDateComparator = new Comparator<Assignment>() {
        @Override
        public int compare(Assignment a1, Assignment a2) {
            String pattern = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date d1 = null;
            try {
                d1 = simpleDateFormat.parse(a1.getDueDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Date d2 = null;
            try {
                d2 = simpleDateFormat.parse(a2.getDueDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Log.d("Assingment", "compare: " + d1.toString() + d2.toString());
            return d1.compareTo(d2);
        }
    };


    @Override
    public String toString() {
        return "Assignment{" +
                "course='" + courseId + '\'' +
                ", assign='" + assign + '\'' +
                ", date='" + date +
                '}';
    }

    public String getID() {return courseId; }
    public void setID(String course) {this.courseId = course;}
    public String getAssignment() { return assign; }
    public void setAssignment(String assign) {this.assign = assign;}
    public String getDueDate() {return date; }
    public void setDate(String date) {this.date = date;}
}
