package com.example.project01cs2340;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseApplication extends Application {
    private static List<Course> coursesList = new ArrayList<Course>();

    public CourseApplication() {
        //fillCoursesList();
    }
    //private void fillCoursesList() {
        //Course course0 = new Course("1111", "MATH", "A", "M", "12:30pm");
        //Course course1 = new Course("2222", "CS", "B", "M", "12:30pm");
        //Course course2 = new Course("3333", "ENGL", "C", "M", "12:30pm");
        //Course course3 = new Course("4444", "PSYC", "D", "M", "12:30pm");
        //Course course4 = new Course("5555", "PHYS", "E", "M", "12:30pm");

        //coursesList.addAll(Arrays.asList(new Course[] {course0, course1, course2, course3, course4}));
    //}

    public static List<Course> getCoursesList() {
        return coursesList;
    }

    public static void setCoursesList(List<Course> coursesList) {
        CourseApplication.coursesList = coursesList;
    }
}