import database.DDL;
import database.SQLiteConnection;

public class Main {
    private static final SQLiteConnection SQLITE_CONNECTION = new SQLiteConnection();
    private static final String URL = "jdbc:sqlite:D:/sqlite/in_memory.db";

    public static void main(String[] args) {

        SQLITE_CONNECTION.createTable(DDL.createTableQuery, URL);
    }
}
