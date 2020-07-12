package service;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class CsvWriterServiceImpl implements CsvWriterService {
    private final Logger logger = Logger.getLogger(CsvWriterServiceImpl.class.getName());


    public void writeNonMatchingRecord(final String csvFile,final int columnNumbers) {
        String message = "All records match with number of columns...";
        List<String[]> nonMatchingRecords = Filters.filterNonMatchingRecords(csvFile, columnNumbers);

        if (nonMatchingRecords.size() > 0) {
            this.writeRecords(nonMatchingRecords);
        } else {
            System.out.println(message);
        }
    }

    public void writeRecords(final Collection<String[]> collection) {
        File file = new File(this.destinationFilePath());
        CSVWriter writer = null;

        try {
            file.createNewFile();
            this.logger.info("New file created... " + file.getAbsolutePath());
            writer = new CSVWriter(new FileWriter(file, true));

            for (String[] record : collection) {
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
        String s = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Timestamp(System.currentTimeMillis()));
        return "bad-data-" + s + ".csv";
    }
}

