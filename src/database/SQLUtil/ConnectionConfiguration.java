package database.SQLUtil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Reaktor on 14.12.2014.
 */
public class ConnectionConfiguration {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/college";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "softserve";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
