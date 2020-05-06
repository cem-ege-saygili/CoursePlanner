package domain;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Schedule implements Serializable{

    private static final long serialVersionUID = 1L;
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

//                if(ClassBundle.AreAllCompatible(curCartesianProductList)){
//
//                    Schedule curSchedule = new Schedule(scheduleIdcounter++, curCartesianProductList);
//                    schedules.add(curSchedule);
//
//                }
                Schedule curSchedule = new Schedule(scheduleIdcounter++, curCartesianProductList);
                schedules.add(curSchedule);
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

    private static void SetProgressBarText(JLabel lblProgressBar,
                                           String progressBarStr,
                                           Double percentage) {
        String str2Display = "<html>"
                + "Running: "
                    + "<br><font face=\"verdana\" color=\"blue\"><b><i>"
                + progressBarStr
                    + ": </i></b></font>"
                + "<font face=\"verdana\" color=\"red\"><b><i>"
                + percentage + "%"
                    + "</i></b></font>"
                + " left.</html>";
        String str2Console = "Running: "
                            + progressBarStr + ": "
                            + percentage + "%" + " left.";
        System.out.println(str2Console);
        lblProgressBar.setText(str2Display);
        lblProgressBar.paintImmediately(lblProgressBar.getVisibleRect());


    }

    public static void FilterOutSchedulesFrom(List<Schedule> scheduleList,
                                              List<DayTimeFramePair> dayTimeFramePairs,
                                              JLabel lblProgressBar){

        int i = 1;
        for(DayTimeFramePair curDayTimeFramePair:dayTimeFramePairs){
            String progressBarStr= "Time & Day Exclusion Filter #" + i++;
            FilterOutSchedulesFrom_forOneDayTimepair(scheduleList,
                                                    curDayTimeFramePair,
                                                    progressBarStr,
                                                    lblProgressBar);
        }
    }

    public static void FilterOutSchedulesFrom_forOneDayTimepair(List<Schedule> scheduleList,
                                                                DayTimeFramePair dayTimeFramePair,
                                                                String progressBarStr,
                                                                JLabel lblProgressBar){
        boolean monFlag = dayTimeFramePair.getMonFlag();
        boolean tuesFlag = dayTimeFramePair.getTuesFlag();
        boolean wedFlag = dayTimeFramePair.getWedFlag();
        boolean thursFlag = dayTimeFramePair.getThursFlag();
        boolean friFlag = dayTimeFramePair.getFriFlag();

        int startTimeStamp2Exclude = Parser.ParseMtgTimeStr2IntegerTimeStamp(dayTimeFramePair.getStartTime());
        int endTimeStamp2Exclude = Parser.ParseMtgTimeStr2IntegerTimeStamp(dayTimeFramePair.getEndTime());

//        List<Integer> indices2BeRemoved = new ArrayList<>();

        int curIndex = 0;
        if(scheduleList == null || scheduleList.size() == 0)
            return;
        while(true){
            if(curIndex == scheduleList.size())
                break;
            Schedule curSchedule = scheduleList.get(curIndex);
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
                            if(!(endTimeStamp2Exclude <=  curClassStartTimeStamp ||
                                    curClassEndTimeStamp <= startTimeStamp2Exclude)){//if they are overlapping
//                                indices2BeRemoved.add(curIndex);
                                scheduleList.remove(curIndex);
                                curIndex--;
                                break nextSchedule;
                            }
                        }else if(!monFlag && !tuesFlag && !wedFlag && !thursFlag && !friFlag){//filter out the entire week
                            if(!(endTimeStamp2Exclude <=  curClassStartTimeStamp ||
                                    curClassEndTimeStamp <= startTimeStamp2Exclude)){//if they are overlapping

//                                indices2BeRemoved.add(curIndex);
                                scheduleList.remove(curIndex);
                                curIndex--;
                                break nextSchedule;
                            }
                        }
                    }
                }
            }
            curIndex++;

            UpdateProgressbarWithPercentage(scheduleList, progressBarStr, lblProgressBar, curIndex);
        }

//        for(Integer index:indices2BeRemoved)
//            scheduleList.remove(index);

//        Schedule.scheduleIdcounter = scheduleList.size();
    }

    private static void UpdateProgressbarWithPercentage(List<Schedule> scheduleList,
                                                        String progressBarStr,
                                                        JLabel lblProgressBar,
                                                        int curIndex) {
        Double size = Double.valueOf(scheduleList.size());

        Double percentage = Math.floor((size - curIndex)/size*1000)/10;

        SetProgressBarText(lblProgressBar, progressBarStr, percentage);

    }

    public static void ClassFilterSchedulesIncluding_ClassLists (List<Schedule> scheduleList,
                                                                 List<Class> classListToInclude,
                                                                 JLabel lblProgressBar){
        int i = 1;
        for(Class curClassToInclude:classListToInclude){
            String progressBarStr= "Active Classes Filter #" + i++;
            ClassFilterSchedulesIncluding_OneClass(scheduleList,
                                                    curClassToInclude,
                                                    progressBarStr,
                                                    lblProgressBar);
        }
    }

    public static void ClassFilterSchedulesIncluding_OneClass(List<Schedule> scheduleList,
                                                              Class classToInclude,
                                                              String progressBarStr,
                                                              JLabel lblProgressBar){
        boolean monFlag = classToInclude.isMonFlag();
        boolean tuesFlag = classToInclude.isTuesFlag();
        boolean wedFlag = classToInclude.isWedFlag();
        boolean thursFlag = classToInclude.isThursFlag();
        boolean friFlag = classToInclude.isFriFlag();

        int startTimeStamp2Include = Parser.ParseMtgTimeStr2IntegerTimeStamp(classToInclude.getStartTime());
        int endTimeStamp2Include = Parser.ParseMtgTimeStr2IntegerTimeStamp(classToInclude.getEndTime());

//        List<Integer> indices2BeRemoved = new ArrayList<>();
        int curIndex = 0;
        if(scheduleList == null || scheduleList.size() == 0)
            return;
        while(true){
            if(curIndex == scheduleList.size())
                break;
            Schedule curSchedule = scheduleList.get(curIndex);
            boolean flag = false;
            nextSchedule:{
                for(ClassBundle curClassBundle:curSchedule.getClassBundleList()){
                    for(Class curClass:curClassBundle.getClassList()){
                        if(curClass.equals(classToInclude)){
                            flag = true;
                            break nextSchedule;
                        }
                    }
                }
            }
            if(!flag){
//                indices2BeRemoved.add(curIndex);
                scheduleList.remove(curIndex);
                curIndex--;
            }
            curIndex++;

            UpdateProgressbarWithPercentage(scheduleList, progressBarStr, lblProgressBar, curIndex);
        }

//        for(Integer index:indices2BeRemoved)
//            scheduleList.remove(index);

//        Schedule.scheduleIdcounter = scheduleList.size();
    }

    public static void PrintOutSchedulesToUser(List<Schedule> schedules, String fName, JLabel lblProgressBar) throws IOException {
        File file = new File("outputs/ScheduleExports/"+fName + "/" + fName + ".html");
        file.delete();
        FileWriter fr = new FileWriter(file, true);

        String str = "<html>";
        int schdeuleNo=0;

        fr.write(str);
        str = "";

        int i=0;
        for(Schedule curSchedule:schedules){

            Double percentage = Math.floor((schedules.size() - ++i)/Double.valueOf(schedules.size())*100);
            String percentage2BeDisplayed = percentage.toString();
            percentage2BeDisplayed = percentage2BeDisplayed.substring(0,percentage2BeDisplayed.length()-2) + "%";
            lblProgressBar.setText("<html>"
                    + "Building an HTML: "
                    + "<font face=\"verdana\" color=\"red\"><b><i>"
                    + percentage2BeDisplayed
                    + "</i></b></font> left.</html>");
            String str2Console = "\n\nWriting HTML:\t" + curSchedule + "\tREMAINING: " + (schedules.size() - i) + "\n\n";
            System.out.println(str2Console);


            str += "<br><br>----------------------------------" +
                            "<font face=\"verdana\" color=\"red\"><b><i>" +
                                    "Plan #" + ++schdeuleNo +
                            "</i></b></font>" +
                    "--------------------------------------<br>";

            fr.write(str);
            str = "";

            List<ClassBundle> classBundles = curSchedule.classBundleList;
            for(ClassBundle curClassBundle:classBundles){
                String courseSubjectAndCatalog = curClassBundle.getCourseSubject()
                                                    + " " + curClassBundle.getCourseCatalog();
                str += "<br><br>" +
                            "<font face=\"verdana\" color=\"blue\"><b><i>" +
                                courseSubjectAndCatalog +
                            "</i></b></font>" +
                        ": ";
                List<Class> classes = curClassBundle.getClassList();
                int numClasses = classes.size();
                String courseDescription = (classes!=null?classes.get(0).getCourseDescription():"");
                String courseLevel = (classes!=null?classes.get(0).getCourseLevel():"");
                String courseFaculty = (classes!=null?classes.get(0).getCourseFaculty():"");

                str += "\"" +
                                "<font face=\"verdana\" color=\"green\"><b><i>" +
                                    courseDescription +
                                "</i></b></font>" +

                            "\" is meant for " +
                                "<font face=\"verdana\" color=\"green\"><b><i>" +
                                    courseLevel +
                                "</i></b></font>" +
                            " students " +  "and given by " +

                                "<font face=\"verdana\" color=\"green\"><b><i>" +
                                    courseFaculty +
                                "</i></b></font>" +
                            " faculty.<br>" +

                            "including " +
                                "<font face=\"verdana\" color=\"green\"><b><i>" +
                                    numClasses +
                                "</i></b></font>" +
                            " sessions as follows:<br><br>";

                fr.write(str);
                str = "";

                int classNo = 0;
                for(Class curClass:classes){
                    String curClassStartTime = curClass.getStartTime();
                    String curClassEndTime = curClass.getEndTime();
                    String curClassComponent = curClass.getComponent();
                    boolean curClassIsMonFlag = curClass.isMonFlag();
                    boolean curClassIsTuesFlag = curClass.isTuesFlag();
                    boolean curClassIsWedFlag = curClass.isWedFlag();
                    boolean curClassIsThursFlag = curClass.isThursFlag();
                    boolean curClassIsFriFlag = curClass.isFriFlag();

                    List<String> daysList = Parser.ParseDayBooleans2List(
                                                                        curClassIsMonFlag,
                                                                        curClassIsTuesFlag,
                                                                        curClassIsWedFlag,
                                                                        curClassIsThursFlag,
                                                                        curClassIsFriFlag);

                    List<InstructorNameRolePair> curClassInstructorNameRolePairs = curClass.getInstructorNameRolePairs();
                    str +=
                                "&#09\u2022 Class #" +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        ++classNo +
                                        "</i></b></font>" +

                                " is of type (" +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        curClassComponent +
                                        "</i></b></font>" +

                                ") <br>&#09and is on " +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        daysList +
                                        "</i></b></font>" +

                                " and btw. " +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        curClassStartTime +
                                        "</i></b></font>" +

                                " and " +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        curClassEndTime +
                                        "</i></b></font>" +
                                        "<br>" +

                                "&#09and is given by " +
                                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                                        curClassInstructorNameRolePairs +
                                        "</i></b></font>" +

                                 "<br><br>";

                    fr.write(str);
                    str = "";
                }

            }
        }
        str += "</html>";

        fr.write(str);
        str = "";

        fr.close();
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
        return  /*"\n-------------------" + */
                "\n\nSchedule #" + (scheduleId+1);
                /*+ ",\n has the following courses: " + getCourseSubjectsAndCatalogsAsString() +
                "\n\nDetails are as follows: \n" + classBundleList;*/
    }

    public int getScheduleId(){
        return scheduleId;
    }

}
