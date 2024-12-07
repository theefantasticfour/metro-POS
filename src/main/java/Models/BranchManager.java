package Models;

import Entites.Employee;
import Entites.Product;
import Entites.Transactions;
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
    public Boolean changePassword(String Password)
    {
        System.out.println("Branch Manager Password changed");
        // sirf update karna hai against the id no checks to be performed
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "UPDATE Employee SET password = ? WHERE employee_id = ? AND role = 'Branch Manager'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Password);
            preparedStatement.setInt(2, this.managerId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully.");
                return true;
            } else {
                System.out.println("BM model,   Error updating password. rowaffected = " + rowsUpdated);
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

    public Boolean addNewEmployee(String employeeType,String EmployeeName, String EmployeeEmail, Float EmployeeSalary) {
        // add new employee

        System.out.println("New Employee added");

        // 4 data to be included coming from parameters
        // branch id from class attribute
        String password = "123";
        int employeeId = getUniqueEmployeeId();
        Boolean isPasswordChanged = false;
        if (true) // if added successfully
        {
            updateTotalEmployees(true); // increase by 1

        }
        return true;

    }

    public ArrayList<Employee> getAllEmployees() {
        // get all employees from the db where branch id is this.branchId
        return new ArrayList<Employee>();
    }

    public boolean updateEmployee(String employeeType, String employeeName, String employeeEmail, Float employeeSalary,Boolean status) {
        // update employee
        System.out.println("Employee updated");
        return true;
    }
    public boolean deleteEmployee( String employeeEmail) {
        // delete employee
        updateTotalEmployees(false); // decrease by 1
        System.out.println("Employee deleted");
        // set status to inactive;
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
