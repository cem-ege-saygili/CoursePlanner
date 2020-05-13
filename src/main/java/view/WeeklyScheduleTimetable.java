package view;

import model.Class;
import model.ClassBundle;
import model.tools.Parser;
import model.Schedule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class WeeklyScheduleTimetable {

    private String[] commands = {
            "UP",
            "DOWN",
            "LEFT",
            "RIGHT"
    };

    public static JFrame scheduleFrame = new JFrame("Weekly Schedule");

    JButton buttonCloseBackgroundPanel;
    JButton buttonNextSchedule;
    JButton buttonPrevSchedule;

    private int screenWidth;
    private int screenHeight;
    private int mainPanelHeight;
    private int mainPanelWidth;
    private int panelDivide;
    /*
    private static MouseListener mouseAction = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            System.out.println("X: " + p.x + "Y: " + p.y);
        }
        @Override
        public void mouseExited(MouseEvent e) {}
    };
    */
    private ActionListener panelAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = (String) ae.getActionCommand();
            if (command.equals("DOWN")) {
                buttonCloseBackgroundPanel.doClick();
            }else if (command.equals("RIGHT")) {
//                buttonCloseBackgroundPanel.doClick();
                buttonNextSchedule.doClick();
            }else if (command.equals("LEFT")) {
//                buttonCloseBackgroundPanel.doClick();
                buttonPrevSchedule.doClick();
            }

            //repaint();
        }
    };

    public WeeklyScheduleTimetable(List<JButton> btnList) {
//        scheduleFrame = new JFrame("Weekly Schedule");
        this.panelDivide=14;
        buttonCloseBackgroundPanel = btnList.get(0);
        buttonNextSchedule = btnList.get(1);
        buttonPrevSchedule = btnList.get(2);
    }

    public void saveWeeklySchedulesAsImages(List<Schedule> schedules, String savePath, JLabel lblStatusBar) throws IOException, AWTException {
        int planNo =0;
        for(Schedule curSchedule2SaveAsImage:schedules){
            saveWeeklyScheduleAsImage(curSchedule2SaveAsImage, ++planNo, savePath);
            Double completeLength = Double.valueOf(schedules.size());
            showImageOutProgress2User(lblStatusBar,(schedules.size() - planNo),completeLength,curSchedule2SaveAsImage);
        }

    }

    public void saveWeeklyScheduleAsImage(Schedule scheduleToView, int planNo, String savePath) throws IOException, AWTException {

        scheduleFrame = new JFrame("Weekly Schedule");
        int selectedItemIndex = Main.ScheduleListComboBox.getSelectedIndex();
        createWeeklySchedule(scheduleToView, selectedItemIndex);
//        scheduleFrame.setVisible(true);
//        try {
//            //TimeUnit.SECONDS.sleep(1);
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        int scheduleIndex = scheduleToView.getScheduleId();
        Parser.SaveFrameAsImage(scheduleFrame, planNo, scheduleIndex, savePath);
        scheduleFrame.setVisible(false);
        scheduleFrame = new JFrame("Weekly Schedule");

    }

    private void showImageOutProgress2User(JLabel lblStatusBar, int remaining, Double completeLength, Schedule curSchedule) throws IOException {
        String str2Console = "\n\nIMAGE OUT:\t" + curSchedule + "\tREMAINING: " + remaining + "\n\n";
        Double percentage = remaining/completeLength;
        percentage = Math.floor(percentage*100);
        String percentage2BeDisplayed = percentage.toString();
        percentage2BeDisplayed = percentage2BeDisplayed.substring(0,percentage2BeDisplayed.length()-2) + "%";
        System.out.println(str2Console);
        lblStatusBar.setText("<html>"
                + "Saving Weekly Schedules As Image: "
                + "<font face=\"verdana\" color=\"red\"><b><i>"
                + percentage2BeDisplayed
                + "</i></b></font> left.</html>");
    }

    public void createWeeklySchedule(Schedule scheduleToView, int selectedItemIndex){

        setTitleForScheduleFrame(scheduleToView, selectedItemIndex);

        clearTimetableFromScheduleFrame();//clearing the schedule frame should there be any left-over from the prev. schedule
                                          //(i.e. clean-start)

        scheduleFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//only closes the view weekly schedule pane
                                                                              // instead of terminating the entire program.
        scheduleFrame.setSize(1200, 840);
        int dayLabelStart_Y = 25;
        int defaultLabelHeight = 20;

        for (ClassBundle currentBundle : scheduleToView.getClassBundleList()) {

            Class currentClass = currentBundle.getLecClass();
            if(currentClass !=null)
                addClassPanels(scheduleFrame, currentClass, dayLabelStart_Y);

            currentClass = currentBundle.getDisClass();
            if(currentClass !=null)
                addClassPanels(scheduleFrame, currentClass, dayLabelStart_Y);

            currentClass = currentBundle.getLabClass();
            if(currentClass !=null)
                addClassPanels(scheduleFrame, currentClass, dayLabelStart_Y);

            currentClass = currentBundle.getPrbClass();
            if(currentClass !=null)
                addClassPanels(scheduleFrame, currentClass, dayLabelStart_Y);

        }

        JLabel mondayLabel = new JLabel("MONDAY");
        mondayLabel.setBounds(150, dayLabelStart_Y, 200, defaultLabelHeight);
        mondayLabel.setFont(mondayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(mondayLabel);

        JLabel tuesdayLabel = new JLabel("TUESDAY");
        tuesdayLabel.setBounds(350, dayLabelStart_Y, 200, defaultLabelHeight);
        tuesdayLabel.setFont(tuesdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(tuesdayLabel);

        JLabel wednesdayLabel = new JLabel("WEDNESDAY");
        wednesdayLabel.setBounds(550, dayLabelStart_Y, 200, defaultLabelHeight);
        wednesdayLabel.setFont(wednesdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(wednesdayLabel);

        JLabel thursdayLabel = new JLabel("THURSDAY");
        thursdayLabel.setBounds(750, dayLabelStart_Y, 200, defaultLabelHeight);
        thursdayLabel.setFont(thursdayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(thursdayLabel);

        JLabel fridayLabel = new JLabel("FRIDAY");
        fridayLabel.setBounds(950, dayLabelStart_Y, 200, defaultLabelHeight);
        fridayLabel.setFont(fridayLabel.getFont().deriveFont(20.0f));
        scheduleFrame.add(fridayLabel);


        int firstTimeStamp_Y = dayLabelStart_Y + 40;
        int lastTimeStamp_Y = firstTimeStamp_Y;

        for (int i = 8; i <= 19; i++) {
            JLabel timeLabel = new JLabel(Integer.toString(i) + ":00");
            lastTimeStamp_Y = (((i - 8) * 600) / 10) + firstTimeStamp_Y;
            timeLabel.setBounds(50, lastTimeStamp_Y, 200, 20);
            scheduleFrame.add(timeLabel);
        }

        int bgPanel_startY = firstTimeStamp_Y;
        int bgPanel_endY = lastTimeStamp_Y + defaultLabelHeight;

        BackgroundPanel bgpanel = new BackgroundPanel(firstTimeStamp_Y, bgPanel_endY);
        for (int i = 0; i < commands.length; i++)
            bgpanel.registerKeyboardAction(panelAction,
                    commands[i],
                    KeyStroke.getKeyStroke(commands[i]),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);

        bgpanel.setVisible(true);
        scheduleFrame.add(bgpanel);

        scheduleFrame.setLayout(null);

        int scheduleFrameWidth = scheduleFrame.getWidth();
        int scheduleFrameHeight = scheduleFrame.getHeight();
        int buttonsBottomOffset = 75;
        int buttonsY = scheduleFrameHeight-buttonsBottomOffset;
        buttonCloseBackgroundPanel.setBounds(scheduleFrameWidth-125,buttonsY,75,25);
        buttonNextSchedule.setBounds(scheduleFrameWidth-225+40,buttonsY,75,25);
        buttonPrevSchedule.setBounds(scheduleFrameWidth-325+40,buttonsY,75,25);
//        scheduleFrame.add(buttonCloseBackgroundPanel);
        scheduleFrame.add(buttonNextSchedule);
        scheduleFrame.add(buttonPrevSchedule);

//        Component[] cps = scheduleFrame.getComponents();

        if(!scheduleFrame.isVisible())
            scheduleFrame.setVisible(true);
    }

    private void setTitleForScheduleFrame(Schedule scheduleToView, int insertedElementCount) {
        int planNo = insertedElementCount +1;
        String selectedCourses = cleanText(scheduleToView.getCourseSubjectsAndCatalogsAsString());

        String curFrameTitle = "Plan #" + planNo + ": Weekly Schedule for \"" + selectedCourses +"\"";
        scheduleFrame.setTitle(curFrameTitle);
    }

    private String cleanText(String str2BeCleaned){
        String str = "";
        for(char c:str2BeCleaned.toCharArray()){
            if(c != '\"' && c != '{' && c!= '}')
                str += c;
        }
        return  str;
    }

    private void clearTimetableFromScheduleFrame() {
        Container c = scheduleFrame.getContentPane();
        Component[] cps = c.getComponents();
        for(int i=0;i<cps.length;i++){
            if(!(cps[i] instanceof JButton)){
                Component cp2BeRemoved = cps[i];
                c.remove(cp2BeRemoved);
            }
        }
        scheduleFrame.repaint();
    }

    public static void addClassPanels(JFrame scheduleFrame, Class currentClass, int dayLabelStart_Y) {
        int yOffset = (dayLabelStart_Y-50);
        int yStart = getStartTime(currentClass) + yOffset;
        int yEnd = getEndTime(currentClass) + yOffset;
        int offsetX = 200;
        for(int dayIndex = 0; dayIndex < 5; dayIndex++) {
            boolean dayFlag = false;
            if (currentClass != null) {
                switch (dayIndex) {
                    case 0:
                        dayFlag = currentClass.isMonFlag();
                        break;
                    case 1:
                        dayFlag = currentClass.isTuesFlag();
                        break;
                    case 2:
                        dayFlag = currentClass.isWedFlag();
                        break;
                    case 3:
                        dayFlag = currentClass.isThursFlag();
                        break;
                    case 4:
                        dayFlag = currentClass.isFriFlag();
                        break;
                }

                if (dayFlag) {
                    String classLabel = currentClass.getCourseName() + " "
                            + Integer.toString(currentClass.getCourseCatalog());

                    String curClassComponentStr = " (" + currentClass.getComponent() + ")";

                    String curClassLocationStr = "@\"" + currentClass.getLocation() + "\"";

                    JLabel currentClassLabel = new JLabel();

                    currentClassLabel.setText("<html>"
                            + classLabel
                            + "<font face=\"verdana\" color=\"red\"><b><i>"
                            + curClassComponentStr
                            + "</i></b></font><br>"
                            + "<font face=\"verdana\" color=\"blue\"><b><i>"
                            + curClassLocationStr
                            + "</i></b></font>"
                            + "</html>");
                    currentClassLabel.setBounds(150 + dayIndex * offsetX, yStart + 110, 200, 40);
                    scheduleFrame.add(currentClassLabel);

                    String timeLabel = "";
                    if (currentClass.getStartTime().length() == 11) {
                        timeLabel += currentClass.getStartTime().substring(0, 5);
                        if (currentClass.getStartTime().charAt(9) == 'P' && !currentClass.getStartTime().substring(0, 2).equals("12")){
                            timeLabel = Integer.toString(Integer.parseInt(timeLabel.substring(0, 2)) + 12) + ":" + timeLabel.substring(3,5);
                        }
                    }
                    else {
                        timeLabel += currentClass.getStartTime().substring(0, 4);
                        if (currentClass.getStartTime().charAt(8) == 'P' && !currentClass.getStartTime().substring(0, 2).equals("12")){
                            timeLabel = Integer.toString(Integer.parseInt(timeLabel.substring(0, 1)) + 12) + ":" + timeLabel.substring(2,4);
                        }
                    }

                    String timeEndLabel = "";
                    if (currentClass.getEndTime().length() == 11) {
                        timeEndLabel += currentClass.getEndTime().substring(0, 5);
                        if (currentClass.getEndTime().charAt(9) == 'P' && !currentClass.getEndTime().substring(0, 2).equals("12")){
                            timeEndLabel = Integer.toString(Integer.parseInt(timeEndLabel.substring(0, 2)) + 12) + ":" + timeEndLabel.substring(3,5);
                        }
                    }
                    else {
                        timeEndLabel += currentClass.getEndTime().substring(0, 4);
                        if (currentClass.getEndTime().charAt(8) == 'P' && !currentClass.getEndTime().substring(0, 2).equals("12")){
                            timeEndLabel = Integer.toString(Integer.parseInt(timeEndLabel.substring(0, 1)) + 12) + ":" + timeEndLabel.substring(2,4);
                        }
                    }


                    timeLabel +=  "-" + timeEndLabel;

                    JLabel currentTimeLabel = new JLabel(timeLabel);
                    currentTimeLabel.setBounds(150 + dayIndex * offsetX, yStart + 150, 200, 20);
                    scheduleFrame.add(currentTimeLabel);

                    String curClassComponent = currentClass.getComponent();
                    ClassPanel currentClassPanel = new ClassPanel(100 + dayIndex * offsetX,
                                                                yStart + 100,
                                                                200,
                                                                yEnd - yStart,
                                                                curClassComponent);
                    currentClassPanel.setVisible(true);

                    currentClassPanel.setToolTipText("<html>"
                            + "Course Description: " + currentClass.getCourseDescription() + "</br>"
                            + "<br>" + "Course Level: " + currentClass.getCourseLevel() + "</br>"
                            + "<br>" + "Course Faculty: " + currentClass.getCourseFaculty() + "</br>"
                            + "<br>" + "Instructor:" + currentClass.getInstructorNameRolePairs() + "</br>"
                            + "</html>");
                    scheduleFrame.add(currentClassPanel);

                }
            }
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
        return ((startTimeInMinutes - 480) * 600) / 600;
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

    public JFrame getScheduleFrame() {
        return scheduleFrame;
    }

    public void setScheduleFrame(JFrame scheduleFrame) {
        this.scheduleFrame = scheduleFrame;
    }

    /*
    public void test(){

        JFrame mainFrame= new JFrame();
        JPanel mainPanel=new JPanel();
        //first column is for time, rest for days.
        int n_columns=6;

        JPanel[] panelHolder = new JPanel[n_columns];
        mainPanel.setLayout(new GridLayout(1,n_columns));
        mainPanel.setSize(screenSize.width,screenSize.height);
        mainPanel.setVisible(true);//making the frame visible
        for(int i=0;i<n_columns;i++){
            panelHolder[i]=new JPanel();
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
                timeLabelCells[i].setBackground(Color.lightGray);
            else
                timeLabelCells[i].setBackground(Color.black);

            timeLabelCells[i].add(timeLabels[i]);
            timeLabels[i].setLocation(timeLabelCells[i].getWidth()/2,timeLabelCells[i].getHeight()/2);
            System.out.println("x: "+timeLabelCells[i].getWidth()/2);
            System.out.println("y: "+timeLabelCells[i].getHeight()/2);
            panelHolder[0].add(timeLabelCells[i]);
        }

        double inc= (screenSize.height/n_times)/4;
        String className1="ethr 105";
        String className2="comp 302";
        //panelHolder[1].setLayout(new BoxLayout(panelHolder[1],BoxLayout.Y_AXIS));
        JPanel class1Panel= new JPanel();
        class1Panel.setLocation(0,(int)inc*10);
        class1Panel.setSize(panelHolder[1].getWidth(),(int)inc*5);
        class1Panel.setBounds(0,(int)inc*2,panelHolder[1].getWidth(),(int)inc*5);

        class1Panel.setBackground(Color.green);
        class1Panel.add(new JLabel(className1));
        JPanel class2Panel = new JPanel();
        class2Panel.add(new JLabel(className2));
        class2Panel.setBounds(0,(int)inc*6,panelHolder[1].getWidth(),(int)inc*5);
        panelHolder[1].add(class1Panel);
        panelHolder[1].add(class2Panel);

        mainPanel.setVisible(true);//making the frame visible
        mainFrame.add(mainPanel);
        mainPanel.setLocation(0,100);

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
    */
}
