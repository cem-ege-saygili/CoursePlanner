package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Parser.ParseDayBooleans2List;

public class DayTimeFramePair {

    private String startTime;
    private String endTime;

    public static List<String> AllTimeStrings = new ArrayList<>(List.of("8:00:00 AM",
            "8:30:00 AM",
            "9:00:00 AM",
            "9:20:00 AM",
            "9:30:00 AM",
            "9:45:00 AM",
            "10:00:00 AM",
            "10:20:00 AM",
            "10:30:00 AM",
            "10:50:00 AM",
            "11:15:00 AM",
            "11:20:00 AM",
            "11:30:00 AM",
            "11:45:00 AM",
            "12:00:00 PM",
            "12:15:00 PM",
            "12:20:00 PM",
            "12:30:00 PM",
            "12:45:00 PM",
            "1:00:00 PM",
            "1:30:00 PM",
            "2:00:00 PM",
            "2:15:00 PM",
            "2:20:00 PM",
            "2:30:00 PM",
            "3:00:00 PM",
            "3:15:00 PM",
            "3:20:00 PM",
            "3:30:00 PM",
            "3:45:00 PM",
            "3:50:00 PM",
            "4:00:00 PM",
            "4:20:00 PM",
            "4:30:00 PM",
            "4:45:00 PM",
            "5:00:00 PM",
            "5:15:00 PM",
            "5:20:00 PM",
            "5:30:00 PM",
            "5:45:00 PM",
            "6:00:00 PM",
            "6:15:00 PM",
            "6:30:00 PM",
            "6:45:00 PM",
            "7:00:00 PM",
            "7:30:00 PM",
            "9:30:00 PM",
            "10:00:00 PM",
            "11:30:00 PM"));


    private List<Boolean> dayBooleans;


    public DayTimeFramePair(boolean monFlag, boolean tuesFlag, boolean wedFlag, boolean thursFlag, boolean friFlag,
                            String startTime, String endTime){
        this.dayBooleans = new ArrayList<>();
        dayBooleans.add(monFlag);
        dayBooleans.add(tuesFlag);
        dayBooleans.add(wedFlag);
        dayBooleans.add(thursFlag);
        dayBooleans.add(friFlag);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean getMonFlag(){
        return dayBooleans.get(0);
    }
    public boolean getTuesFlag(){
        return dayBooleans.get(1);
    }
    public boolean getWedFlag(){
        return dayBooleans.get(2);
    }
    public boolean getThursFlag(){
        return dayBooleans.get(3);
    }
    public boolean getFriFlag(){
        return dayBooleans.get(4);
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    @Override
    public String toString() {

        String filterDaysStr = (

                ((!getMonFlag() &&
                !getTuesFlag() && !getWedFlag() &&
                !getThursFlag() && !getFriFlag())

                        ||

                        (
                                getMonFlag() && getTuesFlag() &&
                                        getThursFlag() &&getFriFlag()

                                ))

                ?

                "all days"

                :

                Parser.ParseDayBooleans2List(getMonFlag(),
                        getTuesFlag(),
                        getWedFlag(),
                        getThursFlag(),
                        getFriFlag()).toString()
                );

        return ("Filter: " +
                //"is for the following days: " +
                filterDaysStr +
                ", from \"" + startTime +
                " - to " + endTime +
                "\".");

    }

    @Override
    public boolean equals(Object obj) {

        DayTimeFramePair dtf2 = (DayTimeFramePair) obj;

        return (this.startTime.equals(dtf2.startTime) &&
                        this.endTime.equals(dtf2.endTime) &&
                        this.dayBooleans.equals(dtf2.dayBooleans));
    }
}
