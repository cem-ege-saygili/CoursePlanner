package domain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;

public class WeeklyScheduleGUI {

    private String[] commands = {
            "UP",
            "DOWN",
            "LEFT",
            "RIGHT"
    };

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame scheduleFrame;

    JButton buttonCloseBackgroundPanel;
    JButton buttonNextSchedule;
    JButton buttonPrevSchedule;


    private ActionListener panelAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = (String) ae.getActionCommand();
            if (command.equals("DOWN")) {
                buttonCloseBackgroundPanel.doClick();
            }else if (command.equals("RIGHT")) {
                buttonCloseBackgroundPanel.doClick();
                buttonNextSchedule.doClick();
            }else if (command.equals("LEFT")) {
                buttonCloseBackgroundPanel.doClick();
                buttonPrevSchedule.doClick();
            }

            //repaint();
        }
    };

    public WeeklyScheduleGUI(JFrame scheduleFrame, List<JButton> btnList) {
        this.scheduleFrame = scheduleFrame;
        buttonCloseBackgroundPanel = btnList.get(0);
        buttonNextSchedule = btnList.get(1);
        buttonPrevSchedule = btnList.get(2);
        //createWeeklySchedule();
    }

    public void createWeeklySchedule(){

    }


    public void createWeeklySchedule1(Schedule scheduleToView, JFrame scheduleFrame){

//        JButton btnCloseBackgroundPanel = btnList.get(0);
//        JButton btnNextSchedule = btnList.get(1);
//        JButton btnPrevSchedule = btnList.get(2);

        scheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scheduleFrame.setSize(1200, 840);

//        buttonCloseBackgroundPanel = btnCloseBackgroundPanel;
//        buttonNextSchedule = btnNextSchedule;
//        buttonPrevSchedule = btnPrevSchedule;


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
        buttonCloseBackgroundPanel.setBounds(scheduleFrameWidth-75,scheduleFrameHeight-50,75,25);
        buttonNextSchedule.setBounds(scheduleFrameWidth-175,scheduleFrameHeight-50,75,25);
        buttonPrevSchedule.setBounds(scheduleFrameWidth-275,scheduleFrameHeight-50,75,25);
        scheduleFrame.add(buttonCloseBackgroundPanel);
        scheduleFrame.add(buttonNextSchedule);
        scheduleFrame.add(buttonPrevSchedule);
        scheduleFrame.setVisible(true);

    }

    public static void addClassPanels(JFrame scheduleFrame, Class currentClass) {
        int yStart = getStartTime(currentClass);
        int yEnd = getEndTime(currentClass);
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

                    String timeLabel = currentClass.getStartTime().substring(0, 5) + "-" + currentClass.getEndTime().substring(0, 5);
                    JLabel currentTimeLabel = new JLabel(timeLabel);
                    currentTimeLabel.setBounds(150 + dayIndex * offsetX, yStart + 150, 200, 20);
                    scheduleFrame.add(currentTimeLabel);

                    ClassPanel currentClassPanel = new ClassPanel(100 + dayIndex * offsetX, yStart + 100, 200, yEnd - yStart);
                    currentClassPanel.setVisible(true);
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
