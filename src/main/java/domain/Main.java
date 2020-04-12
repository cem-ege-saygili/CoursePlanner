package domain;

import DB_Utilities.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;

public class Main {

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


//        String sqlQuery_Insert2Table_Classes_Location = "inputs/Insert2Table_Classes.txt";
//        String sqlQuery_Insert2Table_Instructors_Location = "inputs/Insert2Table_Instructors.txt";
//        String sqlQuery_Insert2Table_Class_Instructor_Infos_Location = "inputs/Insert2Table_Class_Instructor_Infos.txt";
        List<String> sqlQueryLocationList2Create_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/CreateTable_Courses.txt",
                        "inputs/CreateTable_Classes.txt",
                        "inputs/CreateTable_Instructors.txt",
                        "inputs/CreateTable_Class_Instructor_Infos.txt")
        );

        List<String> sqlQueryLocationList2CleanStartAndFill_NormalizedTables = new ArrayList<String>(
                Arrays.asList("inputs/sqlQuery_CleanStart_DB.txt",
                        "inputs/Insert2Table_Courses.txt",
                        "inputs/Insert2Table_Classes.txt",
                        "inputs/Insert2Table_Instructors.txt",
                        "inputs/Insert2Table_Class_Instructor_Infos.txt")
        );

        String dbName = "CoursePlannerDB2";

        //CreateAndFill_DB_from_CSV(classInfoList, fPath, sqlQuery_Create_Location, sqlQuery_Insert_Location, dbName);

        //CreateNormalizedTablesInDB(sqlQueryLocationList2Create_NormalizedTables, dbName);

        //CleanStartAndFill_NormalizedTables(sqlQueryLocationList2CleanStartAndFill_NormalizedTables, dbName);

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

        Integer[] priorityValues = new Integer[] {1,2,3,4,5};

        List<String> courseSubjectList = new ArrayList<>();
        List<Integer> courseCatalogList = new ArrayList<>();

        SelectFromTableInDB.SelectDistinctCourseSubjects(dbName, sqlQuery_SelectDistinctCourseSubjects_Location, courseSubjectList);

        String[] courseSubjectsArr = new String[courseSubjectList.size()];
        courseSubjectsArr = courseSubjectList.toArray(courseSubjectsArr);

        JComboBox<String> courseSubjectsComboBox = new JComboBox<>(courseSubjectsArr);
        JComboBox<Integer> priorityValuesComboBox = new JComboBox<>(priorityValues);

        SelectFromTableInDB.SelectCourseCatalogsOfChosenCourseSubject(dbName, sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location,
                                        courseCatalogList,(String) courseSubjectsComboBox.getSelectedItem());

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
        JButton btnGenerateOptimumSchedule = new JButton("Generate non-overlapping Schedule(s)");
        JButton btnClearPlanningList = new JButton("Clear the Planning List");


        DefaultListModel lstCourses2BePlannedModel = new DefaultListModel();
        JList lstCourses2BePlanned = new JList(lstCourses2BePlannedModel);




//        Integer curTimeStart = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[0];
//        Integer curTimeEnd = timeTables.get((String)courseSubjectsComboBox.getSelectedItem())[1];

//        lblStartTime.setText("Starts at: " + curTimeStart);
//        lblEndTime.setText("Ends at: " + curTimeEnd);

        courseSubjectsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String curCourseSubject = (String) e.getItem();
                    SelectFromTableInDB.SelectCourseCatalogsOfChosenCourseSubject(dbName, sqlQuery_SelectCourseCatalogsOfChosenCourseSubject_Location,
                            courseCatalogList, curCourseSubject);

                    courseCatalogsComboBox.removeAllItems();

                    for(int curCatalog:courseCatalogList){
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
                if(e.getStateChange() == ItemEvent.SELECTED) {
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


        List<List<String>> coursesInPlanningList = new ArrayList<List<String>>();
        List<String> courseNamesInPlanningList = new ArrayList<String>();

        btnAdd2PlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourseName = (String) courseSubjectsComboBox.getSelectedItem();
                if(!courseNamesInPlanningList.contains(selectedCourseName)){
                    String element2BeAdded = "";
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
                }else{
                    JOptionPane.showMessageDialog(btnAdd2PlanningList, selectedCourseName + " has already been added to the planning list!");
                }

            }
        });

        btnRemoveFromPlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedElementIndex = lstCourses2BePlanned.getSelectedIndex();
                if(selectedElementIndex != -1){
                    coursesInPlanningList.remove(selectedElementIndex);
                    courseNamesInPlanningList.remove(selectedElementIndex);
                    lstCourses2BePlannedModel.removeElementAt(selectedElementIndex);
                }

            }
        });

        btnClearPlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesInPlanningList.clear();
                courseNamesInPlanningList.clear();
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
        frame.setSize(500,720);

        //int yInc = 25;

        lblCourseSubject.setBounds(100, 50, 150,25);
        courseSubjectsComboBox.setBounds(250,50,150,25);

        lblCourseCatalog.setBounds(100, 75, 150,25);
        courseCatalogsComboBox.setBounds(250,75,150,25);

        lblPriority.setBounds(100,100,100,25);
        priorityValuesComboBox.setBounds(250,100,150,25);

        lblCourseFaculty.setBounds(100,125,400,25);
        lblCourseLevel.setBounds(100,150,400,25);
        lblCourseDescr.setBounds(100,175,400,75);
        lblCourseDescr.setVerticalAlignment(JLabel.TOP);

        btnAdd2PlanningList.setBounds(100,275,300,25);
        btnRemoveFromPlanningList.setBounds(100,250,300,25);
        btnClearPlanningList.setBounds(100,300,300,25);

        lstCourses2BePlanned.setBounds(100, 350,300,250);

        btnGenerateOptimumSchedule.setBounds(100,625,300,25);

        frame.add(lblCourseSubject);frame.add(courseSubjectsComboBox);frame.add(lblPriority);frame.add(priorityValuesComboBox);
        frame.add(lblCourseCatalog);frame.add(courseCatalogsComboBox);frame.add(lblCourseLevel);frame.add(lblCourseDescr);
        frame.add(btnAdd2PlanningList);frame.add(btnRemoveFromPlanningList);frame.add(btnGenerateOptimumSchedule);frame.add(btnClearPlanningList);
        frame.add(lblCourseFaculty);
        frame.add(lstCourses2BePlanned);
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

        btnGenerateOptimumSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseNamesInPlanningList.size()!=0){
                    for(List<String> course: coursesInPlanningList){
                        String currentCourseName = course.get(0);
                        Integer currentCoursePriority = Integer.valueOf(course.get(1));
                        Integer currentCourseStartTime = Integer.valueOf(course.get(2));
                        Integer currentCourseEndTime = Integer.valueOf(course.get(3));
                        cList.add(new Course(currentCourseName, currentCourseStartTime,currentCourseEndTime,currentCoursePriority));
                    }
                    Scheduler s1 = new Scheduler(cList);
                    s1.generateOptimumCoursePlan();
                    cList.clear();
                    //JOptionPane.showMessageDialog(null,s1.getOutput(),s1.getNumPlans() +" non-overlapping plans are found.", ModifiableJOptionPane.WARNING_MESSAGE);
                    String message2BeDisplayed = s1.getOutput();
                    JTextArea textArea = new JTextArea(25, 75);
                    textArea.setText(message2BeDisplayed);
                    textArea.setEditable(false);
                    textArea.setCaretPosition(0);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(null,scrollPane,s1.getNumPlans() +" non-overlapping plans are found.",JOptionPane.WARNING_MESSAGE);
                }
            }
        });



    }

    private static void CreateAndFill_DB_from_CSV(List<ClassInfo> classInfoList, String fPath, String sqlQuery_Create_Location, String sqlQuery_Insert_Location, String dbName) {

        ReadCSV.FillIntoList(fPath, classInfoList);
        //System.out.println(classInfoList);

        ExecuteDropDB.executeDropDB_ifExists("outputs/"+ dbName + ".db");

        CreateDB.createNewDatabase(dbName);

        CreateTableInDB.createNewTable(dbName, sqlQuery_Create_Location);

        InsertIntoTableInDB.insertAll(dbName,sqlQuery_Insert_Location,classInfoList);

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
                (Integer)courseCatalogsComboBox.getSelectedItem()
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

}
