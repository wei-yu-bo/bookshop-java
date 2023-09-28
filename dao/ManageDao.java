package BookManager.dao;

import BookManager.model.Manage;
import BookManager.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManageDao {
    public Manage manage(Connection con, Manage manage)throws Exception{
        Manage resultmanage=null;
        String sql="select*from manager where mid=? and mpassword=? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,manage.getMid());
        pstmt.setString(2,manage.getMpassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultmanage=new Manage();
            resultmanage.setMid(rs.getString("mid"));
            resultmanage.setMpassword(rs.getString("mpassword"));
        }
        return resultmanage;
    }
}
