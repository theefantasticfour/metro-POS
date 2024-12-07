package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DataEntryOperatorView extends JPanel {
    ActionListener dataEntryListener;

    // Constructor
    public DataEntryOperatorView(DataEntryOperatorController instance) {
        dataEntryListener = instance.dataEntryListener;
    }



    // Temporary implementations with stub data

    public String getNewPassword() {
        return "temporaryPassword";
    }

    public String getConfirmPassword() {
        return "temporaryPassword";
    }

    public String getVendorName() {
        return "temporaryVendorName";
    }

    public String getVendorAddress() {
        return "temporaryVendorAddress";
    }

    public String getVendorPhone() {
        return "1234567890";
    }

    public String getVendorStartDate() {
        return "2024-01-01";
    }

    public String getVendorEndDate() {
        return "2024-12-31";
    }

    public int getProductIdtoAddProduct() {
        return 1;
    }

    public int getVendorIdToaddProduct() {
        return 100;
    }

    public int getStockQtyToAddProduct() {
        return 50;
    }

    public String getCategorieToAddProduct() {
        return "temporaryCategory";
    }

    public float getCostByUnitToAddProduct() {
        return 10.5f;
    }

    public float getSellingPriceToAddProduct() {
        return 15.0f;
    }

    public float getCartonPriceToAddProduct() {
        return 100.0f;
    }

    public int getCartonQtyToAddProduct() {
        return 10;
    }

    public String getProductNameToAddProduct() {
        return "temporaryProductName";
    }

    public String getVendorNameToUpdate() {
        return "updatedVendorName";
    }

    public String getVendorPhoneToUpdate() {
        return "0987654321";
    }

    public int getVendorIdToUpdateVendor() {
        return 200;
    }
}
