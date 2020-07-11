package service;

import com.opencsv.CSVReader;
import model.Customer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CsvServiceImpl implements CsvService<Customer> {
    private final Logger logger = Logger.getLogger(CsvServiceImpl.class.getName());
    private final int columnNumbers = 10;

    @Override
    public List<Customer> readFile(final String filePath) {
        CSVReader reader = null;
        List<String[]> allRows = null;

        try{
            reader = new CSVReader(new FileReader(filePath));
            allRows = reader.readAll();
            logger.info(allRows.size()-2 + " records received from CSV file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return allRows.stream()
                      .skip(1)
                .filter(arr -> arr.length == columnNumbers)
                       .map(mapperToCustomer)
                       .collect(Collectors.toList());
    }

    Function<String[], Customer> mapperToCustomer = (
                      record -> new Customer(record[0], record[1], record[2], record[3], record[4], record[5],
                                            record[6], record[7].replace('$', ' '), record[8], record[9]));

    @Override
    public void loadCSV(final Collection<? extends Customer> collection) {

    }
}
