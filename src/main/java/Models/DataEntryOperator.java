package Models;

import Entites.Vendor;
import Utils.Values;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class DataEntryOperator {
    String username;
    String password;
    int BranchId; // to be used while adding products
    int CreatorId; // to be used while adding products
    int dataEnteryOperatorId;
    public DataEntryOperator(String username, String password) {
        this.username = username;
        this.password = password;
        setBranchId();
        System.out.println("Data Entry Operator Model initialized");
    }

    public int getUniqueVendorId()
    {
        int id=8001;
        Connection connection = ConnectionConfig.getConnection();
        String query = "SELECT MAX(vendor_id) FROM Vendor";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.err.println("Error getting unique vendor id: " + e.getMessage());
            e.printStackTrace();
        }
        return id;
    }
    public void setCreatorId()
    {
        // set the creator id from the db after looking up against email/username
        Connection connection = ConnectionConfig.getConnection();
        String query = "SELECT employee_id FROM Employee WHERE email = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            // Set the parameters
            preparedStatement.setString(1, username);
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CreatorId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting creator id: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void setDataEnteryOperatorId()
    {
        // set the data entry operator id from the db after looking up against email/username
        Connection connection = ConnectionConfig.getConnection();
        String query = "SELECT data_entry_operator_id FROM Employee WHERE email = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the parameters
            preparedStatement.setString(1, username);
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dataEnteryOperatorId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting data entry operator id: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void setBranchId()
    {
        // set the branch id from the db after looking up against email/username
        Connection connection = ConnectionConfig.getConnection();
        String query = "SELECT branch_id FROM Employee WHERE email = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the parameters
            preparedStatement.setString(1, username);
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BranchId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting branch id: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public Boolean changePassword(String newPassword) {
        Connection connection = ConnectionConfig.getConnection();
        String query = "UPDATE Employee SET password = ?, is_password_changed = TRUE WHERE email = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully for user: " + username);
                return true;
            } else {
                System.out.println("No user found with email: " + username);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean isPasswordChanged()
    {
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT is_password_changed FROM Employee WHERE employee_id = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.dataEnteryOperatorId);
            preparedStatement.setString(2, Values.DATA_ENTRY);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                return resultSet.getBoolean("is_password_changed");
            }
            else
            {
                System.out.println("No data entry operator found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while checking password status: " + e.getMessage());
        }
        return false; // Default to false if no result or an error occurs
    }

    public boolean addVendor(String name, String address, String phone, String startDate, String endDate) {
        String query = "INSERT INTO Vendor (vendor_id, name, address, phone, branch_id, creator_id) VALUES (?, ?, ?, ?, ?, ?)";
        int vendorID = getUniqueVendorId();
        int branchID = this.BranchId; // Assuming this method fetches the branch ID attribute
        int creatorID = this.CreatorId; // Assuming this method fetches the Data Entry Operator's ID attribute

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the insert query
            preparedStatement.setInt(1, vendorID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, branchID);
            preparedStatement.setInt(6, creatorID);

            // Execute the insert statement
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Vendor added successfully: " + name);
                return true;
            } else {
                System.out.println("Failed to add vendor: " + name);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error adding vendor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean addProduct(int vendorId, int productId, int stockQty, String categorie, float costByUnit, float sellingPrice, float cartonPrice, int cartonQty) {
        if (productId == -1) {
            productId = getUniqueProductId(); // new product
        }

        // branch id from attribute
        // add the product to the db
        return false;
    }

    private int getUniqueProductId() {
        return 0;
    }

    public Map<Integer, String> getProductNames() {
        // you have to return all the unique product ids name in a map
        return null;
    }

    public ArrayList<Integer> getVendorIds()
    {
        // you have to return all the unique vendor ids in a branch (from attribute)
        ArrayList<Integer> vendorIds = new ArrayList<>();
        String query = "SELECT vendor_id FROM Vendor WHERE branch_id = ?";

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            // Set the branch ID as a parameter
            preparedStatement.setInt(1, this.BranchId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Add the vendor IDs to the list
                    vendorIds.add(resultSet.getInt("vendor_id"));
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error retrieving vendor IDs: " + e.getMessage());
            e.printStackTrace();
        }

        return vendorIds;
    }

    public ArrayList<Vendor> getVendors() {

        // you have to return all the vendors in a branch (from attribute)
        return null;
    }

    public Boolean deleteVendor(int vendorId) {
        // delete the vendor from the db permanently
        return null;
    }

    public Boolean updateVendor(int vendorId, String name, String phone) {
    // update the vendor in the db
        return null;
    }


}
