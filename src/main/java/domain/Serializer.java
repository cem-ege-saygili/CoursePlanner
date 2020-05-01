package domain;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static void SerializeOut(List<Schedule> schedules, String fName, JLabel lblStatusBar) throws IOException {

        (new File("outputs/ScheduleExports/"+fName)).mkdirs();
        File file2SerializeTo = new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json");
        file2SerializeTo.createNewFile();
        FileOutputStream f = new FileOutputStream(file2SerializeTo);
        ObjectOutputStream o = new ObjectOutputStream(f);

        System.out.println("\n-------------SERIALIZE OUT ------------");

        // Write objects to file
        int i=0;
        for(Schedule currSchedule:schedules){
            i = ShowSerializeOutProgess2User(schedules, lblStatusBar, i);
            o.writeObject(currSchedule);
        }

        o.close();
        f.close();
    }

    public static void SerializeIn(String fName, List<Schedule> schedulesIn, JLabel lblStatusBar) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        System.out.println("\n-------------SERIALIZE IN ------------");

        Double completeLength = Double.valueOf(fi.available());
        while(fi.available() > 0){
            Object obj =  oi.readObject();
            if(obj instanceof Schedule){
                Schedule curSchedule = (Schedule) obj;
                schedulesIn.add(curSchedule);
//                System.out.println("\n"+curSchedule);
//                System.out.println(curSchedule.getClassBundleList());
                ShowSerializeInProgress2User(lblStatusBar, fi.available(), completeLength, curSchedule);
            }
        }
        oi.close();
        fi.close();

    }

    public static void SerializeIn(File chosenFile, List<Schedule> schedulesIn, JLabel lblStatusBar) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(chosenFile);
        ObjectInputStream oi = new ObjectInputStream(fi);

        System.out.println("\n-------------SERIALIZE IN ------------");

        Double completeLength = Double.valueOf(fi.available());
        while(fi.available() > 0){
            Object obj =  oi.readObject();
            if(obj instanceof Schedule){
                Schedule curSchedule = (Schedule) obj;
                schedulesIn.add(curSchedule);
//                System.out.println("\n"+curSchedule);
//                System.out.println(curSchedule.getClassBundleList());
                ShowSerializeInProgress2User(lblStatusBar, fi.available(), completeLength, curSchedule);
            }
        }
        oi.close();
        fi.close();

    }

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
