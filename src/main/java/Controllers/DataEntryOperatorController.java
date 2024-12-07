package Controllers;

import Entites.Vendor;
import Models.DataEntryOperator;
import Session.Session;
import Utils.Values;
import Views.DataEntryOperatorView.DataEntryOperatorView;
import Views.Mainscreen;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class DataEntryOperatorController {
    String username;
    String password;
    Session session;
    DataEntryOperator dataEntryOperatorModel;
    DataEntryOperatorView dataEntryOperatorView;
    public ActionListener dataEntryListener;

    public DataEntryOperatorController(String username, String password, Session instance) {
        this.username = username;
        this.password = password;
        this.session = instance;
        setActionListeners();

    }
    public void start() {
        dataEntryOperatorModel = new DataEntryOperator(username, password);
        Mainscreen.getInstance().showDataEntryOperator(setActionListeners(),this);
        Mainscreen.getInstance();
        dataEntryOperatorView = Mainscreen.getDataEntryOperatorView();
    }

    public ActionListener setActionListeners() {
    // for
        //changepassword apply
        // addnewvendor add
        // addnewproduct Save
        // ViewVendor update
        // view Vendor delete
        // logout
        dataEntryListener = e -> {
            if (e.getActionCommand().equals(Values.CHANGE_PASSWORD))
            {
                if (changePassword())
                {
                    JOptionPane.showMessageDialog(null, "Password changed successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Password not changed");
                }
            }
            else if (e.getActionCommand().equals(Values.ADD_NEW_VENDOR))
            {
                if (addNewVendor())
                {
                    JOptionPane.showMessageDialog(null, "Vendor added successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Vendor not added");
                }
            }
            else if (e.getActionCommand().equals(Values.ADD_NEW_PRODUCT))
            {
                if (addNewProduct())
                {
                    JOptionPane.showMessageDialog(null, "Product added successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Product not added");
                }
            }
            else if (e.getActionCommand().equals(Values.UPDATE_VENDOR))
            {
                if (UpdateVendor())
                {
                    JOptionPane.showMessageDialog(null, "Vendor updated successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Vendor not updated");
                }
            }
            else if (e.getActionCommand().equals(Values.DELETE_VENDOR))
            {
                if (DeleteVendor())
                {
                    JOptionPane.showMessageDialog(null, "Vendor deleted successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Vendor not deleted");
                }
            }
            else if (e.getActionCommand().equals(Values.LOGOUT))
            {
                JOptionPane.showMessageDialog(null, "Logging out...");
                session.showLogin();
            }
        };
        return dataEntryListener;
    }


    public Boolean changePassword() {
        // see if the passwords match
        String newPassword = dataEntryOperatorView.getNewPassword();
        String confirmPassword = dataEntryOperatorView.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        return dataEntryOperatorModel.changePassword(newPassword);
    }

    // to see if the password has been changed since first login // will be used to the variable in views
    public boolean isPasswordChanged() {
        return dataEntryOperatorModel.isPasswordChanged();
    }

    public boolean addNewVendor() {
        // id will be genereated from db
        // name
        // address
        // phone
        // starting date
        // ending date
        // branch id will be taken from db
        // dataenteryoperator id will be taken from db
        String name = dataEntryOperatorView.getVendorName();
        String address = dataEntryOperatorView.getVendorAddress();
        String phone = dataEntryOperatorView.getVendorPhone();
        String startDate = dataEntryOperatorView.getVendorStartDate();
        String endDate = dataEntryOperatorView.getVendorEndDate();

        if (dataEntryOperatorModel.addVendor(name, address, phone, startDate, endDate)) {
            JOptionPane.showMessageDialog(null, "Vendor added successfully");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Vendor not added");
            return false;
        }
    }
    public boolean addNewProduct() {
        // branch id from db
        String productName = dataEntryOperatorView.getProductNameToAddProduct();
        Map<Integer, String> productNames = getProductNames();
        int productId = -1; // Default value if the product is not found
        // Iterate through the map to find the product ID
        for (Map.Entry<Integer, String> entry : productNames.entrySet()) {
            if (entry.getValue().equals(productName)) {
                productId = entry.getKey();
                break; // Exit the loop once the product is found
            }
        }
         int vendorId = dataEntryOperatorView.getVendorIdToaddProduct();
        int stockQty = dataEntryOperatorView.getStockQtyToAddProduct();
        String categorie = dataEntryOperatorView.getCategorieToAddProduct();
        float costByUnit = dataEntryOperatorView.getCostByUnitToAddProduct();
        float sellingPrice = dataEntryOperatorView.getSellingPriceToAddProduct();
        float cartonPrice = dataEntryOperatorView.getCartonPriceToAddProduct();
       int cartonQty = dataEntryOperatorView.getCartonQtyToAddProduct();

        if (dataEntryOperatorModel.addProduct(vendorId, productId, stockQty, categorie, costByUnit, sellingPrice, cartonPrice, cartonQty)) {
            JOptionPane.showMessageDialog(null, "Product added successfully");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Product not added");
            return false;
        }

    }
    public Map<Integer,String> getProductNames()
    {
        return dataEntryOperatorModel.getProductNames();
    }
    public ArrayList<Integer> getVendorIds()
    {
        return dataEntryOperatorModel.getVendorIds();
    }
    public ArrayList<Vendor>getVendorsTodisplay() {
        return dataEntryOperatorModel.getVendors();

    }
    public Boolean UpdateVendor()
    {
        // get vendor id
        // get name
        // get phone no
        int vendorId = dataEntryOperatorView.getVendorIdToUpdateVendor();
        String name = dataEntryOperatorView.getVendorNameToUpdate();
        String phone = dataEntryOperatorView.getVendorPhoneToUpdate();
        return dataEntryOperatorModel.updateVendor(vendorId,name,phone);
    }
    public Boolean DeleteVendor()
    {
        int vendorId = dataEntryOperatorView.getVendorIdToUpdateVendor();
        // get vendor id and delete permanently
        return dataEntryOperatorModel.deleteVendor(vendorId);
    }

}
