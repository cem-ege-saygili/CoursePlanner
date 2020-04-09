package DB_Utilities;

import domain.ClassInfo;

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

    public static void insertAll(String dbName, String sqlQuery_Insert_Location, List<ClassInfo> classInfoList) {

        ReadFile sqlQueryFile = new ReadFile(sqlQuery_Insert_Location);

        // SQL statement for creating a new table
        String sql = sqlQueryFile.export2String();

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        try (Connection conn = DriverManager.getConnection(url);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for(int k=0;k<classInfoList.size();k++){
                ClassInfo currClassInfo = classInfoList.get(k);
                pstmt.setString(1, currClassInfo.getComponent());
                pstmt.setString(2, currClassInfo.getSubject());
                pstmt.setInt(3, currClassInfo.getCatalog());
                pstmt.setString(4, currClassInfo.getSection());
                pstmt.setInt(5, currClassInfo.getCourseID());
                pstmt.setString(6, currClassInfo.getDescr());
                pstmt.setInt(7, currClassInfo.getTopicID());
                pstmt.setString(8, currClassInfo.getDescr2());
                pstmt.setInt(9, currClassInfo.getTotEnrl());
                pstmt.setInt(10, currClassInfo.getCapEnrl());
                pstmt.setString(11, currClassInfo.getCareer());
                pstmt.setString(12, currClassInfo.getAcadOrg());
                pstmt.setString(13, currClassInfo.getMon());
                pstmt.setString(14, currClassInfo.getTues());
                pstmt.setString(15, currClassInfo.getWed());
                pstmt.setString(16, currClassInfo.getThurs());
                pstmt.setString(17, currClassInfo.getFri());
                pstmt.setString(18, currClassInfo.getSat());
                pstmt.setString(19, currClassInfo.getSun());
                pstmt.setInt(20, currClassInfo.getPatNbr());
                pstmt.setString(21, currClassInfo.getFacilID());
                pstmt.setString(22, currClassInfo.getType());
                pstmt.setInt(23, currClassInfo.getCapacity());
                pstmt.setString(24, currClassInfo.getMtgStart());
                pstmt.setString(25, currClassInfo.getMtgEnd());
                pstmt.setString(26, currClassInfo.getStartDate());
                pstmt.setString(27, currClassInfo.getEndDate());
                pstmt.setString(28, currClassInfo.getPat());
                pstmt.setString(29, currClassInfo.getRole());
                pstmt.setString(30, currClassInfo.getName());
                pstmt.setInt(31, currClassInfo.getTopicID());
                pstmt.setInt(32, currClassInfo.getTerm());
                pstmt.setInt(33, currClassInfo.getClassNbr());
                pstmt.setString(34, currClassInfo.getClassStat());
                pstmt.setInt(35, currClassInfo.getReqRmCap());
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
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