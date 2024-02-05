package com.example.project01cs2340;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AssignmentApplication extends Application {
    private static List<Assignment> assignmentList = new ArrayList<>();
    public AssignmentApplication() {
        //fillAssignmentsList()
    }

    public static List<Assignment> getAssignmentList() {return assignmentList;}

    public static void setAssignmentList(List<Assignment> assignmentList) {
        AssignmentApplication.assignmentList = assignmentList;
    }
}
