package Backend;

import Backend.Exceptions.DBActionNotPerformed;
import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class User implements DataManager {

    private String username;
    private String newUsername;
    private String firstName;
    private String middleName;
    private String lastName;
    private double loyalityMiles;
    private String gender;
    private String phone;
    private String email;
    private Date dateOfBirth;


    // Creates a user from a result set
    public User(ResultSet row){
        readRow(row);
    }

    // create a user from unique username
    public User(String username) throws NoMatchingRow {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Passenger WHERE Username = '"+username+"'");
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
            throw new NoMatchingRow("Username not found");
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.newUsername = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getLoyalityMiles() {
        return loyalityMiles;
    }

    public void setLoyalityMiles(double loyalityMiles) {
        this.loyalityMiles = loyalityMiles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public void setPassword(String pass) throws InvalidEntry {
        if (ConnectionHandler.isDisconnected()) ConnectionHandler.create();
        Statement statement = ConnectionHandler.getStatement();
        String update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Password", pass, username);
        try {
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
    public void sync() {
        PreparedStatement preparedStatement = QueryManager.prepareSelect("SELECT * FROM Passenger " +
                "WHERE Username = \'"+username+"\'");
        ResultSet resultSet = null;
        try {
            resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            dbActionNotPerformed.printStackTrace();
        }
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
            String update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "First_name", firstName, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Middle_name", middleName, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Last_name", lastName, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Phone", phone, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Email", email, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Gender", gender, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = %f WHERE Username = '%s'", "Loyality_miles", loyalityMiles, username);
            statement.executeUpdate(update);
            update = String.format("UPDATE Passenger SET %s = '%s' WHERE Username = '%s'", "Username", newUsername, username);
            statement.executeUpdate(update);
            PreparedStatement preparedStatement = QueryManager.prepareUpdate("Passenger", "Date_of_birth", "Username = '"+username+"'");
            preparedStatement.setDate(1, dateOfBirth);
            QueryManager.excutePreparedStatementUpdate(preparedStatement);

        } catch (SQLException e) {
            throw new InvalidEntry("Updated value not accepted", e);
        } catch (DBActionNotPerformed dbActionNotPerformed) {
            throw new InvalidEntry(dbActionNotPerformed.getMessage());
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
        attributes.put("Username", username);
        attributes.put("First_name", firstName);
        attributes.put("Middle_name", middleName);
        attributes.put("Last_name", lastName);
        attributes.put("Loyality_Miles", ""+loyalityMiles);
        attributes.put("Gender", gender);
        attributes.put("Phone", phone);
        attributes.put("Email", email);
        attributes.put("Date", ""+dateOfBirth);
        return attributes;
    }

    @Override
    public void readRow(ResultSet row) {
        try {
            username = row.getString("Username");
            newUsername = username;
            loyalityMiles = row.getDouble("Loyality_Miles");
            firstName = row.getString("First_name");
            middleName = row.getString("Middle_name");
            lastName = row.getString("Last_name");
            gender = row.getString("Gender");
            phone = row.getString("Phone");
            email = row.getString("Email");
            dateOfBirth = row.getDate("Date_of_birth");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // todo implement isOnFlight
    public boolean isOnFlight(){
        return false;
    }
    public boolean isOnFlight(Date date){
        return false;
    }
    public static void main(String[] args) throws DBActionNotPerformed, SQLException {
        PreparedStatement preparedStatement =  QueryManager.prepareSelect("Select * from Passenger where Username = 'XXXXX'");
        ResultSet resultSet = QueryManager.executePreparedStatementSelect(preparedStatement);
        resultSet.next();
        User user = new User(resultSet);
        resultSet.close();
        QueryManager.clean();
        System.out.println(user.getAttributes());
        user.setEmail("ThisIsMyEmail@website.domain");
        try {
            user.update();
        } catch (InvalidEntry invalidEntry) {
            invalidEntry.printStackTrace();
        }
        System.out.println("UPDATE ROW on table AND come and SAY SOMETHING");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        user.sync();
        System.out.println(user.getAttributes());
        User user1;
        try {
            user1 = new User("XXXX");
            System.out.println(user1.getAttributes());
        } catch (NoMatchingRow noMatchingRow) {
            System.out.println("Username not found");
        }
        System.out.println("All Systems are a go...");
    }
}
