package service;

import java.util.Collection;

public interface CsvWriterService {

    void writeNonMatchingRecord(final String csvFile,final int columnNumbers);

    void writeRecords(final Collection<String[]> collection);

}
