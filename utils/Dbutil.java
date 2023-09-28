package BookManager.utils;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库巩固类
 */
public class Dbutil {
     String dbDriver="com.mysql.cj.jdbc.Driver";
     String dbUrl="jdbc:mysql://localhost:3306/bookmanager?characterEncoding=utf-8";
     String dbUsername="root";
     String dbPassword="123456";

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception{
        Class.forName(dbDriver);
        Connection con= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        return con;
    }
    public  void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Dbutil dbutil=new Dbutil();
        try{
        dbutil.getConnection();
        System.out.println("数据库连接成功");}catch (Exception e){
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }
}
