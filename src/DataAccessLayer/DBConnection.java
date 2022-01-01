package DataAccessLayer;

import java.sql.*;

/**
 *
 * @author Lian La-Fey
 */
public class DBConnection {
    
    private Connection connection = null;

    public Connection connDb() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/rentcar?user=root&password=ds.BJKB123");
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        
        return connection;
    }
    }
}
