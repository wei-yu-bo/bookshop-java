package BookManager.dao;

import BookManager.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 注册验证
 */
public class UserDao {
    public  User addUser(Connection con, User user)throws Exception{
        User resultUser=null;
        String sql="select*from user where userName=? and password=? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());

        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setUsername(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
            resultUser.setPhone(rs.getString("phone"));
        }
        return resultUser;
    }
}
