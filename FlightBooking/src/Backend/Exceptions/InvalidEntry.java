package Backend.Exceptions;

import java.sql.SQLException;

public class InvalidEntry extends Exception{
    public InvalidEntry(String message){
        super(message);
    }
    public InvalidEntry(String message, SQLException e){
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }
}
