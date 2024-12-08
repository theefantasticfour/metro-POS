package Controllers;

import Entites.Product;
import Models.Cashier;
import Session.Session;
import Utils.Values;
import Views.CashierView.CashierPanelView;
import Views.Mainscreen;

import java.awt.event.ActionListener;
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
        Mainscreen.getInstance().showCashier(actionListener,this);
        cashierPanelView = Mainscreen.getCashierPanelView();

    }

    private void setActionListeners() {
        actionListener = e -> {
            if (e.getActionCommand().equals(Values.LOGOUT))
            {
                session.showLogin();
            }
            else if (e.getActionCommand().equals(Values.GENERATE_BILL))
            {
                Map<String,Integer> cartDetails = cashierPanelView.getbilldetails();
                // process the details and store the transations.
                // Also Print the bill
                // by giving command to the printer
                cashierModel.RecordTransactions(cartDetails);
            }
        };
    }
    public ArrayList<Product> getBranchProductsToDisplay() {
        return cashierModel.getAllBranchProducts();
    }

}
