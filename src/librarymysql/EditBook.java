package librarymysql;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EditBook extends JFrame {
    int tot=0;
    public EditBook(){
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setTitle("管理书籍");
        setBounds(300,300,525,650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] title={"索引号","书名","出版社","书籍总数"};
        String[][] val=new String[10000][4];
        DefaultTableModel tableModel=new DefaultTableModel(null,title);
        try{
            ResultSet resultSet=Connect.select("select * from book");
            while(resultSet.next()){
                val[tot][0]=resultSet.getString("Bid");
                val[tot][1]=resultSet.getString("Bname");
                val[tot][2]=resultSet.getString("Bpublic");
                val[tot][3]=resultSet.getString("Bnum");
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
        JButton jButton=new JButton("下架");
        jButton.setBounds(200,300,100,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=jTable.getSelectedRow();
                try{
                    int x=Connect.update("delete from book where Bid=\""+tableModel.getValueAt(row,0)+"\"");
                    if(x==1){
                        tableModel.removeRow(row);
                        JOptionPane.showMessageDialog(null,"下架成功");
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,330,500,270);
        jPanel.setLayout(null);
        JLabel jLabel=new JLabel("书籍索引号：");
        JLabel jLabel1=new JLabel("书籍名称：");
        JLabel jLabel2=new JLabel("书籍出版社：");
        JLabel jLabel3=new JLabel("书籍总数：");
        JTextField jTextField=new JTextField();
        JTextField jTextField1=new JTextField();
        JTextField jTextField2=new JTextField();
        JTextField jTextField3=new JTextField();
        JButton jButton1=new JButton("添加书籍");
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
                    String Bid=jTextField.getText();
                    String Bname=jTextField1.getText();
                    String Bpublic=jTextField2.getText();
                    String Bnum=jTextField3.getText();
                    val[tot][0]=Bid;
                    val[tot][1]=Bname;
                    val[tot][2]=Bpublic;
                    val[tot][3]=Bnum;
                    ResultSet resultSet=Connect.select("select Bid from book");
                    int flag=0;
                    while(resultSet.next()){
                        if(resultSet.getString("Bid").equals(Bid)){
                            flag=1;
                            JOptionPane.showMessageDialog(null,"该索引号已存在");
                            break;
                        }
                    }
                    if(flag==0){
                        int x=Connect.update("insert into Book(Bid,Bname,Bpublic,Bnum,Bkejie) value(\""+Bid+"\",\""+Bname+"\",\""+Bpublic+"\","+Bnum+","+Bnum+")");
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
