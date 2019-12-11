package librarymysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerLogin extends JPanel {
    public JLabel jLabel=new JLabel("用户名：");
    public JLabel jLabel1=new JLabel("密码：");
    public JTextField jTextField=new JTextField("1001");
    public JPasswordField jPasswordField=new JPasswordField("1001");
    public JButton jButton=new JButton("登录");
    public ManagerLogin(){
        setLayout(null);
        jLabel.setBounds(290,100,100,30);
        jLabel1.setBounds(290,200,100,30);
        jTextField.setBounds(350,100,200,30);
        jPasswordField.setBounds(350,200,200,30);
        jButton.setBounds(350,300,200,30);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String user=jTextField.getText();
                    String password=new String(jPasswordField.getPassword());
                    String askstr="select Mid,Mname,Mpassword from manager";
                    ResultSet re=Connect.select(askstr);
                    int flag=1;
                    while(re.next()){
                        if(user.equals(re.getString("Mid"))&&password.equals(re.getString("Mpassword"))){
                            flag=0;
                            Main.Mid=re.getString("Mid");
                            Main.Mname=re.getString("Mname");
                            Main.init2();
                            Main.createMainWin.add(Main.jLabel1);
                            CreateMainWin.changeToManagerMenu();
                        }
                    }
                    if(flag==1){
                        JOptionPane.showMessageDialog(null,"用户名或密码错误");
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        add(jLabel);
        add(jLabel1);
        add(jTextField);
        add(jPasswordField);
        add(jButton);
        JButton jButtonback=new JButton("返回");
        jButtonback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMainWin.changeToMainWin();
            }
        });
        jButtonback.setBounds(0,0,100,30);
        add(jButtonback);

    }
}
