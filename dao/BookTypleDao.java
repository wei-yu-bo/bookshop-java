package BookManager.dao;
import BookManager.model.BookType;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTypleDao {
    private Dbutil dbutil=new Dbutil();
    private toolUtil toolUtil=new toolUtil();
    boolean b;
    /*
    类别添加
     */
    public int addbooktyple(Connection con, BookType bookType)throws Exception{
        assert con != null;
        String sql="insert into BookType values(null,?,?)";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,bookType.getTypeName());
        pstm.setString(2,bookType.getRemark());
        return pstm.executeUpdate();
    }
    /*
    查询图书类别
     */
    public ResultSet list(Connection con,BookType bookType) throws SQLException {
        StringBuffer sb=new StringBuffer("select * from booktype ");
        if(toolUtil.isNotEmpty(bookType.getTypeName())){
              sb.append(" and bookTypeName like'%"+bookType.getTypeName()+"%'");
        }
        PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstm.executeQuery();
    }
    /*
    删除
     */
    public int delete(Connection con,String id) throws SQLException {
        String sql="delete from booktype where id=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate();
    }
    /*
    修改
     */
    public int update(Connection con,BookType bookType) throws SQLException {
        String sql="update booktype set bookTypeName=?,remark=? where id=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,bookType.getTypeName());
        pstm.setString(2,bookType.getRemark());
        pstm.setInt(3,bookType.getTypeId());
        return pstm.executeUpdate();
    }
}
