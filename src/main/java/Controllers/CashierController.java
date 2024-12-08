package Controllers;

import Entites.Product;
import Models.Cashier;
import Session.Session;
import Utils.Values;
import Views.CashierView.CashierPanelView;
import Views.Mainscreen;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Map;

public class CashierController {
    ActionListener actionListener;
    String username;
    String password;
    Session session;
    Cashier cashierModel;
    CashierPanelView cashierPanelView;

    public CashierController(String username, String password, Session instance) {
        this.username = username;
        this.password = password;
        this.session = instance;
        setActionListeners();
    }

    public void start() {
        System.out.println("Cashier Controller started");
        cashierModel = new Cashier(username, password);
        Mainscreen.getInstance().showCashier(actionListener, this);
        cashierPanelView = Mainscreen.getCashierPanelView();

    }
    public void RecordTransactions(Map<String, Integer> cartDetails) throws SQLException {
        cashierModel.RecordTransactions(cartDetails);
    }

    private void setActionListeners() {
        actionListener = e -> {
            if (e.getActionCommand().equals(Values.LOGOUT)) {
                session.showLogin();
            } else if (e.getActionCommand().equals(Values.GENERATE_BILL)) {
                Map<String, Integer> cartDetails = cashierPanelView.getSaleDetails();
                // process the details and store the transations.
                // Also Print the bill
                // by giving command to the printer
                /*try {
                    cashierModel.RecordTransactions(cartDetails);
                } catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }*/
            } else if (e.getActionCommand().equals(Values.CHANGE_PASSWORD)) {
                if (changePassword()) {

                    System.out.println("Password changed successfully");
                } else {
                    System.out.println("Password not changed");
                }

            }
        };
    }

    private boolean changePassword() {
        String newPassword = cashierPanelView.getNewPassword();
        String confirmPassword = cashierPanelView.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            return false;
        }
        return cashierModel.changePassword(newPassword);
    }

    public ArrayList<Product> getBranchProductsToDisplay() {
        return cashierModel.getAllBranchProducts();
    }

    public Boolean isPasswordChanged() {

        return cashierModel.isPasswordChanged();
    }
}
