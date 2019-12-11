package librarymysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    public JButton jButton1=new JButton("学生登录");
    public JButton jButton2=new JButton("管理员登录");
    public Login(){
        setLayout(null);
        jButton1.setBounds(300,90,200,60);
        jButton2.setBounds(300,250,200,60);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMainWin.changeToUserLogin();
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMainWin.changeToManagerLogin();
            }
        });

        add(jButton1);
        add(jButton2);
    }
}
