package deprecated;

import deprecated.Department;
import model.Course;

import java.util.ArrayList;

public class Student {

    private String FirstName;
    private String LastName;
    private int StudentID;
    private boolean isDoubleMajor=false;
    private Department RegisteredDepartment;
    private Department DoubleMajorDepartment;
    private ArrayList<Course> CourseHistory;

    public Student(String firstName, String lastName, int studentID, Department registeredDepartment) {
        FirstName = firstName;
        LastName = lastName;
        StudentID = studentID;
        RegisteredDepartment = registeredDepartment;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public boolean isDoubleMajor() {
        return isDoubleMajor;
    }

    public void setDoubleMajor(boolean doubleMajor) {
        isDoubleMajor = doubleMajor;
    }

    public Department getRegisteredDepartment() {
        return RegisteredDepartment;
    }

    public void setRegisteredDepartment(Department registeredDepartment) {
        RegisteredDepartment = registeredDepartment;
    }

    public Department getDoubleMajorDepartment() {
        return DoubleMajorDepartment;
    }

    public void setDoubleMajorDepartment(Department doubleMajorDepartment) {
        DoubleMajorDepartment = doubleMajorDepartment;
    }

    public ArrayList<Course> getCourseHistory() {
        return CourseHistory;
    }

    public void setCourseHistory(ArrayList<Course> courseHistory) {
        CourseHistory = courseHistory;
    }
}
