package model.DB_Utilities;

import model.*;
import model.Class;
import model.tools.ReadFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SelectFromTableInDB {

//        /**
//         * Connect to the test.db database
//         * @return the Connection object
//         */
//        private Connection connect() {
//            // SQLite connection string
//            String url = "JDBC:sqlite:CoursePlannerDB.db";
//            Connection conn = null;
//            try {
//                conn = DriverManager.getConnection(url);
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//            return conn;
//        }
//

        /**
         * select all rows in the warehouses table
         */
        public static void SelectDistinctCourseSubjects(String dbName, String sqlQueryLocation, List<String> distinctCourseSubjectList){


            String url = "JDBC:sqlite:outputs/" + dbName + ".db";

            ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

            //Statement stmt  = null;

            try (Connection conn = DriverManager.getConnection(url);){

                String sqlQuery =  sqlQueryFile.export2String();
                //sqlQuery = sqlQuery.substring(1,sqlQuery.length());
//                String str = ("SELECT DISTINCT CourseSubject\n" +
//                        "FROM Courses\n" +
//                        "ORDER BY CourseSubject;").substring(0,5);
//
//                System.out.println("\n" + sqlQuery.equals(str));

                PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {

                    String curCourseSubject = rs.getString("CourseSubject");

                    distinctCourseSubjectList.add(curCourseSubject);
                            //rs.getString("name") + "\t" +
                            //rs.getDouble("capacity"));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    public static void SelectCourseCatalogsOfChosenCourseSubject(String dbName, String sqlQueryLocation, List<Integer> selectedCourseCatalogList, String selectedCourseSubject){


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            //sqlQuery = sqlQuery.substring(1,sqlQuery.length());
//                String str = ("SELECT DISTINCT CourseSubject\n" +
//                        "FROM Courses\n" +
//                        "ORDER BY CourseSubject;").substring(0,5);
//
//                System.out.println("\n" + sqlQuery.equals(str));

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, selectedCourseSubject);
            ResultSet rs = pstmt.executeQuery();

            selectedCourseCatalogList.clear();

            while (rs.next()) {

                int curCourseSubject = rs.getInt("CourseCatalog");

                selectedCourseCatalogList.add(curCourseSubject);
                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<String> SelectCourse_Career_AcadOrg_Descr_Descr2(String dbName,
                                                                String sqlQueryLocation,
                                                                String selectedCourseSubject,
                                                                Integer selectedCourseCatalog){


        List<String> listResponseFromDB = new ArrayList<>();

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            //sqlQuery = sqlQuery.substring(1,sqlQuery.length());
//                String str = ("SELECT DISTINCT CourseSubject\n" +
//                        "FROM Courses\n" +
//                        "ORDER BY CourseSubject;").substring(0,5);
//
//                System.out.println("\n" + sqlQuery.equals(str));

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, selectedCourseSubject);
            pstmt.setInt(2, selectedCourseCatalog);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String curCourseCareer = rs.getString("CourseCareer");
                String curCourseAcadOrg = rs.getString("CourseAcadOrg");
                String curCourseDescr = rs.getString("CourseDescr");
                String curCourseDescr2 = rs.getString("CourseDescr2");

                listResponseFromDB.add(curCourseCareer);
                listResponseFromDB.add(curCourseAcadOrg);
                listResponseFromDB.add(curCourseDescr);
                listResponseFromDB.add(curCourseDescr2);

                break;
                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listResponseFromDB;

    }

    public static void SelectClassesOfOneCourse(String dbName,
                                                              String sqlQueryLocation,
                                                              String selectedCourseSubject,
                                                              Integer selectedCourseCatalog,
                                                              List<model.Class>classListForTuple){

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            //sqlQuery = sqlQuery.substring(1,sqlQuery.length());
//                String str = ("SELECT DISTINCT CourseSubject\n" +
//                        "FROM Courses\n" +
//                        "ORDER BY CourseSubject;").substring(0,5);
//
//                System.out.println("\n" + sqlQuery.equals(str));

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, selectedCourseSubject);
            pstmt.setInt(2, selectedCourseCatalog);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int curId = rs.getInt("ClassId");
                String curComponent = rs.getString("ClassComponent");
                String curStartTime = rs.getString("ClassMtgStart");
                String curEndTime = rs.getString("ClassMtgEnd");
                boolean curMonFlag = rs.getString("ClassMon").equals("Y");
                boolean curTuesFlag = rs.getString("ClassTues").equals("Y");
                boolean curWedFlag = rs.getString("ClassWed").equals("Y");
                boolean curThursFlag = rs.getString("ClassThurs").equals("Y");
                boolean curFriFlag = rs.getString("ClassFri").equals("Y");
                String curLocation = rs.getString("ClassFacilID");
                List<InstructorNameRolePair> instructorNameRolePairs = new ArrayList<>();

                FillInstructorNameRolePairsWithClassId(dbName, curId, instructorNameRolePairs);

//                String curInstructorName = rs.getString("InstructorName");
//                String curInstructorRole = rs.getString("ClassInstructorRole");

                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));


                EnrollmentDetailsPair edPair = GetEnrollmentDetailsPairWithCourseSubjectCatalogAndClassId(dbName,selectedCourseSubject,
                                                                                                            selectedCourseCatalog,curId);

                int curTotlEnrl = edPair.getTotlEnrl();
                int curCapEnrl = edPair.getCapEnrl();

                model.Class curClass = new model.Class(
                                        curId,
                                        curComponent,
                                        curStartTime,
                                        curEndTime,
                                        curMonFlag,
                                        curTuesFlag,
                                        curWedFlag,
                                        curThursFlag,
                                        curFriFlag,
                                        curLocation,
                                        instructorNameRolePairs,
                                        selectedCourseSubject,
                                        selectedCourseCatalog,
                                        curTotlEnrl,
                                        curCapEnrl
//                                        curInstructorName,
//                                        curInstructorRole
                );

                classListForTuple.add(curClass);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void FillInstructorNameRolePairsWithClassId(String dbName, int classId, List<InstructorNameRolePair> instructorNameRolePairs){


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        String sqlQueryLocation = "inputs/sqlQuery_SelectInstructorNamesAndRolesFromClassId.sql";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, classId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String curInstructorName = rs.getString("InstructorName");
                String curClassInstructorRole = rs.getString("ClassInstructorRole");


                InstructorNameRolePair curInstructorNameRolePair  = new InstructorNameRolePair( curInstructorName,
                                                                                                curClassInstructorRole);
                instructorNameRolePairs.add(curInstructorNameRolePair);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    public static EnrollmentDetailsPair GetEnrollmentDetailsPairWithCourseSubjectCatalogAndClassId(String dbName, String courseSubject, int courseCatalog, int classId){


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        String sqlQueryLocation = "inputs/sqlQuery_SelectClassTotEnrlAndCapEnrlFromCourseSubjectCatalogAndClassId.sql";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;
        EnrollmentDetailsPair edPair = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, courseSubject);
            pstmt.setInt(2, courseCatalog);
            pstmt.setInt(3, classId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int classTotalEnrl = rs.getInt("ClassTotEnrl");
                int classCapEnrl = rs.getInt("ClassCapEnrl");

                edPair = new EnrollmentDetailsPair(classTotalEnrl, classCapEnrl);

                break;

            }
            if(edPair == null)//if no result returns, set it to default val.
                edPair = new EnrollmentDetailsPair(0,0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return edPair;

    }

    public static List<String> SelectDistinctClassComponentsOfGivenCourse(String courseSubject, int courseCatalog, String dbName, String sqlQueryLocation){

            List<String> distinctClassComponentsList = new ArrayList<>();


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, courseSubject);
            pstmt.setInt(2, courseCatalog);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String curClassComponent = rs.getString("ClassComponent");
                distinctClassComponentsList.add(curClassComponent);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return distinctClassComponentsList;


    }

    public static List<String> SelectAllPossibleTimeStampsOrderedAsc(String dbName, String sqlQueryLocation){

        List<String> allPossibleTimeStamps = new ArrayList<>();


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            //sqlQuery = sqlQuery.substring(1,sqlQuery.length());
//                String str = ("SELECT DISTINCT CourseSubject\n" +
//                        "FROM Courses\n" +
//                        "ORDER BY CourseSubject;").substring(0,5);
//
//                System.out.println("\n" + sqlQuery.equals(str));

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String curTimeOfClass = rs.getString("Time of Class");

                allPossibleTimeStamps.add(curTimeOfClass);
                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allPossibleTimeStamps;


    }

    public static List<Class> SelectClassesFromCourseSubjectCatalogAndClassComponent(String courseSubject,
                                                                                                       int courseCatalog,
                                                                                                       String classComponent,
                                                                                                       String dbName,
                                                                                                       String sqlQueryLocation){

        List<Class> classList = new ArrayList<>();


        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        //Statement stmt  = null;

        try (Connection conn = DriverManager.getConnection(url);){

            String sqlQuery =  sqlQueryFile.export2String();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, courseSubject);
            pstmt.setInt(2, courseCatalog);
            pstmt.setString(3, classComponent);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int curId = rs.getInt("ClassId");
                String curComponent = rs.getString("ClassComponent");
                String curStartTime = rs.getString("ClassMtgStart");
                String curEndTime = rs.getString("ClassMtgEnd");
                boolean curMonFlag = rs.getString("ClassMon").equals("Y");
                boolean curTuesFlag = rs.getString("ClassTues").equals("Y");
                boolean curWedFlag = rs.getString("ClassWed").equals("Y");
                boolean curThursFlag = rs.getString("ClassThurs").equals("Y");
                boolean curFriFlag = rs.getString("ClassFri").equals("Y");
                String curLocation = rs.getString("ClassFacilID");
                List<InstructorNameRolePair> instructorNameRolePairs = new ArrayList<>();

                FillInstructorNameRolePairsWithClassId(dbName, curId, instructorNameRolePairs);

//                String curInstructorName = rs.getString("InstructorName");
//                String curInstructorRole = rs.getString("ClassInstructorRole");

                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));


                EnrollmentDetailsPair edPair = GetEnrollmentDetailsPairWithCourseSubjectCatalogAndClassId(dbName,courseSubject,
                        courseCatalog,curId);

                int curTotlEnrl = edPair.getTotlEnrl();
                int curCapEnrl = edPair.getCapEnrl();

                model.Class curClass = new model.Class(
                        curId,
                        curComponent,
                        curStartTime,
                        curEndTime,
                        curMonFlag,
                        curTuesFlag,
                        curWedFlag,
                        curThursFlag,
                        curFriFlag,
                        curLocation,
                        instructorNameRolePairs,
                        courseSubject,
                        courseCatalog,
                        curTotlEnrl,
                        curCapEnrl
//                                        curInstructorName,
//                                        curInstructorRole
                );

                classList.add(curClass);

//                Class curClass = new Class()

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return classList;

    }

    public static void PutClassesFromDB2cList(CourseSubject_Catalog_Tuple tuple, List<Class> classList, String dbName, String sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location) {

        classList.clear();


        String tupleCourseSubject = tuple.getSubject();
        int tupleCourseCatalog = tuple.getCatalog();

        SelectClassesOfOneCourse(
                dbName,
                sqlQuery_selectClassesInfoFromCourseSubject_Catalog_Location,
                tupleCourseSubject,
                tupleCourseCatalog,
                classList
        );
    }


//        /**
//         * @param args the command line arguments
//         */
//        public static void main(String[] args) {
//            SelectFromTableInDB app = new SelectFromTableInDB();
//            app.selectAll();
//        }


}