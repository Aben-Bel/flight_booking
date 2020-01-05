package Backend;

import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;
import Backend.Exceptions.OverbookingError;
import Backend.Exceptions.SessionExpired;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class UnsignedUserSession extends UserSession {
    UnsignedUserSession(String uniqueID){
        super.uniqueID = uniqueID;
    }

    static User addcreds(ArrayList<String> creds) throws InvalidEntry{
        User user1;
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Passenger");
        try {
            preparedStatement.setString(1, creds.get(0));
            preparedStatement.setString(2, creds.get(1));
            preparedStatement.setString(3, creds.get(2));
            preparedStatement.setString(4, creds.get(3));
            preparedStatement.setString(5, creds.get(4));
            preparedStatement.setDouble(6, Double.parseDouble(creds.get(5)));
            preparedStatement.setString(7, creds.get(6));
            preparedStatement.setString(8, creds.get(7));
            preparedStatement.setString(9, creds.get(8));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(creds.get(9)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.execute();
            user1 = new User(creds.get(0), creds.get(1));
            return user1;
        } catch (SQLException | NoMatchingRow e) {
            throw new InvalidEntry("Entry is invalid");
        }
    }

    public void addCredentials(ArrayList<String> creds) throws InvalidEntry {
        user = addcreds(creds);
    }

    @Override
    public void reserveFlight(String flightID, String ticketClass, String seatNumber) throws OverbookingError, SessionExpired {
        if (user == null){
            throw new IllegalArgumentException("User Info is not set");
        }
        else {
            super.reserveFlight(flightID, ticketClass, seatNumber);
        }
    }
}
