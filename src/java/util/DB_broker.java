package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import java.sql.ResultSet;


public class DB_broker {

    static Connection conn = null;

    public static Connection getConnection()  {
        try {
            if (conn == null) {
           
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Properties prop = new Properties();
                InputStream is = DB_broker.class.getResourceAsStream("db.properties");
                prop.load(is);
                String url = prop.getProperty("url");
                String username = prop.getProperty("username");
                String password = prop.getProperty("password");
                conn = DriverManager.getConnection(url, username, password);
      
            }
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection()  {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

   
    public static void commitTransaction() {
        try {
            conn.commit();
            //conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void rollbackTransaction() {
        try {
            conn.rollback();
           // conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    
    
    
}
