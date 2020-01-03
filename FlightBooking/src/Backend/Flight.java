package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Flight implements DataManager {
    private String flightID;
    private Date departureDate;
    private Date arrivalDate;
    private String aircraftID;
    private String arrivalLocID;
    private String departureLocID;
    private double firstClassPrice;
    private double businessClassPrice;
    private double economicClassPrice;
    private int seatsAvailable;

    // Creates an Flight from a result set
    Flight(ResultSet row){
        readRow(row);
    }

    // create an Flight from unique aircraftID
    Flight(String flightID) throws NoMatchingRow {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Flight WHERE Flight_ID = '"+flightID+"'");
        try {
            ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
            if (resultSet.next()){
                readRow(resultSet);
            }
            else{
                throw new NoMatchingRow("Flight not found");
            }
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        } catch (SQLException e) {
            throw new NoMatchingRow("Flight not found", e);
        }
    }

    // getters and setters


    public String getFlightID() {
        return flightID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public String getArrivalLocID() {
        return arrivalLocID;
    }

    public String getDepartureLocID() {
        return departureLocID;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public double getBusinessClassPrice() {
        return businessClassPrice;
    }

    public void setBusinessClassPrice(double businessClassPrice) {
        this.businessClassPrice = businessClassPrice;
    }

    public double getEconomicClassPrice() {
        return economicClassPrice;
    }

    public void setEconomicClassPrice(double economicClassPrice) {
        this.economicClassPrice = economicClassPrice;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    @Override
    public void sync() {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Flight " +
                "WHERE Flight_ID = '"+flightID+"'");
        ResultSet resultSet;
        try {
            resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
            resultSet.next();
            readRow(resultSet);
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        } catch (DBActionNotPerformed | SQLException dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        }
    }

    @Override
    public void update() throws InvalidEntry {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        Statement statement = ConnectionHandler.getStatement();
        try {
            String update = String.format("UPDATE Flight SET %s = '%s' WHERE Flight_ID = '%s'", "Aircraft_Aircraft_id", aircraftID, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = '%s' WHERE Flight_ID = '%s'", "Location_arrival_ID", arrivalLocID, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = '%s' WHERE Flight_ID = '%s'", "Location_departure_ID", departureLocID, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = %.2f WHERE Flight_ID = '%s'", "First_Class_Price", firstClassPrice, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = %.2f WHERE Flight_ID = '%s'", "Business_Class_Price", businessClassPrice, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = %.2f WHERE Flight_ID = '%s'", "Economic_Class_Price", economicClassPrice, flightID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Flight SET %s = %d WHERE Flight_ID = '%s'", "Seats_Available", seatsAvailable, flightID);
            statement.executeUpdate(update);
            PreparedStatement preparedStatement = QueryManager.prepareUpdate("Flight", "Departure_time", "Flight_ID = '"+flightID+"'");
            preparedStatement.setDate(1, departureDate);
            QueryManager.excutePreparedStatementUpdateRepeated(preparedStatement);
            preparedStatement = QueryManager.prepareUpdate("Flight", "Arrival_time", "Flight_ID = '" + flightID + "'");
            preparedStatement.setDate(1, arrivalDate);
            QueryManager.excutePreparedStatementUpdateRepeated(preparedStatement);
            QueryManager.clean();
        } catch (SQLException e) {
            throw new InvalidEntry("Updated value not accepted", e);
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            throw new InvalidEntry(dbActionNotPerformed.getMessage());
        } finally {
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
        attributes.put("Flight_ID", flightID);
        attributes.put("Departure_time", departureLocID);
        attributes.put("Arrival_time", arrivalLocID);
        attributes.put("Aircraft_Aircraft_id", aircraftID);
        attributes.put("Location_arrival_ID", ""+arrivalLocID);
        attributes.put("Location_departure_ID", departureLocID);
        attributes.put("First_Class_Price", String.format("%.2f",firstClassPrice));
        attributes.put("Business_Class_Price", String.format("%.2f",businessClassPrice));
        attributes.put("Economic_Class_Price", String.format("%.2f",economicClassPrice));
        attributes.put("Seats_Available", ""+seatsAvailable);
        return attributes;
    }

    @Override
    public void readRow(ResultSet row) {
        try {
            flightID = row.getString("Flight_ID");
            departureDate = row.getDate("Departure_time");
            arrivalDate = row.getDate("Arrival_time");
            aircraftID = row.getString("Aircraft_Aircraft_id");
            departureLocID = row.getString("Location_departure_ID");
            arrivalLocID = row.getString("Location_arrival_ID");
            firstClassPrice = row.getDouble("First_Class_Price");
            businessClassPrice = row.getDouble("Business_Class_Price");
            economicClassPrice = row.getDouble("Economic_Class_Price");
            seatsAvailable = row.getInt("Seats_Available");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DBActionNotPerformed, SQLException {
        PreparedStatement preparedStatement =  QueryManager.prepareSelect("Select * from Flight where Flight_ID = 'FL-ADD-01'");
        ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        resultSet.next();
        Flight flight = new Flight(resultSet);
        resultSet.close();
        QueryManager.clean();
        System.out.println(flight.getAttributes());
        flight.setBusinessClassPrice(100000);
        try {
            flight.update();
        } catch (InvalidEntry invalidEntry) {
            invalidEntry.printStackTrace();
        }
        System.out.println("UPDATE ROW on table AND come and SAY SOMETHING");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        flight.sync();
        System.out.println(flight.getAttributes());
        Flight flight1;
        try {
            flight1 = new Flight("FL-ADD-02");
            System.out.println(flight1.getAttributes());
        } catch (NoMatchingRow noMatchingRow) {
            System.out.println("aircraft not found\n"+noMatchingRow.getMessage());
        }
        System.out.println("All Systems are a go...");
    }
}
