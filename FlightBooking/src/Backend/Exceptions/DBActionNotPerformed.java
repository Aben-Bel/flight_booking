package Backend.Exceptions;

import java.sql.SQLException;

public class DBActionNotPerformed extends Exception {
    public DBActionNotPerformed(String message){
        super(message);
    }
    public DBActionNotPerformed(String message, SQLException e) {
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }
}
