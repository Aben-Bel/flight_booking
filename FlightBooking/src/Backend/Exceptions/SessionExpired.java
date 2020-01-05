package Backend.Exceptions;

import java.sql.SQLException;

public class SessionExpired extends Exception{
    public SessionExpired(String message){
        super(message);
    }
    public SessionExpired(String message, SQLException e){
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }
}
