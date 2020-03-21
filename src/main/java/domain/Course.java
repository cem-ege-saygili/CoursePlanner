package domain;

public class Course implements Comparable<Course> {
    private String courseCode;   //comp
    private String courseNumber; //302
    private Boolean hasLab;
    private Boolean hasPS_DS;
    private int startTimeStamp;
    private int endTimeStamp;
    private int priority;

    public Course(String courseCode, int startTimeStamp, int endTimeStamp, int priority) {
        this.courseCode = courseCode;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.priority = priority;
    }

    public Course(String courseCode,String courseNumber) {
        this.courseCode = courseCode;
        this.courseNumber = courseNumber;
    }

    public int getPriority(){
        return priority;
    }

    public int getStartTimeStamp(){
        return startTimeStamp;
    }

    public boolean isOverlappingWith(Course c2){
        return (this.getEndTimeStamp() == c2.getEndTimeStamp()) || (c2.getStartTimeStamp() <= this.getEndTimeStamp());
    }

    public int getEndTimeStamp(){
        return endTimeStamp;
    }

    public int compareTo(Course c2){
        return (this.getEndTimeStamp() < c2.getEndTimeStamp() ? -1 :
                (this.getEndTimeStamp() == c2.getEndTimeStamp() ? 0 : 1));
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
