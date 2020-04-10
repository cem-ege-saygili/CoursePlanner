package domain;

import java.util.List;

public class CourseBundle {

    private String Subject;
    private int Catalog;
    private List<int[]> timeExtent;

    public int compareTo(CourseBundle b2){
        return (this.getEndTimeStamp() < b2.getEndTimeStamp() ? -1 :
                (this.getEndTimeStamp() == c2.getEndTimeStamp() ? 0 : 1));
    }

}
