package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by MebiuW on 16/5/20.
 */
public class DBConnector {
    public static String query(String sql){
        Connection conn = null;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        String url = "jdbc:mysql://jushuo2.cloudapp.net:3306/mydb?"
                + "user=root&password=jushuo&useUnicode=true&characterEncoding=UTF8";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            return ResultSetToJSON.resultSetToJson(rs);
        } catch (Exception e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]){
        System.out.println(DBConnector.query("SELECT * FROM TimelineItem"));
    }
}
