package librarymysql;

import java.sql.*;

public class Connect {
    public static Connection connection=null;
    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
            // handle the error
        }
        try {
            connection=
                    DriverManager.getConnection("jdbc:mysql://localhost" +
                            "/library?serverTimezone=UTC","librarymanager",
                            "library");
            System.out.println("连接成功");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("连接失败");
        }
    }

    public static ResultSet select(String str){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //System.out.println(str);
            ps = connection.prepareStatement(str);
            rs = ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public static int update(String str){

        int rs=-1;
        try{
            Statement statement=connection.createStatement();
            //System.out.println(str);
            rs = statement.executeUpdate(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
