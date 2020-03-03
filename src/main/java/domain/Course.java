package domain;

public class Course {

    private String courseCode;   //comp
    private String courseNumber; //302

    public Course(String courseCode,String courseNumber) {
        this.courseCode = courseCode;
        this.courseNumber = courseNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }


}
