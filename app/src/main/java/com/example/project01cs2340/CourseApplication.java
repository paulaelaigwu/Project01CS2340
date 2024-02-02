package com.example.project01cs2340;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CourseApplication extends Application {
    private static List<Course> courseList = new ArrayList<>();

    public CourseApplication() {
    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(List<Course> courseList) {
        CourseApplication.courseList = courseList;
    }
}
