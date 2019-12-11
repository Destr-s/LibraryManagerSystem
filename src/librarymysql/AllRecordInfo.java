package librarymysql;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AllRecordInfo extends JFrame {
    int tot=0;
    public AllRecordInfo(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setTitle("查询借阅信息");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(300,300,525,550);
        String[] title={"用户ID","书籍姓名","借阅日期","归还/应归还日期","状态"};
        String[][] val=new String[10000][5];

        DefaultTableModel tableModel=new DefaultTableModel(null,title);
        try{
            ResultSet resultSet=Connect.select("select * from recordbor");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("Sno");
                val[tot][1]=resultSet.getString("Bid");
                val[tot][2]=resultSet.getString("Rbdate");
                val[tot][3]=resultSet.getString("Rrdate");
                val[tot][4]="未归还";
                tableModel.addRow(val[tot]);
                tot++;
            }
            ResultSet resultSet1=Connect.select("select * from recordret");
            while(resultSet1.next()){
                val[tot][0]=resultSet1.getString("Sno");
                val[tot][1]=resultSet1.getString("Bid");
                val[tot][2]=resultSet1.getString("Rbdate");
                val[tot][3]=resultSet1.getString("Rrdate");
                val[tot][4]="已归还";
                tableModel.addRow(val[tot]);
                tot++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JTable jTable=new JTable(tableModel);
        jTable.setRowSorter(new TableRowSorter<>(tableModel));
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(5,0,500,270);
        add(jScrollPane);
        JButton jButton2=new JButton("查看所有");
        jButton2.setBounds(200,270,100,30);
        add(jButton2);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(tableModel.getRowCount()>0){
                    tableModel.removeRow(0);
                }
                tot=0;
                try{
                    ResultSet resultSet=Connect.select("select * from recordbor");
                    while(resultSet.next()){
                        val[tot][0]=resultSet.getString("Sno");
                        val[tot][1]=resultSet.getString("Bid");
                        val[tot][2]=resultSet.getString("Rbdate");
                        val[tot][3]=resultSet.getString("Rrdate");
                        val[tot][4]="未归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                    ResultSet resultSet1=Connect.select("select * from recordret");
                    while(resultSet1.next()){
                        val[tot][0]=resultSet1.getString("Sno");
                        val[tot][1]=resultSet1.getString("Bid");
                        val[tot][2]=resultSet1.getString("Rbdate");
                        val[tot][3]=resultSet1.getString("Rrdate");
                        val[tot][4]="已归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        JPanel jPanel=new JPanel();
        JPanel jPanel1=new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(5,305,250,200);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel1.setLayout(null);
        jPanel1.setBounds(255,305,250,200);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel jLabel=new JLabel("请输入书籍索引号：");
        JLabel jLabel1=new JLabel("请输入学生学号：");
        JTextField jTextField=new JTextField();
        JTextField jTextField1=new JTextField();
        JButton jButton=new JButton("查询");
        JButton jButton1=new JButton("查询");
        jLabel.setBounds(60,40,130,30);
        jTextField.setBounds(60,70,130,30);
        jButton.setBounds(75,140,100,30);
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jButton);
        jLabel1.setBounds(60,40,130,30);
        jTextField1.setBounds(60,70,130,30);
        jButton1.setBounds(75,140,100,30);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jButton1);
        add(jScrollPane);
        add(jPanel);
        add(jPanel1);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(tableModel.getRowCount()>0){
                    tableModel.removeRow(0);
                }
                String str=jTextField.getText();
                try{
                    tot=0;
                    ResultSet resultSet=Connect.select("select * from recordbor where Bid=\""+str+"\"");
                    while (resultSet.next()){
                        val[tot][0]=resultSet.getString("Sno");
                        val[tot][1]=resultSet.getString("Bid");
                        val[tot][2]=resultSet.getString("Rbdate");
                        val[tot][3]=resultSet.getString("Rrdate");
                        val[tot][4]="未归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                    ResultSet resultSet1=Connect.select("select * from recordret where Bid=\""+str+"\"");
                    while (resultSet1.next()){
                        val[tot][0]=resultSet1.getString("Sno");
                        val[tot][1]=resultSet1.getString("Bid");
                        val[tot][2]=resultSet1.getString("Rbdate");
                        val[tot][3]=resultSet1.getString("Rrdate");
                        val[tot][4]="已归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                    jTextField.setText("");
                    JOptionPane.showMessageDialog(null,"查询成功");
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(tableModel.getRowCount()>0){
                    tableModel.removeRow(0);
                }
                String str=jTextField1.getText();
                try{
                    tot=0;
                    ResultSet resultSet=Connect.select("select * from recordbor where Sno=\""+str+"\"");
                    while (resultSet.next()){
                        val[tot][0]=resultSet.getString("Sno");
                        val[tot][1]=resultSet.getString("Bid");
                        val[tot][2]=resultSet.getString("Rbdate");
                        val[tot][3]=resultSet.getString("Rrdate");
                        val[tot][4]="未归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                    ResultSet resultSet1=Connect.select("select * from recordret where Sno=\""+str+"\"");
                    while (resultSet1.next()){
                        val[tot][0]=resultSet1.getString("Sno");
                        val[tot][1]=resultSet1.getString("Bid");
                        val[tot][2]=resultSet1.getString("Rbdate");
                        val[tot][3]=resultSet1.getString("Rrdate");
                        val[tot][4]="已归还";
                        tableModel.addRow(val[tot]);
                        tot++;
                    }
                    jTextField1.setText("");
                    JOptionPane.showMessageDialog(null,"查询成功");
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }

        });
    }
}
