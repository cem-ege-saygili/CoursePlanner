package model;

import model.tools.Parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassFilterElement implements Serializable {

    private Class c;
    private String classStartTimeStr;
    private String classEndTimeStr;
    private String classLocation;
    private String mtgDayStr;
    private List<InstructorNameRolePair> iNameRolePairs = new ArrayList<>();

    public ClassFilterElement(Class c){
        this.c = c;
        classStartTimeStr = c.getStartTime();
        classEndTimeStr = c.getEndTime();
        for(InstructorNameRolePair curInstructorNameRolePair: c.getInstructorNameRolePairs()){
            String currentInstructorRole = curInstructorNameRolePair.getInstructorRole();
            if(!currentInstructorRole.equals("TA"))
                iNameRolePairs.add(curInstructorNameRolePair);
        }
        mtgDayStr = Parser.ParseDayBooleans2List(
                c.isMonFlag(),
                c.isTuesFlag(),
                c.isWedFlag(),
                c.isThursFlag(),
                c.isFriFlag()).toString();
        classLocation = c.getLocation();
    }

    public Class getClassForClassFilterElement(){
        return c;
    }

    public String getClassStartTimeStr() {
        return classStartTimeStr;
    }

    public String getClassEndTimeStr() {
        return classEndTimeStr;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public String getMtgDayStr() {
        return mtgDayStr;
    }

    public List<InstructorNameRolePair> getiNameRolePairs() {
        return iNameRolePairs;
    }

    @Override
    public String toString() {

        return "<html>" +
                    "ON " +
                        "<font face=\"verdana\" color=\"green\"><b><i>" +
                    mtgDayStr +
                        "</i></b></font>" +
                    ", FROM: " +
                        "<font face=\"verdana\" color=\"red\"><b><i>" +
                    classStartTimeStr +
                        "</i></b></font>" +
                    ", TO: " +
                        "<font face=\"verdana\" color=\"red\"><b><i>" +
                    classEndTimeStr +
                        "</i></b></font>" +
                    ", AT: " +
                        "<font face=\"verdana\" color=\"blue\"><b><i>" +
                    classLocation +
                        "</i></b></font>" +
                    ", is given by " +
                        "<font face=\"verdana\"><b><i>" +
                    iNameRolePairs +
                        "</i></b></font>" +
                "</html>";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ClassFilterElement))
            return false;

        ClassFilterElement c2 = (ClassFilterElement) obj;

        return this.c.equals(c2.c);
    }
}
