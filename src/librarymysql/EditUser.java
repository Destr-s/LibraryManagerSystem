package librarymysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EditUser extends JFrame {
    int tot=0;
    public EditUser(){
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("管理用户");
        setBounds(300,300,525,650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] title={"用户ID","用户姓名","用户密码","用户可借阅书籍数"};
        String[][] val=new String[10000][4];
        DefaultTableModel tableModel=new DefaultTableModel(null,title);
        try{
            ResultSet resultSet=Connect.select("select * from student");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("Sno");
                val[tot][1]=resultSet.getString("Sname");
                val[tot][2]=resultSet.getString("Spassword");
                val[tot][3]=resultSet.getString("Skejie");
                tableModel.addRow(val[tot]);
                tot++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JTable jTable=new JTable(tableModel);
        jTable.setRowSorter(new TableRowSorter<>(tableModel));
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(5,0,500,300);
        JButton jButton=new JButton("移除用户");
        jButton.setBounds(200,300,100,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=jTable.getSelectedRow();
                try{
                    int x=Connect.update("delete from student where Sno=\""+tableModel.getValueAt(row,0)+"\"");
                    if(x==1){
                        tableModel.removeRow(row);
                        JOptionPane.showMessageDialog(null,"移除成功");
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,330,500,270);
        jPanel.setLayout(null);
        JLabel jLabel=new JLabel("用户ID：");
        JLabel jLabel1=new JLabel("用户姓名：");
        JLabel jLabel2=new JLabel("用户密码");
        JLabel jLabel3=new JLabel("可借阅书籍数：");
        JTextField jTextField=new JTextField();
        JTextField jTextField1=new JTextField();
        JTextField jTextField2=new JTextField();
        JTextField jTextField3=new JTextField();
        JButton jButton1=new JButton("添加");
        jLabel.setBounds(40,60,100,30);
        jTextField.setBounds(140,60,100,30);
        jLabel1.setBounds(280,60,100,30);
        jTextField1.setBounds(380,60,100,30);
        jLabel2.setBounds(40,150,100,30);
        jTextField2.setBounds(140,150,100,30);
        jLabel3.setBounds(280,150,100,30);
        jTextField3.setBounds(380,150,100,30);
        jButton1.setBounds(212,240,100,30);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String Sno=jTextField.getText();
                    String Sname=jTextField1.getText();
                    String Spassword=jTextField2.getText();
                    String Skejie=jTextField3.getText();
                    val[tot][0]=Sno;
                    val[tot][1]=Sname;
                    val[tot][2]=Spassword;
                    val[tot][3]=Skejie;
                    ResultSet resultSet=Connect.select("select Sno from student");
                    int flag=0;
                    while(resultSet.next()){
                        if(resultSet.getString("Sno").equals(Sno)){
                            flag=1;
                            JOptionPane.showMessageDialog(null,"该学号已存在");
                            break;
                        }
                    }
                    if(flag==0){
                        int x=Connect.update("insert into Student(Sno,Sname,Spassword,Skejie,Syijie) value(\""+Sno+"\",\""+Sname+"\",\""+Spassword+"\","+Skejie+",0)");
                        if(x==1){
                            tableModel.addRow(val[tot]);
                            tot++;
                            JOptionPane.showMessageDialog(null,"添加成功");
                        }
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jTextField);
        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jButton1);
        add(jScrollPane);
        add(jButton);
        add(jPanel);
    }
}
