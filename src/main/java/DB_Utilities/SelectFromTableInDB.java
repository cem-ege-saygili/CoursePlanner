package DB_Utilities;

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
                                                              List<domain.Class>classListForTuple){

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
//                String curInstructorName = rs.getString("InstructorName");
//                String curInstructorRole = rs.getString("ClassInstructorRole");

                //rs.getString("name") + "\t" +
                //rs.getDouble("capacity"));

                domain.Class curClass = new domain.Class(
                                        curId,
                                        curComponent,
                                        curStartTime,
                                        curEndTime,
                                        curMonFlag,
                                        curTuesFlag,
                                        curWedFlag,
                                        curThursFlag,
                                        curFriFlag,
                                        curLocation
//                                        curInstructorName,
//                                        curInstructorRole
                );

                classListForTuple.add(curClass);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


//        /**
//         * @param args the command line arguments
//         */
//        public static void main(String[] args) {
//            SelectFromTableInDB app = new SelectFromTableInDB();
//            app.selectAll();
//        }


}