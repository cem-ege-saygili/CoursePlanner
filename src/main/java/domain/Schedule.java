package domain;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<ClassBundle> classBundleList;
    public static int scheduleIdcounter;
    private int scheduleId;

    public Schedule(int scheduleId, List<ClassBundle> classBundleList){
        this.scheduleId = scheduleId;
        this.classBundleList = classBundleList;

    }


    public List<ClassBundle> getClassBundleList() {
        return classBundleList;
    }


    public static List<Schedule> GenerateSchedulesFromClassBundlesList(List<List<ClassBundle>> classBundlesList){

        List<Schedule> schedules = new ArrayList<>();

        if(!classBundlesList.isEmpty()){

            List<List<ClassBundle>> cartesianProductsList = CartesianProduct.GenerateCartesianProducts(classBundlesList);

            for(List<ClassBundle> curCartesianProductList:cartesianProductsList) {

                if(ClassBundle.AreAllCompatible(curCartesianProductList)){

                    Schedule curSchedule = new Schedule(scheduleIdcounter++, curCartesianProductList);
                    schedules.add(curSchedule);

                }
            }
        }

        return schedules;

    }

    public String getCourseSubjectsAndCatalogsAsString(){

        String str = "{ ";

        for(ClassBundle curClassBundle:classBundleList){
            String curSubject = curClassBundle.getCourseSubject();
            int curCatalog = curClassBundle.getCourseCatalog();

            str += "\"" + curSubject +
                    " " + curCatalog +
                    "\", " ;

        }

        str = str.substring(0,str.length()-2);

        str += " }";

        return str;
    }

    public static void FilterOutSchedulesFrom(List<Schedule> scheduleList, List<DayTimeFramePair> dayTimeFramePairs){
        for(DayTimeFramePair curDayTimeFramePair:dayTimeFramePairs){
            FilterOutSchedulesFrom_forOneDayTimepair(scheduleList, curDayTimeFramePair);
        }
    }

    public static void FilterOutSchedulesFrom_forOneDayTimepair(List<Schedule> scheduleList, DayTimeFramePair dayTimeFramePair){
        boolean monFlag = dayTimeFramePair.getMonFlag();
        boolean tuesFlag = dayTimeFramePair.getTuesFlag();
        boolean wedFlag = dayTimeFramePair.getWedFlag();
        boolean thursFlag = dayTimeFramePair.getThursFlag();
        boolean friFlag = dayTimeFramePair.getFriFlag();

        int startTimeStamp2Exclude = Parser.ParseMtgTimeStr2IntegerTimeStamp(dayTimeFramePair.getStartTime());
        int endTimeStamp2Exclude = Parser.ParseMtgTimeStr2IntegerTimeStamp(dayTimeFramePair.getEndTime());

        List<Schedule> schedules2BeRemoved = new ArrayList<>();

        for(Schedule curSchedule:scheduleList){
            nextSchedule:{
                for(ClassBundle curClassBundle:curSchedule.getClassBundleList()){
                    for(Class curClass:curClassBundle.getClassList()){
                        int curClassStartTimeStamp = Parser.ParseMtgTimeStr2IntegerTimeStamp(curClass.getStartTime());
                        int curClassEndTimeStamp = Parser.ParseMtgTimeStr2IntegerTimeStamp(curClass.getEndTime());
                        boolean curClassMonFlag = curClass.isMonFlag();
                        boolean curClassTuesFlag = curClass.isTuesFlag();
                        boolean curClassWedFlag = curClass.isWedFlag();
                        boolean curClassThursFlag = curClass.isThursFlag();
                        boolean curClassFriFlag = curClass.isFriFlag();

                        if((monFlag && curClassMonFlag) ||
                                (tuesFlag && curClassTuesFlag) ||
                                (wedFlag && curClassWedFlag) ||
                                (thursFlag && curClassThursFlag) ||
                                (friFlag && curClassFriFlag) ){//if the curClass takes place on a day that is to be excluded.
                            if(!(endTimeStamp2Exclude <  curClassStartTimeStamp ||
                                    curClassEndTimeStamp < startTimeStamp2Exclude)){//if they are overlapping
                                schedules2BeRemoved.add(curSchedule);
                                break nextSchedule;
                            }
                        }else if(!monFlag && !tuesFlag && !wedFlag && !thursFlag && !friFlag){//filter out the entire week
                            if(!(endTimeStamp2Exclude <  curClassStartTimeStamp ||
                                    curClassEndTimeStamp < startTimeStamp2Exclude)){//if they are overlapping
                                schedules2BeRemoved.add(curSchedule);
                                break nextSchedule;
                            }
                        }
                    }
                }
            }
        }

        scheduleList.removeAll(schedules2BeRemoved);

//        Schedule.scheduleIdcounter = scheduleList.size();
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Schedule))
            return false;

        Schedule s = (Schedule) obj;
        int id1 = scheduleId;
        int id2 = s.scheduleId;

        if(id1 == id2)
            return true;

        return false;
    }

    @Override
    public String toString(){
        return  "\n-------------------" +
                "\n\nSchedule #" + (scheduleId+1) +
                ",\n has the following courses: " + getCourseSubjectsAndCatalogsAsString() +
                "\n\nDetails are as follows: \n" + classBundleList;
    }

    public int getScheduleId(){
        return scheduleId;
    }

}
