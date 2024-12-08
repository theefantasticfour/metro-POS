package Models;

import Entites.Product;
import Entites.Vendor;
import Utils.Values;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
        setCreatorId();
        //setDataEnteryOperatorId();
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
            String query = "SELECT is_password_changed FROM Employee WHERE email = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, Values.DATA_ENTRY);
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

    public boolean addVendor(String name, String address, String phone, String startDate, String endDate) {
        String query = "INSERT INTO Vendor (vendor_id, name, address, phone, branch_id, creator_id, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int vendorID = getUniqueVendorId();
        int branchID = this.BranchId; // Assuming this fetches the branch ID
        int creatorID = this.CreatorId; // Assuming this fetches the creator ID (Data Entry Operator)

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            // Convert startDate and endDate to java.sql.Date
            java.sql.Date sqlStartDate = new java.sql.Date(dateFormat.parse(startDate).getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(dateFormat.parse(endDate).getTime());

            try {
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                // Set parameters for the insert query
                preparedStatement.setInt(1, vendorID);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, phone);
                preparedStatement.setInt(5, branchID);
                preparedStatement.setInt(6, creatorID);
                preparedStatement.setDate(7, sqlStartDate);
                preparedStatement.setDate(8, sqlEndDate);

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
                throw new RuntimeException(e);
            }
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Integer> getVendorIds()
    {
        // you have to return all the unique vendor ids in a branch (from attribute)
        ArrayList<Integer> vendorIds = new ArrayList<>();
        String query = "SELECT vendor_id FROM Vendor WHERE branch_id = ?";

        try
        {
            Connection connection = ConnectionConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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

    public ArrayList<Vendor> getVendors()
    {
        ArrayList<Vendor> vendors = new ArrayList<>();
        Connection connection = ConnectionConfig.getConnection();

        // SQL query to fetch vendor details along with total payment and total products
        String query = "SELECT v.vendor_id, v.name, v.phone, v.address, v.start_date, v.end_date, " +
                "COALESCE(v.total_payment, 0) AS totalPayment, " +
                "COALESCE(v.total_products, 0) AS totalProduct " +
                "FROM Vendor v " +
                "JOIN Employee e ON v.creator_id = e.employee_id " + // Ensuring creator_id links to Employee table
                "WHERE v.branch_id = ? AND v.creator_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the branch_id and creator_id parameters
            preparedStatement.setInt(1, this.BranchId);  // Assuming this.BranchId refers to the branch of the data entry operator
            preparedStatement.setInt(2, this.CreatorId); // Assuming this.CreatorId refers to the creator (data entry operator)

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the results from the query
                while (resultSet.next()) {
                    // Create a new Vendor object
                    Vendor vendor = new Vendor();

                    // Populate the Vendor object with data from the result set
                    vendor.setVendor_id(resultSet.getInt("vendor_id"));
                    vendor.setName(resultSet.getString("name"));
                    vendor.setPhone(resultSet.getString("phone"));
                    vendor.setAddress(resultSet.getString("address"));
                    vendor.setStartDate(resultSet.getDate("start_date"));
                    vendor.setEndDate(resultSet.getDate("end_date"));
                    vendor.setTotalPayment(resultSet.getFloat("totalPayment"));
                    vendor.setTotalProduct(resultSet.getInt("totalProduct"));

                    // Add the Vendor object to the vendors list
                    vendors.add(vendor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching vendor data: " + e.getMessage());
        }

        // Return the list of vendors
        return vendors;
    }


    public Boolean deleteVendor(int vendorId) {
        Connection connection = ConnectionConfig.getConnection();
        String query = "DELETE FROM Vendor WHERE vendor_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, vendorId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Boolean updateVendor(int vendorId, String name, String phone) {
        Connection connection = ConnectionConfig.getConnection();
        String query = "UPDATE Vendor SET name = ?, phone = ? WHERE vendor_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setInt(3, vendorId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   // ===================================================Products=============================================
    //=========================================================================================================
    //=========================================================================================================
    private int getUniqueProductId()
    {
        int id=10001;
        Connection connection = ConnectionConfig.getConnection();
        String query = "SELECT MAX(product_id) FROM Product";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.err.println("Error getting unique product id: " + e.getMessage());
            e.printStackTrace();
        }
        return id;

    }

    public boolean addProduct(int productId, int vendorId, String name, String category,
                              float sellingPrice, float cartonPrice, int cartonQty, int piecesPerCarton,
                              boolean isNewProduct) {

        // If productId is -1, generate a new product ID
        if (productId == -1) {
            productId = getUniqueProductId(); // Generate new product ID if -1 is provided
        }

        // If the product is not new, update the product
        if (!isNewProduct) {
            // Extract stock quantity, original price per unit, and sale price per carton from the db
            String query = "SELECT stock_quantity, original_price_per_unit, sale_price_per_carton FROM Product WHERE product_id = ? and branch_id = ?";
            try {
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, productId);
                preparedStatement.setInt(2, this.BranchId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int stockQuantity = resultSet.getInt("stock_quantity");
                    float originalPricePerUnit = resultSet.getFloat("original_price_per_unit");
                    float salePricePerCarton = resultSet.getFloat("sale_price_per_carton");

                    // Calculate the new stock quantity
                    int newStockQuantity = stockQuantity + (cartonQty * piecesPerCarton);
                    // Calculate the new original price per unit
                    float newOriginalPricePerUnit = cartonPrice / piecesPerCarton;
                    // Calculate the new selling price per carton
                    float newSellingPricePerCarton = sellingPrice * piecesPerCarton;

                    // Update the product details in the database
                    query = "UPDATE Product SET name = ?, category = ?, original_price_per_unit = ?, " +
                            "sale_price_per_unit = ?, stock_quantity = ?, original_price_per_carton = ?, " +
                            "sale_price_per_carton = ?, carton_quantity = ?, pieces_per_carton = ? " +
                            "WHERE product_id = ? AND branch_id = ?";

                    try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
                        updateStatement.setString(1, name);
                        updateStatement.setString(2, category);
                        updateStatement.setFloat(3, newOriginalPricePerUnit);
                        updateStatement.setFloat(4, sellingPrice);
                        updateStatement.setInt(5, newStockQuantity);
                        updateStatement.setFloat(6, cartonPrice);
                        updateStatement.setFloat(7, newSellingPricePerCarton);
                        updateStatement.setInt(8, cartonQty);
                        updateStatement.setInt(9, piecesPerCarton);
                        updateStatement.setInt(10, productId);
                        updateStatement.setInt(11, this.BranchId);

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Product updated successfully.");
                            return true;
                        } else {
                            System.out.println("Failed to update product.");
                            return false;
                        }
                    }
                } else {
                    System.out.println("Product not found.");
                    return false;
                }
            } catch (SQLException e) {
                System.err.println("Error updating product: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        // If the product is new, calculate missing values and insert it
        int stockQuantity = cartonQty * piecesPerCarton;  // Calculate total stock quantity
        float originalPricePerUnit = cartonPrice / piecesPerCarton;  // Calculate original price per unit
        float salePricePerCarton = sellingPrice * piecesPerCarton;  // Calculate sale price per carton

        // Define the SQL query to insert the product into the Product table
        String query = "INSERT INTO Product (product_id, vendor_id, branch_id, name, category, " +
                "original_price_per_unit, sale_price_per_unit, stock_quantity, original_price_per_carton, " +
                "sale_price_per_carton, carton_quantity, pieces_per_carton) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Establish connection to the database
        try {
            Connection connection = ConnectionConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters for the query
            preparedStatement.setInt(1, productId);  // Product ID
            preparedStatement.setInt(2, vendorId);   // Vendor ID
            preparedStatement.setInt(3, this.BranchId);   // Branch ID
            preparedStatement.setString(4, name);    // Product Name
            preparedStatement.setString(5, category); // Product Category
            preparedStatement.setFloat(6, originalPricePerUnit); // Original Price Per Unit
            preparedStatement.setFloat(7, sellingPrice);  // Sale Price Per Unit
            preparedStatement.setInt(8, stockQuantity);   // Stock Quantity
            preparedStatement.setFloat(9, cartonPrice); // Original Price Per Carton
            preparedStatement.setFloat(10, salePricePerCarton);  // Sale Price Per Carton
            preparedStatement.setInt(11, cartonQty);     // Carton Quantity
            preparedStatement.setInt(12, piecesPerCarton); // Pieces Per Carton
            //preparedStatement.setInt(13, this.BranchId); // Branch ID

            // Execute the insert statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
                return true;
            } else {
                System.out.println("Failed to add product.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public Map<Integer, String> getProductNames() {
        Map<Integer, String> productNames = new HashMap<>();

        // SQL query to select product_id and name from the Product table
        String query = "SELECT product_id, name FROM Product";
        System.out.println("current branch in getproductnamemethod() = "+ BranchId);

        try {
            Connection connection = ConnectionConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, BranchId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set and populate the map with product_id as the key and name as the value
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("name");

                // Adding the product ID and name to the map
                productNames.put(productId, productName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productNames;
    }

    public Boolean updateProduct(int productId, String name, int stockQty, String category, float costByUnit, float sellingPrice, float cartonPrice, int vendorid)
    {
        System.out.println("Product updated: " + productId);
        return true; // Dummy update status
    }


    public Boolean deleteProduct(int productId)
    {
        System.out.println("Product deleted: " + productId);
        return true; // Dummy deletion status
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        // SQL query to fetch the necessary fields in the specified sequence
        String query = "SELECT product_id, name, category, original_price_per_unit, sale_price_per_unit, sale_price_per_carton, stock_quantity, vendor_id FROM Product WHERE branch_id = ?";

        try  {

            Connection connection = ConnectionConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, BranchId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Extract data in the required order and create Product object
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("name");
                String category = resultSet.getString("category");
                float originalPricePerUnit = resultSet.getFloat("original_price_per_unit");
                float salePricePerUnit = resultSet.getFloat("sale_price_per_unit");
                float salePricePerCarton = resultSet.getFloat("sale_price_per_carton");
                int stockQuantity = resultSet.getInt("stock_quantity");
                int vendorId = resultSet.getInt("vendor_id");


                // Create a Product object and add it to the list
                Product product = new Product();
                product.setProductId(productId);
                product.setVendorId(vendorId);
                product.setName(productName);
                product.setCategory(category);
                product.setOriginalPricePerUnit(originalPricePerUnit);
                product.setSalePricePerUnit(salePricePerUnit);
                product.setStockQuantity(stockQuantity);
                product.setOriginalPricePerCarton(salePricePerCarton);
                product.setSalePricePerCarton(salePricePerCarton);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
