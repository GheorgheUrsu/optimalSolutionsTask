package service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

public class BadRecordWriter {
    private final Logger logger = Logger.getLogger(BadRecordWriter.class.getName());
    private final int columnNumbers = 10;

    public void writeNonMatchingRecord(final String sourceFile) {
        File file = new File(destinationFilePath());
        CSVWriter writer = null;

            try {
                file.createNewFile();
                logger.info("New file created... " + file.getAbsolutePath());

                writer = new CSVWriter(new FileWriter(file, true));
                System.out.println(file.getAbsolutePath());

                for (String[] record : filterRecords(sourceFile)) {
                    writer.writeNext(record);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    private String destinationFilePath() {
        String s = new SimpleDateFormat("yyyyMMddHHmmss").format(new Timestamp(currentTimeMillis()));
        return "bad-data-" + s + ".csv";
    }

    private List<String[]> filterRecords(final String filePath) {
        List<String[]> result = null;
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(filePath));

            result = reader.readAll().stream()
                    .skip(1)
                    .filter(arr -> arr.length != columnNumbers)
                    .map(BadRecordWriter::filteringCommas)
                    .collect(Collectors.toList());

            logger.info(result.size() + " records doesn't match with columns...");

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

    private static String[] filteringCommas(String[] arr) {
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i].contains(",")) {
                StringBuilder sb = new StringBuilder("\"" + arr[i] + "\"");
                arr[i] = sb.toString();
            }
        }
        return arr;
    }
}
