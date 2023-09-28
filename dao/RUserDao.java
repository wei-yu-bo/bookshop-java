package BookManager.dao;

import BookManager.model.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RUserDao {

    public void RUser(Connection con, User user)throws Exception{

       int i=0;
       try{
        String sql1="insert into user(userName,password,phone)values(?,?,?)";
        assert con != null;
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getPhone());
        i=pstmt.executeUpdate();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
           JOptionPane.showMessageDialog(null,"注册失败，请重新注册");
       }
       if(i!=0){
           JOptionPane.showMessageDialog(null,"注册成功");
       }
    }
    public ResultSet userb(Connection con,User user) throws SQLException {
        String sql="select userName from user where userName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        return pstmt.executeQuery();
    }
}
