/*
 * Created by JFormDesigner on Mon Apr 11 11:10:12 CST 2022
 */

package BookManager.Jframe;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;

import BookManager.dao.ManageDao;
import BookManager.dao.UserDao;
import BookManager.model.Manage;
import BookManager.model.User;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class LogOnFrm extends JFrame {
    private toolUtil toolUtil=new toolUtil();
    private Dbutil dbutil=new Dbutil();
    private UserDao userDao=new UserDao();
    private ManageDao manageDao=new ManageDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public LogOnFrm() {
        initComponents();
    }

    private void Login(ActionEvent e) {
        String userName = usertxt.getText();
        String password = new String(passwordtxt.getPassword());
        if (toolUtil.isEmpty(userName) || toolUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
            return;
        }
        User user = new User(userName, password);
        Manage manage=new Manage(userName, password);
        Connection con = null;
        try {
            String userType= (String) userTypecbox.getSelectedItem();
            con = dbutil.getConnection();
            if(userType.equals("管理员"))
            {
            Manage currentmanage = manageDao.manage(con, manage);
            if (currentmanage != null) {
                JOptionPane.showMessageDialog(null, "登录成功");
                new Book_manage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "登录失败,用户名或密码错误");
            }
            }
            if(userType.equals("学生"))
            {
                User currentUser =userDao.addUser(con, user);
                if (currentUser != null) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    new Book_student();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败,用户名或密码错误");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    private void Rmoth(ActionEvent e) throws Exception {
        new Regfrm2();
        dispose();
    }

    private void initComponents() {
        this.setVisible(true);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        usertxt = new JTextField();
        passwordtxt = new JPasswordField();
        Dbutton = new JButton();
        button2 = new JButton();
        label4 = new JLabel();
        userTypecbox = new JComboBox<>();

        //======== this ========
        setFont(new Font("\u5e7c\u5706", Font.PLAIN, 22));
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(72, 17));
        label1.setFont(new Font("\u5b8b\u4f53", label1.getFont().getStyle(), label1.getFont().getSize() + 8));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        label2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 12));

        //---- label3 ----
        label3.setText("\u5bc6  \u7801\uff1a");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 12));

        //---- usertxt ----
        usertxt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 12));

        //---- Dbutton ----
        Dbutton.setText("\u767b\u5f55");
        Dbutton.addActionListener(e -> Login(e));

        //---- button2 ----
        button2.setText("\u524d\u5f80\u6ce8\u518c");
        button2.addActionListener(e -> {
            try {
                Rmoth(e);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //---- label4 ----
        label4.setText("\u7528\u6237\u7c7b\u578b\uff1a");

        //---- userTypecbox ----
        userTypecbox.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7ba1\u7406\u5458",
            "\u5b66\u751f"
        }));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(197, 197, 197)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(165, 165, 165)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label4))
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(usertxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordtxt, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                    .addGap(22, 22, 22)
                                                    .addComponent(userTypecbox, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(Dbutton)))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGap(0, 142, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(usertxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userTypecbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Dbutton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField usertxt;
    private JPasswordField passwordtxt;
    private JButton Dbutton;
    private JButton button2;
    private JLabel label4;
    private JComboBox<String> userTypecbox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
