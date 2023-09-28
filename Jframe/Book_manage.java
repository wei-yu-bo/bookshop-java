/*
 * Created by JFormDesigner on Tue Apr 12 08:09:22 CST 2022
 */

package BookManager.Jframe;

import javax.swing.border.*;
import javax.swing.table.*;

import BookManager.dao.BookDao;
import BookManager.dao.BookTypleDao;
import BookManager.dao.UserDao;
import BookManager.model.Book;
import BookManager.model.BookType;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Book_manage {
    private toolUtil toolUtil=new toolUtil();
    private Dbutil dbutil=new Dbutil();
    private UserDao userDao=new UserDao();
    private BookTypleDao bookTypleDao=new BookTypleDao();
    private BookDao bookDao=new BookDao();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   new Book_manage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
/*
构造方法，运行此处
 */
    public Book_manage() throws Exception {
        initComponents();
    }
/*
设置菜单栏事件
 */
//    显示添加类别界面
    private void addTypemenu(ActionEvent e) {
        Rmenu();
        addTypepan.setVisible(true);
    }
//     显示添加图书界面
    private void addbookmenu(ActionEvent e) {
        Rmenu();
        addbookpan.setVisible(true);
    }
//      显示类别维护界面
    private void mtType(ActionEvent e) throws Exception {
        Rmenu();
        mtTyplepan.setVisible(true);
        fillTable(new BookType());
    }
//      显示图书维护界面
    private void mtbookmenu(ActionEvent e) throws Exception {
        Rmenu();Rbook2();
        mtbookpan.setVisible(true);
        fillbooTable(new Book());
    }
//    显示界面重置
    private void Rmenu(){
        addTypepan.setVisible(false);
        addbookpan.setVisible(false);
        mtTyplepan.setVisible(false);
        mtbookpan.setVisible(false);
    }
    /*
    图书类别添加事件
     */
    private void addTyple(ActionEvent e) throws Exception {
        String bookTypeName=bookTypletxt.getText();
        String bookremar=bookremarktxt.getText();
        if(toolUtil.isEmpty(bookTypeName)){
            JOptionPane.showMessageDialog(null,"图书类别不能为空！");
            return;
        }
        BookType bookType=new BookType(bookTypeName,bookremar);
        Connection con=null;

        try {con=dbutil.getConnection();
            int n=bookTypleDao.addbooktyple(con,bookType);
            if(n==1){
                JOptionPane.showMessageDialog(null,"图书类别添加成功!");
                Rbut_Typle(e);
            }else{
                JOptionPane.showMessageDialog(null,"图书类别添加失败！");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null,"图书类别添加失败！");
        }finally {
            dbutil.closeCon(con);
        }
    }
    //图书类别添加重置Typle事件
    private void Rbut_Typle(ActionEvent e) {
        bookremarktxt.setText("");
        bookTypletxt.setText("");
    }
/*
   图书类别维护事件
 */
//    导入数据库表单
        private void fillTable(BookType bookType) throws Exception {
        DefaultTableModel dtm=(DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
           con= dbutil.getConnection();
            ResultSet rs=bookTypleDao.list(con,bookType);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("id"));
                    v.add(rs.getString("bookTypeName"));
                    v.add(rs.getString("remark"));
                    dtm.addRow(v);
                }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbutil.closeCon(con);
        }
        }

//        类别查询功能
private void s_bookTypebut(ActionEvent e) throws Exception {
    String s_TypeName=s_bookTypeNametxt.getText();
    if(toolUtil.isEmpty(s_TypeName)){
        JOptionPane.showMessageDialog(null,"请输入图书类别！");
    }
    BookType bookType=new BookType();
    bookType.setTypeName(s_TypeName);
    fillTable(bookType);
    s_bookTypeNametxt.setText("");
}

//表格事件处理
    private void bookTypleTAbleMousePressed(MouseEvent e) {
        int row=table1.getSelectedRow();
        T_idtxt.setText((String) table1.getValueAt(row,0));
        T_nametxt.setText((String) table1.getValueAt(row,1));
        T_remarktxt.setText((String) table1.getValueAt(row,2));
    }
/*
图书类别修改事件
 */
    private void Bt_update(ActionEvent e) throws Exception {
        Typeupdate();
    }
    /*
    图书类别修改事件处理
     */
    private void Typeupdate() throws Exception {
        String id=T_idtxt.getText();
        String bookTypename=T_nametxt.getText();
        String remark=T_remarktxt.getText();
        if(toolUtil.isEmpty(T_idtxt.getText())){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        BookType bookType=new BookType(Integer.parseInt(id),bookTypename,remark);
        Connection con=null;
        con=dbutil.getConnection();
        int modifyNum=bookTypleDao.update(con,bookType);
        if(modifyNum==1){
            JOptionPane.showMessageDialog(null,"修改成功");
            reseValue();
            fillTable(new BookType());
        }else {
            JOptionPane.showMessageDialog(null,"修改失败");
        }
    }
    //重置表单方法(类别维护)
    private void reseValue(){
        T_idtxt.setText("");
        T_remarktxt.setText("");
        T_nametxt.setText("");
    }

    private void button4(ActionEvent e) throws Exception {
        T_delateEvent();
    }
/*
    删除事件处理
 */
    private void T_delateEvent() throws Exception {
        String id=T_idtxt.getText();
        String bookTypename=T_nametxt.getText();
        String remark=T_remarktxt.getText();
        if(toolUtil.isEmpty(T_idtxt.getText())){
            JOptionPane.showMessageDialog(null,"请选择要删除的记录");
            return;
        }
        BookType bookType=new BookType(Integer.parseInt(id),bookTypename,remark);
        Connection con=null;
        con=dbutil.getConnection();
        int a= JOptionPane.showConfirmDialog(null,"是否删除该信息？");
        if(a==0){
            int delateNum=bookTypleDao.delete(con,id);
        if(delateNum==1){
            JOptionPane.showMessageDialog(null,"信息删除成功");
            reseValue();
            fillTable(new BookType());
        }else {
            JOptionPane.showMessageDialog(null,"信息删除失败");
        }
        }
    }
    /*
    图书管理相关方法
     */
            /*
                   （图书添加）添加按钮事件
             */
    private void button1(ActionEvent e) throws Exception {
        addbookEvent();
    }
//    添加按钮事件处理
    private void addbookEvent() throws Exception {
        String bid=B_idtxt.getText();
        String bname=B_nametxt.getText();
        String bpublish=B_publishtxt.getText();
        String bauther=B_authertxt.getText();
        String bnumber=B_numbertxt.getText();
        String btype=B_typetxt.getText();
        String bremark=B_remarktxt.getText();
        String bstatus=b_statustxt.getText();
    if(toolUtil.isEmpty(bid)||toolUtil.isEmpty(bname)){
        JOptionPane.showMessageDialog(null,"请输入编号或书名！");
        return;
    }
    Book book=new Book(Integer.parseInt(bid),bname,bpublish,bauther,Integer.parseInt(bnumber),btype,bremark,bstatus);
    Connection con=null;
    con=dbutil.getConnection();
    int i=bookDao.addbook(con,book);
    try {
        if (i == 1) {
            JOptionPane.showMessageDialog(null, "添加成功！");
            Rbook();
        } else {
            JOptionPane.showMessageDialog(null, "添加失败！");
        }
    }
    finally {
            dbutil.closeCon(con);
        }
    }
//    重置按钮（图书添加）
    private void button2(ActionEvent e) {
        Rbook();
    }

    //重置事件处理（图书添加）
    private void Rbook() {
        B_idtxt.setText("");
        B_nametxt.setText("");
        B_publishtxt.setText("");
        B_authertxt.setText("");
        B_numbertxt.setText("");
        B_typetxt.setText("");
        B_remarktxt.setText("");
    }

    /*
    MySQL数据导入table2（图书维护）
     */
    private void fillbooTable(Book book) throws Exception {
        DefaultTableModel dtm=(DefaultTableModel) table2.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
            con= dbutil.getConnection();
            try (ResultSet rs = bookDao.list(con, book)) {
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("bookId"));
                    v.add(rs.getString("bookname"));
                    v.add(rs.getString("publish"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("number"));
                    v.add(rs.getString("booktype"));
                    v.add(rs.getString("remark"));
                    dtm.addRow(v);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbutil.closeCon(con);
        }
    }
    //     查询按钮（图书维护）
    private void B_searchbut(ActionEvent e) throws Exception {
        String s_bookName=B_searchtxt.getText();
        if(toolUtil.isEmpty(s_bookName)){
            JOptionPane.showMessageDialog(null,"请输入图书类别！");
        }
        Book book=new Book();
        book.setBookname(s_bookName);
        fillbooTable(book);
        B_searchtxt.setText("");
    }
    /*
         表单从table2获取值（图书维护，table2事件）
     */
    private void table2MousePressed(MouseEvent e) {
        int row=table2.getSelectedRow();
        b_idtxt.setText((String) table2.getValueAt(row,0));
        b_nametxt.setText((String) table2.getValueAt(row,1));
        b_putxt.setText((String) table2.getValueAt(row,2));
        b_autxt.setText((String) table2.getValueAt(row,3));
        b_numtxt.setText((String) table2.getValueAt(row,4));
        b_typetxt.setText((String) table2.getValueAt(row,5));
        b_remarktxt.setText((String) table2.getValueAt(row,6));
    }

    /*
修改图书（图书维护）
 */
    private void b_updatebt(ActionEvent e) throws Exception {
        String bid=b_idtxt.getText();
        String bname=b_nametxt.getText();
        String bpublish= b_putxt.getText();
        String bauther=b_autxt.getText();
        String bnumber=b_numtxt.getText();
        String btype=b_typetxt.getText();
        String bremark=b_remarktxt.getText();
        String bstatus=b_statustxt.getText();
        Connection con;
        con=dbutil.getConnection();
        Book book = new Book(Integer.parseInt(bid),bname,bpublish,bauther,Integer.parseInt(bnumber),btype,bremark,bstatus);
        int upnum=bookDao.updatebook(con,book);
        if(upnum==1){
            JOptionPane.showMessageDialog(null,"修改成功！");
            fillbooTable(new Book());
            Rbook2();
        }
        else {
            JOptionPane.showMessageDialog(null,"修改失败！");
        }
    }

    //删除（图书维护）
    private void b_deletebt(ActionEvent e) throws Exception {
        String bid=b_idtxt.getText();
        String bname=b_nametxt.getText();
        String bpublish= b_putxt.getText();
        String bauther=b_autxt.getText();
        String bnumber=b_numtxt.getText();
        String btype=b_typetxt.getText();
        String bremark=b_remarktxt.getText();
String bstatus=b_statustxt.getText();
        Connection con;
        con=dbutil.getConnection();
        Book book = new Book(Integer.parseInt(bid),bname,bpublish,bauther,Integer.parseInt(bnumber),btype,bremark,bstatus);
        int deletenum=bookDao.delete(con,bid);
        if(deletenum==1){
            JOptionPane.showMessageDialog(null,"删除成功！");
            Rbook2();
            fillbooTable(new Book());
        }else {
            JOptionPane.showMessageDialog(null,"删除失败！");
        }

    }
    //重置事件（图书维护）
    private void b_Rbookbt(ActionEvent e) {
        Rbook2();
    }

    //重置事件处理（图书维护）
    private void Rbook2() {
        b_idtxt.setText("");
        b_nametxt.setText("");
        b_putxt.setText("");
        b_autxt.setText("");
        b_numtxt.setText("");
        b_typetxt.setText("");
        b_remarktxt.setText("");
    }

    /*
用户系统关闭
*/
    private void exitmenu(ActionEvent e) {
        tudhu.dispose();
    }

    private void table1MousePressed(MouseEvent e) {
        bookTypleTAbleMousePressed(e);
    }






   

    /*
            组件
     */
    private void initComponents() throws Exception{
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        tudhu = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        addTypemenu = new JMenuItem();
        mtTypemenu = new JMenuItem();
        menu2 = new JMenu();
        addbookmenu = new JMenuItem();
        mtbookmenu = new JMenuItem();
        exitmenu = new JMenuItem();
        desktopPane1 = new JDesktopPane();
        addbookpan = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        B_nametxt = new JTextField();
        B_publishtxt = new JTextField();
        B_numbertxt = new JTextField();
        B_remarktxt = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        B_authertxt = new JTextField();
        B_idtxt = new JTextField();
        B_typetxt = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        addTypepan = new JPanel();
        label9 = new JLabel();
        label10 = new JLabel();
        bookTypletxt = new JTextField();
        scrollPane1 = new JScrollPane();
        bookremarktxt = new JTextArea();
        addTyplebut = new JButton();
        Rbut_Typle = new JButton();
        label12 = new JLabel();
        mtTyplepan = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        label11 = new JLabel();
        s_bookTypeNametxt = new JTextField();
        s_bookTypebut = new JButton();
        panel1 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        T_idtxt = new JTextField();
        label15 = new JLabel();
        T_nametxt = new JTextField();
        scrollPane3 = new JScrollPane();
        T_remarktxt = new JTextArea();
        Bt_update = new JButton();
        button4 = new JButton();
        mtbookpan = new JPanel();
        label16 = new JLabel();
        B_searchtxt = new JTextField();
        B_searchbut = new JButton();
        scrollPane4 = new JScrollPane();
        table2 = new JTable();
        panel3 = new JPanel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        b_idtxt = new JTextField();
        b_putxt = new JTextField();
        b_numtxt = new JTextField();
        label20 = new JLabel();
        label21 = new JLabel();
        label22 = new JLabel();
        b_nametxt = new JTextField();
        b_autxt = new JTextField();
        b_typetxt = new JTextField();
        label23 = new JLabel();
        scrollPane5 = new JScrollPane();
        b_remarktxt = new JTextArea();
        b_updatebt = new JButton();
        b_deletebt = new JButton();
        b_Rbookbt = new JButton();
        label24 = new JLabel();
        b_statustxt = new JTextField();

        //======== tudhu ========
        {
            tudhu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tudhu.setName("frame1");
            tudhu.setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
            tudhu.setForeground(SystemColor.windowText);
            tudhu.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
            tudhu.setVisible(true);
            var tudhuContentPane = tudhu.getContentPane();
            tudhuContentPane.setLayout(new BorderLayout());

            //======== menuBar1 ========
            {
                menuBar1.setMargin(new Insets(2, 2, 2, 2));
                menuBar1.setMaximumSize(new Dimension(70, 32768));

                //======== menu1 ========
                {
                    menu1.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
                    menu1.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
                    menu1.setForeground(Color.blue);

                    //---- addTypemenu ----
                    addTypemenu.setText("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
                    addTypemenu.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                    addTypemenu.addActionListener(e -> addTypemenu(e));
                    menu1.add(addTypemenu);

                    //---- mtTypemenu ----
                    mtTypemenu.setText("\u56fe\u4e66\u7c7b\u522b\u7ef4\u62a4");
                    mtTypemenu.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                    mtTypemenu.addActionListener(e -> {
                        try {
                            mtType(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    menu1.add(mtTypemenu);
                }
                menuBar1.add(menu1);

                //======== menu2 ========
                {
                    menu2.setText("\u4e66\u7c4d\u7ba1\u7406");
                    menu2.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
                    menu2.setForeground(Color.blue);

                    //---- addbookmenu ----
                    addbookmenu.setText("\u4e66\u7c4d\u6dfb\u52a0");
                    addbookmenu.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                    addbookmenu.addActionListener(e -> addbookmenu(e));
                    menu2.add(addbookmenu);

                    //---- mtbookmenu ----
                    mtbookmenu.setText("\u4e66\u7c4d\u7ef4\u62a4");
                    mtbookmenu.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                    mtbookmenu.addActionListener(e -> {
                        try {
                            mtbookmenu(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    menu2.add(mtbookmenu);
                }
                menuBar1.add(menu2);

                //---- exitmenu ----
                exitmenu.setText("\u9000\u51fa\u7cfb\u7edf");
                exitmenu.setHorizontalAlignment(SwingConstants.CENTER);
                exitmenu.setHorizontalTextPosition(SwingConstants.CENTER);
                exitmenu.setMaximumSize(new Dimension(79, 32767));
                exitmenu.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
                exitmenu.setForeground(Color.blue);
                exitmenu.addActionListener(e -> exitmenu(e));
                menuBar1.add(exitmenu);
            }
            tudhu.setJMenuBar(menuBar1);

            //======== desktopPane1 ========
            {
                desktopPane1.setForeground(Color.black);
                desktopPane1.setBackground(SystemColor.menu);

                //======== addbookpan ========
                {
                    addbookpan.setVisible(false);
                    addbookpan.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                    .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
                    .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
                    Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
                    ),addbookpan. getBorder()));addbookpan. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
                    public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
                    )))throw new RuntimeException();}});

                    //---- label1 ----
                    label1.setText("\u56fe\u4e66\u6dfb\u52a0");
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    label1.setHorizontalTextPosition(SwingConstants.CENTER);
                    label1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));

                    //---- label2 ----
                    label2.setText("\u4e66\u540d\uff1a");
                    label2.setHorizontalTextPosition(SwingConstants.CENTER);
                    label2.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label3 ----
                    label3.setText("\u51fa\u7248\u793e\uff1a");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    label3.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- label4 ----
                    label4.setText("\u5e93\u5b58\uff1a");
                    label4.setHorizontalAlignment(SwingConstants.CENTER);
                    label4.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- label5 ----
                    label5.setText("\u63cf\u8ff0\uff1a");
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    label5.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- label6 ----
                    label6.setText("\u4f5c\u8005\uff1a");
                    label6.setHorizontalAlignment(SwingConstants.CENTER);
                    label6.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- label7 ----
                    label7.setText("\u7f16\u53f7\uff1a");
                    label7.setHorizontalAlignment(SwingConstants.CENTER);
                    label7.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- label8 ----
                    label8.setText("\u7c7b\u522b\uff1a");
                    label8.setHorizontalAlignment(SwingConstants.CENTER);
                    label8.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- button1 ----
                    button1.setText("\u6dfb\u52a0");
                    button1.addActionListener(e -> {
                        try {
                            button1(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    //---- button2 ----
                    button2.setText("\u91cd\u7f6e");
                    button2.addActionListener(e -> button2(e));

                    GroupLayout addbookpanLayout = new GroupLayout(addbookpan);
                    addbookpan.setLayout(addbookpanLayout);
                    addbookpanLayout.setHorizontalGroup(
                        addbookpanLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, addbookpanLayout.createSequentialGroup()
                                .addGap(197, 398, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                .addGap(378, 378, 378))
                            .addGroup(addbookpanLayout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(addbookpanLayout.createParallelGroup()
                                        .addGroup(addbookpanLayout.createSequentialGroup()
                                            .addComponent(label3)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(B_publishtxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addbookpanLayout.createSequentialGroup()
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(B_numbertxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(addbookpanLayout.createSequentialGroup()
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B_idtxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addGroup(addbookpanLayout.createParallelGroup()
                                    .addGroup(addbookpanLayout.createSequentialGroup()
                                        .addGroup(addbookpanLayout.createParallelGroup()
                                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(addbookpanLayout.createParallelGroup()
                                            .addComponent(B_typetxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(B_authertxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(addbookpanLayout.createSequentialGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B_nametxt, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(236, Short.MAX_VALUE))
                            .addGroup(addbookpanLayout.createSequentialGroup()
                                .addGroup(addbookpanLayout.createParallelGroup()
                                    .addGroup(addbookpanLayout.createSequentialGroup()
                                        .addGap(306, 306, 306)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addbookpanLayout.createSequentialGroup()
                                        .addGap(167, 167, 167)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(B_remarktxt, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(282, Short.MAX_VALUE))
                    );
                    addbookpanLayout.setVerticalGroup(
                        addbookpanLayout.createParallelGroup()
                            .addGroup(addbookpanLayout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label7)
                                    .addComponent(B_idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B_nametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(B_authertxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label6)
                                    .addComponent(B_publishtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(44, 44, 44)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(B_numbertxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label8)
                                    .addComponent(B_typetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(B_remarktxt, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addbookpanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(190, Short.MAX_VALUE))
                    );
                }
                desktopPane1.add(addbookpan, JLayeredPane.DEFAULT_LAYER);
                addbookpan.setBounds(30, 105, 915, 605);

                //======== addTypepan ========
                {
                    addTypepan.setVisible(false);

                    //---- label9 ----
                    label9.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");

                    //---- label10 ----
                    label10.setText("\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0\uff1a");

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(bookremarktxt);
                    }

                    //---- addTyplebut ----
                    addTyplebut.setText("\u6dfb\u52a0");
                    addTyplebut.addActionListener(e -> {
                        try {
                            addTyple(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    //---- Rbut_Typle ----
                    Rbut_Typle.setText("\u91cd\u7f6e");
                    Rbut_Typle.addActionListener(e -> Rbut_Typle(e));

                    //---- label12 ----
                    label12.setText("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    label12.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));

                    GroupLayout addTypepanLayout = new GroupLayout(addTypepan);
                    addTypepan.setLayout(addTypepanLayout);
                    addTypepanLayout.setHorizontalGroup(
                        addTypepanLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, addTypepanLayout.createSequentialGroup()
                                .addGap(0, 356, Short.MAX_VALUE)
                                .addGroup(addTypepanLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label10, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addTypepanLayout.createParallelGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookTypletxt, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
                                .addGap(288, 288, 288))
                            .addGroup(GroupLayout.Alignment.TRAILING, addTypepanLayout.createSequentialGroup()
                                .addContainerGap(380, Short.MAX_VALUE)
                                .addGroup(addTypepanLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, addTypepanLayout.createSequentialGroup()
                                        .addComponent(addTyplebut, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(126, 126, 126)
                                        .addComponent(Rbut_Typle, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                        .addGap(273, 273, 273))
                                    .addGroup(GroupLayout.Alignment.TRAILING, addTypepanLayout.createSequentialGroup()
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                        .addGap(417, 417, 417))))
                    );
                    addTypepanLayout.setVerticalGroup(
                        addTypepanLayout.createParallelGroup()
                            .addGroup(addTypepanLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(label12, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(addTypepanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookTypletxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(addTypepanLayout.createParallelGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label10, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(addTypepanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(Rbut_Typle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addTyplebut, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(57, Short.MAX_VALUE))
                    );
                }
                desktopPane1.add(addTypepan, JLayeredPane.DEFAULT_LAYER);
                addTypepan.setBounds(-35, -10, 980, 525);

                //======== mtTyplepan ========
                {
                    mtTyplepan.setVisible(false);

                    //======== scrollPane2 ========
                    {

                        //---- table1 ----
                        table1.setModel(new DefaultTableModel(
                            new Object[][] {
                                {null, null, null},
                                {null, null, null},
                            },
                            new String[] {
                                "\u7f16\u53f7", "\u56fe\u4e66\u7c7b\u522b\u540d\u79f0", "\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0"
                            }
                        ) {
                            boolean[] columnEditable = new boolean[] {
                                false, false, false
                            };
                            @Override
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return columnEditable[columnIndex];
                            }
                        });
                        {
                            TableColumnModel cm = table1.getColumnModel();
                            cm.getColumn(0).setPreferredWidth(100);
                            cm.getColumn(1).setPreferredWidth(150);
                            cm.getColumn(2).setPreferredWidth(230);
                        }
                        table1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                table1MousePressed(e);
                            }
                        });
                        scrollPane2.setViewportView(table1);
                    }

                    //---- label11 ----
                    label11.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");
                    label11.setHorizontalAlignment(SwingConstants.CENTER);
                    label11.setHorizontalTextPosition(SwingConstants.CENTER);

                    //---- s_bookTypebut ----
                    s_bookTypebut.setText("\u67e5\u8be2");
                    s_bookTypebut.addActionListener(e -> {
                        try {
                            s_bookTypebut(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    //======== panel1 ========
                    {
                        panel1.setBorder(new TitledBorder("\u56fe\u4e66\u7c7b\u522b\u7684\u4fee\u6539"));

                        //---- label13 ----
                        label13.setText("\u7f16\u53f7\uff1a");
                        label13.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- label14 ----
                        label14.setText("\u63cf\u8ff0\uff1a");
                        label14.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- T_idtxt ----
                        T_idtxt.setEnabled(false);

                        //---- label15 ----
                        label15.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");
                        label15.setHorizontalAlignment(SwingConstants.CENTER);

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(T_remarktxt);
                        }

                        //---- Bt_update ----
                        Bt_update.setText("\u4fee\u6539");
                        Bt_update.addActionListener(e -> {
                            try {
                                Bt_update(e);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });

                        //---- button4 ----
                        button4.setText("\u5220\u9664");
                        button4.addActionListener(e -> {
                            try {
                                button4(e);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });

                        GroupLayout panel1Layout = new GroupLayout(panel1);
                        panel1.setLayout(panel1Layout);
                        panel1Layout.setHorizontalGroup(
                            panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(T_idtxt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(label15, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(T_nametxt, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
                                            .addGap(43, 43, 43)
                                            .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(Bt_update)
                                                .addComponent(button4))))
                                    .addContainerGap(35, Short.MAX_VALUE))
                        );
                        panel1Layout.setVerticalGroup(
                            panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(T_nametxt, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(T_idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label15, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                    .addGap(28, 28, 28)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label14, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(Bt_update, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(40, Short.MAX_VALUE))
                        );
                    }

                    GroupLayout mtTyplepanLayout = new GroupLayout(mtTyplepan);
                    mtTyplepan.setLayout(mtTyplepanLayout);
                    mtTyplepanLayout.setHorizontalGroup(
                        mtTyplepanLayout.createParallelGroup()
                            .addGroup(mtTyplepanLayout.createSequentialGroup()
                                .addGap(276, 276, 276)
                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s_bookTypeNametxt, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(s_bookTypebut, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(276, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, mtTyplepanLayout.createSequentialGroup()
                                .addContainerGap(205, Short.MAX_VALUE)
                                .addGroup(mtTyplepanLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, mtTyplepanLayout.createSequentialGroup()
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
                                        .addGap(216, 216, 216))
                                    .addGroup(GroupLayout.Alignment.TRAILING, mtTyplepanLayout.createSequentialGroup()
                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(188, 188, 188))))
                    );
                    mtTyplepanLayout.setVerticalGroup(
                        mtTyplepanLayout.createParallelGroup()
                            .addGroup(mtTyplepanLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(mtTyplepanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label11, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s_bookTypeNametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s_bookTypebut, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                    );
                }
                desktopPane1.add(mtTyplepan, JLayeredPane.DEFAULT_LAYER);
                mtTyplepan.setBounds(15, 15, 1000, 645);

                //======== mtbookpan ========
                {
                    mtbookpan.setVisible(false);

                    //---- label16 ----
                    label16.setText("\u4e66\u540d\uff1a");

                    //---- B_searchbut ----
                    B_searchbut.setText("\u67e5\u8be2");
                    B_searchbut.addActionListener(e -> {
                        try {
                            B_searchbut(e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    //======== scrollPane4 ========
                    {

                        //---- table2 ----
                        table2.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "\u7f16\u53f7", "\u4e66\u540d", "\u51fa\u7248\u793e", "\u4f5c\u8005", "\u5e93\u5b58", "\u7c7b\u522b", "\u63cf\u8ff0"
                            }
                        ) {
                            boolean[] columnEditable = new boolean[] {
                                false, false, false, false, false, false, false
                            };
                            @Override
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return columnEditable[columnIndex];
                            }
                        });
                        table2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                table2MousePressed(e);
                            }
                        });
                        scrollPane4.setViewportView(table2);
                    }

                    //======== panel3 ========
                    {

                        //---- label17 ----
                        label17.setText("\u7f16\u53f7:");

                        //---- label18 ----
                        label18.setText("\u51fa\u7248\u793e\uff1a");

                        //---- label19 ----
                        label19.setText("\u5e93\u5b58\uff1a");

                        //---- label20 ----
                        label20.setText("\u4e66\u540d\uff1a");

                        //---- label21 ----
                        label21.setText("\u4f5c\u8005\uff1a");

                        //---- label22 ----
                        label22.setText("\u7c7b\u522b\uff1a");

                        //---- label23 ----
                        label23.setText("\u63cf\u8ff0\uff1a");

                        //======== scrollPane5 ========
                        {
                            scrollPane5.setViewportView(b_remarktxt);
                        }

                        //---- b_updatebt ----
                        b_updatebt.setText("\u4fee\u6539");
                        b_updatebt.addActionListener(e -> {
                            try {
                                b_updatebt(e);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });

                        //---- b_deletebt ----
                        b_deletebt.setText("\u5220\u9664");
                        b_deletebt.addActionListener(e -> {
                            try {
                                b_deletebt(e);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });

                        //---- b_Rbookbt ----
                        b_Rbookbt.setText("\u91cd\u7f6e");
                        b_Rbookbt.addActionListener(e -> b_Rbookbt(e));

                        //---- label24 ----
                        label24.setText("\u72b6\u6001\uff1a");
                        label24.setHorizontalTextPosition(SwingConstants.CENTER);

                        GroupLayout panel3Layout = new GroupLayout(panel3);
                        panel3.setLayout(panel3Layout);
                        panel3Layout.setHorizontalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addGroup(panel3Layout.createParallelGroup()
                                        .addGroup(panel3Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(panel3Layout.createParallelGroup()
                                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(label18, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label17, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label19, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(label24))
                                            .addGap(14, 14, 14)
                                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(b_statustxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                .addComponent(b_numtxt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                .addComponent(b_putxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                .addComponent(b_idtxt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                                            .addGap(32, 32, 32)
                                            .addGroup(panel3Layout.createParallelGroup()
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label20, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                                        .addComponent(label21, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                                        .addComponent(label22, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(b_nametxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                        .addComponent(b_autxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                        .addComponent(b_typetxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                                                    .addGroup(panel3Layout.createParallelGroup()
                                                        .addGroup(panel3Layout.createSequentialGroup()
                                                            .addGap(76, 76, 76)
                                                            .addComponent(b_updatebt))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(b_Rbookbt))))
                                                .addComponent(b_deletebt, GroupLayout.Alignment.TRAILING)))
                                        .addGroup(panel3Layout.createSequentialGroup()
                                            .addComponent(label23, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(68, Short.MAX_VALUE))
                        );
                        panel3Layout.setVerticalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label20)
                                        .addComponent(label17)
                                        .addComponent(b_idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b_nametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel3Layout.createSequentialGroup()
                                            .addGap(27, 27, 27)
                                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label18)
                                                .addComponent(b_putxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label21)
                                                .addComponent(b_autxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(25, 25, 25)
                                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label19)
                                                .addComponent(b_numtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label22)
                                                .addComponent(b_typetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(panel3Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(b_updatebt)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(b_Rbookbt)
                                            .addGap(27, 27, 27)))
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(b_deletebt)
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(b_statustxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label24)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                    .addGroup(panel3Layout.createParallelGroup()
                                        .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label23, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
                        );
                    }

                    GroupLayout mtbookpanLayout = new GroupLayout(mtbookpan);
                    mtbookpan.setLayout(mtbookpanLayout);
                    mtbookpanLayout.setHorizontalGroup(
                        mtbookpanLayout.createParallelGroup()
                            .addGroup(mtbookpanLayout.createSequentialGroup()
                                .addGroup(mtbookpanLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(mtbookpanLayout.createSequentialGroup()
                                        .addGap(0, 88, Short.MAX_VALUE)
                                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.LEADING, mtbookpanLayout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(GroupLayout.Alignment.LEADING, mtbookpanLayout.createSequentialGroup()
                                        .addGap(323, 323, 323)
                                        .addComponent(label16, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B_searchtxt, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B_searchbut, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(178, Short.MAX_VALUE))
                    );
                    mtbookpanLayout.setVerticalGroup(
                        mtbookpanLayout.createParallelGroup()
                            .addGroup(mtbookpanLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(mtbookpanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(B_searchtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B_searchbut, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }
                desktopPane1.add(mtbookpan, JLayeredPane.DEFAULT_LAYER);
                mtbookpan.setBounds(15, 5, 1040, 675);
            }
            tudhuContentPane.add(desktopPane1, BorderLayout.CENTER);
            tudhu.setSize(1045, 750);
            tudhu.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame tudhu;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem addTypemenu;
    private JMenuItem mtTypemenu;
    private JMenu menu2;
    private JMenuItem addbookmenu;
    private JMenuItem mtbookmenu;
    private JMenuItem exitmenu;
    private JDesktopPane desktopPane1;
    private JPanel addbookpan;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField B_nametxt;
    private JTextField B_publishtxt;
    private JTextField B_numbertxt;
    private JTextField B_remarktxt;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField B_authertxt;
    private JTextField B_idtxt;
    private JTextField B_typetxt;
    private JButton button1;
    private JButton button2;
    private JPanel addTypepan;
    private JLabel label9;
    private JLabel label10;
    private JTextField bookTypletxt;
    private JScrollPane scrollPane1;
    private JTextArea bookremarktxt;
    private JButton addTyplebut;
    private JButton Rbut_Typle;
    private JLabel label12;
    private JPanel mtTyplepan;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JLabel label11;
    private JTextField s_bookTypeNametxt;
    private JButton s_bookTypebut;
    private JPanel panel1;
    private JLabel label13;
    private JLabel label14;
    private JTextField T_idtxt;
    private JLabel label15;
    private JTextField T_nametxt;
    private JScrollPane scrollPane3;
    private JTextArea T_remarktxt;
    private JButton Bt_update;
    private JButton button4;
    private JPanel mtbookpan;
    private JLabel label16;
    private JTextField B_searchtxt;
    private JButton B_searchbut;
    private JScrollPane scrollPane4;
    private JTable table2;
    private JPanel panel3;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JTextField b_idtxt;
    private JTextField b_putxt;
    private JTextField b_numtxt;
    private JLabel label20;
    private JLabel label21;
    private JLabel label22;
    private JTextField b_nametxt;
    private JTextField b_autxt;
    private JTextField b_typetxt;
    private JLabel label23;
    private JScrollPane scrollPane5;
    private JTextArea b_remarktxt;
    private JButton b_updatebt;
    private JButton b_deletebt;
    private JButton b_Rbookbt;
    private JLabel label24;
    private JTextField b_statustxt;
    // eclaration  //GEN-END:variables
}
