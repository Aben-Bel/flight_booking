package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;

import java.sql.ResultSet;
import java.util.HashMap;

public interface DataManager {
    // refresh the values of the object by syncing with database
    void sync();

    // saves the changes in object to the database
    void update() throws InvalidEntry;

    // returns a HashMap whose keys are the attributes and values are the
    // values of those attributes
    HashMap<String, String> getAttributes();

    // generate object by reading a row of the database
    // doesn't move ResultSet to next entry
    void readRow(ResultSet row);
}
