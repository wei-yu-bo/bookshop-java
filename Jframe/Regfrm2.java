package BookManager.Jframe;

import BookManager.dao.RUserDao;
import BookManager.dao.UserDao;
import BookManager.model.User;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;

class Regfrm2 extends JFrame {
     JPanel panel;
     JTextField textField;
     JPasswordField passwordField;
     JTextField phoneField;
     JLabel passwordMes;
     JLabel unameMes;
     private Dbutil dbutil=new Dbutil();
     private toolUtil toolUtil=new toolUtil();
     private UserDao userDao=new UserDao();
     private RUserDao rUserDao=new RUserDao();
     private  Connection con;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Regfrm2 frame = new Regfrm2();
                    frame.setSize(500,300);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public  Regfrm2() throws Exception {
        setSize(500,300);
        this.setVisible(true) ;
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
/*
用户名组件
 */
        JLabel userlabel = new JLabel("用户名：");
        userlabel.setBounds(100,80,70,20);
        panel.add(userlabel);

        unameMes=new JLabel();
        unameMes.setBounds(320,80,150,20);
        textField = new JTextField();
        textField.setBounds(150,80,150,20);
        textField.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                String uname=textField.getText();
                if(toolUtil.isEmpty(uname)){
                    unameMes.setText("用户名不能为空");
                    unameMes.setForeground(Color.red);
                }
                    else{
                        unameMes.setText("√");
                        unameMes.setForeground(Color.green);
                }

            }
            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        panel.add(unameMes);
        panel.add(textField);
        textField.setColumns(10);
/*
密码组件
 */
        JLabel passlabel = new JLabel("密码：");
        passlabel.setBounds(100,120,70,20);
        panel.add(passlabel);
        passwordMes=new JLabel();
        passwordMes.setBounds(320,120,150,20);
        passwordField = new JPasswordField();
        passwordField.setBounds(150,120,150,20);
        panel.add(passwordMes);
        passwordField.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                String pwd=new String(passwordField.getPassword());
                if(toolUtil.isEmpty(pwd)){
                    passwordMes.setText("密码不能为空");
                    passwordMes.setForeground(Color.red);
                }else {
                    boolean flag = pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
                    if (flag) {
                        passwordMes.setText("√");
                        passwordMes.setForeground(Color.green);
                    }else{
                        JOptionPane.showMessageDialog(null,"密码须为6-16位数字和字母组合");
                        passwordMes.setText("");
                    }
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        panel.add(passwordField);

        JLabel phonelabel = new JLabel("手机号：");
        phonelabel.setBounds(100,160,70,20);
        JLabel phoneMes=new JLabel();
        phoneMes.setBounds(320,160,150,20);
        phoneField = new JTextField();
        phoneField.setBounds(150,160,150,20);
        phoneField.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                String phonenumber=phoneField.getText();
                if(toolUtil.isEmpty(phonenumber)){
                    phoneMes.setText("手机号不能为空");
                    phoneMes.setForeground(Color.red);
                }
                else {
                    boolean flag = phonenumber.matches("^\\d{11}$");
                    if (flag) {
                        phoneMes.setText("√");
                        phoneMes.setForeground(Color.green);
                    }else{
                        JOptionPane.showMessageDialog(null,"手机号须为11位数字");
                        phoneMes.setText("");
                    }
                }

            }
            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        panel.add(phonelabel);
        panel.add(phoneField);
        panel.add(phoneMes);

        JButton Pbt = new JButton("前往登录页面");
        Pbt.setBounds(100,190,120,35);
        panel.add(Pbt);
        Pbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogOnFrm();
                dispose();
            }
        });

        JButton Rbt = new JButton("注册");
        Rbt.setBounds(250,190,80,35);
        panel.add(Rbt);

        this.setLocation(500,300);

        Rbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegChek(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        }
     private void RegChek(ActionEvent e) throws Exception {
        String username=textField.getText();
        String password=new String(passwordField.getPassword());
        String phone=phoneField.getText();
        if(toolUtil.isEmpty(username)||toolUtil.isEmpty(password)||toolUtil.isEmpty(phone)){
            JOptionPane.showMessageDialog(null,"请输入相关信息!");
            return;
        }
        con=null;
        try {
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            con=dbutil.getConnection();
            ResultSet rs=rUserDao.userb(con,user);
            if(rs.next())
            {
                System.out.println(rs.getString(1));
                JOptionPane.showMessageDialog(null,"用户名已存在");
            }else
            {rUserDao.RUser(con,user);}

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            dbutil.closeCon(con);
        }
     }
    }



