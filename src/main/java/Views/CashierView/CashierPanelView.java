package Views.CashierView;

import Controllers.CashierController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class CashierPanelView extends JPanel {
     ActionListener actionListener;
    private Boolean isPasswordChanged = false;
     // will be used for
    // Logout and generate bill
    // do not change your implementation just add this actionlistner as well so we can record the
    // transactions in the backend

    CashierGenerateSalePanel GenerateSale;
    CashierController cashierController;
    public CashierPanelView(ActionListener Listener, CashierController insatnce) {
        this.actionListener = Listener;
        this.cashierController = insatnce;
        isPasswordChanged = cashierController.isPasswordChanged();
        inIt();

    }
    public void inIt()
    {
        // to get all products available in a branch
        // cashierController.getBranchProductsToDisplay();
        // will return all products available in the branch in the form of arraylist

    }
    public Map<String,Integer> getbilldetails()
    {
        // whatever the cart is
        // <k,v> k is product id and v is quantity
        return GenerateSale.getCartDetails();

    }


    public String getNewPassword() {
        // return feilds of the new password
        return null;
    }

    public String getConfirmPassword() {
        // return feilds of the confirm password
        return null;
    }
}
