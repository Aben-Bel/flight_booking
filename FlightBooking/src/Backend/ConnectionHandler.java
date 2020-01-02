package Backend;

import Backend.Exceptions.ConnectionError;
import Backend.Exceptions.NoDriverFound;

import java.sql.*;

public class ConnectionHandler {
    private static Connection connection;
    private static String driverpath = "com.mysql.cj.jdbc.Driver";
    private static String dbLocation = "jdbc:mysql://localhost:3306/FlightBooking";
    private static String user = "root";
    private static String password ="root";

    static {
        create();
    }

    public static Statement getStatement() {
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new ConnectionError("Can't create more statements");
        }
        return statement;
    }
    public static PreparedStatement getPreparedStatement(String query){
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new ConnectionError("Can't create more prepared statements");
        }
        return statement;
    }
    public static void clean(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void create(){
        try {
            Class.forName(driverpath);
        } catch (ClassNotFoundException e) {
            throw new NoDriverFound("You don't have a MySQL JDBC driver.");
        }
        try {
            connection = DriverManager.getConnection(dbLocation, user, password);
        } catch (SQLException e) {
            throw new ConnectionError("Something wrong with username info or password");
        }
    }

    public static boolean isDisconnected(){
        boolean disconnected = true;
        try {
            disconnected = connection.isClosed();
        }catch (SQLException e){
            e.getSQLState();
        }
        return disconnected;
    }

    public static void main(String[] args){
        System.out.println((connection == null));
        ConnectionHandler.create();
        System.out.println((connection == null));
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement("SELECT * FROM LOCATION");
        Statement preparedStatement1 = ConnectionHandler.getStatement();
        try {
            preparedStatement1.executeUpdate("UPDATE Location SET City = \'Success\' WHERE Location_ID = \'PDRF\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionHandler.clean();
        try {
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println((connection == null));
    }
}
