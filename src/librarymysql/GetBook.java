package librarymysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetBook extends JFrame {
    public String change(int a){
        if(a==0){
            return "0";
        }
        String s="";
        while(a>0){
            int x=a%10;
            a/=10;
            s=s+x;
        }
        return s;
    }
    public int getRbid(){
        int Rbid=0;
        ResultSet resultSet=Connect.select("select Rbid from recordbor");
        try{
            while(resultSet.next()){
                Rbid=resultSet.getInt("Rbid");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Rbid;
    }
    public String getTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String time = dateFormat.format( now );
        return time;
    }
    public String changeTime(String str){
        String ans;
        int x=(str.charAt(5)-'0')*10+(str.charAt(6)-'0');
        x+=3;
        if(x>12)x-=12;
        ans=str.substring(0,5)+change(x)+str.substring(7,str.length());
        if(x<10){
            ans=ans.substring(0,5)+"0"+ans.substring(5,ans.length());
        }
        return ans;
    }
    public GetBook(){
        setVisible(true);
        setResizable(false);
        setTitle("借书");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(300,300,300,500);
        JPanel jPanel=new JPanel();
        jPanel.setLayout(null);
        JLabel jLabel=new JLabel("书本记录号：");
        JLabel jLabel1=new JLabel("书本名称：");
        JTextField jTextField=new JTextField();
        JTextField jTextField1=new JTextField();
        JButton jButton=new JButton("确认借书");
        jLabel.setBounds(60,100,80,30);
        jLabel1.setBounds(60,230,80,30);
        jTextField.setBounds(140,100,100,30);
        jTextField1.setBounds(140,230,100,30);
        jButton.setBounds(100,360,100,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int flag=0;
                    String id=jTextField.getText();
                    String name=jTextField1.getText();
                    if(id.equals("")||name.equals("")){
                        JOptionPane.showMessageDialog(null,"请输入完整图书信息");
                        flag=1;
                    }
                    String askstr="select * from book";
                    ResultSet resultSet=Connect.select(askstr);

                    while(resultSet.next()){
                        if(resultSet.getString("Bid").equals(id)&& resultSet.getString("Bname").equals(name)){
                            flag=1;
                            int num=resultSet.getInt("Bkejie");
                            if(num<=0){
                                JOptionPane.showMessageDialog(null, "该图书已全部借出，暂无库存");
                            }
                            else{
                                String askstr2=
                                        "update book set Bkejie="+change(num-1)+" where Bid=\""+resultSet.getString("Bid")+"\";";
                                String askstr3="insert into recordbor(Rbid,Sno,Bid,Rbdate,Rrdate) value("+change(getRbid()+1)+",\""+Main.Sno+"\",\""
                                        +resultSet.getString("Bid")+"\",\""+getTime()+"\",\""+changeTime(getTime())+"\");";
                                System.out.println(askstr2);
                                System.out.println(askstr3);
                                int x1=Connect.update(askstr2);
                                int x2=Connect.update(askstr3);
                                jTextField.setText("");
                                jTextField1.setText("");
                                JOptionPane.showMessageDialog(null,"借书成功");
                            }
                        }
                    }
                    if(flag==0){
                        JOptionPane.showMessageDialog(null,"查无此书籍");
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });

        jPanel.add(jButton);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jTextField);
        jPanel.add(jTextField1);
        setContentPane(jPanel);
    }
}
