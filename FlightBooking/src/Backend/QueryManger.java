package Backend;

import Backend.Exceptions.DBActionNotPerformed;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryManger {

    public static PreparedStatement prepareUpdate(String table, String var, String condition){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String query = "UPDATE " + table + "SET " + var + " =  ? WHERE " + condition;
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement(query);
        ConnectionHandler.clean();
        return preparedStatement;
    }
    public static void excutePreparedStatementUpdate(PreparedStatement preparedStatement) throws DBActionNotPerformed {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBActionNotPerformed("Can't preform action.\n" +
                e.getSQLState()+"\n"+e.getMessage());
        }
        ConnectionHandler.clean();
    }
    public static PreparedStatement prepareDelete(String table, String condition) {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String query = "DELETE FROM" + table + " WHERE " + condition;
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement(query);
        ConnectionHandler.clean();
        return preparedStatement;
    }
    public static PreparedStatement prepareInsert(String table, int fieldCount){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        String inserts = "?, ";
        String query = "INSERT INTO " + table + " VALUES <";
        for (int i = 0; i < fieldCount; i++){
            query += inserts;
        }
        query += "?>";
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement(query);
        ConnectionHandler.clean();
        return preparedStatement;
    }

    public static PreparedStatement prepareSelect(String query){
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        PreparedStatement preparedStatement = ConnectionHandler.getPreparedStatement(query);
        ConnectionHandler.clean();
        return preparedStatement;
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
        ConnectionHandler.clean();
        return resultSet;
    }

}
