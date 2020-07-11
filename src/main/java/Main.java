import database.DDL;
import database.SQLiteConnection;
import model.Customer;
import service.CsvService;
import service.CsvServiceImpl;

public class Main {
    private static final SQLiteConnection SQLITE_CONNECTION = new SQLiteConnection();
    private static final String URL = "jdbc:sqlite:D:/sqlite/in_memory.db";
    private static final String CSV_FILE = "file.csv";

    public static void main(String[] args) {

        SQLITE_CONNECTION.createTable(DDL.createTableQuery, URL);

        CsvService<Customer> service = new CsvServiceImpl();
        service.readFile(CSV_FILE);
    }
}
