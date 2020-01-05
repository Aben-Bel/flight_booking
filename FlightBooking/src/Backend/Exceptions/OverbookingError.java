package Backend.Exceptions;

import java.sql.SQLException;

public class OverbookingError extends Exception{
    public OverbookingError(String message){
        super(message);
    }
    public OverbookingError(String message, SQLException e){
        super(message + "\n" + e.getSQLState() + "\n" + e.getMessage());
    }
}
