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

public class Aircraft implements DataManager {
    private String aircraftID;
    private String model;
    private String brand;
    private int firstClassSeats;
    private int businessClassSeats;
    private int economicClassSeats;
    private String seatArrangement;

    // setters and getters


    public String getAircraftID() {
        return aircraftID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public int getBusinessClassSeats() {
        return businessClassSeats;
    }

    public void setBusinessClassSeats(int businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    public int getEconomicClassSeats() {
        return economicClassSeats;
    }

    public void setEconomicClassSeats(int economicClassSeats) {
        this.economicClassSeats = economicClassSeats;
    }

    public String getSeatArrangement() {
        return seatArrangement;
    }

    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    // Creates an aircraft from a result set
    public Aircraft(ResultSet row){
        readRow(row);
    }

    // create an aircraft from unique aircraftID
    public Aircraft(String aircraftID) throws NoMatchingRow {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Aircraft WHERE Aircraft_id = '"+aircraftID+"'");
        try {
            ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
            if (resultSet.next()){
                readRow(resultSet);
            }
            else{
                throw new NoMatchingRow("Username not found");
            }
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        } catch (SQLException e) {
            throw new NoMatchingRow("Username not found", e);
        }
    }
    @Override
    public void sync() {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Aircraft " +
                "WHERE Aircraft_id = '"+aircraftID+"'");
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
            String update = String.format("UPDATE Aircraft SET %s = '%s' WHERE Aircraft_id = '%s'", "Model", model, aircraftID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Aircraft SET %s = '%s' WHERE Aircraft_id = '%s'", "Brand", brand, aircraftID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Aircraft SET %s = '%s' WHERE Aircraft_id = '%s'", "Seat_arrangment", seatArrangement, aircraftID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Aircraft SET %s = %d WHERE Aircraft_id = '%s'", "First_class_seats", firstClassSeats, aircraftID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Aircraft SET %s = %d WHERE Aircraft_id = '%s'", "Business_class_seats", businessClassSeats, aircraftID);
            statement.executeUpdate(update);
            update = String.format("UPDATE Aircraft SET %s = %d WHERE Aircraft_id = '%s'", "Economic_class_seats", economicClassSeats, aircraftID);
            statement.executeUpdate(update);
        } catch (SQLException e) {
            throw new InvalidEntry("Updated value not accepted", e);
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
        attributes.put("Aircraft_id", aircraftID);
        attributes.put("Model", model);
        attributes.put("Brand", brand);
        attributes.put("Seat_arrangment", seatArrangement);
        attributes.put("First_class_seats", ""+firstClassSeats);
        attributes.put("Business_class_seats", ""+businessClassSeats);
        attributes.put("Economic_class_seats", ""+economicClassSeats);
        return attributes;
    }

    public static HashMap<String, String> getAttributes(String aircraftID) throws NoMatchingRow {
        return new Aircraft(aircraftID).getAttributes();
    }

    @Override
    public void readRow(ResultSet row) {
        try {
            aircraftID = row.getString("Aircraft_id");
            model = row.getString("Model");
            brand = row.getString("Brand");
            seatArrangement = row.getString("Seat_arrangment");
            firstClassSeats = row.getInt("First_class_seats");
            businessClassSeats = row.getInt("Business_class_seats");
            economicClassSeats = row.getInt("Economic_class_seats");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DBActionNotPerformed, SQLException {
        PreparedStatement preparedStatement =  QueryManager.prepareSelect("Select * from Aircraft where Aircraft_id = 'AC001'");
        ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        resultSet.next();
        Aircraft aircraft = new Aircraft(resultSet);
        resultSet.close();
        QueryManager.clean();
        System.out.println(aircraft.getAttributes());
        aircraft.setBrand("AirBus");
        try {
            aircraft.update();
        } catch (InvalidEntry invalidEntry) {
            invalidEntry.printStackTrace();
        }
        System.out.println("UPDATE ROW on table AND come and SAY SOMETHING");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        aircraft.sync();
        System.out.println(aircraft.getAttributes());
        Aircraft aircraft1;
        try {
            aircraft1 = new Aircraft("AC002");
            System.out.println(aircraft1.getAttributes());
        } catch (NoMatchingRow noMatchingRow) {
            System.out.println("aircraft not found\n"+noMatchingRow.getMessage());
        }
        System.out.println("All Systems are a go...");
    }
}
