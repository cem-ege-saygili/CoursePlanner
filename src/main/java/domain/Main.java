package domain;

import DB_Utilities.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Main {
    final static int upperLimit = 500000;//SET # of max. result set size of the core scheduling algorithm. (500K)

    static final int MAIN_FRAME_WIDTH = 1350;
    static final int  MAIN_FRAME_HEIGHT = 805;
    static final int PROGRESS_BAR_X_MARGIN = 15;
    static final int PROGRESS_BAR_Y_MARGIN = 15;
    static final int PROGRESS_BAR_WIDTH = 400;
    static final int PROGRESS_BAR_HEIGHT = 35;

    static final int COURSE_SELECTION_X_MARGIN = 15;
    static final int COURSE_SELECTION_Y_MARGIN = 50;
    static final int COURSE_SELECTION_COMBO_X_MARGIN = 165;
    static final int COURSE_SELECTION_WIDTH = 150;
    static final int COURSE_SELECTION_HEIGHT = 25;

    static final int FILTER_CLASS_X_MARGIN = 420;
    static final int FILTER_CLASS_TEXT_Y_MARGIN = 50;
    static final int FILTER_ACTIVE_CLASS_TEXT_Y_MARGIN = 350;
    static final int FILTER_CLASS_TEXT_WIDTH = 130;
    static final int FILTER_CLASS_TEXT_HEIGHT = 25;
    static final int FILTER_CLASS_COMBO_WIDTH = 100;
    static final int FILTER_CLASS_COMBO_HEIGHT = 25;
    static final int FILTER_CLASS_BUTTON_WIDTH = 150;
    static final int FILTER_CLASS_BUTTON_HEIGHT = 25;
    static final int FILTER_CLASS_SCROLLABLE_PANE_1_Y_MARGIN = 130;
    static final int FILTER_CLASS_SCROLLABLE_PANE_2_Y_MARGIN = 375;
    static final int FILTER_CLASS_SCROLLABLE_PANE_WIDTH = 900;
    static final int FILTER_CLASS_SCROLLABLE_PANE_HEIGHT = 200;


    static final int COURSE_INFO_X_MARGIN = 15;
    static final int COURSE_INFO_Y_MARGIN = 125;
    static final int COURSE_INFO_WIDTH = 400;
    static final int COURSE_INFO_HEIGHT = 25;

    static final int COURSE_MANAGEMENT_X_MARGIN = 15;
    static final int COURSE_MANAGEMENT_Y_MARGIN = 250;
    static final int COURSE_MANAGEMENT_WIDTH = 300;
    static final int COURSE_MANAGEMENT_HEIGHT = 25;

    static final int FILTER_DAY_X_MARGIN = 10;
    static final int FILTER_DAY_Y_MARGIN = 325;
    static final int FILTER_DAY_WIDTH = 80;
    static final int FILTER_DAY_HEIGHT = 25;

    static final int FILTER_FROM_TEXT_X_MARGIN = 15;
    static final int FILTER_FROM_AND_TO_TEXT_Y_MARGIN = 350;
    static final int FILTER_FROM_TEXT_WIDTH = 50;
    static final int FILTER_TO_TEXT_WIDTH = 30;
    static final int FILTER_TEXT_HEIGHT = 25;

    static final int FILTER_TIME_X_MARGIN = 55;
    static final int FILTER_TIME_Y_MARGIN = 350;
    static final int FILTER_TIME_WIDTH = 125;
    static final int FILTER_TIME_HEIGHT = 25;
    static final int FILTER_BUTTON_X_MARGIN = 15;
    static final int FILTER_BUTTON_Y_MARGIN = 380;
    static final int FILTER_ADD_BUTTON_WIDTH = 100;
    static final int FILTER_REMOVE_BUTTON_WIDTH = 120;
    static final int FILTER_CLEAR_BUTTON_WIDTH = 100;
    static final int FILTER_BUTTON_HEIGHT = 25;

    static final int ACTIVE_FILTERS_TEXT_X_MARGIN = 15;
    static final int ACTIVE_FILTERS_TEXT_Y_MARGIN = 410;
    static final int ACTIVE_FILTERS_TEXT_WIDTH = 200;
    static final int ACTIVE_FILTERS_TEXT_HEIGHT = 25;
    static final int ACTIVE_FILTERS_PANE_X_MARGIN = 10;
    static final int ACTIVE_FILTERS_PANE_Y_MARGIN = 435;
    static final int ACTIVE_FILTERS_PANE_WIDTH = 400;
    static final int ACTIVE_FILTERS_PANE_HEIGHT = 75;

    static final int COURSES_TO_BE_PLANNED_TEXT_X_MARGIN = 15;
    static final int COURSES_TO_BE_PLANNED_TEXT_Y_MARGIN = 525;
    static final int COURSES_TO_BE_PLANNED_TEXT_WIDTH = 200;
    static final int COURSES_TO_BE_PLANNED_TEXT_HEIGHT = 25;
    static final int COURSES_TO_BE_PLANNED_PANE_X_MARGIN = 10;
    static final int COURSES_TO_BE_PLANNED_PANE_Y_MARGIN = 550;
    static final int COURSES_TO_BE_PLANNED_PANE_WIDTH = 400;
    static final int COURSES_TO_BE_PLANNED_PANE_HEIGHT = 120;

    static final int SCHEDULE_MANAGEMENT_X_MARGIN = 15;
    static final int SCHEDULE_MANAGEMENT_Y_MARGIN = 680;
    static final int SCHEDULE_MANAGEMENT_WIDTH = 300;
    static final int SCHEDULE_MANAGEMENT_Y_HEIGHT = 25;

    static final int IMPORT_BUTTON_X_MARGIN = 320;
    static final int IMPORT_BUTTON_Y_MARGIN = 680;
    static final int IMPORT_BUTTON_WIDTH = 100;
    static final int IMPORT_BUTTON_HEIGHT = 75;

    static List<Schedule> scheduleListToView;
    static JFrame scheduleFrame;
    private static List<String> distinctClassComponentsList_GivenCourse;
    private static List<Class> classListForClassFilter;
    public static String dbName = "CoursePlannerDB";
    static JFrame frame = new JFrame("CourseScheduler v.5 - Final");
    static JComboBox<Schedule> scheduleListComboBox = new JComboBox<>();

//    static boolean monExclFlag;
//    static boolean tuesExclFlag;
//    static boolean wedExclFlag;
//    static boolean thursExclFlag;
//    static boolean friExclFlag;

    public static void main(String[] args) {
        System.out.println("\nRunnable\n\n");


        //READ CSV FILE INTO an array of ClassInfo objects. (i.e. classInfoList)

        List<ClassInfo> classInfoList = new ArrayList<>();

//        String fPath = System.getProperty("user.dir") + "/src/main/java/KU_STD_ALL_LEC_COURSECODE_NAME_1341695219.csv";

        String fPath = "inputs/KU_STD_ALL_LEC_COURSECODE_NAME_1341695219.csv";
        String sqlQuery_Create_Location = "inputs/sqlQuery_Create.sql";
        String sqlQuery_Insert_Location = "inputs/sqlQuery_Insert.sql";
        String sqlQuery_SelectDistinctCourseSubjects_Location = "inputs/sqlQuery_selectDistinctCourseSubjects.sql";
        String sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location = "inputs/sqlQuery_selectCourseCatalogsOfChosenCourseSubject.sql";
        String sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location = "inputs/sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog.sql";
        String sqlQuery_SelectDistinctClassComponentsOfGivenCourse_Location = "inputs/sqlQuery_SelectDistinctClassComponentsOfGivenCourse.sql";
        String sqlQuery_sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent_Location = "inputs/sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent.sql";

        String sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location = "inputs/sqlQuery_selectClassesInfoFromCourseSubject_Catalog.sql";

//        String sqlQuery_Insert2Table_Classes_Location = "inputs/Insert2Table_Classes.txt";
//        String sqlQuery_Insert2Table_Instructors_Location = "inputs/Insert2Table_Instructors.txt";
//        String sqlQuery_Insert2Table_Class_Instructor_Infos_Location = "inputs/Insert2Table_Class_Instructor_Infos.txt";
        List<String> sqlQueryLocationList2Create_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/CreateTable_Courses.sql",
                        "inputs/CreateTable_Classes.sql",
                        "inputs/CreateTable_Instructors.sql",
                        "inputs/CreateTable_Class_Instructor_Infos.sql",
                        "inputs/CreateTable_Class_Course_Infos.sql")
        );

        List<String> sqlQueryLocationList2CleanStartAndFill_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/sqlQuery_CleanStart_DB.sql",
                        "inputs/Insert2Table_Courses.sql",
                        "inputs/Insert2Table_Classes.sql",
                        "inputs/Insert2Table_Instructors.sql",
                        "inputs/Insert2Table_Class_Instructor_Infos.sql",
                        "inputs/Insert2Table_Class_Course_Infos.sql")
        );

        //run only once BEGIN //////////////////////

        /*

                CreateAndFill_DB_from_CSV(classInfoList, fPath, sqlQuery_Create_Location, sqlQuery_Insert_Location, dbName);

                CreateNormalizedTablesInDB(sqlQueryLocationList2Create_NormalizedTables, dbName);

                CleanStartAndFill_NormalizedTables(sqlQueryLocationList2CleanStartAndFill_NormalizedTables, dbName);

        */


        //run only once END ////////////////////////


        //System.out.println("after CleanStartAndFill_NormalizedTables");

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

        JLabel lblCourseClassFilter = new JLabel();
        lblCourseClassFilter.setText("Course Class Filter: ");

        JLabel lblCourseSubjectAndCatalog = new JLabel();
        lblCourseSubjectAndCatalog.setText("Please double click to a course in \"Courses to be planned\" pane");

        JComboBox<String> distinctClassComponents_GivenCourse_ComboBox = new JComboBox<>();

        JButton btnAddClassFilter = new JButton("Add Class Filter");
        JButton btnRemoveClassFilter = new JButton("Remove Class Filter");
        JButton btnClearClassFilters = new JButton("Clear Class Filters");
        DefaultListModel lstClassFiltersListModel = new DefaultListModel();
        JList lstClassFiltersList = new JList(lstClassFiltersListModel);
        JScrollPane scrollablePane = new JScrollPane(lstClassFiltersList);

        JLabel lblAddedClassFilters = new JLabel("Active Class Filters:");

        DefaultListModel lstAddedClassFiltersListModel = new DefaultListModel();
        JList lstAddedClassFiltersList = new JList(lstAddedClassFiltersListModel);
        JScrollPane scrollablePaneAddedClassFilters = new JScrollPane(lstAddedClassFiltersList);

        SelectFromTableInDB.SelectCourseCatalogsOfChosenCourseSubject(dbName, sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location,
                courseCatalogList, (String) courseSubjectsComboBox.getSelectedItem());

        Integer[] courseCatalogsArr = new Integer[courseCatalogList.size()];
        courseCatalogsArr = courseCatalogList.toArray(courseCatalogsArr);

        JComboBox<Integer> courseCatalogsComboBox = new JComboBox<>(courseCatalogsArr);

        JLabel lblProgressBar = new JLabel();
        lblProgressBar.setText("<html>"
                + "Status: "
                + "<font face=\"verdana\" color=\"green\"><b><i>"
                + "idle"
                + "</i></b></font></html>");

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

        String selectedCourseSubject = (String) courseSubjectsComboBox.getSelectedItem();
        int selectedCourseCatalog = (Integer) courseCatalogsComboBox.getSelectedItem();

        UpdateLabelsFromDB(

                dbName,
                sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                selectedCourseSubject,
                selectedCourseCatalog,
                lblCourseLevel,
                lblCourseFaculty,
                lblCourseDescr

        );

        JButton btnAdd2PlanningList = new JButton("Add Course to the Planning List");
        JButton btnRemoveFromPlanningList = new JButton("Remove Selected from the Planning List");
        JButton btnGenerateNonOverlappingSchedules = new JButton("Generate non-overlapping Schedule(s)");
        JButton btnClearPlanningList = new JButton("Clear the Planning List");

        JButton btnImport = new JButton("<html>Import<br>Schedules</html>");
        JButton btnExport = new JButton("<html>Export<br>Schedules</html>");
        JButton btnClearAll = new JButton("<html>Clear<br>All</html>");
        JButton btnApplyFilters = new JButton("<html>Apply<br>Filters</html>");

        JCheckBox checkBox_Exclude_Mon = new JCheckBox("Monday");
        JCheckBox checkBox_Exclude_Tues = new JCheckBox("Tuesday");
        JCheckBox checkBox_Exclude_Wed = new JCheckBox("Wednesday");
        JCheckBox checkBox_Exclude_Thurs = new JCheckBox("Thursday");
        JCheckBox checkBox_Exclude_Fri = new JCheckBox("Friday");

        JComboBox<String> filterStartTimeComboBox = new JComboBox<>();
        JComboBox<String> filterEndTimeComboBox = new JComboBox<>();

        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");

        JLabel activeFiltersLabel = new JLabel("Filters to be applied:");
        JLabel courses2BePlannedLabel = new JLabel("Courses to be planned:");

        JButton btnAddFilter = new JButton("Add filter");
        JButton btnRemoveFilter = new JButton("Remove filter");
        JButton btnClearFilters = new JButton("Clear filters");

        DefaultListModel lstFiltersModel = new DefaultListModel();
        JList lstFilters = new JList(lstFiltersModel);
        JScrollPane scrollPaneFilters = new JScrollPane(lstFilters);

        InitialFillFilterTimeComboBoxes(filterStartTimeComboBox, filterEndTimeComboBox);

        JButton btnViewWeeklySchedule = new JButton("View weekly schedule");

        JButton btnCloseBackgroundPanel = new JButton("CLOSE");
        JButton btnPrevSchedule = new JButton("<-");
        JButton btnNextSchedule = new JButton("->");

        List<JButton> btnList = new ArrayList<>();
        btnList.add(btnCloseBackgroundPanel);
        btnList.add(btnNextSchedule);btnList.add(btnPrevSchedule);


        DefaultListModel lstCourses2BePlannedModel = new DefaultListModel();
        JList lstCourse2BePlanned = new JList(lstCourses2BePlannedModel);
        JScrollPane scrollPaneCourse2BePlanned = new JScrollPane(lstCourse2BePlanned);


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

                    String selectedCourseSubject = (String) courseSubjectsComboBox.getSelectedItem();
                    int selectedCourseCatalog = (Integer) courseCatalogsComboBox.getSelectedItem();

                    UpdateLabelsFromDB(

                            dbName,
                            sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                            selectedCourseSubject,
                            selectedCourseCatalog,
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
                    String selectedCourseSubject = (String) courseSubjectsComboBox.getSelectedItem();
                    int selectedCourseCatalog = (Integer) courseCatalogsComboBox.getSelectedItem();

                    UpdateLabelsFromDB(

                            dbName,
                            sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                            selectedCourseSubject,
                            selectedCourseCatalog,
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

//                String courseTuple2BeAdded_Subject = courseTuple2BeAdded.getSubject();
//                int courseTuple2BeAdded_Catalog = courseTuple2BeAdded.getCatalog();
//                int courseTuple2BeAdded_Priority = courseTuple2BeAdded.getPriority();

//                String element2BeAdded = "Subject: " + courseTuple2BeAdded_Subject
//                        + ", Catalog: " + courseTuple2BeAdded_Catalog
//                        + ", Priority: " + courseTuple2BeAdded_Priority;


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

                    SelectFromTableInDB.PutClassesFromDB2cList(courseTuple2BeAdded, classListForTuple, dbName, sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location);


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
                    lstCourses2BePlannedModel.addElement(courseTuple2BeAdded);

                } else {
                    JOptionPane.showMessageDialog(btnAdd2PlanningList, "\"" + courseTuple2BeAdded.getSubject()
                            + " " + courseTuple2BeAdded.getCatalog() + "\" has already been added to the planning list!");
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
                ClearPlanningList(tupleList, classesList, lstCourses2BePlannedModel, scheduleListComboBox);
            }
        });

        btnAddFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isMonFilterEnabled = checkBox_Exclude_Mon.isSelected();
                boolean isTuesFilterEnabled = checkBox_Exclude_Tues.isSelected();
                boolean isWedFilterEnabled = checkBox_Exclude_Wed.isSelected();
                boolean isThursFilterEnabled = checkBox_Exclude_Thurs.isSelected();
                boolean isFriFilterEnabled = checkBox_Exclude_Fri.isSelected();
                String selectedFilterStartTime = (String) filterStartTimeComboBox.getSelectedItem();
                String selectedFilterEndTime = (String) filterEndTimeComboBox.getSelectedItem();



                DayTimeFramePair curDayTimeFramePair = new DayTimeFramePair(isMonFilterEnabled,
                        isTuesFilterEnabled,
                        isWedFilterEnabled,
                        isThursFilterEnabled,
                        isFriFilterEnabled,
                        selectedFilterStartTime,
                        selectedFilterEndTime);

                if(!lstFiltersModel.contains(curDayTimeFramePair)){
                    lstFiltersModel.addElement(curDayTimeFramePair);
                }

            }
        });

        btnRemoveFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexOfElt2BeRemoved = lstFilters.getSelectedIndex();
                if(indexOfElt2BeRemoved != -1){
                    lstFiltersModel.removeElementAt(indexOfElt2BeRemoved);
                }

            }
        });

        btnClearFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lstFiltersModel.removeAllElements();
            }
        });

        filterStartTimeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedFilterStartTimeStr = (String) e.getItem();
                    int index = DayTimeFramePair.AllTimeStrings.indexOf(selectedFilterStartTimeStr);
                    filterEndTimeComboBox.removeAllItems();
                    for(int i = index+1;i<DayTimeFramePair.AllTimeStrings.size();i++){
                        String curStartTimeStr = DayTimeFramePair.AllTimeStrings.get(i);
                        filterEndTimeComboBox.addItem(curStartTimeStr);
                    }
                }
            }
        });

        checkBox_Exclude_Mon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JCheckBox cbLog = (JCheckBox) e.getSource();
//                if (cbLog.isSelected()) {
//                    System.out.println("Filter-Enabled");
//                } else {
//                    System.out.println("Filter-Disabled");
//                }
            }
        });

        lstCourse2BePlanned.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                CourseSubject_Catalog_Priority_Tuple selectedTuple = (CourseSubject_Catalog_Priority_Tuple) lstCourse2BePlanned.getSelectedValue();

                if(selectedTuple == null)
                    return;

                String courseSubject = selectedTuple.getSubject();
                int courseCatalog = selectedTuple.getCatalog();

                if(e.getClickCount() == 2){//if double-clicked

                    lblCourseSubjectAndCatalog.setText("Selected Course: "
                            + "\""
                            + courseSubject + " " + courseCatalog
                            + "\"");

                    distinctClassComponentsList_GivenCourse = new ArrayList<>();
                    distinctClassComponents_GivenCourse_ComboBox.removeAllItems();

                    distinctClassComponentsList_GivenCourse = SelectFromTableInDB.SelectDistinctClassComponentsOfGivenCourse(courseSubject, courseCatalog, dbName, sqlQuery_SelectDistinctClassComponentsOfGivenCourse_Location);


                    for(String curClassComponentStr:distinctClassComponentsList_GivenCourse){
                        distinctClassComponents_GivenCourse_ComboBox.addItem(curClassComponentStr);
                    }

                    ReFillClassFilterList(
                            courseSubject,
                            courseCatalog,
                            distinctClassComponents_GivenCourse_ComboBox,
                            dbName,
                            sqlQuery_sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent_Location,
                            lstClassFiltersListModel);

                }
            }
        });

        btnRemoveClassFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedActiveClassFilterElementIndex = lstAddedClassFiltersList.getSelectedIndex();
                if(selectedActiveClassFilterElementIndex != -1){
                    ActiveClassFilterElement selectedActiveClassFilterElement = (ActiveClassFilterElement) lstAddedClassFiltersListModel.getElementAt(selectedActiveClassFilterElementIndex);
                    lstAddedClassFiltersListModel.removeElement(selectedActiveClassFilterElement);
                }
            }
        });

        btnClearClassFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearClassFilters(lstAddedClassFiltersListModel, lblCourseSubjectAndCatalog, lblCourseClassFilter, distinctClassComponents_GivenCourse_ComboBox, lstClassFiltersListModel);
            }
        });

        distinctClassComponents_GivenCourse_ComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String lblCourseSubjectAndCatalogStr = lblCourseSubjectAndCatalog.getText();
                    int startIndex = lblCourseSubjectAndCatalogStr.indexOf("\"");
                    int endIndex = lblCourseSubjectAndCatalogStr.indexOf("\"", startIndex+1);
                    String courseSubjectAndCatalogStr = lblCourseSubjectAndCatalogStr.substring(startIndex+1, endIndex);
                    String[] arr = courseSubjectAndCatalogStr.split(" ");
                    String courseSubject = arr[0];
                    int courseCatalog = Integer.parseInt(arr[1]);
                    ReFillClassFilterList(
                            courseSubject,
                            courseCatalog,
                            distinctClassComponents_GivenCourse_ComboBox,
                            dbName,
                            sqlQuery_sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent_Location,
                            lstClassFiltersListModel);
                }
            }
        });

        btnAddClassFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedClassFilterElementIndex = lstClassFiltersList.getSelectedIndex();
                if(selectedClassFilterElementIndex != -1){
                    ClassFilterElement selectedClassFilterElement = (ClassFilterElement) lstClassFiltersListModel.getElementAt(selectedClassFilterElementIndex);
                    Class selectedClass = selectedClassFilterElement.getClassForClassFilterElement();
                    ActiveClassFilterElement acfe = new ActiveClassFilterElement(selectedClass);
                    if(!lstAddedClassFiltersListModel.contains(acfe))
                        lstAddedClassFiltersListModel.addElement(acfe);
                }


            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);

        lblProgressBar.setBounds(PROGRESS_BAR_X_MARGIN, PROGRESS_BAR_Y_MARGIN, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        lblCourseSubject.setBounds(COURSE_SELECTION_X_MARGIN, COURSE_SELECTION_Y_MARGIN, COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);
        courseSubjectsComboBox.setBounds(COURSE_SELECTION_COMBO_X_MARGIN, COURSE_SELECTION_Y_MARGIN, COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);
        lblCourseCatalog.setBounds(COURSE_SELECTION_X_MARGIN, (COURSE_SELECTION_Y_MARGIN + COURSE_SELECTION_HEIGHT), COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);
        courseCatalogsComboBox.setBounds(COURSE_SELECTION_COMBO_X_MARGIN, (COURSE_SELECTION_Y_MARGIN + COURSE_SELECTION_HEIGHT), COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);
        lblPriority.setBounds(COURSE_SELECTION_X_MARGIN, (COURSE_SELECTION_Y_MARGIN + 2 * COURSE_SELECTION_HEIGHT), COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);
        priorityValuesComboBox.setBounds(COURSE_SELECTION_COMBO_X_MARGIN, (COURSE_SELECTION_Y_MARGIN + 2 * COURSE_SELECTION_HEIGHT), COURSE_SELECTION_WIDTH, COURSE_SELECTION_HEIGHT);

        lblCourseClassFilter.setBounds(FILTER_CLASS_X_MARGIN, FILTER_CLASS_TEXT_Y_MARGIN, FILTER_CLASS_TEXT_WIDTH, FILTER_CLASS_TEXT_HEIGHT);
        lblCourseSubjectAndCatalog.setBounds(FILTER_CLASS_X_MARGIN, (FILTER_CLASS_TEXT_Y_MARGIN + FILTER_CLASS_TEXT_HEIGHT), (3 * FILTER_CLASS_TEXT_WIDTH), FILTER_CLASS_TEXT_HEIGHT);

        distinctClassComponents_GivenCourse_ComboBox.setBounds(FILTER_CLASS_X_MARGIN, (FILTER_CLASS_TEXT_Y_MARGIN + 2 * FILTER_CLASS_TEXT_HEIGHT), FILTER_CLASS_COMBO_WIDTH, FILTER_CLASS_COMBO_HEIGHT);
        btnAddClassFilter.setBounds((FILTER_CLASS_X_MARGIN + FILTER_CLASS_COMBO_WIDTH), (FILTER_CLASS_TEXT_Y_MARGIN + 2 * FILTER_CLASS_TEXT_HEIGHT), FILTER_CLASS_BUTTON_WIDTH, FILTER_CLASS_BUTTON_HEIGHT);
        scrollablePane.setBounds(FILTER_CLASS_X_MARGIN, FILTER_CLASS_SCROLLABLE_PANE_1_Y_MARGIN, FILTER_CLASS_SCROLLABLE_PANE_WIDTH, FILTER_CLASS_SCROLLABLE_PANE_HEIGHT);

        lblAddedClassFilters.setBounds(FILTER_CLASS_X_MARGIN, FILTER_ACTIVE_CLASS_TEXT_Y_MARGIN, FILTER_CLASS_TEXT_WIDTH, FILTER_CLASS_TEXT_HEIGHT);
        btnRemoveClassFilter.setBounds((FILTER_CLASS_X_MARGIN + FILTER_CLASS_TEXT_WIDTH), FILTER_ACTIVE_CLASS_TEXT_Y_MARGIN,FILTER_CLASS_BUTTON_WIDTH,FILTER_CLASS_BUTTON_HEIGHT);
        btnClearClassFilters.setBounds((FILTER_CLASS_X_MARGIN + FILTER_CLASS_TEXT_WIDTH + FILTER_CLASS_BUTTON_WIDTH), FILTER_ACTIVE_CLASS_TEXT_Y_MARGIN,FILTER_CLASS_BUTTON_WIDTH,FILTER_CLASS_BUTTON_HEIGHT);
        scrollablePaneAddedClassFilters.setBounds(FILTER_CLASS_X_MARGIN, FILTER_CLASS_SCROLLABLE_PANE_2_Y_MARGIN, FILTER_CLASS_SCROLLABLE_PANE_WIDTH, FILTER_CLASS_SCROLLABLE_PANE_HEIGHT);

        lblCourseFaculty.setBounds(COURSE_INFO_X_MARGIN, COURSE_INFO_Y_MARGIN, COURSE_INFO_WIDTH, COURSE_INFO_HEIGHT);
        lblCourseLevel.setBounds(COURSE_INFO_X_MARGIN, (COURSE_INFO_Y_MARGIN + COURSE_INFO_HEIGHT), COURSE_INFO_WIDTH, COURSE_INFO_HEIGHT);
        lblCourseDescr.setBounds(COURSE_INFO_X_MARGIN, (COURSE_INFO_Y_MARGIN + 2 * COURSE_INFO_HEIGHT), COURSE_INFO_WIDTH, (3 * COURSE_INFO_HEIGHT));
        lblCourseDescr.setVerticalAlignment(JLabel.TOP);


        btnRemoveFromPlanningList.setBounds(COURSE_MANAGEMENT_X_MARGIN, COURSE_MANAGEMENT_Y_MARGIN, COURSE_MANAGEMENT_WIDTH, COURSE_MANAGEMENT_HEIGHT);
        btnAdd2PlanningList.setBounds(COURSE_MANAGEMENT_X_MARGIN, (COURSE_MANAGEMENT_Y_MARGIN + COURSE_MANAGEMENT_HEIGHT), COURSE_MANAGEMENT_WIDTH, COURSE_MANAGEMENT_HEIGHT);
        btnClearPlanningList.setBounds(COURSE_MANAGEMENT_X_MARGIN, (COURSE_MANAGEMENT_Y_MARGIN + 2 * COURSE_MANAGEMENT_HEIGHT), COURSE_MANAGEMENT_WIDTH, COURSE_MANAGEMENT_HEIGHT);

        checkBox_Exclude_Mon.setBounds(FILTER_DAY_X_MARGIN, FILTER_DAY_Y_MARGIN, FILTER_DAY_WIDTH, FILTER_DAY_HEIGHT);
        checkBox_Exclude_Tues.setBounds((FILTER_DAY_X_MARGIN + FILTER_DAY_WIDTH), FILTER_DAY_Y_MARGIN, FILTER_DAY_WIDTH, FILTER_DAY_HEIGHT);
        checkBox_Exclude_Wed.setBounds((FILTER_DAY_X_MARGIN + 2 * FILTER_DAY_WIDTH), FILTER_DAY_Y_MARGIN, (FILTER_DAY_WIDTH + 10), FILTER_DAY_HEIGHT);
        checkBox_Exclude_Thurs.setBounds((FILTER_DAY_X_MARGIN + 3 * FILTER_DAY_WIDTH + 10), FILTER_DAY_Y_MARGIN, FILTER_DAY_WIDTH, FILTER_DAY_HEIGHT);
        checkBox_Exclude_Fri.setBounds((FILTER_DAY_X_MARGIN + 4 * FILTER_DAY_WIDTH + 10), FILTER_DAY_Y_MARGIN, FILTER_DAY_WIDTH, FILTER_DAY_HEIGHT);


        fromLabel.setBounds(FILTER_FROM_TEXT_X_MARGIN, FILTER_FROM_AND_TO_TEXT_Y_MARGIN, FILTER_FROM_TEXT_WIDTH, FILTER_TEXT_HEIGHT);
        toLabel.setBounds((FILTER_FROM_TEXT_X_MARGIN + FILTER_FROM_TEXT_WIDTH + FILTER_TIME_WIDTH), FILTER_FROM_AND_TO_TEXT_Y_MARGIN, FILTER_TO_TEXT_WIDTH, FILTER_TEXT_HEIGHT);
        filterStartTimeComboBox.setBounds(FILTER_TIME_X_MARGIN, FILTER_TIME_Y_MARGIN, FILTER_TIME_WIDTH, FILTER_TIME_HEIGHT);
        filterEndTimeComboBox.setBounds((FILTER_FROM_TEXT_X_MARGIN + FILTER_FROM_TEXT_WIDTH + FILTER_TIME_WIDTH + FILTER_TO_TEXT_WIDTH), FILTER_TIME_Y_MARGIN, FILTER_TIME_WIDTH, FILTER_TIME_HEIGHT);

        btnAddFilter.setBounds(FILTER_BUTTON_X_MARGIN, FILTER_BUTTON_Y_MARGIN, FILTER_ADD_BUTTON_WIDTH, FILTER_BUTTON_HEIGHT);
        btnRemoveFilter.setBounds(FILTER_BUTTON_X_MARGIN + FILTER_ADD_BUTTON_WIDTH, FILTER_BUTTON_Y_MARGIN, FILTER_REMOVE_BUTTON_WIDTH, FILTER_BUTTON_HEIGHT);
        btnClearFilters.setBounds(FILTER_BUTTON_X_MARGIN + FILTER_ADD_BUTTON_WIDTH + FILTER_REMOVE_BUTTON_WIDTH, FILTER_BUTTON_Y_MARGIN, FILTER_CLEAR_BUTTON_WIDTH, FILTER_BUTTON_HEIGHT);

        activeFiltersLabel.setBounds(ACTIVE_FILTERS_TEXT_X_MARGIN, ACTIVE_FILTERS_TEXT_Y_MARGIN, ACTIVE_FILTERS_TEXT_WIDTH, ACTIVE_FILTERS_TEXT_HEIGHT);
        scrollPaneFilters.setBounds(ACTIVE_FILTERS_PANE_X_MARGIN, ACTIVE_FILTERS_PANE_Y_MARGIN, ACTIVE_FILTERS_PANE_WIDTH, ACTIVE_FILTERS_PANE_HEIGHT);

        courses2BePlannedLabel.setBounds(COURSES_TO_BE_PLANNED_TEXT_X_MARGIN, COURSES_TO_BE_PLANNED_TEXT_Y_MARGIN, COURSES_TO_BE_PLANNED_TEXT_WIDTH, COURSES_TO_BE_PLANNED_TEXT_HEIGHT);
        scrollPaneCourse2BePlanned.setBounds(COURSES_TO_BE_PLANNED_PANE_X_MARGIN, COURSES_TO_BE_PLANNED_PANE_Y_MARGIN, COURSES_TO_BE_PLANNED_PANE_WIDTH, COURSES_TO_BE_PLANNED_PANE_HEIGHT);

        btnGenerateNonOverlappingSchedules.setBounds(SCHEDULE_MANAGEMENT_X_MARGIN, SCHEDULE_MANAGEMENT_Y_MARGIN, SCHEDULE_MANAGEMENT_WIDTH, SCHEDULE_MANAGEMENT_Y_HEIGHT);
        scheduleListComboBox.setBounds(SCHEDULE_MANAGEMENT_X_MARGIN, (SCHEDULE_MANAGEMENT_Y_MARGIN + SCHEDULE_MANAGEMENT_Y_HEIGHT), SCHEDULE_MANAGEMENT_WIDTH, SCHEDULE_MANAGEMENT_Y_HEIGHT);;
        btnViewWeeklySchedule.setBounds(SCHEDULE_MANAGEMENT_X_MARGIN, (SCHEDULE_MANAGEMENT_Y_MARGIN + 2 * SCHEDULE_MANAGEMENT_Y_HEIGHT), SCHEDULE_MANAGEMENT_WIDTH, SCHEDULE_MANAGEMENT_Y_HEIGHT);

        btnImport.setBounds(IMPORT_BUTTON_X_MARGIN, IMPORT_BUTTON_Y_MARGIN, IMPORT_BUTTON_WIDTH, IMPORT_BUTTON_HEIGHT);

        btnExport.setBounds(IMPORT_BUTTON_X_MARGIN + 100, IMPORT_BUTTON_Y_MARGIN, IMPORT_BUTTON_WIDTH, IMPORT_BUTTON_HEIGHT);
        btnClearAll.setBounds(IMPORT_BUTTON_X_MARGIN + 200, IMPORT_BUTTON_Y_MARGIN, IMPORT_BUTTON_WIDTH, IMPORT_BUTTON_HEIGHT);
        btnApplyFilters.setBounds(IMPORT_BUTTON_X_MARGIN + 300, IMPORT_BUTTON_Y_MARGIN, IMPORT_BUTTON_WIDTH, IMPORT_BUTTON_HEIGHT);

        frame.add(lblProgressBar);
        frame.add(lblCourseSubject);
        frame.add(courseSubjectsComboBox);
        frame.add(lblPriority);
        frame.add(priorityValuesComboBox);
        frame.add(lblCourseCatalog);
        frame.add(lblCourseClassFilter);/////////////////////////////////////////
        frame.add(lblCourseSubjectAndCatalog);
        frame.add(distinctClassComponents_GivenCourse_ComboBox);
        frame.add(btnAddClassFilter);
        frame.add(btnRemoveClassFilter);
        frame.add(btnClearClassFilters);
        frame.add(scrollablePane);
        frame.add(lblAddedClassFilters);
        frame.add(scrollablePaneAddedClassFilters);
        frame.add(courseCatalogsComboBox);
        frame.add(lblCourseLevel);
        frame.add(lblCourseDescr);
        frame.add(btnAdd2PlanningList);
        frame.add(btnRemoveFromPlanningList);
        frame.add(filterStartTimeComboBox);
        frame.add(filterEndTimeComboBox);
        frame.add(btnAddFilter);
        frame.add(btnRemoveFilter);
        frame.add(btnClearFilters);
        frame.add(scrollPaneFilters);
        frame.add(btnGenerateNonOverlappingSchedules);
        frame.add(btnImport);
        frame.add(btnExport);
        frame.add(btnClearAll);
        frame.add(btnApplyFilters);
        frame.add(scheduleListComboBox);
        frame.add(btnClearPlanningList);
        frame.add(checkBox_Exclude_Mon);
        frame.add(checkBox_Exclude_Tues);
        frame.add(checkBox_Exclude_Wed);
        frame.add(checkBox_Exclude_Thurs);
        frame.add(checkBox_Exclude_Fri);
        frame.add(fromLabel);
        frame.add(toLabel);
        frame.add(activeFiltersLabel);
        frame.add(courses2BePlannedLabel);
        frame.add(btnViewWeeklySchedule);
        frame.add(lblCourseFaculty);
        frame.add(scrollPaneCourse2BePlanned);
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
        String savePath = "";

        btnGenerateNonOverlappingSchedules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SetProgressBarText(lblProgressBar, "Algorithm");

                Schedule.scheduleIdcounter = 0;

                List<List<ClassBundle>> classBundlesList = new ArrayList<>();

                scheduleListComboBox.removeAllItems();

                scheduleListToView = new ArrayList<>();

                long algorithm_startTime = System.nanoTime();

                //############################## BEGIN ######################################################## : ALGORITHM to generate all possible schedules from classes list

                Thread threadAExecuteAlgorithm = ExecuteAlgorithm(classBundlesList, classesList);//it runs on a separate thread.

                try {
                    threadAExecuteAlgorithm.join();//wait for algorithm to finish.
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                //############################## END ######################################################## : ALGORITHM to generate all possible schedules from classes list

                int resultSetSizeWithoutAnyFilter = scheduleListToView.size();

                boolean canApplyFiltersAutomatically = (resultSetSizeWithoutAnyFilter<=upperLimit);

                long lostTime = 0;
                Thread threadApplyFilter = null;

                if(!canApplyFiltersAutomatically && (!lstFiltersModel.isEmpty() || !lstAddedClassFiltersListModel.isEmpty())){
                    long lostTime_start = System.nanoTime();
                    int dialogResult = JOptionPane.showConfirmDialog (frame,
                            "Would You Still like to apply the chosen filter(s)?" +
                                    "\n OK -> Apply filter. This may take a while !" +
                                    "\n Cancel -> Clear selected filters (i.e. don't apply any filter(s)), which yields the result set faster.",
                            "There are " + resultSetSizeWithoutAnyFilter + " schedules to be filtered !" ,
                            JOptionPane.WARNING_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){//still want to apply filters

                        threadApplyFilter = ApplyFilters(scheduleListToView, lblProgressBar, lstFiltersModel, lstAddedClassFiltersListModel);

                    }else{//skip applying filters
                        lstAddedClassFiltersListModel.removeAllElements();
                        lstFiltersModel.removeAllElements();
                    }
                    long lostTime_end = System.nanoTime();
                    lostTime = lostTime_end - lostTime_start;
                }else{//if filters can be applied automatically, no need to ask to user, just apply them.

                    threadApplyFilter = ApplyFilters(scheduleListToView, lblProgressBar, lstFiltersModel, lstAddedClassFiltersListModel);

                }

                if(threadApplyFilter != null) {
                    try {
                        threadApplyFilter.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

                long algorithm_endTime = System.nanoTime();

                long timeElapsedInNanoseconds = algorithm_endTime - algorithm_startTime - lostTime;

                if (scheduleListToView != null && scheduleListToView.size() !=0){

                    int numSchedulesGenerated = scheduleListToView.size();

                    long mins = ((timeElapsedInNanoseconds / 1000000000)/60);
                    timeElapsedInNanoseconds -= mins*60*1000000000;
                    long secs =  timeElapsedInNanoseconds / 1000000000;
                    timeElapsedInNanoseconds -= secs*1000000000;
                    long msecs = timeElapsedInNanoseconds/1000000;

                    JOptionPane.showMessageDialog(frame,
                            numSchedulesGenerated + " non-overlapping schedules are successfully generated."
                                    + "\n\nSuccessfully Executed in "
                                    + mins + " mins : "
                                    + secs + " secs : "
                                    + msecs + " msecs"
                                    + "\n\n\tYou may find the generated schedules above the \"View Weekly Schedule\" button."
                                    + "\n\n\tNow, saving the generated non-overlapping schedules locally to a .json file.",
                            numSchedulesGenerated + " non-overlapping schedules are found.",
                            JOptionPane.PLAIN_MESSAGE);
                }else{
                    lblProgressBar.setText("<html>"
                            + "Status: "
                            + "<font face=\"verdana\" color=\"green\"><b><i>"
                            + "idle"
                            + "</i></b></font></html>");
                    JOptionPane.showMessageDialog(frame,
                            "No non-overlapping schedules are found, " +
                                    "\nTry to remove filters or courses from the planning list.",
                            "0 non-overlapping schedules are found !",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }


                RefreshScheduleListComboBox(scheduleListToView);

                Thread threadSerializeOutAndWriteHtml = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String finalRecordName = GetFinalRecordName(lstCourses2BePlannedModel);
                            final String savePath = GetSavePath(lstCourses2BePlannedModel);
                            Serializer.SerializeOut(scheduleListToView, tupleList, lstAddedClassFiltersListModel, lstFiltersModel, finalRecordName, lblProgressBar);//Save the resulting schedules as a serializable .json file (i.e. export schedules)
//                            Serializer.SerializeIn(finalRecordName, schedulesIn, lblProgressBar);//Restore the saved schedules (i.e. import schedules)
//                            ExportSchedulesAsImages(savePath, btnList, schedules, lblProgressBar);//Save the resulting schedules as .jpeg files.
                            Schedule.PrintOutSchedulesToUser(scheduleListToView, finalRecordName, lblProgressBar);//write to .html file
                            JEditorPane ep = new JEditorPane();
                            ep.setContentType("text/html");
                            File htmlFile = new File(savePath + finalRecordName + ".html");
                            try {
                                ep.setPage(htmlFile.toURI().toURL());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            ep.setEditable(false);
                            ep.setCaretPosition(0);
                            JScrollPane scrollPane = new JScrollPane(ep);
                            scrollPane.setPreferredSize(new Dimension(1000,500));


                            if(htmlFile.exists() && //if supported & file exists, then open an .html file.
                                    Desktop.isDesktopSupported())
                                Desktop.getDesktop().open(htmlFile);


//                            JOptionPane.showMessageDialog(null, //Prompt user .html file.
//                                    scrollPane,
//                                    numSchedulesGenerated + " non-overlapping plans are found.",
//                                    JOptionPane.WARNING_MESSAGE);


                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        lblProgressBar.setText("<html>"
                                + "Status: "
                                + "<font face=\"verdana\" color=\"green\"><b><i>"
                                + "idle"
                                + "</i></b></font></html>");

                    }
                });
                threadSerializeOutAndWriteHtml.start();
            }


        });

        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    String fName = selectedFile.getName();
                    String extension = "";

                    int i = fName.lastIndexOf('.');
                    if (i > 0) {
                        extension = fName.substring(i+1);
                    }

                    if(extension.equals("json")){

                        ClearAll(lstAddedClassFiltersListModel, lblCourseSubjectAndCatalog, lblCourseClassFilter, distinctClassComponents_GivenCourse_ComboBox, lstClassFiltersListModel, lstFiltersModel, tupleList, classesList, lstCourses2BePlannedModel, scheduleListComboBox);

                        Thread threadSerializeIn = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                List<Schedule> schedules = new ArrayList<>();

                                try {
                                    Serializer.SerializeIn(selectedFile,
                                                            schedules,
                                                            classesList,
                                                            tupleList,
                                                            lstCourses2BePlannedModel,
                                                            lstAddedClassFiltersListModel,
                                                            lstFiltersModel,
                                                            lblProgressBar);//Restore the saved schedules (i.e. import schedules)
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }

                                if(schedules!=null && schedules.size()!=0){
                                    JOptionPane.showMessageDialog(frame,
                                            "Successfully imported " + schedules.size() + " schedules.",
                                            "Import Succeeded",
                                            JOptionPane.PLAIN_MESSAGE);
                                    scheduleListComboBox.removeAllItems();
                                    for(Schedule s:schedules)
                                        scheduleListComboBox.addItem(s);
                                    scheduleListToView = schedules;
                                }
                                lblProgressBar.setText("<html>"
                                        + "Status: "
                                        + "<font face=\"verdana\" color=\"green\"><b><i>"
                                        + "idle"
                                        + "</i></b></font></html>");
                            }
                        });
                        threadSerializeIn.start();

                    }else{
                        JOptionPane.showMessageDialog(frame,
                                "Please choose a .json file to import. ",
                                "Wrong extension !",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println("\n\nWrong extension.\n\n");
                        lblProgressBar.setText("<html>"
                                + "Status: "
                                + "<font face=\"verdana\" color=\"green\"><b><i>"
                                + "idle"
                                + "</i></b></font></html>");
                    }
                }

            }
        });

        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread threadExportSchedulesAsImages = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List < Schedule > schedules = scheduleListToView;

                        if(schedules !=null && !schedules.isEmpty()){
                            String savePath = GetSavePath(lstCourses2BePlannedModel);
                            try {
                                ExportSchedulesAsImages(savePath, btnList, schedules, lblProgressBar);//Save the resulting schedules as .jpeg files.
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (AWTException e1) {
                                e1.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(frame,
                                    "There is no schedule to export. ",
                                    "Schedule list is empty !",
                                    JOptionPane.ERROR_MESSAGE);
                            System.out.println("\n\n!!! There is no schedule to export !!!\n\n");
                        }
                        lblProgressBar.setText("<html>"
                                + "Status: "
                                + "<font face=\"verdana\" color=\"green\"><b><i>"
                                + "idle"
                                + "</i></b></font></html>");

                    }
                    });
                threadExportSchedulesAsImages.start();
                }
        });

        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearAll(lstAddedClassFiltersListModel, lblCourseSubjectAndCatalog, lblCourseClassFilter, distinctClassComponents_GivenCourse_ComboBox, lstClassFiltersListModel, lstFiltersModel, tupleList, classesList, lstCourses2BePlannedModel, scheduleListComboBox);
            }
        });

        btnApplyFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyFilters(scheduleListToView, lblProgressBar, lstFiltersModel, lstAddedClassFiltersListModel);
            }
        });


        btnViewWeeklySchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (scheduleListToView != null && !scheduleListToView.isEmpty()) {
                    System.out.println("hi1");
//                    String selectedItemStr = (String) scheduleListComboBox.getSelectedIndex();
                    int scheduleIndex2BeDisplayed = scheduleListComboBox.getSelectedIndex();
                    if(scheduleIndex2BeDisplayed == -1){
                        JOptionPane.showMessageDialog(null,
                                "Please select a schedule first.",
                                "No Schedule has been selected.",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Schedule scheduleToView = scheduleListToView.get(scheduleIndex2BeDisplayed);



                    ViewWeeklySchedule(scheduleToView, btnList);



                }
            }
        });

        btnCloseBackgroundPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleFrame.setVisible(false);
            }
        });

        btnNextSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleFrame.setVisible(false);
                int nextScheduleIndex = (scheduleListComboBox.getSelectedIndex() + 1) % scheduleListComboBox.getItemCount();
                scheduleListComboBox.setSelectedIndex(nextScheduleIndex);
                btnViewWeeklySchedule.doClick();
            }
        });

        btnPrevSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleFrame.setVisible(false);
                int prevScheduleIndex = (scheduleListComboBox.getSelectedIndex() - 1);
                if(prevScheduleIndex<0)
                    prevScheduleIndex += scheduleListComboBox.getItemCount();
                scheduleListComboBox.setSelectedIndex(prevScheduleIndex);
                btnViewWeeklySchedule.doClick();
            }
        });

    }

    private static Thread ExecuteAlgorithm(List<List<ClassBundle>> classBundlesList, List<List<Class>> classesList) {
        Thread threadExecuteAlgorithm = new Thread(new Runnable() {
            @Override
            public void run() {
                AlgorithmPart_1_Call_GenerateClassBundles(classBundlesList, classesList);
                AlgorithmPart_2_Call_GenerateSchedulesFromClassBundlesList(classBundlesList);
            }
        });
        threadExecuteAlgorithm.start();
        return  threadExecuteAlgorithm;
    }

    private static void AlgorithmPart_1_Call_GenerateClassBundles(List<List<ClassBundle>> classBundlesList, List<List<Class>> classesList) {
        for (List<Class> curClassList : classesList) {//ALGORITHM_PART1_GenerateClassBundlesList
            List<ClassBundle> curBundles = ClassBundle.GenerateClassBundlesFromClasses(curClassList);
            System.out.println(curBundles);
            classBundlesList.add(curBundles);
        }
    }

    private static void AlgorithmPart_2_Call_GenerateSchedulesFromClassBundlesList(List<List<ClassBundle>> classBundlesList){
        scheduleListToView = Schedule.GenerateSchedulesFromClassBundlesList(classBundlesList);//ALGORITHM_PART2_GenerateSchedules;
    }



    private static void ClearAll(DefaultListModel lstAddedClassFiltersListModel, JLabel lblCourseSubjectAndCatalog, JLabel lblCourseClassFilter, JComboBox<String> distinctClassComponents_GivenCourse_ComboBox, DefaultListModel lstClassFiltersListModel, DefaultListModel lstFiltersModel, List<CourseSubject_Catalog_Priority_Tuple> tupleList, List<List<Class>> classesList, DefaultListModel lstCourses2BePlannedModel, JComboBox<Schedule> scheduleListComboBox) {
        ClearClassFilters(lstAddedClassFiltersListModel,lblCourseSubjectAndCatalog,lblCourseClassFilter,distinctClassComponents_GivenCourse_ComboBox,lstClassFiltersListModel);
        lstFiltersModel.removeAllElements();//clear day & time filters
        ClearPlanningList(tupleList, classesList, lstCourses2BePlannedModel, scheduleListComboBox);
    }

    private static void ClearPlanningList(List<CourseSubject_Catalog_Priority_Tuple> tupleList, List<List<Class>> classesList, DefaultListModel lstCourses2BePlannedModel, JComboBox<Schedule> scheduleListComboBox) {
        for (CourseSubject_Catalog_Priority_Tuple curTuple : tupleList) {
            System.out.println(curTuple);
        }

        System.out.println("\n Has been cleared from the planning list!");

        tupleList.clear();
        classesList.clear();
        lstCourses2BePlannedModel.clear();
        scheduleListComboBox.removeAllItems();

        System.out.println("Course Planning list has been cleared!");
    }

    private static void ClearClassFilters(DefaultListModel lstAddedClassFiltersListModel, JLabel lblCourseSubjectAndCatalog, JLabel lblCourseClassFilter, JComboBox<String> distinctClassComponents_GivenCourse_ComboBox, DefaultListModel lstClassFiltersListModel) {
        lstAddedClassFiltersListModel.removeAllElements();
        lblCourseSubjectAndCatalog.setText("Please double click to a course in \"Courses to be planned\" pane");
        lblCourseClassFilter.setText("Course Class Filter: ");
        distinctClassComponents_GivenCourse_ComboBox.removeAllItems();
        lstClassFiltersListModel.removeAllElements();
    }

    private static String GetSavePath(DefaultListModel lstCourses2BePlannedModel) {

        String finalRecordName = GetFinalRecordName(lstCourses2BePlannedModel);

        String savePath = "outputs/ScheduleExports/"+ (finalRecordName.equals("")?"Exported ":finalRecordName + "/");

        return savePath;
    }

    private static String GetFinalRecordName(DefaultListModel lstCourses2BePlannedModel) {
        if(lstCourses2BePlannedModel.isEmpty())
            return "";
        String serializedSchedules_recordName = "";
        List<Schedule> schedulesIn = new ArrayList<>();
        for(int i=0;i<lstCourses2BePlannedModel.getSize();i++){
            CourseSubject_Catalog_Priority_Tuple curTuple = (CourseSubject_Catalog_Priority_Tuple)lstCourses2BePlannedModel.getElementAt(i);
            serializedSchedules_recordName += curTuple.getSubject() + " " + curTuple.getCatalog() + ", ";
        }

        serializedSchedules_recordName = serializedSchedules_recordName.substring(0,serializedSchedules_recordName.length()-2);

        return serializedSchedules_recordName;
    }

    private static void ExportSchedulesAsImages(String savePath, List<JButton> btnList, List<Schedule> schedules, JLabel lblProgressBar) throws IOException, AWTException {
        WeeklyScheduleGUI wsGUI = new WeeklyScheduleGUI(btnList);
        wsGUI.saveWeeklySchedulesAsImages(schedules, savePath, lblProgressBar);//Save the resulting schedules as .jpeg files.

    }

    private static void SetProgressBarText(JLabel lblProgressBar,
                                           String progressBarStr) {
        lblProgressBar.setText("<html>"
                + "Running: "
                + "<font face=\"verdana\" color=\"red\"><b><i>"
                + progressBarStr
                + "</i></b></font></html>");
        lblProgressBar.paintImmediately(lblProgressBar.getVisibleRect());
    }

    private static Thread ApplyFilters(List<Schedule> schedules,
                                       JLabel lblProgressBar,
                                       DefaultListModel lstFiltersModel,
                                       DefaultListModel lstAddedClassFiltersListModel) {

        Thread threadApplyFilters = new Thread(new Runnable() {
            @Override
            public void run() {
                int beforeFilter_scheduleCount = schedules.size();
                ApplyFiltersAlgorithm(lstFiltersModel, schedules, lblProgressBar, lstAddedClassFiltersListModel);
                int dtfCount = lstFiltersModel.getSize();
                int acfCount = lstAddedClassFiltersListModel.getSize();
                int afterFilter_scheduleCount = schedules.size();
                String message = "On " + beforeFilter_scheduleCount
                        + " schedules:\n\n"
                        + "\t" + dtfCount
                        + " day & time exclusion filter(s)\n"
                        + "\t" + acfCount
                        + " class inclusion filter(s) \n\nhave been applied successfully.\n\n"
                        + afterFilter_scheduleCount
                        + " schedules are obtained after filtering.";
                System.out.println("\n\n" + message + "\n\n");
                lblProgressBar.setText("<html>"
                        + "Status: "
                        + "<font face=\"verdana\" color=\"green\"><b><i>"
                        + "idle"
                        + "</i></b></font></html>");
                ShowMessage(message);
                RefreshScheduleListComboBox(schedules);
            }
        });
        threadApplyFilters.start();
        return threadApplyFilters;
    }

    private static void ShowMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(frame,
                                            message,
                                            "Filter(s) have been applied successfully.",
                                            JOptionPane.PLAIN_MESSAGE);
            }
        });
    }


    private static void ApplyFiltersAlgorithm(DefaultListModel lstFiltersModel, List<Schedule> schedules, JLabel lblProgressBar, DefaultListModel lstAddedClassFiltersListModel) {
        if (!lstFiltersModel.isEmpty()) {//Apply Time & Day Exclusion Filter
            List<DayTimeFramePair> dayTimeFramePairs = new ArrayList<>();
            for (int i = 0; i < lstFiltersModel.size();i++) {
                DayTimeFramePair curDayTimeFramePair = (DayTimeFramePair) lstFiltersModel.getElementAt(i);
                dayTimeFramePairs.add(curDayTimeFramePair);
            }
            Schedule.FilterOutSchedulesFrom(schedules,
                    dayTimeFramePairs,
                    lblProgressBar);
        }

        if (!lstAddedClassFiltersListModel.isEmpty()) {//Apply INCLUDE CLASSES filter
            List<Class> activeClassFilterElementClassList = new ArrayList<>();
            for (int i = 0; i < lstAddedClassFiltersListModel.size(); i++) {
                Class curActiveClassFilterElementClass = ((ActiveClassFilterElement) lstAddedClassFiltersListModel
                        .getElementAt(i))
                        .getClassForActiveClassFilterElement();
                activeClassFilterElementClassList.add(curActiveClassFilterElementClass);
            }
            Schedule.ClassFilterSchedulesIncluding_ClassLists(schedules,
                    activeClassFilterElementClassList,
                    lblProgressBar);
        }
    }

    private static void ReFillClassFilterList(String courseSubject,
                                              int courseCatalog,
                                              JComboBox<String> distinctClassComponents_GivenCourse_ComboBox,
                                              String dbName,
                                              String sqlQuery_sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent_Location,
                                              DefaultListModel lstClassFiltersListModel) {

        String selectedClassComponent = (String) distinctClassComponents_GivenCourse_ComboBox.getSelectedItem();

        classListForClassFilter = SelectFromTableInDB.SelectClassesFromCourseSubjectCatalogAndClassComponent(
                courseSubject,
                courseCatalog,
                selectedClassComponent,dbName,sqlQuery_sqlQuery_SelectClassesFromCourseSubjectCatalogAndClassComponent_Location);

        lstClassFiltersListModel.removeAllElements();

        for(Class curClass:classListForClassFilter){
            ClassFilterElement cfe = new ClassFilterElement(curClass);
            lstClassFiltersListModel.addElement(cfe);
        }
    }

    private static void InitialFillFilterTimeComboBoxes(JComboBox<String> filterStartTimeComboBox, JComboBox<String> filterEndComboBox) {
        for(int i = 0; i< DayTimeFramePair.AllTimeStrings.size(); i++){
            String curTimeStr = DayTimeFramePair.AllTimeStrings.get(i);
            if(i==0){
                filterStartTimeComboBox.addItem(curTimeStr);
                continue;
            }
            filterEndComboBox.addItem(curTimeStr);
            filterStartTimeComboBox.addItem(curTimeStr);
        }
    }


    private static void PrinOutClassesList(List<List<Class>> classesList) {

        System.out.println("-------");

        int i = 0;

        for (List<Class> cList : classesList) {

            System.out.println("\nCLASS LIST:" + ++i + " \n\n" + cList);
        }
    }

    private static void CreateAndFill_DB_from_CSV(List<ClassInfo> classInfoList, String fPath, String sqlQuery_Create_Location, String sqlQuery_Insert_Location, String dbName) {

        ReadCSV.FillIntoList(fPath, classInfoList);
        //System.out.println(classInfoList);

        ExecuteDropDB.executeDropDB_ifExists("outputs/" + dbName + ".db");

        CreateDB.createNewDatabase(dbName);

        CreateTableInDB.createNewTable(dbName, sqlQuery_Create_Location);

        InsertIntoTableInDB.insertAll(dbName, sqlQuery_Insert_Location, classInfoList);

        System.out.println("end of CreateAndFill_DB_from_CSV");
    }

    private static void CreateNormalizedTablesInDB(List<String> sqlQuery_Create_NormalizedTables, String dbName) {
        CreateTableInDB.createNewTablesFromList(dbName, sqlQuery_Create_NormalizedTables);
    }

    private static void CleanStartAndFill_NormalizedTables(List<String> sqlQueryLocationList2CleanStartAndFill_NormalizedTables, String dbName) {
        InsertIntoTableInDB.execQueriesFromList(dbName, sqlQueryLocationList2CleanStartAndFill_NormalizedTables);
    }

    private static void UpdateLabelsFromDB(String dbName,
                                           String sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                                           String selectedCourseSubject,
                                           int selectedCourseCatalog,
                                           JLabel lblCourseLevel,
                                           JLabel lblCourseFaculty,
                                           JLabel lblCourseDescr) {

        String defaultCourseFacultyLabel = "Course Faculty: ";
        String defaultCourseLevelLabel = "Course Level: ";
        String defaultCourseDescrLabel = "Course Description: ";

        List<String> strListCourse_Career_AcadOrg_Descr_Descr2 = SelectFromTableInDB.SelectCourse_Career_AcadOrg_Descr_Descr2(
                dbName,
                sqlQuery_SelectCourse_Career_AcadOrg_Descr_Descr2_with_CourseSubject_Catalog_Location,
                selectedCourseSubject,
                selectedCourseCatalog
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
                + "</i></b></font></html>");

        lblCourseDescr.setText("<html>"
                + defaultCourseDescrLabel
                + "<br><font face=\"verdana\" color=\"green\"><b><i>"
                + CourseDescrInfo
                + "</i></b></font><br><font face=\"verdana\" color=\"green\"><b><i>"
                + CourseDescr2Info
                + "</i></b></font></html>");
    }

    /*
    private static void ViewWeeklySchedule(Schedule scheduleToView, JFrame scheduleFrame, List<JButton> btnList){
        WeeklyScheduleGUI weeklyScheduleView = new WeeklyScheduleGUI( btnList);
        weeklyScheduleView.createWeeklySchedule1(scheduleToView, scheduleFrame);
    }


     */
    private static void ViewWeeklySchedule(Schedule scheduleToView, List<JButton> btnList){
        WeeklyScheduleGUI weeklyScheduleView= new WeeklyScheduleGUI(btnList);
        scheduleFrame=weeklyScheduleView.getScheduleFrame();
        //weeklyScheduleView.createWeeklySchedule1(scheduleToView,btnCloseBackgroundPanel);
        //weeklyScheduleView.createWeeklySchedule1(scheduleToView, scheduleFrame, btnCloseBackgroundPanel);
        weeklyScheduleView.createWeeklySchedule(scheduleToView);
        scheduleFrame.setVisible(true);
        //weeklyScheduleView.createWeeklySchedule2(scheduleToView);
    }

    private static void RefreshScheduleListComboBox(List<Schedule> schedules){
        scheduleListComboBox.removeAllItems();
        for(Schedule curSchedule:schedules){
            scheduleListComboBox.addItem(curSchedule);
        }
    }



    /*
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

    */
}
