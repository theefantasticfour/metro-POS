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
    public Cashier(String username, String password) {
        this.username = username;
        this.password = password;
        setBranchID();
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

    public void RecordTransactions(Map<String, Integer> cartDetails) {
        // we have the cart details
        // we have the product id but in String as key
        // we have the quantity as value
        // if (Quantity > 1)
        // record n Transactions
        // first of all
        // search by productid+branchid
        for (Map.Entry<String, Integer> entry : cartDetails.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            // search by productid+branchid (Key + BranchID) and get the product
            // register a transaction as now you will have everything
            // to register a transaction
            // from product attributes ect
           // recordATransaction(Product);
            // if (value > 1)
                // for (int i = 0; i < value; i++)
                //recordATransaction(Product);

            // record n Transactions
        }

        // update the stock
        // update the transaction table

    }
    public void recordATransaction(Product product) {
        // register a transaction
        // you have the product
        // you have the branch id
        // you have the cashier id if needed

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
}
