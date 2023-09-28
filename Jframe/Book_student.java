/*
 * Created by JFormDesigner on Fri Apr 22 09:55:23 CST 2022
 */

package BookManager.Jframe;

import BookManager.dao.BookDao;
import BookManager.dao.BookTypleDao;
import BookManager.dao.UserDao;
import BookManager.model.Book;
import BookManager.model.BookType;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class Book_student extends JFrame {
    private BookManager.utils.toolUtil toolUtil=new toolUtil();
    private Dbutil dbutil=new Dbutil();
    private UserDao userDao=new UserDao();
    private BookTypleDao bookTypleDao=new BookTypleDao();
    private BookDao bookDao=new BookDao();
    String status;
    String bookid;

    public static void main(String[] args) {
        new Book_student();
    }
    public Book_student() {
        initComponents();
    }

//查询
    private void S_chaxunbt(ActionEvent e) throws Exception {
        String bookname =S_bnametext.getText();
        if(toolUtil.isEmpty(bookname)){
            JOptionPane.showMessageDialog(null,"请输入图书名！");
        }
        Book book=new Book();
        book.setBookname(bookname);
        S_fillTable(book);
        S_bnametext.setText("");
    }

    private void S_fillTable(Book book) throws Exception {
        DefaultTableModel dtm=(DefaultTableModel) S_table.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
            con= dbutil.getConnection();
            ResultSet rs=bookDao.list(con,book);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("bookId"));
                v.add(rs.getString("bookname"));
                v.add(rs.getString("status"));
                dtm.addRow(v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbutil.closeCon(con);
        }
    }

    private void S_jieyuebt(ActionEvent e) throws Exception {
    if(status.equals("未借阅")||status.equals("")) {
        status = "已借阅";
        Book book = new Book(Integer.parseInt(bookid), status);
        Connection con = null;
        con = dbutil.getConnection();
        int i = bookDao.updatebook2(con, book);
        if (i == 1) {
            JOptionPane.showMessageDialog(null, "借阅成功");
            S_fillTable(new Book());

        } else {
            JOptionPane.showMessageDialog(null, "借阅失败");
        }
    }else{
        JOptionPane.showMessageDialog(null,"借阅失败,该书已被借阅");
    }
    }

    private void S_tableMousePressed(MouseEvent e) {
        int row=S_table.getSelectedRow();
        bookid= (String) S_table.getValueAt(row,0);
        status=(String) S_table.getValueAt(row,2);
//        S_table.setValueAt("借阅",row,2);
    }

    private void S_guihuanbt(ActionEvent e) throws Exception {
        if(status.equals("已借阅")){
            status="未借阅";
        Book book=new Book(Integer.parseInt(bookid),status);
        Connection con=null;
        con=dbutil.getConnection();
        int i=bookDao.updatebook2(con,book);
        if(i==1){
            JOptionPane.showMessageDialog(null,"归还成功");
            S_fillTable(new Book());
        }else{
            JOptionPane.showMessageDialog(null,"归还失败");
        }}else{
            JOptionPane.showMessageDialog(null,"归还失败,该书已被归还");
        }
    }
    private void initComponents() {
        this.setVisible(true);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        S_bnametext = new JTextField();
        S_chaxunbt = new JButton();
        scrollPane1 = new JScrollPane();
        S_table = new JTable();
        S_jieyuebt = new JButton();
        S_guihuanbt = new JButton();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) )
            ; panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;

            //---- S_chaxunbt ----
            S_chaxunbt.setText("\u67e5\u8be2");
            S_chaxunbt.addActionListener(e -> {
                try {
                    S_chaxunbt(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            //======== scrollPane1 ========
            {

                //---- S_table ----
                S_table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "\u56fe\u4e66\u7f16\u53f7", "\u4e66\u540d", "\u72b6\u6001"
                    }
                ));
                S_table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        S_tableMousePressed(e);
                    }
                });
                scrollPane1.setViewportView(S_table);
            }

            //---- S_jieyuebt ----
            S_jieyuebt.setText("\u501f\u9605");
            S_jieyuebt.addActionListener(e -> {
                try {
                    S_jieyuebt(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            //---- S_guihuanbt ----
            S_guihuanbt.setText("\u5f52\u8fd8");
            S_guihuanbt.addActionListener(e -> {
                try {
                    S_guihuanbt(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\uff1a");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(S_bnametext, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(S_chaxunbt)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(S_jieyuebt)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(S_guihuanbt))
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
                        .addGap(155, 155, 155))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                            .addComponent(S_bnametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(S_chaxunbt)
                            .addComponent(S_jieyuebt)
                            .addComponent(S_guihuanbt))
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(152, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(212, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JTextField S_bnametext;
    private JButton S_chaxunbt;
    private JScrollPane scrollPane1;
    private JTable S_table;
    private JButton S_jieyuebt;
    private JButton S_guihuanbt;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
