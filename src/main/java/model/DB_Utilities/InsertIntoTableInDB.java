package model.DB_Utilities;

import model.CoursePlannerBIGGEST_Entry;
import model.tools.ReadFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsertIntoTableInDB {

//    /**
//     * Connect to the test.db database
//     *
//     * @return the Connection object
//     */
//    private Connection connect(String dbName) {
//        // SQLite connection string
//        String url = "JDBC:sqlite:" + dbName + ".db";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }

    /**
     * Insert a new row into the warehouses table
     */

    public static void insertAll(String dbName, String sqlQuery_Insert_Location, List<CoursePlannerBIGGEST_Entry> coursePlannerBIGGESTEntryList) {

        ReadFile sqlQueryFile = new ReadFile(sqlQuery_Insert_Location);

        // SQL statement for creating a new table
        String sql = sqlQueryFile.export2String();

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        try (Connection conn = DriverManager.getConnection(url);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for(int k = 0; k< coursePlannerBIGGESTEntryList.size(); k++){
                CoursePlannerBIGGEST_Entry currCoursePlannerBIGGESTEntry = coursePlannerBIGGESTEntryList.get(k);
                pstmt.setString(1, currCoursePlannerBIGGESTEntry.getComponent());
                pstmt.setString(2, currCoursePlannerBIGGESTEntry.getSubject());
                pstmt.setInt(3, currCoursePlannerBIGGESTEntry.getCatalog());
                pstmt.setString(4, currCoursePlannerBIGGESTEntry.getSection());
                pstmt.setInt(5, currCoursePlannerBIGGESTEntry.getCourseID());
                pstmt.setString(6, currCoursePlannerBIGGESTEntry.getDescr());
                pstmt.setInt(7, currCoursePlannerBIGGESTEntry.getTopicID());
                pstmt.setString(8, currCoursePlannerBIGGESTEntry.getDescr2());
                pstmt.setInt(9, currCoursePlannerBIGGESTEntry.getTotEnrl());
                pstmt.setInt(10, currCoursePlannerBIGGESTEntry.getCapEnrl());
                pstmt.setString(11, currCoursePlannerBIGGESTEntry.getCareer());
                pstmt.setString(12, currCoursePlannerBIGGESTEntry.getAcadOrg());
                pstmt.setString(13, currCoursePlannerBIGGESTEntry.getMon());
                pstmt.setString(14, currCoursePlannerBIGGESTEntry.getTues());
                pstmt.setString(15, currCoursePlannerBIGGESTEntry.getWed());
                pstmt.setString(16, currCoursePlannerBIGGESTEntry.getThurs());
                pstmt.setString(17, currCoursePlannerBIGGESTEntry.getFri());
                pstmt.setString(18, currCoursePlannerBIGGESTEntry.getSat());
                pstmt.setString(19, currCoursePlannerBIGGESTEntry.getSun());
                pstmt.setInt(20, currCoursePlannerBIGGESTEntry.getPatNbr());
                pstmt.setString(21, currCoursePlannerBIGGESTEntry.getFacilID());
                pstmt.setString(22, currCoursePlannerBIGGESTEntry.getType());
                pstmt.setInt(23, currCoursePlannerBIGGESTEntry.getCapacity());
                pstmt.setString(24, currCoursePlannerBIGGESTEntry.getMtgStart());
                pstmt.setString(25, currCoursePlannerBIGGESTEntry.getMtgEnd());
                pstmt.setString(26, currCoursePlannerBIGGESTEntry.getStartDate());
                pstmt.setString(27, currCoursePlannerBIGGESTEntry.getEndDate());
                pstmt.setString(28, currCoursePlannerBIGGESTEntry.getPat());
                pstmt.setString(29, currCoursePlannerBIGGESTEntry.getRole());
                pstmt.setString(30, currCoursePlannerBIGGESTEntry.getName());
                pstmt.setInt(31, currCoursePlannerBIGGESTEntry.getTopicID());
                pstmt.setInt(32, currCoursePlannerBIGGESTEntry.getTerm());
                pstmt.setInt(33, currCoursePlannerBIGGESTEntry.getClassNbr());
                pstmt.setString(34, currCoursePlannerBIGGESTEntry.getClassStat());
                pstmt.setInt(35, currCoursePlannerBIGGESTEntry.getReqRmCap());
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ExecQueriesFromList(String dbName, List<String> sql_InsertQuery2Execute_LocationList) {

        for(String curQueryLocation : sql_InsertQuery2Execute_LocationList){
            ExecQuery(dbName, curQueryLocation);
        }


    }

    public static void ExecQuery(String dbName,
                                 String sqlQueryLocation){
        ReadFile sqlQueryFile = new ReadFile(sqlQueryLocation);

        // SQL statement for creating a new table
        String sql = sqlQueryFile.export2String();

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        try (Connection conn = DriverManager.getConnection(url);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();

        } catch (SQLException e) {
            String asd = e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//
//        InsertIntoTableInDB app = new InsertIntoTableInDB();
//        // insert three new rows
//        app.insert("Raw Materials", 3000);
//        app.insert("Semifinished Goods", 4000);
//        app.insert("Finished Goods", 5000);
//    }

}