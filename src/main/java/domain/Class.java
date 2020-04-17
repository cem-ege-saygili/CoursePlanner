package domain;

import java.util.List;

public class Class implements Comparable<domain.Class>{

    private int id;
    private String component;
    private String startTime;
    private String endTime;
    private boolean monFlag;
    private boolean tuesFlag;
    private boolean wedFlag;
    private boolean thursFlag;
    private boolean friFlag;
    private String location;
    private List<InstructorNameRolePair> instructorNameRolePairs;
    private String courseName;
    private int courseCatalog;
    private int totlEnrl;
    private int capEnrl;
//    private String instructorName;
//    private String instructorRole;

    public Class(
            int id,
            String component,
            String startTime,
            String endTime,
            boolean monFlag,
            boolean tuesFlag,
            boolean wedFlag,
            boolean thursFlag,
            boolean friFlag,
            String location,
            List<InstructorNameRolePair> instructorNameRolePairs,
            String courseName,
            int courseCatalog,
            int totlEnrl,
            int capEnrl

//            String instructorName,
//            String instructorRole
    ){

        this.id = id;
        this.component = component;
        this.startTime = startTime;
        this.endTime = endTime;
        this.monFlag = monFlag;
        this.tuesFlag = tuesFlag;
        this.wedFlag = wedFlag;
        this.thursFlag = thursFlag;
        this.friFlag = friFlag;
        this.location = location;
        this.instructorNameRolePairs = instructorNameRolePairs;
        this.courseName = courseName;
        this.courseCatalog = courseCatalog;
        this.capEnrl = capEnrl;
        this.totlEnrl = totlEnrl;
//        this.instructorName = instructorName;
//        this.instructorRole = instructorRole;

    }

    public boolean isCompatibleWith(Class c2){

        int id1 = id;
        int id2 = c2.id;

        if(id1 == id2)
            return false;

//        String day1 = Parser.ParseDayBooleans2List(monFlag,tuesFlag,wedFlag,thursFlag,friFlag).toString();
//        String day2 = Parser.ParseDayBooleans2List(c2.monFlag,c2.tuesFlag,c2.wedFlag,c2.thursFlag,c2.friFlag).toString();
//
//        if(!day1.equals(day2))
//            return true;

        if(!((monFlag && c2.monFlag)
                || (tuesFlag && c2.tuesFlag)
                || (wedFlag && c2.wedFlag)
                || (thursFlag && c2.thursFlag)
                || (friFlag && c2.friFlag))) // If 2 classes are on different days. (i.e. distinct days, no overlapping days)
            return true;

        int s1 = Parser.ParseMtgTimeStr2IntegerTimeStamp(this.startTime);
        int e1 = Parser.ParseMtgTimeStr2IntegerTimeStamp(this.endTime);

        int s2 = Parser.ParseMtgTimeStr2IntegerTimeStamp(c2.startTime);
        int e2 = Parser.ParseMtgTimeStr2IntegerTimeStamp(c2.endTime);

        return (

                e1 < s2 || e2 < s1

                );

    }

    public static boolean AreAllCompatible(List<Class> classList){

        boolean flag = true;

        if(classList.size()<=1){
            return flag;
        }

        for (int i=0;i<classList.size()-1;i++){

            Class c1 = classList.get(i);

            for(int j = i+1;j<classList.size();j++){

                Class c2 = classList.get(j);
                if(!c1.isCompatibleWith(c2)){
                    flag =  false;
                    break;
                }

            }

        }

        return flag;

    }


    @Override
    public String toString() {

        String dayStr ="";

        List<String> dayList = Parser.ParseDayBooleans2List(
                                                            monFlag,
                                                            tuesFlag,
                                                            wedFlag,
                                                            thursFlag,
                                                            friFlag
        );

        for(String day:dayList){
            dayStr += day + " and ";
        }

        dayStr = dayStr.substring(0,dayStr.length()-5);


        String instructorNameRolePairsStr ="{ ";

        for(InstructorNameRolePair curInstructorNameRolePair:instructorNameRolePairs){

            String curInstructorName = curInstructorNameRolePair.getInstructorName();
            String curInstructorRole = curInstructorNameRolePair.getInstructorRole();

            instructorNameRolePairsStr +=  curInstructorName + " as "
                                            + curInstructorRole + "; ";
        }

        instructorNameRolePairsStr = instructorNameRolePairsStr.substring(0,instructorNameRolePairsStr.length()-2);

        instructorNameRolePairsStr += " }";


        return (

                "\nClass: #" + id
                       // + " is given by " + instructorName
                       // + "(" + instructorRole + ")"
                        + " as for (\"" + courseName + " " + courseCatalog + "\""
                        + " (with TotalEnrl: " + totlEnrl + " and CapEnrl: " + capEnrl + ") "
                        + " is given by " + instructorNameRolePairsStr
                        + " and is of type " + component
                        + ", taking place on " + dayStr
                        + " between " + startTime + " and " + endTime
                        + " in " + location

                );
    }

    @Override
    public boolean equals(Object obj) {
        Class c2 = (Class) obj;
        return (this.id == c2.id);
    }

    @Override
    public int compareTo(Class c) {

        int dayInteger1 = convertToDayInteger();
        int dayInteger2 = c.convertToDayInteger();

        if(dayInteger1>dayInteger2){
            return -1;
        }else if(dayInteger1<dayInteger2){
            return 1;
        }else{

            int s1 = Parser.ParseMtgTimeStr2IntegerTimeStamp(startTime);
            int s2 = Parser.ParseMtgTimeStr2IntegerTimeStamp(c.getStartTime());

            if(s1 == s2){
                return 0;
            }else if(s1<s2){
                return -1;
            }
            return 1;
        }
    }

    private int convertToDayInteger() {

        int integer = 0;

        if(monFlag)
            integer += 10000;
        if(tuesFlag)
            integer += 1000;
        if(wedFlag)
            integer += 100;
        if(thursFlag)
            integer += 10;
        if(friFlag)
            integer += 1;

        return integer;
    }

    public int getId() {
        return id;
    }

    public String getComponent() {
        return component;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean isMonFlag() {
        return monFlag;
    }

    public boolean isTuesFlag() {
        return tuesFlag;
    }

    public boolean isWedFlag() {
        return wedFlag;
    }

    public boolean isThursFlag() {
        return thursFlag;
    }

    public boolean isFriFlag() {
        return friFlag;
    }

    public String getLocation() {
        return location;
    }

    public List<InstructorNameRolePair> getInstructorNameRolePairs() {
        return instructorNameRolePairs;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCatalog() {
        return courseCatalog;
    }

    public int getTotlEnrl() {
        return totlEnrl;
    }

    public int getCapEnrl() {
        return capEnrl;
    }


}