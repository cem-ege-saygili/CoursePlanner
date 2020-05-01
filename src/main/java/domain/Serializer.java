package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static void SerializeOut(List<Schedule> schedules, String fName) throws IOException {

        (new File("outputs/ScheduleExports/"+fName)).mkdirs();
        File file2SerializeTo = new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json");
        file2SerializeTo.createNewFile();
        FileOutputStream f = new FileOutputStream(file2SerializeTo);
        ObjectOutputStream o = new ObjectOutputStream(f);

        System.out.println("\n-------------SERIALIZE OUT ------------");

        // Write objects to file
        int i=0;
        for(Schedule currSchedule:schedules){
            System.out.println("\n\nOUT:\t" + ++i + " in " + schedules.size() + "\tREMAINING: " + (schedules.size() - i) + "\n\n");
            o.writeObject(currSchedule);
        }

        o.close();
        f.close();
    }

    public static void SerializeIn(String fName, List<Schedule> schedulesIn) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("outputs/ScheduleExports/"+fName + "/" + fName + ".json"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        System.out.println("\n-------------SERIALIZE IN ------------");

        while(fi.available() > 0){
            Object obj =  oi.readObject();
            if(obj instanceof Schedule){
                Schedule curSchedule = (Schedule) obj;
                schedulesIn.add(curSchedule);
//                System.out.println("\n"+curSchedule);
//                System.out.println(curSchedule.getClassBundleList());
                System.out.println("\n\nIN:\t" + curSchedule + "\tREMAINING: " + fi.available() + "\n\n");
            }
        }
        oi.close();
        fi.close();

    }

}
