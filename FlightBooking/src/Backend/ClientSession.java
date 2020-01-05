package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.OverbookingError;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientSession {
    protected String uniqueID;

    ClientSession(){

    }

    private ClientSession(String uniqueID){
        this.uniqueID = uniqueID;
    }

    public static ArrayList<HashMap<String, String>> findFlights(Timestamp arrivalDate, Timestamp departureDate, String arrivalCity, String departureCity) {
        String query ="{CALL findFlight(?, ?, ?, ?)}";
        CallableStatement flightQuery = ConnectionHandler.prepareCall(query);
        try {
            flightQuery.setTimestamp(1, arrivalDate);
            flightQuery.setTimestamp(2, departureDate);
            flightQuery.setString(3, arrivalCity);
            flightQuery.setString(4, departureCity);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Can't input these queries | " + e.getMessage());
        }
        try {
            ResultSet resultset = flightQuery.executeQuery();
            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            while (resultset.next()){
                results.add(new Flight(resultset).getAttributes());
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> findBookedSeats(String flightID) {
        ArrayList<String> results = new ArrayList<>();
        String query = "SELECT Seat_number FROM Booking WHERE Flight_Flight_ID = ?";
        PreparedStatement preparedStatement = QueryManager.prepareSelect(query);
        try {
            preparedStatement.setString(1, flightID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet resultset = QueryManager.executePreparedStatementSelect(preparedStatement);
             while (resultset.next()){
                 results.add(resultset.getString(1));
             }
        } catch (DBActionNotPerformed | SQLException dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        }
        return results;
    }

    public boolean isSessionAlive() {
        String checkerQuery ="{CALL checkIfBooking(?)}";
        CallableStatement bookingQuery = ConnectionHandler.prepareCall(checkerQuery);

        try {
            bookingQuery.setString(1, uniqueID);
            ResultSet resultSet = bookingQuery.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void holdSeat(String flightID) throws OverbookingError {
        if (isSessionAlive()){
            throw new OverbookingError("Client already reserved seat.");
        }

        String updateQuery = "update Flight set Seat_Available = Seat_Available - 1 where Flight_ID = " + flightID;

        String query = "create event "+uniqueID+" on schedule" +
                "    at current_time + interval 10 minute" +
                "    on completion not preserve" +
                "    do" +
                "    update Flight set Seats_Available = Seats_Available + 1 where Flight_ID = ?";
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement(query);
        try {
            preparedStatement.setString(1, flightID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public static void main(String[] args){
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Flight");
        try {
            preparedStatement.setString(1, "pewpew");
            preparedStatement.setString(3, "2020-03-12 00:00:00");
            preparedStatement.setString(2, "2020-03-22 20:00:00");
            preparedStatement.setDouble(4, 32000);
            preparedStatement.setDouble(5, 24000);
            preparedStatement.setDouble(6, 18000);
            preparedStatement.setInt(7, 145);
            preparedStatement.setString(8, "AC001");
            preparedStatement.setString(9, "LO251-1");
            preparedStatement.setString(10, "LO253-1");
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println("And then "+e.getErrorCode()+"\nFinally "+e.getMessage());
        }
        System.out.println(findFlights(Timestamp.valueOf("2020-03-22 20:00:00"), Timestamp.valueOf("2020-03-12 00:00:00"), "Addis Ababa", "Beijing"));
        System.out.println(findBookedSeats("FL-ADD-01"));
        ClientSession clientSession = new ClientSession("hiiii");
        try {
            clientSession.holdSeat("FL-ADD-01");
        } catch (OverbookingError overbookingError) {
            overbookingError.printStackTrace();
        }
    }
}
