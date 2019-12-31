package Backend.Exceptions;

public class NoDriverFound extends RuntimeException {
    public NoDriverFound(String message){
        super(message);
    }
}
