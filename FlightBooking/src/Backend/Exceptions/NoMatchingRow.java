package Backend.Exceptions;

import java.sql.SQLException;

public class NoMatchingRow extends Exception {
    public NoMatchingRow(String message){
        super(message);
    }
    public NoMatchingRow(String message, SQLException e) {
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }
}
