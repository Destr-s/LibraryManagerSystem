package librarymysql;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {

    public static String Sno;
    public static String Sname;
    public static String Mid;
    public static String Mname;
    public static CreateMainWin createMainWin;
    public static JLabel jLabel;
    public static JLabel jLabel1;
    public static void init1(){
        jLabel=new JLabel("欢迎您，"+Sname+"!");
        jLabel.setBounds(680,0,100,30);
    }
    public static void init2(){
        jLabel1=new JLabel("欢迎您，"+Mname+"!");
        jLabel1.setBounds(680,0,100,30);
    }
    public static void main(String []args) {
        Connect.connect();
        createMainWin=new CreateMainWin();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try{
//            ps = connection.prepareStatement(str);
//            rs = ps.executeQuery();
//            while(rs.next()) {
//                String sno = rs.getString("Sno");
//                String name = rs.getString("Sname");
//                int num=rs.getInt("Skejie");
//                System.out.println(sno+" "+name+" "+num);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}