package domain;

import DB_Utilities.SelectFromTableInDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    static String parserDBName = Main.dbName;

    static String sqlQuery_location = "inputs/sqlQuery_SelectAllPossibleTimeStamps.sql";

    static List<String> AllPossibleTimeStamps = SelectFromTableInDB.SelectAllPossibleTimeStampsOrderedAsc(parserDBName, sqlQuery_location);

    public static int ParseMtgTimeStr2IntegerTimeStamp(String mtgTime){

//        switch(mtgTime) {
//            case "8:00:00 AM":
//                return 1;
//            case "8:30:00 AM":
//                return 2;
//            case "9:00:00 AM":
//                return 3;
//            case "9:20:00 AM":
//                return 4;
//            case "9:30:00 AM":
//                return 5;
//            case "9:45:00 AM":
//                return 6;
//            case "10:00:00 AM":
//                return 7;
//            case "10:20:00 AM":
//                return 8;
//            case "10:30:00 AM":
//                return 9;
//            case "10:50:00 AM":
//                return 10;
//            case "11:15:00 AM":
//                return 11;
//            case "11:20:00 AM":
//                return 12;
//            case "11:30:00 AM":
//                return 13;
//            case "11:45:00 AM":
//                return 14;
//            case "12:00:00 PM":
//                return 15;
//            case "12:15:00 PM":
//                return 16;
//            case "12:20:00 PM":
//                return 17;
//            case "12:30:00 PM":
//                return 18;
//            case "12:45:00 PM":
//                return 19;
//            case "1:00:00 PM":
//                return 20;
//            case "1:30:00 PM":
//                return 21;
//            case "2:00:00 PM":
//                return 22;
//            case "2:15:00 PM":
//                return 23;
//            case "2:20:00 PM":
//                return 24;
//            case "2:30:00 PM":
//                return 25;
//            case "3:00:00 PM":
//                return 26;
//            case "3:15:00 PM":
//                return 27;
//            case "3:20:00 PM":
//                return 28;
//            case "3:30:00 PM":
//                return 29;
//            case "3:45:00 PM":
//                return 30;
//            case "3:50:00 PM":
//                return 31;
//            case "4:00:00 PM":
//                return 32;
//            case "4:20:00 PM":
//                return 33;
//            case "4:30:00 PM":
//                return 34;
//            case "4:45:00 PM":
//                return 35;
//            case "5:00:00 PM":
//                return 36;
//            case "5:15:00 PM":
//                return 37;
//            case "5:20:00 PM":
//                return 38;
//            case "5:30:00 PM":
//                return 39;
//            case "5:45:00 PM":
//                return 40;
//            case "6:00:00 PM":
//                return 41;
//            case "6:15:00 PM":
//                return 42;
//            case "6:30:00 PM":
//                return 43;
//            case "6:45:00 PM":
//                return 44;
//            case "7:00:00 PM":
//                return 45;
//            case "7:30:00 PM":
//                return 46;
//            case "9:30:00 PM":
//                return 47;
//            case "10:00:00 PM":
//                return 48;
//            case "11:30:00 PM":
//                return 49;
//            default:
//                // code block
//                return -1; //failed to convert mtg. time str. to integer time stamp :(
//        }

        return AllPossibleTimeStamps.indexOf(mtgTime);

    }

    public static String ParseIntegerTimeStamp2MtgTimeStr(int integerTimeStamp){

//        switch(integerTimeStamp) {
//            case 1:
//                return "8:00:00 AM";
//            case 2:
//                return "8:30:00 AM";
//            case 3:
//                return "9:00:00 AM";
//            case 4:
//                return "9:20:00 AM";
//            case 5:
//                return "9:30:00 AM";
//            case 6:
//                return "9:45:00 AM";
//            case 7:
//                return "10:00:00 AM";
//            case 8:
//                return "10:20:00 AM";
//            case 9:
//                return "10:30:00 AM";
//            case 10:
//                return "10:50:00 AM";
//            case 11:
//                return "11:15:00 AM";
//            case 12:
//                return "11:20:00 AM";
//            case 13:
//                return "11:30:00 AM";
//            case 14:
//                return "11:45:00 AM";
//            case 15:
//                return "12:00:00 PM";
//            case 16:
//                return "12:15:00 PM";
//            case 17:
//                return "12:20:00 PM";
//            case 18:
//                return "12:30:00 PM";
//            case 19:
//                return "12:45:00 PM";
//            case 20:
//                return "1:00:00 PM";
//            case 21:
//                return "1:30:00 PM";
//            case 22:
//                return "2:00:00 PM";
//            case 23:
//                return "2:15:00 PM";
//            case 24:
//                return "2:20:00 PM";
//            case 25:
//                return "2:30:00 PM";
//            case 26:
//                return "3:00:00 PM";
//            case 27:
//                return "3:15:00 PM";
//            case 28:
//                return "3:20:00 PM";
//            case 29:
//                return "3:30:00 PM";
//            case 30:
//                return "3:45:00 PM";
//            case 31:
//                return "3:50:00 PM";
//            case 32:
//                return "4:00:00 PM";
//            case 33:
//                return "4:20:00 PM";
//            case 34:
//                return "4:30:00 PM";
//            case 35:
//                return "4:45:00 PM";
//            case 36:
//                return "5:00:00 PM";
//            case 37:
//                return "5:15:00 PM";
//            case 38:
//                return "5:20:00 PM";
//            case 39:
//                return "5:30:00 PM";
//            case 40:
//                return "5:45:00 PM";
//            case 41:
//                return "6:00:00 PM";
//            case 42:
//                return "6:15:00 PM";
//            case 43:
//                return "6:30:00 PM";
//            case 44:
//                return "6:45:00 PM";
//            case 45:
//                return "7:00:00 PM";
//            case 46:
//                return "7:30:00 PM";
//            case 47:
//                return "9:30:00 PM";
//            case 48:
//                return "10:00:00 PM";
//            case 49:
//                return "11:30:00 PM";
//            default:
//                // code block
//                return ""; //failed to convert integer time stamp to mtg. time str :(
//        }

        return AllPossibleTimeStamps.get(integerTimeStamp);

    }

    public static List<String> ParseDayBooleans2List(
            boolean monFlag,
            boolean tuesFlag,
            boolean wedFlag,
            boolean thursFlag,
            boolean friFlag
    ){
        List<String> dayAsString = new ArrayList<>();

        if(monFlag)
            dayAsString.add("Monday");
        if(tuesFlag)
            dayAsString.add("Tuesday");
        if(wedFlag)
            dayAsString.add("Wednesday");
        if(thursFlag)
            dayAsString.add("Thursday");
        if(friFlag)
            dayAsString.add("Friday");

        return dayAsString;
    }

    public static void SaveFrameAsImage(JFrame frame, int planNo, int scheduleIndex, String savePath) throws IOException, AWTException {
//
//        JPanel panel = new JPanel ();
//        panel.setLayout(new FlowLayout());
//        panel.setBackground(Color.WHITE);
//        panel.add(vv);
//
//        Properties p = new Properties();
//        p.setProperty("PageSize","A4");
//
//// vv is the VirtualizationViewer
//
//        VectorGraphics g = new GIFGraphics2D(new File("Network.gif"), vv);
//
//        g.setProperties(p);
//        g.startExport();
//        panel.print(g);
//        g.endExport();


        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

//        Dimension dim = new Dimension(frameWidth, frameHeight);
//        frame.setPreferredSize(dim);


//        Rectangle bounds = new Rectangle(frame.getBounds());


        BufferedImage image = new BufferedImage(frameWidth,frameHeight,BufferedImage.TYPE_INT_RGB);
//        frame.setLocation(-100000000,-100000000);
//        frame.setOpacity(0);
//        frame.dispose();
        frame.setVisible(true);
//        frame.setUndecorated(true);

        frame.printAll(image.getGraphics());
        frame.setVisible(false);
        frame.dispose();
//        BufferedImage image = ScreenImage.createImage(frame);
//        Graphics2D graphics2D = image.createGraphics();
//        Container frameContent = frame.getContentPane();
//        frame.paint(graphics2D);
//        graphics2D.dispose();
        new File(savePath + "Weekly Schedule Visualizations/").mkdirs();
        File jpegFile = new File(
                savePath
                + "Weekly Schedule Visualizations/Plan #" + planNo
                        + "_AsIn_Schedule #" + (scheduleIndex + 1)
                + ".jpeg");
        ImageIO.write(image,"jpeg", jpegFile);
    }

}
