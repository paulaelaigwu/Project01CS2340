package com.example.project01cs2340;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ExamApplication extends Application {
    private static List<ExamModel> examList = new ArrayList<>();

    public ExamApplication() {
    }

    public static List<ExamModel> getExamList() {
        return examList;
    }

    public static void setCourseList(List<ExamModel> examList) {
        ExamApplication.examList = examList;
    }
}
