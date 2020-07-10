package service;

import model.Customer;

import java.util.Collection;
import java.util.List;

public class CsvServiceImpl implements CsvService<Customer> {

    @Override
    public List<Customer> readFile(final String filePath) {
        return null;
    }

    @Override
    public void loadCSV(final Collection<? extends Customer> collection) {

    }
}
