package domain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WeeklyScheduleGUI {

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame scheduleFrame;
    private int screenWidth;
    private int screenHeight;
    private int mainPanelHeight;
    private int mainPanelWidth;
    private int panelDivide;

    public WeeklyScheduleGUI() {
        scheduleFrame = new JFrame("Weekly Schedule");
        this.panelDivide=14;
        //createWeeklySchedule();
    }




    //for testing
    public static void main(String args[]){
        WeeklyScheduleGUI gui= new WeeklyScheduleGUI();
        //gui.test1();
        //gui.test2();
        //gui.createWeeklySchedule2();


    }

    public void createWeeklySchedule(){

    }



    public void createWeeklySchedule2(Schedule scheduleToView, JButton btnCloseBackgroundPanel){
    //public void createWeeklySchedule2(){


        //JFrame mainFrame= new JFrame();
        scheduleFrame.setLayout(null);
        JPanel mainPanel=new JPanel();
        //first column is for time, rest for days.
        int n_columns=6;
        double size=(3/4);
        int mainPanelWidth= 3*(screenSize.width)/4;
        int mainPanelHeight=  3*(screenSize.height/4);
        mainPanelWidth=screenSize.width/2;
        mainPanelHeight=screenSize.height/2;
        this.mainPanelHeight=mainPanelHeight;
        this.mainPanelWidth=mainPanelWidth;
        JPanel[] panelHolder = new JPanel[n_columns];
        mainPanel.setLayout(new GridLayout(1,n_columns));
        mainPanel.setSize(mainPanelWidth,mainPanelHeight);


        //mainPanel.setVisible(true);//making the frame visible
        for(int i=0;i<n_columns;i++){
            panelHolder[i]=new JPanel();
            panelHolder[i].setLayout(null);
            if(i%2==0)
                panelHolder[i].setBackground(Color.gray);
            else
                panelHolder[i].setBackground(Color.lightGray);
            mainPanel.add(panelHolder[i]);
        }

        int n_times=14;
        JLabel[] timeLabels = new JLabel[n_times];
        panelHolder[0].setLayout(new GridLayout(n_times,1));
        JPanel[] timeLabelCells= new JPanel[n_times];

        for(int i=0;i<n_times;i++){
            String text=Integer.toString((i+8))+".00";
            //System.out.println(text);
            timeLabels[i]= new JLabel(text);
            timeLabelCells[i]=new JPanel();
            if(i%2==0)
                timeLabelCells[i].setBackground(Color.cyan);
            else
                timeLabelCells[i].setBackground(Color.green);

            timeLabelCells[i].add(timeLabels[i]);
            timeLabels[i].setLocation(timeLabelCells[i].getWidth()/2,timeLabelCells[i].getHeight()/2);
            System.out.println("x: "+timeLabelCells[i].getWidth()/2);
            System.out.println("y: "+timeLabelCells[i].getHeight()/2);
            panelHolder[0].add(timeLabelCells[i]);
        }

        double inc= (screenSize.height/n_times)/4;

        String className1="ethr 105";
        String className2="comp 302";
        String hour1="";
        String hour2="";
        //panelHolder[1].setLayout(new BoxLayout(panelHolder[1],BoxLayout.Y_AXIS));

        //class1Panel.setLocation(0,(int)inc*10);
        // class1Panel.setSize(panelHolder[1].getWidth(),(int)inc*5);
        //class1Panel.setBounds(0,(int)inc*2,panelHolder[1].getWidth(),(int)inc*5);

        /*
        JPanel class1Panel = new JPanel();
        class1Panel.setBounds(0,getClassLocation(mainPanelHeight,14,11,30),mainPanelWidth/6,(mainPanelHeight/14)*(5/4));

        class1Panel.setVisible(true);
        class1Panel.setBackground(Color.green);
        JLabel classLabel1=new JLabel(className1);
        classLabel1.setBackground(Color.green);
        classLabel1.setBounds(0,getClassLocation(mainPanelHeight,14,11,30),mainPanelWidth/6,(mainPanelHeight/14)*(5/4));
        classLabel1.setVisible(true);
        panelHolder[1].add(classLabel1);
        panelHolder[1].add(class1Panel);

         */
        for (ClassBundle currentBundle : scheduleToView.getClassBundleList()) {

            Class currentClass = currentBundle.getLecClass();
            addClasstoPanel(panelHolder, currentClass);

            currentClass = currentBundle.getDisClass();
            addClasstoPanel(panelHolder, currentClass);

            currentClass = currentBundle.getLabClass();
            addClasstoPanel(panelHolder, currentClass);

            currentClass = currentBundle.getPrbClass();
            addClasstoPanel(panelHolder, currentClass);

        }

        for(int i=1;i<6;i++) {
            panelHolder[1].setVisible(true);
        }

        mainPanel.setVisible(true);//making the frame visible
        scheduleFrame.add(mainPanel);
        mainPanel.setLocation(100,200);

        scheduleFrame.setVisible(true);

        /*
        BackgroundPanel bgpanel = new BackgroundPanel();
        bgpanel.setVisible(true);
        scheduleFrame.add(bgpanel);
        */

        int scheduleFrameWidth = scheduleFrame.getWidth();
        int scheduleFrameHeight = scheduleFrame.getHeight();
        btnCloseBackgroundPanel.setBounds(scheduleFrameWidth-75,scheduleFrameHeight-50,75,25);
        scheduleFrame.add(btnCloseBackgroundPanel);
        scheduleFrame.setVisible(true);

    }

    private void addClasstoPanel(JPanel[] panelHolder,Class currentClass){
        if (currentClass != null && currentClass.isMonFlag()) {
            addClassPanel(panelHolder[1], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
            addClassPanel(panelHolder[3], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
        }else if (currentClass != null && currentClass.isTuesFlag()) {
            addClassPanel(panelHolder[2], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
            addClassPanel(panelHolder[4], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
        }else if (currentClass != null && currentClass.isWedFlag()) {
            addClassPanel(panelHolder[3], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
        }else if (currentClass != null && currentClass.isThursFlag()) {
            addClassPanel(panelHolder[4], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
        }else if (currentClass != null && currentClass.isFriFlag()) {
            addClassPanel(panelHolder[5], currentClass,this.mainPanelHeight,this.mainPanelWidth,this.panelDivide);
        }
    }

    public void createWeeklySchedule1(Schedule scheduleToView, JButton btnCloseBackgroundPanel){



        scheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scheduleFrame.setSize(1200, 840);

        for (ClassBundle currentBundle : scheduleToView.getClassBundleList()) {

            Class currentClass = currentBundle.getLecClass();
            addClassPanels(scheduleFrame, currentClass);

            currentClass = currentBundle.getDisClass();
            addClassPanels(scheduleFrame, currentClass);

            currentClass = currentBundle.getLabClass();
            addClassPanels(scheduleFrame, currentClass);

            currentClass = currentBundle.getPrbClass();
            addClassPanels(scheduleFrame, currentClass);

        }

        JLabel mondayLabel = new JLabel("MONDAY");
        mondayLabel.setBounds(150, 50, 200, 20);
        mondayLabel.setFont(mondayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(mondayLabel);

        JLabel tuesdayLabel = new JLabel("TUESDAY");
        tuesdayLabel.setBounds(350, 50, 200, 20);
        tuesdayLabel.setFont(tuesdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(tuesdayLabel);

        JLabel wednesdayLabel = new JLabel("WEDNESDAY");
        wednesdayLabel.setBounds(550, 50, 200, 20);
        wednesdayLabel.setFont(wednesdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(wednesdayLabel);

        JLabel thursdayLabel = new JLabel("THURSDAY");
        thursdayLabel.setBounds(750, 50, 200, 20);
        thursdayLabel.setFont(thursdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(thursdayLabel);

        JLabel fridayLabel = new JLabel("FRIDAY");
        fridayLabel.setBounds(950, 50, 200, 20);
        fridayLabel.setFont(fridayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(fridayLabel);


        for (int i = 8; i < 19; i++) {
            JLabel timeLabel = new JLabel(Integer.toString(i) + ":00");
            timeLabel.setBounds(50, (((i - 8) * 600) / 10) + 90, 200, 20);
            scheduleFrame.add(timeLabel);
        }


        BackgroundPanel bgpanel = new BackgroundPanel();
        bgpanel.setVisible(true);
        scheduleFrame.add(bgpanel);

        scheduleFrame.setLayout(null);

        int scheduleFrameWidth = scheduleFrame.getWidth();
        int scheduleFrameHeight = scheduleFrame.getHeight();
        btnCloseBackgroundPanel.setBounds(scheduleFrameWidth-75,scheduleFrameHeight-50,75,25);
        scheduleFrame.add(btnCloseBackgroundPanel);
        scheduleFrame.setVisible(true);

    }

    public static void addClassPanels(JFrame scheduleFrame, Class currentClass) {
        addClassPanelMonday(scheduleFrame, currentClass);
        addClassPanelTuesday(scheduleFrame, currentClass);
        addClassPanelWednesday(scheduleFrame, currentClass);
        addClassPanelThursday(scheduleFrame, currentClass);
        addClassPanelFriday(scheduleFrame, currentClass);
    }

    public void addClassPanel(JPanel dayPanel, Class currentClass,int mainPanelHeight,int mainPanelWidth,int panelDivide){
        String classLabelStr = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
        JLabel classLabel = new JLabel(classLabelStr);
        int startLoc=getClassLocationFromString(currentClass,mainPanelHeight,panelDivide);
        double panelHeight=((double) ((mainPanelHeight/14)*5)/4);
        System.out.println("panelHeight: "+panelHeight);
        classLabel.setBounds(0,startLoc,mainPanelWidth/6,(int) panelHeight/2);

        dayPanel.add(classLabel);
        String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
        JLabel currentTimeLabel = new JLabel(timeLabel);
        currentTimeLabel.setBounds(0, startLoc+20, 200, 20);
        dayPanel.add(currentTimeLabel);

        JPanel classPanel=new JPanel();
        classPanel.setBounds(0,startLoc,mainPanelWidth/6,(int) panelHeight);
        classPanel.setBackground(Color.GREEN);
        classPanel.setVisible(true);
        dayPanel.add(classPanel);
    }

    private static void addClassPanelMonday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isMonFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(150, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);
            System.out.println(classLabel+" ystart: "+ystart+" yend"+yend );
            System.out.println(classLabel+" ystart: "+currentClass.getStartTime()+" yend"+currentClass.getEndTime());
            String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
            JLabel currentTimeLabel = new JLabel(timeLabel);
            currentTimeLabel.setBounds(150, ystart + 130, 200, 20);
            scheduleFrame.add(currentTimeLabel);

            ClassPanel currentClassPanel = new ClassPanel(100, ystart + 100, 200, yend - ystart);
            currentClassPanel.setVisible(true);
            scheduleFrame.add(currentClassPanel);
        }
    }

    private static void addClassPanelTuesday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isTuesFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(350, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);

            String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
            JLabel currentTimeLabel = new JLabel(timeLabel);
            currentTimeLabel.setBounds(350, ystart + 130, 200, 20);
            scheduleFrame.add(currentTimeLabel);

            ClassPanel currentClassPanel = new ClassPanel(300, ystart + 100, 200, yend - ystart);
            currentClassPanel.setVisible(true);
            scheduleFrame.add(currentClassPanel);
        }
    }

    private static void addClassPanelWednesday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isWedFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(550, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);

            String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
            JLabel currentTimeLabel = new JLabel(timeLabel);
            currentTimeLabel.setBounds(550, ystart + 130, 200, 20);
            scheduleFrame.add(currentTimeLabel);

            ClassPanel currentClassPanel = new ClassPanel(500, ystart + 100, 200, yend - ystart);
            currentClassPanel.setVisible(true);
            scheduleFrame.add(currentClassPanel);
        }
    }

    private static void addClassPanelThursday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isThursFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(750, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);

            String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
            JLabel currentTimeLabel = new JLabel(timeLabel);
            currentTimeLabel.setBounds(750, ystart + 130, 200, 20);
            scheduleFrame.add(currentTimeLabel);

            ClassPanel currentClassPanel = new ClassPanel(700, ystart + 100, 200, yend - ystart);
            currentClassPanel.setVisible(true);
            scheduleFrame.add(currentClassPanel);
        }
    }

    private static void addClassPanelFriday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isFriFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(950, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);

            String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
            JLabel currentTimeLabel = new JLabel(timeLabel);
            currentTimeLabel.setBounds(950, ystart + 130, 200, 20);
            scheduleFrame.add(currentTimeLabel);

            ClassPanel currentClassPanel = new ClassPanel(900, ystart + 100, 200, yend - ystart);
            currentClassPanel.setVisible(true);
            scheduleFrame.add(currentClassPanel);
        }
    }


    public static int getStartTime(Class currentClass) {
        int startTimeInMinutes;
        if (currentClass.getStartTime().length() == 11) {
            startTimeInMinutes = Integer.parseInt(currentClass.getStartTime().substring(0, 2)) * 60
                    + Integer.parseInt(currentClass.getStartTime().substring(3, 5));
            if (currentClass.getStartTime().charAt(9) == 'P')
                startTimeInMinutes += 12 * 60;
        } else {
            startTimeInMinutes = Integer.parseInt(currentClass.getStartTime().substring(0, 1)) * 60
                    + Integer.parseInt(currentClass.getStartTime().substring(2, 4));
            if (currentClass.getStartTime().charAt(8) == 'P')
                startTimeInMinutes += 12 * 60;
        }
        //System.out.println(currentClass.getStartTime());
        return ((startTimeInMinutes - 480) * 600) / 600;
    }


    private int getClassLocationFromString(Class currentClass,int PanelHeight,int PanelDivide){
        int startHour;
        int startMin;
        if (currentClass.getStartTime().length() == 11) {

            startHour=Integer.parseInt(currentClass.getStartTime().substring(0, 2));
            startMin=Integer.parseInt(currentClass.getStartTime().substring(3, 5));
            if (currentClass.getStartTime().charAt(9) == 'P')
                startHour += 12;
        } else {

            startHour=Integer.parseInt(currentClass.getStartTime().substring(0, 1));
            startMin=Integer.parseInt(currentClass.getStartTime().substring(2, 4));
            if (currentClass.getStartTime().charAt(8) == 'P')
                startHour += 12;
        }

        return getClassLocation(PanelHeight,PanelDivide,startHour,startMin);
    }


    private int getClassLocation(int PanelHeight,int PanelDivide,int hour,int min){

        int startHour=hour;
        int startMin=min;
        System.out.println("startHour"+startHour);
        System.out.println("startMin "+startMin);
        /*
        if (currentClass.getStartTime().length() == 11) {

            startHour=Integer.parseInt(currentClass.getStartTime().substring(0, 2));
            startMin=Integer.parseInt(currentClass.getStartTime().substring(3, 5));
            if (currentClass.getStartTime().charAt(9) == 'P')
                startHour += 12;
        } else {

            startHour=Integer.parseInt(currentClass.getStartTime().substring(0, 1));
            startMin=Integer.parseInt(currentClass.getStartTime().substring(2, 4));
            if (currentClass.getStartTime().charAt(8) == 'P')
                startMin += 12;
        }

         */
        //System.out.println(currentClass.getStartTime());

        int hourInc=PanelHeight/PanelDivide;
        double locationD= hourInc*(startHour-8);
        System.out.println("locationDHour"+locationD);
        locationD =locationD+ (((startMin))/60)*hourInc;

        System.out.println("hourInc: "+hourInc);
        System.out.println("location: "+locationD);
        return (int)locationD+5;
    }


    public static int getEndTime(Class currentClass) {
        int endTimeInMinutes;
        if (currentClass.getEndTime().length() == 11) {
            endTimeInMinutes = Integer.parseInt(currentClass.getEndTime().substring(0, 2)) * 60
                    + Integer.parseInt(currentClass.getEndTime().substring(3, 5));
            if (currentClass.getEndTime().charAt(9) == 'P' && !currentClass.getEndTime().substring(0, 2).equals("12"))
                endTimeInMinutes += 12 * 60;
        } else {
            endTimeInMinutes = Integer.parseInt(currentClass.getEndTime().substring(0, 1)) * 60
                    + Integer.parseInt(currentClass.getEndTime().substring(2, 4));
            if (currentClass.getEndTime().charAt(8) == 'P' && !currentClass.getEndTime().substring(0, 2).equals("12"))
                endTimeInMinutes += 12 * 60;
        }
        return ((endTimeInMinutes - 480) * 600) / 600;
    }







    public void test1(){
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(screenSize.width,screenSize.height);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    public void test2(){
        JFrame f=new JFrame("Button Example");
        final JTextField tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,95,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText("Welcome to Javatpoint.");
                tf.setLocation(400,500);
            }
        });
        f.add(b);f.add(tf);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public JFrame getScheduleFrame() {
        return scheduleFrame;
    }

    public void setScheduleFrame(JFrame scheduleFrame) {
        this.scheduleFrame = scheduleFrame;
    }

}
