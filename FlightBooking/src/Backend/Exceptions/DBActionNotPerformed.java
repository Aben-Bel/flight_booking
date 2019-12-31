package Backend.Exceptions;

public class DBActionNotPerformed extends Exception {
    public DBActionNotPerformed(String message){
        super(message);
    }
}
