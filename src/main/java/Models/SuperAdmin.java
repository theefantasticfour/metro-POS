package Models;

import Entites.Branch;
import Entites.Product;
import Entites.Transactions;
import Utils.Values;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SuperAdmin {
    private String username;
    private String password;
    public SuperAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // ------- Getters and Setters -------
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    //  ---------- database operations ----------
    public int getID(String query,int id)
    {
        Connection connection = ConnectionConfig.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id =  resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
     }
    public int getUniqueBranchId()
    {
        int id =1; //for branch id, the first branch id will be 1
        String query = "SELECT MAX(branch_id) FROM Branch";
        return getID(query,id);

    }
    public int getUniqueManagerId() {
         //logic to get unique manager id either from DB or txt file
        int id =7001; //for manager id, the first manager id will be 7001
        String query = "SELECT MAX(employee_id) FROM Employee";
        return getID(query,id);
    }
    // DB operations
    public Boolean RegisterBranch(int branchId,String name, String city,String Address,String phoneNo,int noOfEmployees,Boolean Status) {
        Boolean isRegistered = false; // if duplicate exsits or due to some other reason we cannot register it
        System.out.println("reached in supera admin model method regsiter branch\n and branch name = "+name);
        // logic to register branch in DB

        Connection connection = ConnectionConfig.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Branch VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,branchId);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,city);
            preparedStatement.setString(4,Address);
            preparedStatement.setString(5,phoneNo);
            preparedStatement.setInt(6,noOfEmployees); // we should set it to zero initially
            preparedStatement.setBoolean(7,true);
            preparedStatement.executeUpdate();
            System.out.println("Branch Registered SUccessfullyyyyyyyyyy");
            isRegistered = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("isregistered value = "+isRegistered+"    wese true ani chahiye");
        return isRegistered;
    }
    public boolean doesmanagerexist(int branchId)
    {
        Connection connection = ConnectionConfig.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE branch_id = ? AND role = 'Branch Manager'");
            preparedStatement.setInt(1,branchId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean createManagerOfBranch(int branchId,int managerId,String name,Float Salary , String email,String password) {
        if(doesmanagerexist(branchId))
        {
            //show joptionpane that manager already exists
            JOptionPane.showMessageDialog(null,"Manager already exists for this branch");
            return false;

        }
        Boolean isCreated = false; // if duplicate exsits or due to some other reason we cannot create it
        System.out.println("reached in create manager methoddddddddddddddd");
        // logic to create manager in DB
        Connection connection = ConnectionConfig.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,branchId);
            preparedStatement.setInt(2,managerId);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,password);
            preparedStatement.setString(6, Values.BRANCH_MANAGER);
            preparedStatement.setFloat(7,Salary);
            preparedStatement.setBoolean(8,false);
            preparedStatement.setBoolean(9,true);
            preparedStatement.executeUpdate();
            System.out.println("Manager Created Successfullyyyyyyy");
            isCreated = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isCreated;
    }
    public ArrayList<Branch> getBranches() {
        ArrayList<Branch> branches = new ArrayList<Branch>();
        Connection connection = ConnectionConfig.getConnection();
        try {
            // Query to get all branches
            String branchQuery = "SELECT branch_id, name, city, address, phone, no_of_employees, active FROM Branch";
            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
            ResultSet branchResultSet = branchStmt.executeQuery();

            while (branchResultSet.next()) {
                Branch branch = new Branch();

                // Set branch details
                branch.setBranchId(branchResultSet.getInt("branch_id"));
                branch.setName(branchResultSet.getString("name"));
                branch.setCity(branchResultSet.getString("city"));
                branch.setAddress(branchResultSet.getString("address"));
                branch.setPhone(branchResultSet.getString("phone"));
                branch.setNoOfEmployees(branchResultSet.getInt("no_of_employees"));
                branch.setActive(branchResultSet.getBoolean("active"));

                // Query to get manager details for the current branch
                String managerQuery = "SELECT employee_id, name, salary FROM Employee WHERE branch_id = ? AND role = 'Branch Manager' LIMIT 1";
                PreparedStatement managerStmt = connection.prepareStatement(managerQuery);
                managerStmt.setInt(1, branch.getBranchId());
                ResultSet managerResultSet = managerStmt.executeQuery();

                if (managerResultSet.next()) {
                    // Set manager details if a manager exists
                    branch.setManagerId(managerResultSet.getInt("employee_id"));
                    branch.setManagerName(managerResultSet.getString("name"));
                    branch.setManagerSalary(managerResultSet.getFloat("salary"));
                }

                branches.add(branch);
                managerStmt.close();
            }

            branchStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log error, rethrow as custom exception, etc.)
        }
        return branches;
        // logic to get all branches from DB

        // Note : Branch have manager name, manager salry and managerid that would be set by calling
        //        setManagerName and setManagerSalary setManagerId methods of Branch class
        //        because there is a possible case that branch have no manager assigned yet but branch has been created

    }
    public Boolean updateBranch(int branchId,String city,String Address,String phoneNo,int noOfEmployees,Boolean Status,String managerName,Float managerSalary) {
        Boolean isUpdated = false; // if branch not found or due to some other reason we cannot update it

        // logic to update branch in DB
        // user can update  anything in the branch so make sure that you update all the fields including manager data recieved in parameters
        Connection connection = ConnectionConfig.getConnection();
        try {
            // Update branch details
            String branchQuery = "UPDATE Branch SET city = ?, address = ?, phone = ?, no_of_employees = ?, active = ? WHERE branch_id = ?";
            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
            branchStmt.setString(1, city);
            branchStmt.setString(2, Address);
            branchStmt.setString(3, phoneNo);
            branchStmt.setInt(4, noOfEmployees);
            branchStmt.setBoolean(5, Status);
            branchStmt.setInt(6, branchId);
            branchStmt.executeUpdate();

            // Update manager details
            String managerQuery = "UPDATE Employee SET name = ?, salary = ? WHERE branch_id = ?";
            PreparedStatement managerStmt = connection.prepareStatement(managerQuery);
            managerStmt.setString(1, managerName);
            managerStmt.setFloat(2, managerSalary);
            managerStmt.setInt(3, branchId);
            managerStmt.executeUpdate();

            isUpdated = true;
            branchStmt.close();
            managerStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    public Boolean deleteBranch(int branchId) {
        Boolean isDeleted = false; // if branch not found or due to some other reason we cannot delete it

        // logic to delete branch in DB
        // NOTE : we will not delete the branch we will just change the status of the branch to false
        //         so that it will not be shown in the list of branches
        Connection connection = ConnectionConfig.getConnection();
        try {
            // Delete branch
            String branchQuery = "UPDATE Branch SET active = false WHERE branch_id = ?";
            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
            branchStmt.setInt(1, branchId);
            branchStmt.executeUpdate();

            isDeleted = true;
            branchStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    // reports + graphs
    // of Sales || Remaining Stock || Profit
    // All this to be calculated daily, weekly, monthly, yearly
    public static ArrayList<Transactions> getTransactions(int branch_id, String type) {
        ArrayList<Transactions> transactions = new ArrayList<>();
        Connection connection = ConnectionConfig.getConnection();

        // Get today's date
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Adjust the calendar based on the type
        switch (type.toLowerCase()) {
            case "weekly":
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                break;
            case "monthly":
                calendar.add(Calendar.DAY_OF_YEAR, -30);
                break;
            case "yearly":
                calendar.add(Calendar.DAY_OF_YEAR, -365);
                break;
            default:
                throw new IllegalArgumentException("Invalid report type: " + type);
        }

        // Get the date range
        Date startDate = calendar.getTime();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(today.getTime());

        // SQL query to fetch transactions
        String query;
        if (branch_id == -1) {
            // Get all transactions for all branches
            query = "SELECT * FROM Transaction WHERE transaction_date BETWEEN ? AND ?";
        } else {
            // Get transactions for a specific branch
            query = "SELECT * FROM Transaction WHERE branch_id = ? AND transaction_date BETWEEN ? AND ?";
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            // Set parameters for the query
            if (branch_id != -1) {
                stmt.setInt(1, branch_id);
                stmt.setDate(2, sqlStartDate);
                stmt.setDate(3, sqlEndDate);
            } else {
                stmt.setDate(1, sqlStartDate);
                stmt.setDate(2, sqlEndDate);
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int branchId = rs.getInt("branch_id");
                int cashierId = rs.getInt("cashier_id");
                int vendorId = rs.getInt("vendor_id");
                int productId = rs.getInt("product_id");
                Date transactionDate = rs.getDate("transaction_date");
                int quantity = rs.getInt("quantity");
                float transactionAmount = rs.getFloat("transaction_amount");
                float transactionCost = rs.getFloat("transaction_cost");

                // Create a Transaction object
                Transactions transaction = new Transactions();
                transaction.setTransactionId(transactionId);
                transaction.setBranchId(branchId);
                transaction.setCashierId(cashierId);
                transaction.setVendorId(vendorId);
                transaction.setProductId(productId);
                transaction.setTransactionDate(transactionDate);
                transaction.setQuantity(quantity);
                transaction.setTransactionAmount(transactionAmount);
                transaction.setTransactionCost(transactionCost);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static ArrayList<Product> getRemainingStock(int branchId)
    {
        System.out.println(branchId);
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = ConnectionConfig.getConnection(); // Assuming you have a method to get the DB connection

        // SQL query to get the remaining stock of products
        String query;
        if (branchId == -1) {
            // Get stock for all branches
            query = "SELECT p.product_id, p.name, SUM(p.stock_quantity) AS stock_quantity "
                    + "FROM Product p GROUP BY p.product_id, p.name";
        } else {
            // Get stock for a specific branch
            query = "SELECT p.product_id, p.name, p.stock_quantity "
                    + "FROM Product p WHERE p.branch_id = ? "
                    + "GROUP BY p.product_id, p.name";
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the branch ID if it's not -1
            if (branchId != -1) {
                stmt.setInt(1, branchId);
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process the result set and add products to the list
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("name");
                int stockQuantity = rs.getInt("stock_quantity");

                // Create a Product object and set its properties
                Product product = new Product();
                product.setProductId(productId);
                product.setName(name);
                product.setStockQuantity(stockQuantity);

                // Add the product to the list
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public static ArrayList<Integer> getAllBranchIds() {
        ArrayList<Integer> branches = new ArrayList<Integer>();
        // logic to get all branch ids
        // getBranches() sai sari branches mil jain gi or jese tumhe sahi lage
        // phir unki ids nikal kar forward karna hai

        Connection connection = ConnectionConfig.getConnection();
        try {
            // Query to get all branches
            String branchQuery = "SELECT branch_id FROM Branch";
            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
            ResultSet branchResultSet = branchStmt.executeQuery();

            while (branchResultSet.next()) {
                branches.add(branchResultSet.getInt("branch_id"));
            }

            branchStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log error, rethrow as custom exception, etc.)
        }

        return branches;
    }

    // -------- Logical Operations -------

    public static Float CalculateProfit(int BranchId,String type) {
        // logic to generate profit report by using getTransactions and type = daily, monthly, yearly
        // if -1 then we will get the profit of all branches combined
        // logic to calculate profit from transactions
        // Danish this will be implemented as selling price - cost price of all products invloveld in the transaction
        return 0.0f;
    }

}
