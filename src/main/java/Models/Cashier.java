package Models;
import Entites.Product;
import Utils.Values;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
// NOTE : A single transaction wwill always have qty 1 no more than that
//
public class Cashier {
    String username;
    String password;
    int BranchID;
    int cashierId;
    int vendorId;
    public Cashier(String username, String password) {
        this.username = username;
        this.password = password;
        setBranchID();
        setCashierId();
        setVendorId();
    }
    public void setCashierId() {
        // set the cashier id after looking up the cashier username
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT employee_id FROM Employee WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.cashierId = resultSet.getInt("employee_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setVendorId() {
        // set the vendor id after looking up the vendor username
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT vendor_id FROM Vendor WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.vendorId = resultSet.getInt("vendor_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setBranchID() {
        // set the branch id of the cashier after looking up the cashier username
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT branch_id FROM Employee WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.BranchID = resultSet.getInt("branch_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getAllBranchProducts() {
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("reached here in getallbranchproducts././././/.");
        String query = "SELECT product_id, branch_id, vendor_id, name, category, " +
                "original_price_per_unit, sale_price_per_unit, stock_quantity, " +
                "original_price_per_carton, sale_price_per_carton, carton_quantity, pieces_per_carton " +
                "FROM Product WHERE stock_quantity > 0 and branch_id = ?";

        try  {
            Connection connection = ConnectionConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.BranchID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int branchId = resultSet.getInt("branch_id");
                int vendorId = resultSet.getInt("vendor_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                float originalPricePerUnit = resultSet.getFloat("original_price_per_unit");
                float salePricePerUnit = resultSet.getFloat("sale_price_per_unit");
                int stockQuantity = resultSet.getInt("stock_quantity");
                float originalPricePerCarton = resultSet.getFloat("original_price_per_carton");
                float salePricePerCarton = resultSet.getFloat("sale_price_per_carton");
                int cartonQuantity = resultSet.getInt("carton_quantity");
                int piecesPerCarton = resultSet.getInt("pieces_per_carton");

                // Create a Product object and add it to the list
                Product product = new Product();
                product.setProductId(productId);
                product.setBranchId(branchId);
                product.setVendorId(vendorId);
                product.setName(name);
                product.setCategory(category);
                product.setOriginalPricePerUnit(originalPricePerUnit);
                product.setSalePricePerUnit(salePricePerUnit);
                product.setStockQuantity(stockQuantity);
                product.setOriginalPricePerCarton(originalPricePerCarton);
                product.setSalePricePerCarton(salePricePerCarton);
                product.setCartonQuantity(cartonQuantity);
                product.setPiecesPerCarton(piecesPerCarton);

                products.add(product);
                System.out.println("Product added to list: ");
            }

        } catch (SQLException e) {
            System.err.println("Error fetching branch products: " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }
    public float getSellingPrice(Connection connection, String productId) throws SQLException {
        String query = "SELECT sale_price_per_unit FROM Product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(productId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getFloat("sale_price_per_unit");
            } else {
                throw new SQLException("Product not found with ID: " + productId);
            }
        }
    }
    public float getCostPrice(Connection connection, String productId) throws SQLException {
        String query = "SELECT original_price_per_unit FROM Product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(productId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getFloat("original_price_per_unit");
            } else {
                throw new SQLException("Product not found with ID: " + productId);
            }
        }
    }

    public void RecordTransactions(Map<String, Integer> cardDetails) throws SQLException {
        System.out.println("reached in record transactions......");
        Connection connection = ConnectionConfig.getConnection();
        String insertQuery = "INSERT INTO `Transaction` (branch_id,cashier_id, vendor_id, product_id, transaction_date, quantity, transaction_amount, transaction_cost)"
                + " VALUES (?,?,?, ?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (Map.Entry<String, Integer> entry : cardDetails.entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();

                // Fetch product details from the Product table to calculate costs and amounts
                float sellingPrice = getSellingPrice(connection, productId);
                float costPrice = getCostPrice(connection, productId);

                float transactionAmount = sellingPrice * quantity;
                float transactionCost = costPrice * quantity;
                //get real time transaction date
                java.util.Date transactionDate = new java.util.Date();
                preparedStatement.setInt(1, this.BranchID);
                preparedStatement.setInt(2, this.cashierId);
                if (this.vendorId != 0) {
                    preparedStatement.setInt(3, this.vendorId);
                } else {
                    preparedStatement.setNull(3, java.sql.Types.INTEGER);
                }
                preparedStatement.setInt(4, Integer.parseInt(productId));
                preparedStatement.setDate(5, new Date(transactionDate.getTime()));
                preparedStatement.setInt(6, quantity);
                preparedStatement.setFloat(7, transactionAmount);
                preparedStatement.setFloat(8, transactionCost);

                preparedStatement.addBatch(); // Batch execution for better performance
            }

            preparedStatement.executeBatch(); // Execute all transactions in one go
        }

        //update stock
        //



    }


    public Boolean isPasswordChanged() {
        Connection connection = ConnectionConfig.getConnection();
        try {
            String query = "SELECT is_password_changed FROM Employee WHERE email = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, Values.CASHIER);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next())
            {
                System.out.println("Ispasswordchaged value =====  " + resultSet.getBoolean("is_password_changed"));
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

    public boolean changePassword(String newPassword) {
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

    public void updateInventry(Map<String, Integer> cartItems)
    {
        System.out.println("updating inventory............");
        Connection connection = ConnectionConfig.getConnection();
        String query = "UPDATE Product SET stock_quantity = stock_quantity - ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();

                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, Integer.parseInt(productId));

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error updating inventory: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
