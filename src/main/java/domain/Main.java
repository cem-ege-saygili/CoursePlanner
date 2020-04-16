package domain;

import DB_Utilities.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Main {

    static List<Schedule> scheduleListToView = null;

    public static void main(String[] args) {
        System.out.println("\nRunnable\n\n");


        //READ CSV FILE INTO an array of ClassInfo objects. (i.e. classInfoList)

        List<ClassInfo> classInfoList = new ArrayList<>();

//        String fPath = System.getProperty("user.dir") + "/src/main/java/KU_STD_ALL_LEC_COURSECODE_NAME_1341695219.csv";

        String fPath = "inputs/KU_STD_ALL_LEC_COURSECODE_NAME_1341695219.csv";
        String sqlQuery_Create_Location = "inputs/sqlQuery_Create.txt";
        String sqlQuery_Insert_Location = "inputs/sqlQuery_Insert.txt";
        String sqlQuery_SelectDistinctCourseSubjects_Location = "inputs/sqlQuery_selectDistinctCourseSubjects.sql";
        String sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location = "inputs/sqlQuery_selectCourseCatalogsOfChosenCourseSubject.sql";
        String sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location = "inputs/sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog.sql";

        String sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location = "inputs/sqlQuery_selectClassesInfoFromCourseSubject_Catalog.sql";

//        String sqlQuery_Insert2Table_Classes_Location = "inputs/Insert2Table_Classes.txt";
//        String sqlQuery_Insert2Table_Instructors_Location = "inputs/Insert2Table_Instructors.txt";
//        String sqlQuery_Insert2Table_Class_Instructor_Infos_Location = "inputs/Insert2Table_Class_Instructor_Infos.txt";
        List<String> sqlQueryLocationList2Create_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/CreateTable_Courses.txt",
                        "inputs/CreateTable_Classes.txt",
                        "inputs/CreateTable_Instructors.txt",
                        "inputs/CreateTable_Class_Instructor_Infos.txt",
                        "inputs/CreateTable_Class_Course_Infos.txt")
        );

        List<String> sqlQueryLocationList2CleanStartAndFill_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/sqlQuery_CleanStart_DB.txt",
                        "inputs/Insert2Table_Courses.txt",
                        "inputs/Insert2Table_Classes.txt",
                        "inputs/Insert2Table_Instructors.txt",
                        "inputs/Insert2Table_Class_Instructor_Infos.txt",
                        "inputs/Insert2Table_Class_Course_Infos.txt")
        );

        String dbName = "CoursePlannerDB2";

        CreateAndFill_DB_from_CSV(classInfoList, fPath, sqlQuery_Create_Location, sqlQuery_Insert_Location, dbName);

        CreateNormalizedTablesInDB(sqlQueryLocationList2Create_NormalizedTables, dbName);

        CleanStartAndFill_NormalizedTables(sqlQueryLocationList2CleanStartAndFill_NormalizedTables, dbName);

        System.out.println("asdasd");

        /*ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

        cList.add(c6);
        cList.add(c2);cList.add(c7);cList.add(c3);
        cList.add(c1);
        cList.add(c4); cList.add(c5);*/

//        String[] courseSubjectList = new String[] {"A", "B",
//                "C", "D", "E", "F", "G"};


//        Map<String, Integer[]> timeTables = new HashMap<String, Integer[]>();
//        timeTables.put("A", new Integer[]{1, 2} );
//        timeTables.put("B", new Integer[]{1, 4} );
//        timeTables.put("C", new Integer[]{3, 4} );
//        timeTables.put("D", new Integer[]{3, 7} );
//        timeTables.put("E", new Integer[]{7, 8} );
//        timeTables.put("F", new Integer[]{11, 14} );
//        timeTables.put("G", new Integer[]{13, 14} );

        Integer[] priorityValues = new Integer[]{1, 2, 3, 4, 5};

        List<String> courseSubjectList = new ArrayList<>();
        List<Integer> courseCatalogList = new ArrayList<>();

        SelectFromTableInDB.SelectDistinctCourseSubjects(dbName, sqlQuery_SelectDistinctCourseSubjects_Location, courseSubjectList);

        String[] courseSubjectsArr = new String[courseSubjectList.size()];
        courseSubjectsArr = courseSubjectList.toArray(courseSubjectsArr);

        JComboBox<String> courseSubjectsComboBox = new JComboBox<>(courseSubjectsArr);
        JComboBox<Integer> priorityValuesComboBox = new JComboBox<>(priorityValues);

        JComboBox<String> shceduleListComboBox = new JComboBox<>();

        SelectFromTableInDB.SelectCourseCatalogsOfChosenCourseSubject(dbName, sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location,
                courseCatalogList, (String) courseSubjectsComboBox.getSelectedItem());

        Integer[] courseCatalogsArr = new Integer[courseCatalogList.size()];
        courseCatalogsArr = courseCatalogList.toArray(courseCatalogsArr);

        JComboBox<Integer> courseCatalogsComboBox = new JComboBox<>(courseCatalogsArr);

        JLabel lblCourseSubject = new JLabel();
        lblCourseSubject.setText("Course Subject: ");

        JLabel lblCourseCatalog = new JLabel();
        lblCourseCatalog.setText("Course Catalog: ");

        JLabel lblPriority = new JLabel();
        lblPriority.setText("Priority: ");

        JLabel lblCourseFaculty = new JLabel();
        lblCourseFaculty.setText("Course Faculty: ");

        JLabel lblCourseLevel = new JLabel();
        lblCourseLevel.setText("Course Level: ");

        JLabel lblCourseDescr = new JLabel();
        lblCourseDescr.setText("Course Description: ");

        UpdateLabelsFromDB(

                dbName,
                sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                courseSubjectsComboBox,
                courseCatalogsComboBox,
                lblCourseLevel,
                lblCourseFaculty,
                lblCourseDescr

        );

        JButton btnAdd2PlanningList = new JButton("Add Course to the Planning List");
        JButton btnRemoveFromPlanningList = new JButton("Remove Selected from the Planning List");
        JButton btnGenerateNonOverlappingSchedules = new JButton("Generate non-overlapping Schedule(s)");
        JButton btnClearPlanningList = new JButton("Clear the Planning List");
        JButton btnViewWeeklySchedule = new JButton("View weekly schedule");


        DefaultListModel lstCourses2BePlannedModel = new DefaultListModel();
        JList lstCourse2BePlanned = new JList(lstCourses2BePlannedModel);


//        Integer curTimeStart = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[0];
//        Integer curTimeEnd = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[1];

//        lblStartTime.setText("Starts at: " + curTimeStart);
//        lblEndTime.setText("Ends at: " + curTimeEnd);

        courseSubjectsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String curCourseSubject = (String) e.getItem();
                    SelectFromTableInDB.SelectCourseCatalogsOfChosenCourseSubject(dbName, sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location,
                            courseCatalogList, curCourseSubject);

                    courseCatalogsComboBox.removeAllItems();

                    for (int curCatalog : courseCatalogList) {
                        courseCatalogsComboBox.addItem(curCatalog);
                    }

                    UpdateLabelsFromDB(

                            dbName,
                            sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                            courseSubjectsComboBox,
                            courseCatalogsComboBox,
                            lblCourseLevel,
                            lblCourseFaculty,
                            lblCourseDescr

                    );
//                    Integer curTimeStart = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[0];
//                    Integer curTimeEnd = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[1];
//                    lblStartTime.setText("Starts at: " + curTimeStart);
//                    lblEndTime.setText("Ends at: " + curTimeEnd);
                }
            }
        });

        courseCatalogsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int curCourseCatalog = (int) e.getItem();
                    UpdateLabelsFromDB(

                            dbName,
                            sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                            courseSubjectsComboBox,
                            courseCatalogsComboBox,
                            lblCourseLevel,
                            lblCourseFaculty,
                            lblCourseDescr

                    );
                }
            }
        });


        List<CourseSubject_Catalog_Priority_Tuple> tupleList = new ArrayList<CourseSubject_Catalog_Priority_Tuple>();
        List<List<domain.Class>> classesList = new ArrayList<>();


        btnAdd2PlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedCourseSubject = (String) courseSubjectsComboBox.getSelectedItem();
                int selectedCourseCatalog = (int) courseCatalogsComboBox.getSelectedItem();
                int selectedPriority = (int) priorityValuesComboBox.getSelectedItem();

                CourseSubject_Catalog_Priority_Tuple courseTuple2BeAdded = new CourseSubject_Catalog_Priority_Tuple(
                        selectedCourseSubject,
                        selectedCourseCatalog,
                        selectedPriority);

                String courseTuple2BeAdded_Subject = courseTuple2BeAdded.getSubject();
                int courseTuple2BeAdded_Catalog = courseTuple2BeAdded.getCatalog();
                int courseTuple2BeAdded_Priority = courseTuple2BeAdded.getPriority();

                String element2BeAdded = "Subject: " + courseTuple2BeAdded_Subject
                        + ", Catalog: " + courseTuple2BeAdded_Catalog
                        + ", Priority: " + courseTuple2BeAdded_Priority;


                if (!tupleList.contains(courseTuple2BeAdded)) {// CHECKING for ELEC317 vs. ELEC317 case. (i.e. attempt for adding the same course)

//                    Integer[] selectedCourseTimeTable = timeTables.get(selectedCourseName);
//                    Integer selectedCourseStartTime = selectedCourseTimeTable[0];
//                    Integer selectedCourseEndTime = selectedCourseTimeTable[1];
//                    Integer selectedCoursePriority = (Integer) priorityValuesComboBox.getSelectedItem();
//                    element2BeAdded += selectedCourseName + " with priority: " + selectedCoursePriority + ", from: t" + selectedCourseStartTime
//                            + ", to: t" + selectedCourseEndTime;
//                    lstCourses2BePlannedModel.addElement(element2BeAdded);
//                    ArrayList<String> arrList2BeAdded = new ArrayList<String>();
//                    arrList2BeAdded.add(selectedCourseName);arrList2BeAdded.add(selectedCoursePriority.toString());
//                    arrList2BeAdded.add(selectedCourseStartTime.toString());arrList2BeAdded.add(selectedCourseEndTime.toString());
//                    coursesInPlanningList.add(arrList2BeAdded);
//                    courseNamesInPlanningList.add(selectedCourseName);

                    List<domain.Class> classListForTuple = new ArrayList<>();

                    PutClassesFromDB2cList(courseTuple2BeAdded, classListForTuple, dbName, sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location);


                    for (int i = 0; i < classesList.size(); i++) {// CHECKING for ELEC317 vs. COMP317 case. (i.e. courses with same classes)
                        List<domain.Class> curClassList = classesList.get(i);
                        for (domain.Class curClass : classListForTuple) {
                            if (curClassList.contains(curClass)) {
                                CourseSubject_Catalog_Priority_Tuple tupleAlreadyAdded = tupleList.get(i);
                                String subjectAlreadyAdded = tupleAlreadyAdded.getSubject();
                                int catalogAlreadyAdded = tupleAlreadyAdded.getCatalog();
                                String subject2BeAdded = courseTuple2BeAdded.getSubject();
                                int catalog2BeAdded = courseTuple2BeAdded.getCatalog();
                                JOptionPane.showMessageDialog(btnAdd2PlanningList, "\"" + subject2BeAdded
                                        + " " + catalog2BeAdded + "\" has already been added to the planning list as follows: "
                                        + "\"" + subjectAlreadyAdded + " " + catalogAlreadyAdded + "\"");
                                return;
                            }
                        }
                    }

                    classesList.add(classListForTuple);
                    tupleList.add(courseTuple2BeAdded);
                    lstCourses2BePlannedModel.addElement(element2BeAdded);

                } else {
                    JOptionPane.showMessageDialog(btnAdd2PlanningList, "\"" + courseTuple2BeAdded_Subject
                            + " " + courseTuple2BeAdded_Catalog + "\" has already been added to the planning list!");
                }

                PrinOutClassesList(classesList);

            }
        });

        btnRemoveFromPlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedElementIndex = lstCourse2BePlanned.getSelectedIndex();
                if (selectedElementIndex != -1) {
                    tupleList.remove(selectedElementIndex);
                    lstCourses2BePlannedModel.removeElementAt(selectedElementIndex);
                    classesList.remove(selectedElementIndex);
                    //lstCourse2BePlanned.remove(selectedElementIndex);
                }

                PrinOutClassesList(classesList);

            }
        });

        btnClearPlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (CourseSubject_Catalog_Priority_Tuple curTuple : tupleList) {
                    System.out.println(curTuple);
                }

                System.out.println("\n Has been cleared from the planning list!");

                tupleList.clear();
                classesList.clear();
                lstCourses2BePlannedModel.clear();

                System.out.println("Course Planning list has been cleared!");
            }
        });


//        NumberFormat format = NumberFormat.getInstance();
//        NumberFormatter formatter = new NumberFormatter(format);
//        formatter.setValueClass(Integer.class);
//        formatter.setMinimum(0);
//        formatter.setMaximum(5);
//        formatter.setAllowsInvalid(false);
//        // If you want the value to be committed on each keystroke instead of focus lost
//        formatter.setCommitsOnValidEdit(true);
//        JFormattedTextField txtFieldPriority = new JFormattedTextField(formatter);


        JFrame frame = new JFrame("CourseScheduler v.2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 720);

        //int yInc = 25;

        lblCourseSubject.setBounds(100, 50, 150, 25);
        courseSubjectsComboBox.setBounds(250, 50, 150, 25);

        lblCourseCatalog.setBounds(100, 75, 150, 25);
        courseCatalogsComboBox.setBounds(250, 75, 150, 25);

        lblPriority.setBounds(100, 100, 100, 25);
        priorityValuesComboBox.setBounds(250, 100, 150, 25);

        lblCourseFaculty.setBounds(100, 125, 400, 25);
        lblCourseLevel.setBounds(100, 150, 400, 25);
        lblCourseDescr.setBounds(100, 175, 400, 75);
        lblCourseDescr.setVerticalAlignment(JLabel.TOP);


        btnRemoveFromPlanningList.setBounds(100, 250, 300, 25);
        btnAdd2PlanningList.setBounds(100, 275, 300, 25);
        btnClearPlanningList.setBounds(100, 300, 300, 25);

        lstCourse2BePlanned.setBounds(100, 350, 300, 250);

        btnGenerateNonOverlappingSchedules.setBounds(100, 600, 300, 25);
        shceduleListComboBox.setBounds(100, 625, 300, 25);;

        btnViewWeeklySchedule.setBounds(100, 650, 300, 25);

        frame.add(lblCourseSubject);
        frame.add(courseSubjectsComboBox);
        frame.add(lblPriority);
        frame.add(priorityValuesComboBox);
        frame.add(lblCourseCatalog);
        frame.add(courseCatalogsComboBox);
        frame.add(lblCourseLevel);
        frame.add(lblCourseDescr);
        frame.add(btnAdd2PlanningList);
        frame.add(btnRemoveFromPlanningList);
        frame.add(btnGenerateNonOverlappingSchedules);
        frame.add(shceduleListComboBox);
        frame.add(btnClearPlanningList);
        frame.add(btnViewWeeklySchedule);
        frame.add(lblCourseFaculty);
        frame.add(lstCourse2BePlanned);
        frame.setLayout(null);
        frame.setVisible(true);


//      ALGORITHM:_______________________________________________________________________________________

        ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

//        cList.add(c6);
//        cList.add(c2);cList.add(c7);cList.add(c3);
//        cList.add(c1);
//        cList.add(c4); cList.add(c5);

//        Scheduler s1 = new Scheduler(cList);
//        s1.generateOptimumCoursePlan();

        btnGenerateNonOverlappingSchedules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                List<domain.Class> c1List = classesList.get(0);

                Schedule.scheduleIdcounter = 0;

                List<List<ClassBundle>> classBundlesList = new ArrayList<>();

                for (List<Class> curClassList : classesList) {
                    List<ClassBundle> curBundles = ClassBundle.GenerateClassBundlesFromClasses(curClassList);
                    System.out.println(curBundles);
                    classBundlesList.add(curBundles);
                }

                List<Schedule> schedules = Schedule.GenerateSchedulesFromClassBundlesList(classBundlesList);

//                System.out.println(schedules);

                String message2BeDisplayed = schedules.toString();
                JTextArea textArea = new JTextArea(25, 75);
                textArea.setText(message2BeDisplayed);
                textArea.setEditable(false);
                textArea.setCaretPosition(0);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(null, scrollPane, Schedule.scheduleIdcounter + " non-overlapping plans are found.", JOptionPane.WARNING_MESSAGE);

                if (schedules != null)
                    scheduleListToView = schedules;

                shceduleListComboBox.removeAllItems();

                for(int i = 0;i<Schedule.scheduleIdcounter;i++){
                    shceduleListComboBox.addItem("Schedule #" + (i+1));
                }

                System.out.println("hi");
            }
        });


        btnViewWeeklySchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (scheduleListToView != null && !scheduleListToView.isEmpty()) {
                    System.out.println("hi1");
                    JFrame scheduleFrame = new JFrame("Weekly Schedule");

                    scheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    scheduleFrame.setSize(1200, 840);


/*                  JLabel testlabel = new JLabel("Comp 130 LEC");
                    testlabel.setBounds(150,50,300,200);
                    scheduleFrame.add(testlabel);

                    ClassPanel testPanel = new ClassPanel(100,100,200,90);
                    testPanel.setVisible(true);
                    scheduleFrame.add(testPanel);*/

                    int scheduleIndex2BeDisplayed = shceduleListComboBox.getSelectedIndex();

                    Schedule scheduleToView = scheduleListToView.get(scheduleIndex2BeDisplayed);
                    // Class currentClass = scheduleToView.getClassBundleList().get(0).getLecClass();
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
                    scheduleFrame.setVisible(true);

                }
            }
        });

    }


    private static void PrinOutClassesList(List<List<Class>> classesList) {

        System.out.println("-------");

        int i = 0;

        for (List<Class> cList : classesList) {

            System.out.println("\nCLASS LIST:" + ++i + " \n\n" + cList);
        }
    }

    private static void PutClassesFromDB2cList(CourseSubject_Catalog_Priority_Tuple tuple, List<Class> classList, String dbName, String sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location) {

        classList.clear();


        String tupleCourseSubject = tuple.getSubject();
        int tupleCourseCatalog = tuple.getCatalog();

        SelectFromTableInDB.SelectClassesOfOneCourse(
                dbName,
                sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location,
                tupleCourseSubject,
                tupleCourseCatalog,
                classList
        );
//                    Scheduler s1 = new Scheduler(cList);
//                    s1.generateOptimumCoursePlan();
//                    cList.clear();
//                    //JOptionPane.showMessageDialog(null,s1.getOutput(),s1.getNumPlans() +" non-overlapping plans are found.", ModifiableJOptionPane.WARNING_MESSAGE);
//                    String message2BeDisplayed = s1.getOutput();
//                    JTextArea textArea = new JTextArea(25, 75);
//                    textArea.setText(message2BeDisplayed);
//                    textArea.setEditable(false);
//                    textArea.setCaretPosition(0);
//                    JScrollPane scrollPane = new JScrollPane(textArea);
//                    JOptionPane.showMessageDialog(null,scrollPane,s1.getNumPlans() +" non-overlapping plans are found.",JOptionPane.WARNING_MESSAGE);

//            for(Class curClass:classList){
//                System.out.println(curClass);
//            }
    }

    private static void CreateAndFill_DB_from_CSV(List<ClassInfo> classInfoList, String fPath, String sqlQuery_Create_Location, String sqlQuery_Insert_Location, String dbName) {

        ReadCSV.FillIntoList(fPath, classInfoList);
        //System.out.println(classInfoList);

        ExecuteDropDB.executeDropDB_ifExists("outputs/" + dbName + ".db");

        CreateDB.createNewDatabase(dbName);

        CreateTableInDB.createNewTable(dbName, sqlQuery_Create_Location);

        InsertIntoTableInDB.insertAll(dbName, sqlQuery_Insert_Location, classInfoList);

        System.out.println("asdasd");
    }

    private static void CreateNormalizedTablesInDB(List<String> sqlQuery_Create_NormalizedTables, String dbName) {
        CreateTableInDB.createNewTablesFromList(dbName, sqlQuery_Create_NormalizedTables);
    }

    private static void CleanStartAndFill_NormalizedTables(List<String> sqlQueryLocationList2CleanStartAndFill_NormalizedTables, String dbName) {
        InsertIntoTableInDB.execQueriesFromList(dbName, sqlQueryLocationList2CleanStartAndFill_NormalizedTables);
    }

    private static void UpdateLabelsFromDB(String dbName,
                                           String sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                                           JComboBox<String> courseSubjectsComboBox,
                                           JComboBox<Integer> courseCatalogsComboBox,
                                           JLabel lblCourseLevel,
                                           JLabel lblCourseFaculty,
                                           JLabel lblCourseDescr) {

        String defaultCourseFacultyLabel = "Course Faculty: ";
        String defaultCourseLevelLabel = "Course Level: ";
        String defaultCourseDescrLabel = "Course Description: ";

        List<String> strListCourse_Career_AcadOrg_Descr_Descr2 = SelectFromTableInDB.SelectCourse_Career_AcadOrg_Descr_Descr2(
                dbName,
                sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                (String) courseSubjectsComboBox.getSelectedItem(),
                (Integer) courseCatalogsComboBox.getSelectedItem()
        );

        String CourseCareerInfo = strListCourse_Career_AcadOrg_Descr_Descr2.get(0);
        String CourseAcadCareerInfo = strListCourse_Career_AcadOrg_Descr_Descr2.get(1);
        String CourseDescrInfo = strListCourse_Career_AcadOrg_Descr_Descr2.get(2);
        String CourseDescr2Info = strListCourse_Career_AcadOrg_Descr_Descr2.get(3);

        lblCourseLevel.setText("<html>"
                + defaultCourseLevelLabel
                + "<font face=\"verdana\" color=\"green\"><b><i>"
                + CourseCareerInfo
                + "</i></b></font></html>");

        lblCourseFaculty.setText("<html>"
                + defaultCourseFacultyLabel
                + "<font face=\"verdana\" color=\"green\"><b><i>"
                + CourseAcadCareerInfo
                + "</i></b></font></hmtl>");

        lblCourseDescr.setText("<html>"
                + defaultCourseDescrLabel
                + "<br><font face=\"verdana\" color=\"green\"><b><i>"
                + CourseDescrInfo
                + "</i></b></font><br><font face=\"verdana\" color=\"green\"><b><i>"
                + CourseDescr2Info
                + "</i></b></font></hmtl>");
    }


    private static int getStartTime(Class currentClass) {
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


    private static int getEndTime(Class currentClass) {
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


    private static void addClassPanels(JFrame scheduleFrame, Class currentClass) {
        addClassPanelMonday(scheduleFrame, currentClass);
        addClassPanelTuesday(scheduleFrame, currentClass);
        addClassPanelWednesday(scheduleFrame, currentClass);
        addClassPanelThursday(scheduleFrame, currentClass);
        addClassPanelFriday(scheduleFrame, currentClass);
    }

    private static void addClassPanelMonday(JFrame scheduleFrame, Class currentClass) {
        if (currentClass != null && currentClass.isMonFlag()) {
            int ystart = getStartTime(currentClass);
            int yend = getEndTime(currentClass);

            String classLabel = currentClass.getCourseName() + " " + Integer.toString(currentClass.getCourseCatalog()) + " " + currentClass.getComponent();
            JLabel currentClassLabel = new JLabel(classLabel);
            currentClassLabel.setBounds(150, ystart + 110, 200, 20);
            scheduleFrame.add(currentClassLabel);

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


}
