package librarymysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;

public class ShowBook extends JFrame {
    public ShowBook(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setTitle("书籍信息");
        setBounds(300,300,500,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] title={"索引号","书名","出版社","书籍总数","可借数量"};
        String[][] val=new String[10000][5];
        int tot=0;
        try{
            ResultSet resultSet=Connect.select("select * from book");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("Bid");
                val[tot][1]=resultSet.getString("Bname");
                val[tot][2]=resultSet.getString("Bpublic");
                val[tot][3]=resultSet.getString("Bnum");
                val[tot][4]=resultSet.getString("Bkejie");
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
