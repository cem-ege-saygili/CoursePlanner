package domain;

import java.util.List;

public class CourseBundle {

    private String Subject;
    private int Catalog;
    private List<int[]> timeExtentList;

    public CourseBundle(String Subject,
            int Catalog,
            List<int[]> timeExtentList){

        this.Catalog = Catalog;
        this.Subject = Subject;
        this.timeExtentList = timeExtentList;

    }

    public boolean isCompatibleWith(CourseBundle b2){
        // A bundle of a course is incompatible with a bundle with the same course.
        if (b2.Subject == this.Subject) return false;
        if (notOverlappingWith(b2)) return false;
        return true;
    }

    private boolean notOverlappingWith(CourseBundle b2){
        boolean notOverlapping = true;
        for(int[] thisCurrTimeFrame:this.timeExtentList){
            for(int[] b2CurrTimeFrame:b2.timeExtentList){
                int thisCurrTimeFrameStart = thisCurrTimeFrame[0];
                int thisCurrTimeFrameEnd = thisCurrTimeFrame[1];
                int b2CurrTimeFrameStart = b2CurrTimeFrame[0];
                int b2CurrTimeFrameEnd = b2CurrTimeFrame[1];

                boolean isCurrTimeFramesOverlapping =   thisCurrTimeFrameEnd >= b2CurrTimeFrameStart &&  b2CurrTimeFrameEnd>=thisCurrTimeFrameEnd    ||
                                                        thisCurrTimeFrameStart<=b2CurrTimeFrameEnd && b2CurrTimeFrameStart<=thisCurrTimeFrameStart ||
                        thisCurrTimeFrameStart>b2CurrTimeFrameStart && thisCurrTimeFrameEnd< b2CurrTimeFrameEnd ||
                        thisCurrTimeFrameStart<b2CurrTimeFrameStart && b2CurrTimeFrameEnd<thisCurrTimeFrameEnd;

                if(isCurrTimeFramesOverlapping){
                    notOverlapping = false;
                }

            }


        }
        return notOverlapping;
    }

}
