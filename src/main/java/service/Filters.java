package service;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Filters {
    private static final Logger logger = Logger.getLogger(Filters.class.getName());

    public static List<String[]> filterNonMatchingRecords(final String csvFile, final int columnNumbers) {
        List<String[]> allRows = null;
        CSVReader reader = null;

        if (columnNumbers <= 0) {
            return Collections.emptyList();
        }

        try {
            reader = new CSVReader(new FileReader(csvFile));

            allRows = reader.readAll().stream()
                                      .skip(1L)
                                      .filter(arr -> arr.length != columnNumbers)
                                      .map(Filters::filteringCommas)
                                      .collect(Collectors.toList());
            logger.info(allRows.size() + " records failed");

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
        List<String[]> allRows = null;

        try {
            reader = new CSVReader(new FileReader(filePath));

            allRows = reader.readAll().stream()
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
        return allRows;
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
