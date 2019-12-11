package librarymysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;

public class RecordInfo extends JFrame {
    public RecordInfo(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setTitle("借阅信息");
        setBounds(300,300,560,600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] title={"索引号","书名","出版社","借书日期","归还/应归还日期","当前状态"};
        String[][] val=new String[10000][6];
        int tot=0;
        try{
            ResultSet resultSet=Connect.select("select book.Bid,book.Bname,book.Bpublic,recordbor.Rbdate,recordbor.Rrdate from book,recordbor where book.Bid=recordbor.Bid and recordbor.Sno=\""+Main.Sno+"\"");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("book.Bid");
                val[tot][1]=resultSet.getString("book.Bname");
                val[tot][2]=resultSet.getString("book.Bpublic");
                val[tot][3]=resultSet.getString("recordbor.Rbdate");
                val[tot][4]=resultSet.getString("recordbor.Rrdate");
                val[tot][5]="未归还";
                tot++;
            }
            ResultSet resultSet1=Connect.select("select book.Bid,book.Bname,book.Bpublic,recordret.Rbdate,recordret.Rrdate from book,recordret where book.Bid=recordret.Bid and " +
                    "recordret.Sno=\""+Main.Sno+"\"");
            while(resultSet1.next()){
                val[tot][0]=resultSet1.getString("book.Bid");
                val[tot][1]=resultSet1.getString("book.Bname");
                val[tot][2]=resultSet1.getString("book.Bpublic");
                val[tot][3]=resultSet1.getString("recordret.Rbdate");
                val[tot][4]=resultSet1.getString("recordret.Rrdate");
                val[tot][5]="已归还";
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
        setContentPane(jScrollPane);
    }
}
