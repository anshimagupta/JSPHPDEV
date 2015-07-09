package database;

/**
 * Created by anshima on 7/7/15.
 */

import java.sql.*;
import java.util.Iterator;

import model.Automobile;

public class MakeConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Automobiles";

    //  Database credentials
    static final String USER = "anshima";
    static final String PASS = "anshima";

    public Connection connection;

    public boolean isConnected() {
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
