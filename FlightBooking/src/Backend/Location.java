package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Location implements DataManager {
    private String locationID;
    private String city;
    private String country;
    private String airportName;

    // Creates a location from a result set
    public Location(ResultSet row){
        readRow(row);
    }

    // create a location from unique location_ID
    public Location(String locationID) throws NoMatchingRow {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Location WHERE Location_ID = '"+locationID+"'");
        try {
            ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
            if (resultSet.next()){
                readRow(resultSet);
            }
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        } catch (SQLException e) {
            throw new NoMatchingRow("location_ID not found");
        }
    }

    public String getLocationID() {
        return locationID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public void sync() throws DBActionNotPerformed {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Location " +
                "WHERE Location_ID = \'"+locationID+"\'");
        ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        try {
            resultSet.next();
        } catch (SQLException e) {
            e.getMessage();
        }
        readRow(resultSet);
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void update() throws InvalidEntry {

        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        Statement statement = ConnectionHandler.getStatement();
        try {
            String update = String.format("UPDATE Location SET %s = '%s' WHERE Location_ID = '%s'", "City", city, locationID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Location SET %s = '%s' WHERE Location_ID = '%s'", "Country", country, locationID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Location SET %s = '%s' WHERE Location_ID = '%s'", "Airport_Name", airportName, locationID);
            statement.executeUpdate(update);
        } catch (SQLException e) {
            throw new InvalidEntry("Updated value not accepted", e);
        }finally {
            try {
                statement.close();
                ConnectionHandler.clean();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Location_ID", locationID);
        attributes.put("City", city);
        attributes.put("Country", country);
        attributes.put("Airport_Name", airportName);
        return attributes;
    }

    @Override
    public void readRow(ResultSet row) {
        try {
            locationID = row.getString("Location_ID");
            city = row.getString("City");
            country = row.getString("Country");
            airportName = row.getString("Airport_Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, DBActionNotPerformed {
        PreparedStatement preparedStatement =  QueryManager.prepareSelect("Select * from Location where Location_ID = 'ADD,ETH'");
        ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        resultSet.next();
        Location location = new Location(resultSet);
        resultSet.close();
        QueryManager.clean();
        System.out.println(location.getAttributes());
        location.setAirportName("NOTaREALairport");
        try {
            location.update();
        } catch (InvalidEntry invalidEntry) {
            invalidEntry.printStackTrace();
        }
        System.out.println("UPDATE ROW on table AND come and SAY SOMETHING");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        location.sync();
        System.out.println(location.getAttributes());
        Location location1;
        try {
            location1 = new Location("FINF");
            System.out.println(location1.getAttributes());
        } catch (NoMatchingRow noMatchingRow) {
            System.out.println("locationID not found");
        }
        System.out.println("All Systems are a go...");
    }

}
