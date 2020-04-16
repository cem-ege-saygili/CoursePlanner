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

    @Override
    public boolean equals(Object obj) {
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
