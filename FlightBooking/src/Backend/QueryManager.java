package Backend;

import Backend.Exceptions.DBActionNotPerformed;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryManager {

    public static PreparedStatement prepareUpdate(String table, String var, String condition){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String query = "UPDATE " + table + " SET " + var + " =  ? WHERE " + condition;
        return ConnectionHandler.getPreparedStatement(query);
    }

    public static void excutePreparedStatementUpdateRepeated(PreparedStatement preparedStatement) throws DBActionNotPerformed {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBActionNotPerformed("Can't preform action.\n" +
                    e.getSQLState()+"\n"+e.getMessage());
        }
    }
    public static PreparedStatement prepareDelete(String table, String condition) {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String query = "DELETE FROM " + table + " WHERE " + condition;
        return ConnectionHandler.getPreparedStatement(query);
    }
    public static PreparedStatement prepareKnownInsert(String table){
        switch (table){
            case "Flight":
            case "Passenger":
                return prepareInsert(table, 10);
            case "Booking":
                return prepareInsert(table, 5);
            case "Location":
            case "Baggage":
                return prepareInsert(table, 4);
            case "Aircraft":
                return prepareInsert(table, 7);
            case "Checkin":
                return prepareInsert(table, 3);
            default:
                throw new IllegalArgumentException("Requested table is not available");
        }

    }

    public static PreparedStatement prepareInsert(String table, int fieldCount){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String inserts = "?, ";
        StringBuilder query = new StringBuilder("INSERT INTO " + table + " VALUES (");
        for (int i = 0; i < fieldCount - 1; i++){
            query.append(inserts);
        }
        query.append("?)");
        return ConnectionHandler.getPreparedStatement(query.toString());
    }
    public static PreparedStatement prepareSelect(String query){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        return ConnectionHandler.getPreparedStatement(query);
    }
    public static ResultSet executePreparedStatementSelect(PreparedStatement preparedStatement) throws DBActionNotPerformed {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        ResultSet resultSet;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DBActionNotPerformed("Can't preform action.\n" +
                    e.getSQLState()+"\n"+e.getMessage());
        }
        return resultSet;
    }
    public static void clean(){
        ConnectionHandler.clean();
    }

    public static void main(String[] args) throws DBActionNotPerformed {
        PreparedStatement preparedStatement = prepareSelect("Select * from Location");
        ResultSet resultSet = executePreparedStatementSelect(preparedStatement);
        System.out.println("All Systems good...");
        while (true){
            try {
                if (!resultSet.next()) break;
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + " ");
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        PreparedStatement preparedStatement1 = prepareInsert("Location", 4);
        try {
            preparedStatement1.setString(1, "pDRF");
            preparedStatement1.setString(2, "Addis Ababa");
            preparedStatement1.setString(3, "Ethiopia");
            preparedStatement1.setString(4, "Ethiopian Airlines");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        excutePreparedStatementUpdate(preparedStatement1);
        System.out.println("Update is a go.");

        PreparedStatement preparedStatement2 = prepareUpdate("Location", "City", "Location_ID = 'ADRF'");
        try {
            preparedStatement2.setString(1, "Belfast");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        QueryManager.excutePreparedStatementUpdate(preparedStatement2);
        PreparedStatement preparedStatement3 = prepareDelete("Location", "Location_ID != 'ADD,ETH'");
        QueryManager.excutePreparedStatementUpdate(preparedStatement3);
        System.out.println("All systems still good...");
    }

    public static void excutePreparedStatementUpdate(PreparedStatement preparedStatement1) throws DBActionNotPerformed {
        excutePreparedStatementUpdateRepeated(preparedStatement1);
        ConnectionHandler.clean();
    }
}
