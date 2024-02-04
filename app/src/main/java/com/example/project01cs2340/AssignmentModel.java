package com.example.project01cs2340;

public class AssignmentModel {
    private String courseId;
    private String assignmentName;
    private String dueDate;

    public AssignmentModel(String courseId, String assignmentName, String dueDate) {
        this.courseId = courseId;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
