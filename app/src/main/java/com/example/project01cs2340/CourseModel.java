package com.example.project01cs2340;

public class CourseModel {
    private String course_title;
    private String course_professor;
    private int course_time;

    // Constructor

    public CourseModel(String course_title, String course_professor, int course_time) {
        this.course_title = course_title;
        this.course_professor = course_professor;
        this.course_time = course_time;
    }

    // Getters and Setters
    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_professor() {
        return course_professor;
    }

    public void setCourse_professor(String course_professor) {
        this.course_professor = course_professor;
    }

    public int getCourse_time() {
        return course_time;
    }

    public void setCourse_time(int course_time) {
        this.course_time = course_time;
    }

}
