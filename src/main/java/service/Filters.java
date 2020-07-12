package service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import config.CsvLogger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Filters {

    public static List<String[]> filterNonMatchingRecords(final String csvFile, final int columnNumbers) {
        List<String[]> allRows = null;
        CSVReader reader = null;

        if (columnNumbers <= 0) {
            return Collections.emptyList();
        }

        try {
            reader = new CSVReaderBuilder(new FileReader(csvFile)).withSkipLines(1).build();

            allRows = reader.readAll().stream()
                                      .skip(1)
                                      .filter(arr -> arr.length != columnNumbers)
                                      .map(Filters::filteringCommas)
                                      .collect(Collectors.toList());

            CsvLogger.log(Level.INFO,  allRows.size() + " records failed");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allRows;
    }

    public static List<String[]> filterMatchingRecords(final String filePath, final int columnNumbers) {
        CSVReader reader = null;
        List<String[]> allRows;
        List<String[]> result = null;

        if (filePath.isEmpty() || filePath.equals(null)) {
            return Collections.emptyList();
        }
        try {
            reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build();

            allRows = reader.readAll();
            CsvLogger.log(Level.INFO,  allRows.size()-1  + " records received");

            result =allRows.stream()
                           .skip(1)
                           .filter(arr -> arr.length == columnNumbers)
                           .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String[] filteringCommas(final String[] arr) {
        for (int i = 0; i <= arr.length - 1; ++i) {
            if (arr[i].contains(",")) {
                StringBuilder sb = new StringBuilder("\"" + arr[i] + "\"");
                arr[i] = sb.toString();
            }
        }
        return arr;
    }
}
