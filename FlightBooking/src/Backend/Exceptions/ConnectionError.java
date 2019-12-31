package Backend.Exceptions;

public class ConnectionError extends RuntimeException {
    public ConnectionError(String message){
        super(message);
    }
}
