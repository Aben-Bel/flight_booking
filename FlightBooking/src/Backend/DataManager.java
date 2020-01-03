package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;

import java.sql.ResultSet;
import java.util.HashMap;

public interface DataManager {
    void sync();
    void update() throws InvalidEntry;
    HashMap<String, String> getAttributes();
    void readRow(ResultSet row);
}
