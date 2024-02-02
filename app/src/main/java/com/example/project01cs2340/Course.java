package com.example.project01cs2340;

import android.graphics.Color;

public class Course {
    private String id;

    private String title;

    private String professor;

    private String days;

    private String time;

    public Course(String id, String title, String professor, String days, String time) {
        this.id = id;
        this.title = title;
        this.professor = professor;
        this.days = days;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", professor='" + professor + '\'' +
                ", days='" + days + '\'' +
                ", time=" + time +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}