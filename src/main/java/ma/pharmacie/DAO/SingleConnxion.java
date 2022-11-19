package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class SingleConnxion{
    public static Connection getConnection() {
        Connection con = null;

        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/pharmacie ";


            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbURL,"root","1234");
            System.out.println("DB Connecting");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Database.getConnection() Error -->" + e.getMessage());
        }
        return con;
    }
    public void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }

    }
}