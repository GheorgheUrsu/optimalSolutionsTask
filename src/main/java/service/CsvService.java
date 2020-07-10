package service;

import model.Customer;

import java.util.Collection;
import java.util.List;

public interface CsvService<T> {

   List<Customer> readFile(final String filePath);

   void loadCSV(final Collection<? extends T> collection);

}
