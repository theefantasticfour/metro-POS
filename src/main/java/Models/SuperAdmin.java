package Models;

import Entites.Branch;
import Entites.Product;
import Entites.Transactions;
import Utils.Values;

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
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                id =  resultSet.getInt(1) + 1;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
 //       return id;
    return  0 ;
     }
    public int getUniqueBranchId()
    {
//        int id =1; //for branch id, the first branch id will be 1
//        String query = "SELECT MAX(branch_id) FROM Branch";
//        return getID(query,id);
        return 0;
    }
    public int getUniqueManagerId() {
        // logic to get unique manager id either from DB or txt file
//        int id =7001; //for manager id, the first manager id will be 7001
//        String query = "SELECT MAX(employee_id) FROM employee";
//        return getID(query,id);
        return 0;
    }
    // DB operations
    public Boolean RegisterBranch(int branchId,String name, String city,String Address,String phoneNo,int noOfEmployees,Boolean Status) {
//        Boolean isRegistered = false; // if duplicate exsits or due to some other reason we cannot register it
//        System.out.println("reached in supera admin model method regsiter branch\n and branch name = "+name);
//        // logic to register branch in DB
//
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Branch VALUES(?,?,?,?,?,?,?)");
//            preparedStatement.setInt(1,branchId);
//            preparedStatement.setString(2,name);
//            preparedStatement.setString(3,city);
//            preparedStatement.setString(4,Address);
//            preparedStatement.setString(5,phoneNo);
//            preparedStatement.setInt(6,noOfEmployees);
//            preparedStatement.setBoolean(7,Status);
//            preparedStatement.executeUpdate();
//            System.out.println("Branch Registered SUccessfullyyyyyyyyyy");
//            isRegistered = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("isregistered value = "+isRegistered+"    wese true ani chahiye");
//        return isRegistered;
        return true;
    }
    public Boolean createManagerOfBranch(int branchId,int managerId,String name,Float Salary , String email,String password) {
//        Boolean isCreated = false; // if duplicate exsits or due to some other reason we cannot create it
//        System.out.println("reached in create manager methoddddddddddddddd");
//        // logic to create manager in DB
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)");
//            preparedStatement.setInt(1,branchId);
//            preparedStatement.setInt(2,managerId);
//            preparedStatement.setString(3,name);
//            preparedStatement.setString(4,email);
//            preparedStatement.setString(5,password);
//            preparedStatement.setString(6, Values.BRANCH_MANAGER);
//            preparedStatement.setFloat(7,Salary);
//            preparedStatement.setBoolean(8,true);
//            preparedStatement.executeUpdate();
//            System.out.println("Manager Created Successfullyyyyyyy");
//            isCreated = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return isCreated;
        return true;
    }
    public ArrayList<Branch> getBranches() {
//        ArrayList<Branch> branches = new ArrayList<Branch>();
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            // Query to get all branches
//            String branchQuery = "SELECT branch_id, name, city, address, phone, no_of_employees, active FROM Branch";
//            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
//            ResultSet branchResultSet = branchStmt.executeQuery();
//
//            while (branchResultSet.next()) {
//                Branch branch = new Branch();
//
//                // Set branch details
//                branch.setBranchId(branchResultSet.getInt("branch_id"));
//                branch.setName(branchResultSet.getString("name"));
//                branch.setCity(branchResultSet.getString("city"));
//                branch.setAddress(branchResultSet.getString("address"));
//                branch.setPhone(branchResultSet.getString("phone"));
//                branch.setNoOfEmployees(branchResultSet.getInt("no_of_employees"));
//                branch.setActive(branchResultSet.getBoolean("active"));
//
//                // Query to get manager details for the current branch
//                String managerQuery = "SELECT employee_id, name, salary FROM Employee WHERE branch_id = ? AND role = 'Branch Manager' LIMIT 1";
//                PreparedStatement managerStmt = connection.prepareStatement(managerQuery);
//                managerStmt.setInt(1, branch.getBranchId());
//                ResultSet managerResultSet = managerStmt.executeQuery();
//
//                if (managerResultSet.next()) {
//                    // Set manager details if a manager exists
//                    branch.setManagerId(managerResultSet.getInt("employee_id"));
//                    branch.setManagerName(managerResultSet.getString("name"));
//                    branch.setManagerSalary(managerResultSet.getFloat("salary"));
//                }
//
//                branches.add(branch);
//                managerStmt.close();
//            }
//
//            branchStmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions (e.g., log error, rethrow as custom exception, etc.)
//        }
            ArrayList<Branch> branches = new ArrayList<Branch>();
            Branch branch = new Branch();
            branch.setBranchId(1);
            branch.setName("Branch 1");
            branch.setCity("Lahore");
            branch.setAddress("Model Town");
            branch.setPhone("042-1234567");
            branch.setNoOfEmployees(10);
            branch.setActive(true);
            branch.setManagerName("Manager 1");
            branch.setManagerSalary(50000.0f);
            branches.add(branch);
        return branches;
        // logic to get all branches from DB

        // Note : Branch have manager name, manager salry and managerid that would be set by calling
        //        setManagerName and setManagerSalary setManagerId methods of Branch class
        //        because there is a possible case that branch have no manager assigned yet but branch has been created

    }
    public Boolean updateBranch(int branchId,String city,String Address,String phoneNo,int noOfEmployees,Boolean Status,String managerName,Float managerSalary) {
//        Boolean isUpdated = false; // if branch not found or due to some other reason we cannot update it
//
//        // logic to update branch in DB
//        // user can update  anything in the branch so make sure that you update all the fields including manager data recieved in parameters
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            // Update branch details
//            String branchQuery = "UPDATE Branch SET city = ?, address = ?, phone = ?, no_of_employees = ?, active = ? WHERE branch_id = ?";
//            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
//            branchStmt.setString(1, city);
//            branchStmt.setString(2, Address);
//            branchStmt.setString(3, phoneNo);
//            branchStmt.setInt(4, noOfEmployees);
//            branchStmt.setBoolean(5, Status);
//            branchStmt.setInt(6, branchId);
//            branchStmt.executeUpdate();
//
//            // Update manager details
//            String managerQuery = "UPDATE Employee SET name = ?, salary = ? WHERE branch_id = ?";
//            PreparedStatement managerStmt = connection.prepareStatement(managerQuery);
//            managerStmt.setString(1, managerName);
//            managerStmt.setFloat(2, managerSalary);
//            managerStmt.setInt(3, branchId);
//            managerStmt.executeUpdate();
//
//            isUpdated = true;
//            branchStmt.close();
//            managerStmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //return isUpdated;
        return true;
    }
    public Boolean deleteBranch(int branchId) {
        Boolean isDeleted = false; // if branch not found or due to some other reason we cannot delete it

        // logic to delete branch in DB
        // NOTE : we will not delete the branch we will just change the status of the branch to false
        //         so that it will not be shown in the list of branches
//        Connection connection = ConnectionConfig.getConnection();
//        try {
//            // Delete branch
//            String branchQuery = "UPDATE Branch SET active = false WHERE branch_id = ?";
//            PreparedStatement branchStmt = connection.prepareStatement(branchQuery);
//            branchStmt.setInt(1, branchId);
//            branchStmt.executeUpdate();
//
//            isDeleted = true;
//            branchStmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return isDeleted;
    }
    // reports + graphs
    // of Sales || Remaining Stock || Profit
    // All this to be calculated daily, weekly, monthly, yearly
    public static ArrayList<Transactions> getTransactions(int branchId, String type) {
        ArrayList<Transactions> transactions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();

            // Define the base SQL query
            String sql = "SELECT s.sale_id, s.branch_id, s.cashier_id, sd.product_id, s.sale_date, (sd.quantity * sd.price_per_unit) AS transaction_amount " +
                    "FROM Sale s " +
                    "JOIN Sale_Details sd ON s.sale_id = sd.sale_id " +
                    "WHERE s.branch_id = ? AND DATE(s.sale_date) BETWEEN ? AND ?";

            // Calculate date range based on the type
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date endDate = calendar.getTime();
            String endDateStr = sdf.format(endDate);
            String startDateStr;

            switch (type.toLowerCase()) {
                case "daily":
                    startDateStr = sdf.format(endDate); // Same day
                    break;
                case "monthly":
                    calendar.set(Calendar.DAY_OF_MONTH, 1); // Start of the month
                    startDateStr = sdf.format(calendar.getTime());
                    break;
                case "yearly":
                    calendar.set(Calendar.DAY_OF_YEAR, 1); // Start of the year
                    startDateStr = sdf.format(calendar.getTime());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid report type: " + type);
            }

            // Prepare the statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, branchId);
            preparedStatement.setString(2, startDateStr);
            preparedStatement.setString(3, endDateStr);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("sale_id");
                int cashierId = resultSet.getInt("cashier_id");
                int productId = resultSet.getInt("product_id");
                Date transactionDate = resultSet.getDate("sale_date");
                float transactionAmount = resultSet.getFloat("transaction_amount");

                transactions.add(new Transactions(transactionId, branchId, cashierId, productId, transactionDate, transactionAmount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transactions;
    }

    public static  ArrayList<Product> getRemainingStock(int branchId) {
        ArrayList<Product> products = new ArrayList<Product>();
        // if branch id is -1 then we will get the remaining stock of the product
        // phir ham sirf product id ki base pai search karien ga
        // or agar branch id ki koi value ho to phir ham us branch ki stock nikalie ga
        // phir ham prodcutid+branchid ki base pai search karien ga
        // logic to get remaining stock of a particlular id from DB
        // product kai constructor mai sari details hain sirf branchid ko set karna pare ga
        // agar to branch id -1 nahi hai to branch id set karni hai or remaining stock us
        // branch ki nikalni hai
        // agar branch id -1 hai to hamain sari branches ki stock nikalni hai in that case branch id set hi nahi karien ga
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
