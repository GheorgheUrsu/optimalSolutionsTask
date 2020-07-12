import database.DDL;
import database.SQLiteConnection;
import service.CsvReaderService;
import service.CsvReaderServiceImpl;
import service.CsvWriterService;
import service.CsvWriterServiceImpl;

public class Main {
    private static final SQLiteConnection SQLITE_CONNECTION = new SQLiteConnection();
    private static final String URL = "jdbc:sqlite:D:/sqlite/in_memory.db";
    private static final String CSV_FILE = "file.csv";
    private final static int COLUMN_NUMBERS = 10;

    public static void main(String[] args){

        SQLITE_CONNECTION.createTable(DDL.createTableQuery, URL);

        CsvReaderService service = new CsvReaderServiceImpl();

        service.loadCSV(CSV_FILE, COLUMN_NUMBERS);

        CsvWriterService writer = new CsvWriterServiceImpl();
        writer.writeNonMatchingRecord(CSV_FILE, COLUMN_NUMBERS);


    }
}
