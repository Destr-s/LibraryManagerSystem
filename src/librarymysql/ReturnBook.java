package librarymysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnBook extends JFrame {
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
    public int getRrid(){
        int Rbid=0;
        ResultSet resultSet=Connect.select("select Rrid from recordret");
        try{
            while(resultSet.next()){
                Rbid=resultSet.getInt("Rrid");
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
    public ReturnBook(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setTitle("还书");
        setBounds(300,300,517,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] title={"索引号","书名","出版社","借书日期","归还截止日期"};
        String[][] val=new String[10000][5];
        int tot=0;
        try{
            ResultSet resultSet=Connect.select("select book.Bid,book.Bname,book.Bpublic,recordbor.Rbdate,recordbor.Rrdate from book,recordbor where book.Bid=recordbor.Bid and recordbor.Sno=\""+Main.Sno+"\"");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("book.Bid");
                val[tot][1]=resultSet.getString("book.Bname");
                val[tot][2]=resultSet.getString("book.Bpublic");
                val[tot][3]=resultSet.getString("recordbor.Rbdate");
                val[tot][4]=resultSet.getString("recordbor.Rrdate");
                tot++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DefaultTableModel tableModel=new DefaultTableModel(val,title);
        while(tableModel.getRowCount()>0){
            tableModel.removeRow(0);
        }
        for(int i=0;i<tot;i++){
            tableModel.addRow(val[i]);
        }
        JTable jTable=new JTable(tableModel);
        jTable.setRowSorter(new TableRowSorter<>(tableModel));
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(0,0,500,400);
        JPanel jPanel=new JPanel();
        JButton jButton=new JButton("归还");
        jButton.setBounds(200,35,100,30);
        jPanel.add(jButton);
        jPanel.setBounds(0,400,500,100);
        add(jScrollPane);
        add(jPanel);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=jTable.getSelectedRow();
                try{

                    int x=Connect.update("delete from recordbor where Bid=\""+tableModel.getValueAt(row,0)+"\"");
                    if(x==1) {
                        String ss="insert into recordret(Rrid,Sno,Bid,Rbdate,Rrdate) value(\""+change(getRrid()+1)+"\",\""+Main.Sno+"\",\""
                                +tableModel.getValueAt(row,0)+"\",\""+tableModel.getValueAt(row,3)+"\",\""+getTime()+"\")";
                        System.out.println(ss);
                        int x1=Connect.update(ss);

                        if(x1==1){
                            JOptionPane.showMessageDialog(null,"还书成功");
                        }
                        ResultSet resultSet=Connect.select("select Bkejie from book where Bid=\""+tableModel.getValueAt(row,0)+"\"");
                        while(resultSet.next()){
                            String ss1="update book set Bkejie=\""+change(resultSet.getInt("Bkejie")+1)+"\" where Bid=\""+tableModel.getValueAt(row,0)+"\";";
                            System.out.println(ss1);
                            int x2=Connect.update(ss1);
                        }


                    }
                    tableModel.removeRow(row);
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
    }
}
