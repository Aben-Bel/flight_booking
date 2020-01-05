package Backend;

import Backend.Exceptions.NoMatchingRow;
import Backend.Exceptions.OverbookingError;
import Backend.Exceptions.SessionExpired;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSession extends ClientSession {
    User user;

    protected UserSession(){

    }

    public UserSession(String username, String password) throws NoMatchingRow {
        user = new User(username, password);
        super.uniqueID = user.getUsername();
    }

    public UserSession(User user) {
        this.user = user;
        super.uniqueID = user.getUsername();
    }

    public User getUser() {
        return user;
    }
    public void registerBaggage(ArrayList<ArrayList<String>> info, String flightID){
        PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Baggage");
        for (ArrayList<String> item : info){
            try {
                preparedStatement.setString(1, item.get(0));
                preparedStatement.setString(2, item.get(1));
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, flightID);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void checkIn(String flightID){
        PreparedStatement preparedStatement = QueryManager.prepareUpdate("Checkin", "Boards",
                String.format("%s = %s and %s = %s", "Passenger_Username", user.getUsername(), "Flight_Flight_ID", flightID));
        try {
            preparedStatement.setInt(1, 0);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void holdSeat(String flightID) throws OverbookingError {
        String checkerQuery ="{CALL checkIfOnFlight(?)}";
        CallableStatement flightCheckQuery = ConnectionHandler.prepareCall(checkerQuery);

        try {
            flightCheckQuery.setString(1, uniqueID);
            ResultSet resultSet = flightCheckQuery.executeQuery();
            if (resultSet.next()){
                throw new OverbookingError("Client already booked flight at this time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.holdSeat(flightID);
    }

    public void reserveFlight(String flightID, String ticketClass, String seatNumber) throws OverbookingError, SessionExpired {
        if (isSessionAlive()){
            double price;
            try {
                Flight flight = new Flight(flightID);
                switch (ticketClass){
                    case "First Class":
                        price = flight.getFirstClassPrice();
                        break;
                    case "Business Class":
                        price = flight.getBusinessClassPrice();
                        break;
                    case "Economic Class":
                        price = flight.getEconomicClassPrice();
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown Ticket class");
                }
                if (user.isOnFlight(flight.getDepartureDate())){
                    throw new OverbookingError("Client already has flight at this time");
                }
            } catch (NoMatchingRow noMatchingRow) {
                throw new IllegalArgumentException("No flight ID with this name " + noMatchingRow.getMessage());
            }
            PreparedStatement preparedStatement = QueryManager.prepareKnownInsert("Booking");
            try {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, flightID);
                preparedStatement.setDouble(3, price);
                preparedStatement.setString(4, ticketClass);
                preparedStatement.setString(5, seatNumber);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement preparedStatement1 = QueryManager.prepareKnownInsert("Checkin");
            try {
                preparedStatement1.setString(1, user.getUsername());
                preparedStatement1.setString(2, flightID);
                preparedStatement1.setInt(3, 0);
                preparedStatement1.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new SessionExpired("Session is over");
        }
    }
}
