package Backend;

import Backend.Exceptions.InvalidEntry;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class SyncManager {
    private String tableName;
    private String UniqueID;
    public SyncManager(String tableName, String uniqueID){
        this.tableName = tableName;
        this.UniqueID = uniqueID;
    }
    public void updateRowStrings(HashMap<String, String> attribs) throws InvalidEntry {
        Set<String> keys = attribs.keySet();
        for (String key:
              keys) {
            commitUpdate(key, attribs.get(key));
        }
    }

    private void commitUpdate(String key, String s) throws InvalidEntry {
        PreparedStatement preparedStatement = QueryManager.prepareUpdate(tableName, key, "key = \'"+UniqueID+"\'");
        try {
            preparedStatement.setString(1, s);
        } catch (SQLException e) {
            throw new InvalidEntry("Can't input value " + s + " for key "+key+".\n"+e.getSQLState()+"\n"+e.getMessage());
        }
    }
    public static void main(String[] args){
        SyncManager syncManager = new SyncManager("Aircraft", "XXX");
        HashMap<String, String> newAttribs = new HashMap<String, String>();
        newAttribs.put("Aircraft_id", "XXX");
        newAttribs.put("Model", "XO@33");
        newAttribs.put("First_class_seats", "23");
    }
}
