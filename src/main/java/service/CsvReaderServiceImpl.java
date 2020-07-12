package service;

import config.CsvLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class CsvReaderServiceImpl implements CsvReaderService {
    private final String URL = "jdbc:sqlite:D:/sqlite/in_memory.db";


    @Override
    public void loadCSV(final String pathFile, final int columnNumbers) {
        String insertQuery = "INSERT INTO X (A, B, C, D, E,F,G,H,I,J) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement st = conn.prepareStatement(insertQuery)) {

            conn.setAutoCommit(false);
            for (String[] arr : Filters.filterMatchingRecords(pathFile, columnNumbers)) {
                st.setString(1, arr[0]);
                st.setString(2, arr[1]);
                st.setString(3, arr[2]);
                st.setString(4, arr[3]);
                st.setString(5, arr[4]);
                st.setString(6, arr[5]);
                st.setString(7, arr[6]);
                st.setString(8, arr[7]);
                st.setString(9, arr[8]);
                st.setString(10, arr[9]);
                st.addBatch();
            }
            int[] result = st.executeBatch();
            conn.commit();
            CsvLogger.log(Level.INFO, result.length + " of records successful ");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
