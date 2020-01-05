package Backend;

import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AdminSession {
    AdminSession(){

    }
    public Flight findFlight(String flightID) throws NoMatchingRow {
        return new Flight(flightID);
    }
    public Location findLocation(String locationID) throws NoMatchingRow {
        return new Location(locationID);
    }
    public Aircraft findAircraft(String aircraftID) throws NoMatchingRow {
        return new Aircraft(aircraftID);
    }
    public void addLocation(ArrayList<String> creds) throws InvalidEntry {
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Location");
        try {
            preparedStatement.setString(1, creds.get(0));
            preparedStatement.setString(2, creds.get(1));
            preparedStatement.setString(3, creds.get(2));
            preparedStatement.setString(4, creds.get(3));
        } catch (SQLException e) {
            throw new InvalidEntry("Location info Invalid");
        }
    }
    public void addAircraft(ArrayList<String> creds) throws InvalidEntry {
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Aircraft");
        try {
            preparedStatement.setString(1, creds.get(0));
            preparedStatement.setString(2, creds.get(1));
            preparedStatement.setString(3, creds.get(2));
            preparedStatement.setInt(4, Integer.parseInt(creds.get(3)));
            preparedStatement.setInt(5, Integer.parseInt(creds.get(4)));
            preparedStatement.setInt(6, Integer.parseInt(creds.get(5)));
            preparedStatement.setString(7, creds.get(6));
        } catch (SQLException e) {
            throw new InvalidEntry("Aircraft info Invalid");
        }
    }
    public void addFlight(ArrayList<String> creds) throws InvalidEntry{
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Flight");
        try {
            preparedStatement.setString(1, creds.get(0));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(creds.get(1)));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(creds.get(2)));
            preparedStatement.setDouble(4, Double.parseDouble(creds.get(3)));
            preparedStatement.setDouble(5, Double.parseDouble(creds.get(4)));
            preparedStatement.setDouble(6, Double.parseDouble(creds.get(5)));
            preparedStatement.setInt(7, Integer.parseInt(creds.get(6)));
            preparedStatement.setString(8, creds.get(7));
            preparedStatement.setString(9, creds.get(8));
            preparedStatement.setString(10, creds.get(9));
        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())) {
                throw new InvalidEntry("Departure time is set after Arrival time");
            }
            throw new InvalidEntry("Aircraft info Invalid");
        }
    }
}
