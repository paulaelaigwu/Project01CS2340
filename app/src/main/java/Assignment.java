public class Assignment {
    private String CourseID;
    private String CourseName;

    public Assignment(String courseID, String courseName) {
        CourseID = courseID;
        CourseName = courseName;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "CourseID='" + CourseID + '\'' +
                ", CourseName='" + CourseName + '\'' +
                '}';
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
