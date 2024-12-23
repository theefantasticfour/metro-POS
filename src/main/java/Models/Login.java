package Models;

import Utils.Values;

import java.sql.*;

public class Login {
    private String username, password, typeOfUser;

    public Login() {
        System.out.println("Login model initialized");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Boolean validateUser() {

        System.out.println("Reached in validateuser() method");
        Connection connection = ConnectionConfig.getConnection(); // Get the database connection
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query;
        if(typeOfUser.equals(Values.SUPER_ADMIN))
        {
            query = "SELECT * FROM SuperAdmin WHERE email = ? AND password = ?"; // Query to validate the user
        }
        else {
            query = "SELECT * FROM Employee WHERE email = ? AND password = ? AND role = ?"; // Query to validate the user
        }
        try {
            // Prepare the statement to prevent SQL injection
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            preparedStatement.setString(2, password); // Set the password
            if(!typeOfUser.equals(Values.SUPER_ADMIN))
            {
                preparedStatement.setString(3, typeOfUser); // Set the type of user
            }
            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // If a record exists, the user is valid
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error during user validation: " + e.getMessage());
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return false; // Return false if no valid user is found
    }
}
