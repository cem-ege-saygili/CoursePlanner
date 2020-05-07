package deprecated;

import model.Course;

import java.util.ArrayList;

public class Plan {

    private ArrayList<Course> courseList;

    public Plan(ArrayList<Course> courseList){
        this.courseList = courseList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course c){
        courseList.add(c);
    }

    @Override
    public String toString() {
        int count = 0;
        String str = "";
        for(Course c:courseList){
            str += "\t" + ++count + ". course ''" + c.getCourseCode() +  "'' starts @t" + c.getStartTimeStamp() + "  -  ends @t" + c.getEndTimeStamp()
                    + " and has the priority: " + c.getPriority() + "\n";
        }
        return str;
    }
}
