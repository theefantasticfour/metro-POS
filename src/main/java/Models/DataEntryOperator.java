package Models;

import Entites.Product;
import Entites.Vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataEntryOperator {
    String username;
    String password;
    int BranchId; // to be used while adding products
    int dataEnteryOperatorId;

    public DataEntryOperator(String username, String password) {
        this.username = username;
        this.password = password;
        setBranchId();
        System.out.println("Data Entry Operator Model initialized");
    }

    public int getUniqueVendorId() {
        return 1001; // Dummy unique vendor ID
    }

    public void setBranchId() {
        this.BranchId = 101; // Dummy branch ID
    }

    public Boolean changePassword(String newPassword) {
        this.password = newPassword; // Dummy password change
        return true;
    }

    public boolean isPasswordChanged() {
        return true; // Dummy password change status
    }

    public boolean addVendor(String name, String address, String phone, String startDate, String endDate) {
        System.out.println("Vendor added: " + name);
        return true;
    }

    public boolean addProduct(int vendorId, int productId, int stockQty, String categorie, float costByUnit, float sellingPrice, float cartonPrice, int cartonQty, String name) {
        if (productId == -1) {
            productId = getUniqueProductId(); // Dummy new product ID
        }
        System.out.println("Product added: " + name);
        return true;
    }

    private int getUniqueProductId() {
        return 2001; // Dummy unique product ID
    }

    public Map<Integer, String> getProductNames() {
        Map<Integer, String> productNames = new HashMap<>();
        productNames.put(2001, "Product A");
        productNames.put(2002, "Product B");
        return productNames; // Dummy product names
    }

    public ArrayList<Integer> getVendorIds() {
        ArrayList<Integer> vendorIds = new ArrayList<>();
        vendorIds.add(1001);
        vendorIds.add(1002);
        return vendorIds; // Dummy vendor IDs
    }

    public ArrayList<Vendor> getVendors() {
        ArrayList<Vendor> vendors = new ArrayList<>();
//        vendors.add(new Vendor(1001, "Vendor A", "Address A", "1234567890"));
//        vendors.add(new Vendor(1002, "Vendor B", "Address B", "0987654321"));
   return vendors; // Dummy vendor data
    }

    public Boolean deleteVendor(int vendorId) {
        System.out.println("Vendor deleted: " + vendorId);
        return true; // Dummy deletion status
    }

    public Boolean updateVendor(int vendorId, String name, String phone) {
        System.out.println("Vendor updated: " + vendorId);
        return true; // Dummy update status
    }

    public Boolean updateProduct(int productId, String name, int stockQty, String category, float costByUnit, float sellingPrice, float cartonPrice, int vendorid) {
        System.out.println("Product updated: " + productId);
        return true; // Dummy update status
    }

    public Boolean deleteProduct(int productId) {
        System.out.println("Product deleted: " + productId);
        return true; // Dummy deletion status
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Product A", 2001, 1001, 50, "Category A", 10.5f, 12.5f, 100.0f, 10));
        products.add(new Product("Product B", 2002, 1002, 30, "Category B", 15.0f, 18.0f, 150.0f, 5));
        return products; // Dummy product data
    }
}
