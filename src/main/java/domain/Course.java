package domain;

public class Course {
    private String courseCode;   //comp
    private String courseNumber; //302
    private Boolean hasLab;
    private Boolean hasPS_DS;


    public Course(String courseCode,String courseNumber) {
        this.courseCode = courseCode;
        this.courseNumber = courseNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Boolean getHasLab() {
        return hasLab;
    }

    public void setHasLab(Boolean hasLab) {
        this.hasLab = hasLab;
    }

    public Boolean getHasPS_DS() {
        return hasPS_DS;
    }

    public void setHasPS_DS(Boolean hasPS_DS) {
        this.hasPS_DS = hasPS_DS;
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
