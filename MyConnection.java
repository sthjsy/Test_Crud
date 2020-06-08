package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection createConnection() throws SQLException,ClassNotFoundException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.24:1521:orclnew1", "C##test_admin", "system123#");
        return conn;
    }
    
}
