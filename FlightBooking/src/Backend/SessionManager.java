package Backend;

import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;

import java.util.ArrayList;

public class SessionManager {
    public static UserSession createUserSession(String username, String password) throws NoMatchingRow {
        return new UserSession(username, password);
    }
    public static UserSession createUserSession(User user){
        return new UserSession(user);
    }
    public static User createUser(ArrayList<String> creds) throws InvalidEntry {
        return UnsignedUserSession.addcreds(creds);
    }
    public static AdminSession createAdminSession(String username, String password) throws NoMatchingRow {
        if (username.equals("root") && password.equals("root")){
            return new AdminSession();
        }
        else{
            throw new NoMatchingRow("Admin doesn't exist");
        }
    }
    public static UnsignedUserSession createUnsignedUserSession(String uniqueID){
        return new UnsignedUserSession(uniqueID);
    }
}
