package Models;

import Entites.Vendor;

import java.util.ArrayList;
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

    public int getUniqueVendorId()
    {
        return 0;
    }
    public void setBranchId() {
        // set the branch id from the db after looking up against email/username
    }
    public Boolean changePassword(String newPassword) {
        // change the password against the username
        return null;
    }

    public boolean isPasswordChanged() {
        return false;
    }

    public boolean addVendor(String name, String address, String phone, String startDate, String endDate) {
        // add the vendor to the db
        int vendorID = getUniqueVendorId();
        // branch id from attribute
        // dataentryoperator id from attribute
        // add the vendor to the db

        return true;
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

    public ArrayList<Integer> getVendorIds() {
        // you have to return all the unique vendor ids in a branch (from attribute)
        return null;
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
