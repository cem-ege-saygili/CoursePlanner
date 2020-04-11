package domain;

public class MtgTimeParser {

    public static int ParseMtgTime2IntegerTimeStamp(String mtgTime){

        switch(mtgTime) {
            case "8:00:00 AM":
                return 1;
            case "8:30:00 AM":
                return 2;
            case "9:00:00 AM":
                return 3;
            case "9:20:00 AM":
                return 4;
            case "9:30:00 AM":
                return 5;
            case "9:45:00 AM":
                return 6;
            case "10:00:00 AM":
                return 7;
            case "10:20:00 AM":
                return 8;
            case "10:30:00 AM":
                return 9;
            case "10:50:00 AM":
                return 10;
            case "11:15:00 AM":
                return 11;
            case "11:20:00 AM":
                return 12;
            case "11:30:00 AM":
                return 13;
            case "11:45:00 AM":
                return 14;
            case "12:00:00 PM":
                return 15;
            case "12:15:00 PM":
                return 16;
            case "12:20:00 PM":
                return 17;
            case "12:30:00 PM":
                return 18;
            case "12:45:00 PM":
                return 19;
            case "1:00:00 PM":
                return 20;
            case "1:30:00 PM":
                return 21;
            case "2:00:00 PM":
                return 22;
            case "2:15:00 PM":
                return 23;
            case "2:20:00 PM":
                return 24;
            case "2:30:00 PM":
                return 25;
            case "3:00:00 PM":
                return 26;
            case "3:15:00 PM":
                return 27;
            case "3:20:00 PM":
                return 28;
            case "3:30:00 PM":
                return 29;
            case "3:45:00 PM":
                return 30;
            case "3:50:00 PM":
                return 31;
            case "4:00:00 PM":
                return 32;
            case "4:20:00 PM":
                return 33;
            case "4:30:00 PM":
                return 34;
            case "4:45:00 PM":
                return 35;
            case "5:00:00 PM":
                return 36;
            case "5:15:00 PM":
                return 37;
            case "5:20:00 PM":
                return 38;
            case "5:30:00 PM":
                return 39;
            case "5:45:00 PM":
                return 40;
            case "6:00:00 PM":
                return 41;
            case "6:15:00 PM":
                return 42;
            case "6:30:00 PM":
                return 43;
            case "6:45:00 PM":
                return 44;
            case "7:00:00 PM":
                return 45;
            case "7:30:00 PM":
                return 46;
            case "9:30:00 PM":
                return 47;
            case "10:00:00 PM":
                return 48;
            case "11:30:00 PM":
                return 49;
            default:
                // code block
                return -1; //failed to convert mtg. time to integer time stamp :(
        }

    }

}
