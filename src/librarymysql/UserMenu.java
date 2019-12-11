package librarymysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu extends JPanel {
    public UserMenu(){
        JButton jButton=new JButton("借书");
        JButton jButton1=new JButton("还书");
        JButton jButton2=new JButton("查看书籍信息");
        JButton jButton3=new JButton("查看借阅信息");
        setLayout(null);
        jButton.setBounds(133,126,200,60);
        jButton1.setBounds(133,312,200,60);
        jButton2.setBounds(466,126,200,60);
        jButton3.setBounds(466,312,200,60);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetBook();
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReturnBook();
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowBook();
            }
        });
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RecordInfo();
            }
        });
        add(jButton);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        JButton jButtonback=new JButton("返回");
        jButtonback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.createMainWin.remove(Main.jLabel);
                CreateMainWin.changeToMainWin();
            }
        });
        jButtonback.setBounds(0,0,100,30);
        add(jButtonback);
        String str="欢迎你，"+Main.Sname+"!";
    }
}
