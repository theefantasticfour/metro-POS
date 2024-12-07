package Models;

import Entites.Employee;
import Entites.Product;
import Entites.Transactions;
import Utils.Values;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public class BranchManager {
    String Email;
    String password;
    int branchId;
    int managerId;
    int TotalEmployees;

    public BranchManager(String Email, String password) {
        this.Email = Email;
        this.password = password;
        setManagerId();
        setBranchId();
        setTotalEmployeesinitially();

        System.out.println("Branch Manager Model initialized");
    }
    // setters
    public void setManagerId()
    {
        // set the manager id from the db after looking up against email
        Connection connection = ConnectionConfig.getConnection();
        try {
            // Query to find the manager ID based on email
            String query = "SELECT employee_id FROM Employee WHERE email = ? AND role = 'Branch Manager'";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);

            // Assuming you have the manager's email stored in a field or variable
            preparedStatement.setString(1, this.Email); // Replace `managerEmail` with your field name for the manager's email.
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Set the manager ID
                System.out.println("///...Manager Id currently = " + resultSet.getInt("employee_id"));
                this.managerId = resultSet.getInt("employee_id"); // Replace `managerId` with your class field name.
            }
            else
            {
                System.out.println("No manager found with the specified email.");
            }
        } catch (SQLException e) {
            System.err.println("Error while setting manager ID: " + e.getMessage());
        }

    }
    public void setBranchId() {
        // Set the branch ID from the database after looking up against email
        Connection connection = ConnectionConfig.getConnection();
        try {
            // Query to find the branch ID based on email
            String query = "SELECT branch_id FROM Employee WHERE email = ?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);

            // Assuming you have the employee's email stored in a field or variable
            preparedStatement.setString(1, this.Email); // Replace `Email` with your field name for the employee's email.
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Set the branch ID
                System.out.println("///...Branch Id currently = " + resultSet.getInt("branch_id"));
                this.branchId = resultSet.getInt("branch_id"); // Replace `branchId` with your class field name.
            } else {
                System.out.println("No branch found with the specified email.");
            }
        } catch (SQLException e) {
            System.err.println("Error while setting branch ID: " + e.getMessage());
        }
    }

    public void setTotalEmployeesinitially()
    {
        //if this function gives error then is se pehle setbranchid call krlo then is method ko call kro
        // set the total employees from the db after looking up against branch id
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT no_of_employees FROM Branch WHERE branch_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.branchId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.TotalEmployees = resultSet.getInt("no_of_employees");
            } else {
                System.out.println("No branch found with the specified branch ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while setting total employees: " + e.getMessage());
        }
    }

    public void updateTotalEmployees(Boolean isAdded)
    {
//        if (isAdded)
//        {
//            TotalEmployees++;
//        }
//        else {
//            TotalEmployees--;
//        }
        // update the total employees after adding a new employee
        // increase by 1 if isAdded is true, decrease by 1 if isAdded is false
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query;
            if (isAdded) {
                query = "UPDATE Branch SET no_of_employees = no_of_employees + 1 WHERE branch_id = ?";
            } else {
                query = "UPDATE Branch SET no_of_employees = no_of_employees - 1 WHERE branch_id = ?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.branchId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0)
            {
                //the following should be implemented here, but for now start pr hi hai method ke
//                if (isAdded)
//        {
//            TotalEmployees++;
//        }
//        else {
//            TotalEmployees--;
//        }
                System.out.println("Total employees updated successfully.");
            } else {
                System.out.println("Error updating total employees.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating total employees: " + e.getMessage());
        }
    }
    // DB operations
    public Boolean changePassword(String Password) {
        System.out.println("Branch Manager Password changed");
        Connection connection = ConnectionConfig.getConnection();

        try {
            // Update the password for the Branch Manager
            String query = "UPDATE Employee SET password = ? WHERE employee_id = ? AND role = 'Branch Manager'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Password);
            preparedStatement.setInt(2, this.managerId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully.");

                // Set is_password_changed to true
                String updateFlagQuery = "UPDATE Employee SET is_password_changed = true WHERE employee_id = ?";
                PreparedStatement flagStatement = connection.prepareStatement(updateFlagQuery);
                flagStatement.setInt(1, this.managerId);

                int flagRowsUpdated = flagStatement.executeUpdate();
                if (flagRowsUpdated > 0) {
                    System.out.println("is_password_changed flag set to true.");
                    return true;
                } else {
                    System.out.println("Failed to update is_password_changed flag.");
                }
            } else {
                System.out.println("BM model, Error updating password. rows affected = " + rowsUpdated);
            }
        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
        }

        return false;
    }

    public boolean isPasswordChanged() {
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT is_password_changed FROM Employee WHERE employee_id = ? AND role = 'Branch Manager'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.managerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean("is_password_changed");
            } else {
                System.out.println("No branch manager found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while checking password status: " + e.getMessage());
        }
        return false; // Default to false if no result or an error occurs
    }

    public int getUniqueEmployeeId()
    {
        // get the unique employee id
        // get the max employee id from the db and return max+1
        int id=9000;
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT MAX(employee_id) AS max_id FROM Employee";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("max_id") + 1;
            } else {
                System.out.println("No employee found.");
            }
        } catch (SQLException e) {
            System.err.println("Error while getting unique employee ID: " + e.getMessage());
        }
        return id;
    }

    public Boolean addNewEmployee(String employeeType, String employeeName, String employeeEmail, Float employeeSalary) {
        // Add new employee
        System.out.println("New Employee added");

        // 4 data to be included coming from parameters
        String password = "123"; // default password
        int employeeId = getUniqueEmployeeId(); // Assuming this method exists to generate a unique employee ID
        Boolean isPasswordChanged = false;

        // Get branchId from the class attribute (assuming it's already set)
        int branchId = this.branchId; // Replace with the actual attribute if needed
        // SQL query to insert new employee into the Employee table
        String query = "INSERT INTO Employee (branch_id, employee_id, name, email, password, role, salary, is_password_changed, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set values for the prepared statement
            preparedStatement.setInt(1, branchId); // branch_id
            preparedStatement.setInt(2, employeeId); // employee_id
            preparedStatement.setString(3, employeeName); // employee name
            preparedStatement.setString(4, employeeEmail); // employee email
            preparedStatement.setString(5, password); // employee password
            preparedStatement.setString(6, employeeType); // role
            preparedStatement.setFloat(7, employeeSalary); // salary
            preparedStatement.setBoolean(8, isPasswordChanged); // password change status
            preparedStatement.setBoolean(9, true); // employee status (active)

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee added successfully.");

                // If employee is added successfully, update the total number of employees in the branch
                updateTotalEmployees(true); // Increase the total employees by 1
                return true; // Return true if employee was added successfully
            } else {
                System.out.println("Failed to add new employee.");
                return false; // Return false if insert was unsuccessful
            }
        } catch (SQLException e) {
            System.err.println("Error adding new employee: " + e.getMessage());
            return false; // Return false in case of any SQL exception
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        System.out.println("All Employees will be fetched here");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Connection connection = ConnectionConfig.getConnection(); // Assume this provides a valid DB connection
        System.out.println("idhar tk agya ");
        String query = "SELECT * FROM Employee WHERE branch_id = ? AND (role = ? OR role = ?)"; // Adjust table and column names as necessary

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.branchId); // Bind the branchId value to the query
            stmt.setString(2, Values.CASHIER);
            stmt.setString(3,Values.DATA_ENTRY);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setRole(rs.getString("role")); // Replace with actual column name
                    employee.setName(rs.getString("name")); // Replace with actual column name
                    employee.setEmail(rs.getString("email")); // Replace with actual column name
                    employee.setSalary(rs.getFloat("salary")); // Replace with actual column name
                    employee.setStatus(rs.getBoolean("Status")); // Replace with actual column name

                    employees.add(employee); // Add the employee to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception
        }
        System.out.println("All Employees fetched, method end heereeee");
        return employees;
    }


    public boolean updateEmployee(String employeeEmail, String employeeName, String employeePassword, String employeeRole, Float employeeSalary, Boolean status) {
        // Establish database connection
        Connection connection = ConnectionConfig.getConnection();

        // Define the SQL query to update employee details
        String query = "UPDATE Employee SET name = ?, password = ?, role = ?, salary = ?, is_password_changed = ?, status = ? WHERE email = ?";

        try {
            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the query
            preparedStatement.setString(1, employeeName);        // employee_name
            preparedStatement.setString(2, employeePassword);    // employee_password
            preparedStatement.setString(3, employeeRole);        // employee_role
            preparedStatement.setFloat(4, employeeSalary);       // employee_salary
            preparedStatement.setBoolean(6, status);             // employee status
            preparedStatement.setString(7, employeeEmail);       // email (used as unique identifier)

            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            // If any row was updated, return true, otherwise return false
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
                return true;
            } else {
                System.out.println("No rows updated. Please check the employee details.");
            }

        } catch (SQLException e) {
            // Print any SQL exceptions that occur
            System.err.println("Error updating employee: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteEmployee( String employeeEmail) {
        // delete employee
        updateTotalEmployees(false); // decrease by 1
        System.out.println("Employee deleted");
        // set status to inactive;
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "UPDATE Employee SET Status = false WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeEmail);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee deleted successfully.");
                return true;
            } else {
                System.out.println("Error deleting employee.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
        return true;
    }

    public ArrayList<Transactions> getTransactions(String type) {
        return SuperAdmin.getTransactions(branchId,type);
    }

    public ArrayList<Product> getStockReport() {
        return SuperAdmin.getRemainingStock(branchId);
    }

    public Float getProfitReport(String type) {
        return SuperAdmin.CalculateProfit(branchId,type);
    }
}
