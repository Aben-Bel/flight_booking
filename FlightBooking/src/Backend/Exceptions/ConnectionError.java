package Backend.Exceptions;

import java.sql.SQLException;

public class ConnectionError extends RuntimeException {
    public ConnectionError(String message){
        super(message);
    }

    public ConnectionError(String message, SQLException e) {
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }

}
