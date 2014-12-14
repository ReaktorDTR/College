package database;

import database.SQLUtil.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Reaktor on 14.12.2014.
 */
public class SQLStorage {
    Connection connection;

    public void connection() {
        this.connection = null;
        try {
            this.connection = new ConnectionConfiguration().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.connection != null) {
            System.out.println("Connection established!");
        }
    }

    public void outAllData() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

