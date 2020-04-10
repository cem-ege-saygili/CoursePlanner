package domain;

import java.util.List;

public class CourseBundle {

    private String Subject;
    private int Catalog;
    private List<int[]> timeExtentList;

//    public int compareTo(CourseBundle b2){
//        return (this.getEndTimeStamp() < b2.getEndTimeStamp() ? -1 :
//                (this.getEndTimeStamp() == c2.getEndTimeStamp() ? 0 : 1));
//    }

    public CourseBundle(String Subject,
            int Catalog,
            List<int[]> timeExtentList){

        this.Catalog = Catalog;
        this.Subject = Subject;
        this.timeExtentList = timeExtentList;

    }

    private boolean notOverlappingWith(CourseBundle b2){
        boolean notOverlapping = true;
        for(int[] thisCurrTimeFrame:this.timeExtentList){
            for(int[] b2CurrTimeFrame:b2.timeExtentList){
                int thisCurrTimeFrameStart = thisCurrTimeFrame[0];
                int thisCurrTimeFrameEnd = thisCurrTimeFrame[1];
                int b2CurrTimeFrameStart = b2CurrTimeFrame[0];
                int b2CurrTimeFrameEnd = b2CurrTimeFrame[1];

                boolean isCurrTimeFramesOverlapping = thisCurrTimeFrameEnd >= b2CurrTimeFrameStart &&  b2CurrTimeFrameEnd>=thisCurrTimeFrameEnd    ||
                                                        thisCurrTimeFrameStart<=b2CurrTimeFrameEnd && b2CurrTimeFrameStart<=thisCurrTimeFrameStart;

                if(isCurrTimeFramesOverlapping){
                    notOverlapping = false;
                }

            }


        }
        return notOverlapping;
    }

}
