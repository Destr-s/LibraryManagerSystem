package librarymysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ChangeInfo extends JFrame {
    public ChangeInfo(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("修改图书及用户信息");
        setBounds(300,300,500,550);
        JTabbedPane jTabbedPane=new JTabbedPane();
        JPanel jPanel=new JPanel();
        jPanel.setLayout(null);
        JLabel jLabel=new JLabel("请输入原用户ID：");
        JLabel jLabel1=new JLabel("用户ID：");
        JLabel jLabel2=new JLabel("用户姓名：");
        JLabel jLabel3=new JLabel("用户密码：");
        JLabel jLabel4=new JLabel("用户可借阅书籍数：");
        JTextField jTextField=new JTextField();
        JTextField jTextField1=new JTextField();
        JTextField jTextField2=new JTextField();
        JTextField jTextField3=new JTextField();
        JTextField jTextField4=new JTextField();
        JButton jButton=new JButton("修改");
        jLabel.setBounds(125,45,150,30);
        jLabel1.setBounds(125,120,150,30);
        jLabel2.setBounds(125,195,150,30);
        jLabel3.setBounds(125,270,150,30);
        jLabel4.setBounds(125,345,150,30);
        jTextField.setBounds(275,45,100,30);
        jTextField1.setBounds(275,120,100,30);
        jTextField2.setBounds(275,195,100,30);
        jTextField3.setBounds(275,270,100,30);
        jTextField4.setBounds(275,345,100,30);
        jButton.setBounds(200,420,100,30);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jLabel4);
        jPanel.add(jTextField);
        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jTextField4);
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Sno1=jTextField.getText();
                String Sno2=jTextField1.getText();
                String Sname=jTextField2.getText();
                String Spassword=jTextField3.getText();
                String Skejie=jTextField4.getText();
                if(Sno2.equals("")||Sname.equals("")||Spassword.equals("")||Skejie.equals("")){
                    JOptionPane.showMessageDialog(null,"请输入完整信息");
                }
                else
                try{
                    ResultSet resultSet=Connect.select("select Sno from student");
                    int flag=0;
                    while(resultSet.next()){
                        if(resultSet.getString("Sno").equals(Sno2)){
                            flag=1;
                            JOptionPane.showMessageDialog(null,"该用户ID已存在");
                        }
                    }
                    if(flag==0){
                        //System.out.println("update student set Sno=\""+Sno2+"\",Sname=\""+Sname+"\",Spassword=\""+Spassword+"\",Skejie="+Skejie+" where Sno=\""+Sno1+"\"");
                        int x=Connect.update("update student set Sno=\""+Sno2+"\",Sname=\""+Sname+"\",Spassword=\""+Spassword+"\",Skejie="+Skejie+" where Sno=\""+Sno1+"\"");
                        int x1=Connect.update("update recordbor set Sno=\""+Sno2+"\" where Sno=\""+Sno1+"\"");
                        int x2=Connect.update("update recordret set Sno=\""+Sno2+"\" where Sno=\""+Sno1+"\"");
                        if(x==1){
                            JOptionPane.showMessageDialog(null,"修改成功");
                            jTextField.setText("");
                            jTextField1.setText("");
                            jTextField2.setText("");
                            jTextField3.setText("");
                            jTextField4.setText("");
                        }
                    }

                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        jTabbedPane.addTab("修改用户信息",jPanel);
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(null);
        JLabel jLabel5=new JLabel("请输入图书索引号：");
        JLabel jLabel6=new JLabel("图书索引号：");
        JLabel jLabel7=new JLabel("图书名称：");
        JLabel jLabel8=new JLabel("图书出版社");
        JLabel jLabel9=new JLabel("图书总数：");
        JTextField jTextField5=new JTextField();
        JTextField jTextField6=new JTextField();
        JTextField jTextField7=new JTextField();
        JTextField jTextField8=new JTextField();
        JTextField jTextField9=new JTextField();
        JButton jButton1=new JButton("修改");
        jLabel5.setBounds(125,45,150,30);
        jLabel6.setBounds(125,120,150,30);
        jLabel7.setBounds(125,195,150,30);
        jLabel8.setBounds(125,270,150,30);
        jLabel9.setBounds(125,345,150,30);
        jTextField5.setBounds(275,45,100,30);
        jTextField6.setBounds(275,120,100,30);
        jTextField7.setBounds(275,195,100,30);
        jTextField8.setBounds(275,270,100,30);
        jTextField9.setBounds(275,345,100,30);
        jButton1.setBounds(200,420,100,30);
        jPanel1.add(jLabel5);
        jPanel1.add(jLabel6);
        jPanel1.add(jLabel7);
        jPanel1.add(jLabel8);
        jPanel1.add(jLabel9);
        jPanel1.add(jTextField5);
        jPanel1.add(jTextField6);
        jPanel1.add(jTextField7);
        jPanel1.add(jTextField8);
        jPanel1.add(jTextField9);
        jPanel1.add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Bid1=jTextField5.getText();
                String Bid2=jTextField6.getText();
                String Bname=jTextField7.getText();
                String Bpublic=jTextField8.getText();
                String Bnum=jTextField9.getText();
                if(Bid2.equals("")||Bname.equals("")||Bpublic.equals("")||Bnum.equals("")){
                    JOptionPane.showMessageDialog(null,"请输入完整信息");
                }
                else
                try{
                    ResultSet resultSet=Connect.select("select Bid from book");
                    int flag=0;
                    while(resultSet.next()){
                        if(resultSet.getString("Bid").equals(Bid2)){
                            flag=1;
                            JOptionPane.showMessageDialog(null,"该索引号已存在");
                        }
                    }
                    if(flag==0){
                        int x=Connect.update("update book set Bid=\""+Bid2+"\",Bname=\""+Bname+"\",Bpublic=\""+Bpublic+"\",Bnum="+Bnum+" where Bid=\""+Bid1+"\"");
                        int x1=Connect.update("update recordbor set Bid=\""+Bid2+"\" where Bid=\""+Bid1+"\"");
                        int x2=Connect.update("update recordret set Bid=\""+Bid2+"\" where Bid=\""+Bid1+"\"");
                        if(x==1){
                            JOptionPane.showMessageDialog(null,"修改成功");
                            jTextField5.setText("");
                            jTextField6.setText("");
                            jTextField7.setText("");
                            jTextField8.setText("");
                            jTextField9.setText("");
                        }
                    }

                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        jTabbedPane.addTab("修改图书信息",jPanel1);
        setContentPane(jTabbedPane);
    }
}
