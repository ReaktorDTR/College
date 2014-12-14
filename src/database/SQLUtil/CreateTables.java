package database.SQLUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Reaktor on 14.12.2014.
 */
public class CreateTables {
    private final String createGroupTableQuery = "CREATE TABLE `groups` (\n" +
            "`idGroup`  integer(16) NOT NULL AUTO_INCREMENT ,\n" +
            "`groupName`  varchar(50) NOT NULL ,\n" +
            "PRIMARY KEY (`idGroup`)\n" +
            ")\n" +
            ";";

    private final String createSubjectTableQuery = "CREATE TABLE `subjects` (\n" +
            "`idSubject`  integer(16) NOT NULL AUTO_INCREMENT ,\n" +
            "`subjectName`  varchar(50) NOT NULL ,\n" +
            "PRIMARY KEY (`idSubject`)\n" +
            ")\n" +
            ";";

    private final String createStudentTableQuery = "CREATE TABLE `students` (\n" +
            "`idStudent`  integer(16) NOT NULL AUTO_INCREMENT ,\n" +
            "`firstName`  varchar(50) NOT NULL ,\n" +
            "`lastName`  varchar(50) NOT NULL ,\n" +
            "`idGroup`  integer(16) NOT NULL ,\n" +
            "PRIMARY KEY (`idStudent`)\n" +
            ")\n" +
            ";";

    private final String createRatingTableQuery = "CREATE TABLE `ratings` (\n" +
            "`idRating`  integer(16) NOT NULL AUTO_INCREMENT ,\n" +
            "`idStudent`  integer(50) NOT NULL ,\n" +
            "`idSubject`  integer(50) NOT NULL ,\n" +
            "`mark`  integer(1) NOT NULL ,\n" +
            "PRIMARY KEY (`idRating`)\n" +
            ")\n" +
            ";";

    public void createTables() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionConfiguration().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(createGroupTableQuery);
            statement.executeUpdate(createStudentTableQuery);
            statement.executeUpdate(createSubjectTableQuery);
            statement.executeUpdate(createRatingTableQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
