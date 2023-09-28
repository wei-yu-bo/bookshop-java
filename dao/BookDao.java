package BookManager.dao;
import BookManager.model.Book;
import BookManager.utils.Dbutil;
import BookManager.utils.toolUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
    private Dbutil dbutil=new Dbutil();
    private toolUtil toolUtil=new toolUtil();
    /*
    添加
     */
    public int addbook(Connection con, Book book)throws Exception{
        assert con != null;
        String sql="insert into Book values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setInt(1, book.getBookId());
        pstm.setString(2, book.getBookname());
        pstm.setString(3, book.getPublish());
        pstm.setString(4, book.getAuthor());
        pstm.setInt(5, book.getNumber());
        pstm.setString(6, book.getBookType());
        pstm.setString(7, book.getRemark());
        pstm.setString(8, book.getStatus());
        return pstm.executeUpdate();
    }
    /*
    查询
     */
    public ResultSet list(Connection con, Book book) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from book ");
        if (toolUtil.isNotEmpty(book.getBookname())) {
            sb.append(" and bookname like'%" + book.getBookname() + "%'");
        }
            PreparedStatement pstm = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
            return pstm.executeQuery();
        }

/*
更新
 */
    public int updatebook(Connection con,Book book)throws  SQLException{
        String sql="update book set bookname=?,publish=?,author=?,number=?,booktype=?,remark=? where bookId=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,book.getBookname());
        pstm.setString(2,book.getPublish());
        pstm.setString(3,book.getAuthor());
        pstm.setInt(4,book.getNumber());
        pstm.setString(5,book.getBookType());
        pstm.setString(6,book.getRemark());
        pstm.setInt(7,book.getBookId());
        return pstm.executeUpdate();
    }
    public int updatebook2(Connection con,Book book) throws SQLException {
        String sql="update book set status=? where bookid=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,book.getStatus());
        pstm.setInt(2,book.getBookId());
        return pstm.executeUpdate();
    }
    public int delete(Connection con,String bid) throws SQLException {
        String sql="delete from book where bookId=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,bid);
        return pstm.executeUpdate();
    }
}
