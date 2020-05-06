package domain;

import DB_Utilities.SelectFromTableInDB;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static void SerializeOut(List<Schedule> schedules,
                                    List<CourseSubject_Catalog_Priority_Tuple> tupleList,
                                    DefaultListModel listModelActiveClassFilters,
                                    DefaultListModel listModelDayTimeFilters,
                                    String fName,
                                    JLabel lblStatusBar)
            throws IOException {

        (new File("outputs/ScheduleExports/"+fName)).mkdirs();
        File appliedFiltersAndSelectedCourseHTML = new File("outputs/ScheduleExports/"+fName + "/" + "Details.html");//Create an .html for applied filters & selected courses.

        appliedFiltersAndSelectedCourseHTML.delete();
        appliedFiltersAndSelectedCourseHTML.createNewFile();
        Append2File(appliedFiltersAndSelectedCourseHTML, "<html>");


        File file2SerializeTo = new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json");//Create serialized .json file.
        file2SerializeTo.delete();
        file2SerializeTo.createNewFile();

        FileOutputStream f = new FileOutputStream(file2SerializeTo);
        ObjectOutputStream o = new ObjectOutputStream(f);

        System.out.println("\n-------------SERIALIZE OUT ------------");

        // Write objects to file
        int i=0;

        boolean firstRound_selectedCourses = true;
        boolean firstRound_dtFilters = true;
        boolean firstRound_acFilters = true;

        int acfSize = listModelActiveClassFilters.getSize();
        int dtfSize = listModelDayTimeFilters.getSize();
        for(CourseSubject_Catalog_Priority_Tuple curTuple:tupleList){//write courses
            firstRound_selectedCourses = FirstRoundHandler(appliedFiltersAndSelectedCourseHTML,
                                                            firstRound_selectedCourses,
                                                            "Selected Courses:");
            Append2File(appliedFiltersAndSelectedCourseHTML, curTuple);//append current scpTuple to .html
            Append2File(appliedFiltersAndSelectedCourseHTML, "<br><br>");
            o.writeObject(curTuple);
        }
        for(int dtfIndex = 0;dtfIndex<dtfSize;dtfIndex++){//write Day & Time Filters
            Object obj = listModelDayTimeFilters.getElementAt(dtfIndex);
            if(obj instanceof DayTimeFramePair){
                firstRound_dtFilters = FirstRoundHandler(appliedFiltersAndSelectedCourseHTML,
                        firstRound_dtFilters,
                        "Day & Time Exclusion Filters:");
                DayTimeFramePair dayTimeFramePair = (DayTimeFramePair) obj;
                Append2File(appliedFiltersAndSelectedCourseHTML, dayTimeFramePair);//append current dtfPair to .html
                Append2File(appliedFiltersAndSelectedCourseHTML, "<br><br>");
                o.writeObject(dayTimeFramePair);//write current dtfPair to .json file
            }
        }
        for(int acfIndex = 0;acfIndex<acfSize;acfIndex++){//write Active Class Filters
            Object obj = listModelActiveClassFilters.getElementAt(acfIndex);
            if(obj instanceof ActiveClassFilterElement){
                firstRound_acFilters = FirstRoundHandler(appliedFiltersAndSelectedCourseHTML,
                        firstRound_acFilters,
                        "Class Inclusion Filters:");
                ActiveClassFilterElement acfElement = (ActiveClassFilterElement) obj;
                Append2File(appliedFiltersAndSelectedCourseHTML, acfElement);//append current acf to .html
                Append2File(appliedFiltersAndSelectedCourseHTML, "<br><br>");
                o.writeObject(acfElement);//write current acf to .json file
            }
        }
        for(Schedule currSchedule:schedules){//write schedules
            i = ShowSerializeOutProgess2User(schedules, lblStatusBar, i);
            o.writeObject(currSchedule);
        }

        o.close();
        f.close();

        Append2File(appliedFiltersAndSelectedCourseHTML, "</html>");
    }

    private static boolean FirstRoundHandler(File appliedFiltersAndSelectedCourseHTML,
                                          boolean firstRoundBoolean, String str2BeWritten) throws IOException {
        if(firstRoundBoolean){
            String firstRoundStr =   "<br><br>" +
                                    "<font size=\"+2\" face=\"verdana\" color=\"black\"><u><b><i>"
                                    + str2BeWritten
                                    + "</i></b></u></font><br><br>";
            Append2File(appliedFiltersAndSelectedCourseHTML, firstRoundStr);
        }
        return false;
    }

    private static void Append2File(File file,
                                    Object obj)
            throws IOException {

        FileWriter fr = new FileWriter(file, true);
//        if(obj instanceof ActiveClassFilterElement){
//            ActiveClassFilterElement acf = (ActiveClassFilterElement) obj;
//            String acfStr = acf.toString();
//            String objStrWithoutHtmlTags = obj.toString().substring(6,acfStr.length()-7);
//            fr.write(acfStrWithoutHtmlTags);
//        }else if(obj instanceof DayTimeFramePair){
//            DayTimeFramePair dtfPair = (DayTimeFramePair) obj;
//            fr.write(dtfPair.toString());
//        }else if(obj instanceof CourseSubject_Catalog_Priority_Tuple){
//            CourseSubject_Catalog_Priority_Tuple scpTuple = (CourseSubject_Catalog_Priority_Tuple) obj;
//            fr.write(scpTuple.toString());
//        }else if(obj instanceof String){
//            fr.write((String) obj);
//        }
        if(obj instanceof String){
            fr.write((String) obj);
        }else{
            String objStr = obj.toString();
            String objStrWithoutHtmlTags = objStr.substring(6,objStr.length()-7);
            fr.write(objStrWithoutHtmlTags);
        }
        fr.close();
    }

    public static void SerializeIn(File chosenFile,
                                   List<Schedule> schedulesIn,
                                   List<List<Class>> classesList,
                                   List<CourseSubject_Catalog_Priority_Tuple> tupleList,
                                   DefaultListModel listModelCourses2BePlanned,
                                   DefaultListModel listModelActiveClassFilters,
                                   DefaultListModel listModelDayTimeFilters,
                                   JLabel lblStatusBar)
            throws IOException, ClassNotFoundException {

//        FileInputStream fi = new FileInputStream(new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json"));
        FileInputStream fi = new FileInputStream(chosenFile);
        ObjectInputStream oi = new ObjectInputStream(fi);

        System.out.println("\n-------------SERIALIZE IN ------------");

        Double completeLength = Double.valueOf(fi.available());

        boolean firstRoundFlag_tuple = true;
        boolean firstRoundFlag_activeClassFilter = true;
        boolean firstRoundFlag_dTimeFilter = true;
        while(fi.available() > 0){
            Object obj =  oi.readObject();
            if(obj instanceof Schedule){//read schedules
                Schedule curSchedule = (Schedule) obj;
                schedulesIn.add(curSchedule);
//                System.out.println("\n"+curSchedule);
//                System.out.println(curSchedule.getClassBundleList());
                ShowSerializeInProgress2User(lblStatusBar,
                                            fi.available(),
                                            completeLength,
                                            curSchedule);
            }else if(obj instanceof CourseSubject_Catalog_Priority_Tuple){//read courses

                if(firstRoundFlag_tuple){
                    firstRoundFlag_tuple = false;
                    tupleList.clear();
                    classesList.clear();
                    listModelCourses2BePlanned.removeAllElements();
                }
                CourseSubject_Catalog_Priority_Tuple curTuple = (CourseSubject_Catalog_Priority_Tuple) obj;


                List<Class> classListForTuple = new ArrayList<>();

                String sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location = "inputs/sqlQuery_selectClassesInfoFromCourseSubject_Catalog.sql";

                SelectFromTableInDB.PutClassesFromDB2cList(curTuple, classListForTuple, Main.dbName, sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location);

                classesList.add(classListForTuple);
                tupleList.add(curTuple);
                listModelCourses2BePlanned.addElement(curTuple);

            }else if(obj instanceof ActiveClassFilterElement){//read courses

                if(firstRoundFlag_activeClassFilter){
                    firstRoundFlag_activeClassFilter = false;
                    listModelActiveClassFilters.removeAllElements();
                }
                ActiveClassFilterElement curActiveClassFilterElt = (ActiveClassFilterElement) obj;
                listModelActiveClassFilters.addElement(curActiveClassFilterElt);

            }else if(obj instanceof DayTimeFramePair){//read courses
                if(firstRoundFlag_dTimeFilter){
                    firstRoundFlag_dTimeFilter = false;
                    listModelDayTimeFilters.removeAllElements();
                }
                DayTimeFramePair curDayTimeFramePair = (DayTimeFramePair) obj;
                listModelDayTimeFilters.addElement(curDayTimeFramePair);
            }
        }
        oi.close();
        fi.close();

    }

//    public static void SerializeIn(File chosenFile, List<Schedule> schedulesIn, JLabel lblStatusBar) throws IOException, ClassNotFoundException {
//        FileInputStream fi = new FileInputStream(chosenFile);
//        ObjectInputStream oi = new ObjectInputStream(fi);
//
//        System.out.println("\n-------------SERIALIZE IN ------------");
//
//        Double completeLength = Double.valueOf(fi.available());
//        while(fi.available() > 0){
//            Object obj =  oi.readObject();
//            if(obj instanceof Schedule){
//                Schedule curSchedule = (Schedule) obj;
//                schedulesIn.add(curSchedule);
////                System.out.println("\n"+curSchedule);
////                System.out.println(curSchedule.getClassBundleList());
//                ShowSerializeInProgress2User(lblStatusBar, fi.available(), completeLength, curSchedule);
//            }
//        }
//        oi.close();
//        fi.close();
//
//    }

    private static void ShowSerializeInProgress2User(JLabel lblStatusBar, int remaining, Double completeLength, Schedule curSchedule) throws IOException {
        String str2Console = "\n\nIN:\t" + curSchedule + "\tREMAINING: " + remaining + "\n\n";
        Double percentage = remaining/completeLength;
        percentage = Math.floor(percentage*100);
        String percentage2BeDisplayed = percentage.toString();
        percentage2BeDisplayed = percentage2BeDisplayed.substring(0,percentage2BeDisplayed.length()-2) + "%";
        System.out.println(str2Console);
        lblStatusBar.setText("<html>"
                + "Importing: "
                + "<font face=\"verdana\" color=\"red\"><b><i>"
                + percentage2BeDisplayed
                + "</i></b></font> left.</html>");
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//
//            }
//        });
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lblStatusBar.setText("<html>"
//                        + "Progress: "
//                        + "<font face=\"verdana\" color=\"red\"><b><i>"
//                        + str2BeDisplayed
//                        + "</i></b></font></html>");
//            }
//        });
//        t.start();
    }

    private static int ShowSerializeOutProgess2User(List<Schedule> schedules, JLabel lblProgressBar, int i) {
        Double percentage = Math.floor((schedules.size() - ++i)/Double.valueOf(schedules.size())*100);
        String percentage2BeDisplayed = percentage.toString();
        percentage2BeDisplayed = percentage2BeDisplayed.substring(0,percentage2BeDisplayed.length()-2) + "%";
        String str2Console = "\n\nOUT:\t" + i + " in " + schedules.size() + "\tREMAINING: " + (schedules.size() - i) + "\n\n";
        System.out.println(str2Console);
        lblProgressBar.setText("<html>"
                + "Saving Results: "
                + "<font face=\"verdana\" color=\"red\"><b><i>"
                + percentage2BeDisplayed
                + "</i></b></font> left.</html>");
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                lblProgressBar.setText("<html>"
//                        + "Progress: "
//                        + "<font face=\"verdana\" color=\"red\"><b><i>"
//                        + str2BeDisplayed
//                        + "</i></b></font></html>");
//            }
//        });
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
//        t.start();
        return i;
    }

}
