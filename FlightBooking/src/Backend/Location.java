package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Location implements DataManager {
    private String locationID;
    private String city;
    private String country;
    private String airportName;

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
            update = String.format("UPDATE Location SET %s = '%s' WHERE Location_ID = '%s'", "AirportName", airportName, locationID);
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
}
