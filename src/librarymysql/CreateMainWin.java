package librarymysql;


import javax.swing.*;
import java.awt.*;

public class CreateMainWin extends JFrame{
    private static Login login=new Login();
    private static UserLogin userLogin=new UserLogin();
    private static ManagerLogin managerLogin=new ManagerLogin();
    private static JPanel jPanel=new JPanel();
    private static CardLayout cardLayout=new CardLayout();
    private static UserMenu userMenu=new UserMenu();
    private static ManagerMenu managerMenu=new ManagerMenu();
    public static void changeToUserLogin(){
        cardLayout.show(jPanel,"userLogin");
    }
    public static void changeToManagerLogin(){
        cardLayout.show(jPanel,"managerLogin");
    }
    public static void changeToUserMenu(){
        cardLayout.show(jPanel,"userMenu");
    }
    public static void changeToManagerMenu(){
        cardLayout.show(jPanel,"managerMenu");
    }
    public static void changeToMainWin(){
        cardLayout.show(jPanel,"login");
    }
    public CreateMainWin(){
        String path=System.getProperty("user.dir");
        //System.out.println(path);
        ImageIcon background=
                new ImageIcon(path+"\\src\\librarymysql\\background.jpg");
        background.setImage(background.getImage().getScaledInstance(790, 480,
                Image.SCALE_DEFAULT ));
        JLabel jLabel=new JLabel();
        getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
        jLabel.setIcon(background);
        jLabel.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        ((JPanel)getContentPane()).setOpaque(false);
        jPanel.setOpaque(false);
        setTitle("图书管理系统");
        setVisible(true);
        jPanel.setLayout(cardLayout);
        setBounds(500,300,780,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setOpaque(false);
        userLogin.setOpaque(false);
        managerLogin.setOpaque(false );
        userMenu.setOpaque(false);
        managerMenu.setOpaque(false);
        jPanel.add("login",login);
        jPanel.add("userLogin",userLogin);
        jPanel.add("managerLogin",managerLogin);
        jPanel.add("userMenu",userMenu);
        jPanel.add("managerMenu",managerMenu);
        jPanel.setBounds(0,0,800,500);
        add(jPanel);
        setLayout(null);
        setResizable(false);

    }
}
