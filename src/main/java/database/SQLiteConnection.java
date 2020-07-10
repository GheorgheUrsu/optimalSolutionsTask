package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


public class SQLiteConnection {
    private static final Logger logger = Logger.getLogger(SQLiteConnection.class.getName());

    public void createTable(final String createTableQuery, final String dbUrl) {

        try(Connection conn = DriverManager.getConnection(dbUrl)) {
            logger.info("Connected... creating the database if not exist...");

            PreparedStatement statement = conn.prepareStatement(createTableQuery);
            statement.executeUpdate();
            logger.info("Table 'X' created if not exist...");

        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
