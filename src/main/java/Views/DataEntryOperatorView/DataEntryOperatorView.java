package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;

import Utils.Values;
import Views.Components.CustomChangePassword;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.event.ActionListener;


public class DataEntryOperatorView extends JPanel {
    private Boolean isPasswordChanged = false;
    ActionListener dataEntryListener;
    private DataEntryOperatorController dataEntryOperatorController;
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display
    private DataEntryOperatorAddProduct addProduct;
    private DataEntryOperatorAddVendor addVendor;
    private DataEntryOperatorViewVendor viewVendor;
    private DataEntryOperatorViewProduct viewProduct;
    private CustomChangePassword changePassword;


    // Constructor
    public DataEntryOperatorView(ActionListener LISTENER,DataEntryOperatorController instance) {
        dataEntryListener = LISTENER;
        dataEntryOperatorController =instance;
        isPasswordChanged = dataEntryOperatorController.isPasswordChanged();
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuPanel("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuPanel("Add New Vendor", Values.CREATION_ICON),
                new MenuPanel("Add New Product", Values.CREATION_ICON),
                new MenuPanel("View Products", Values.VIEW_ICON),
                new MenuPanel("View Vendor", Values.VIEW_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            // Conditional logic based on isPasswordChanged
            if (!isPasswordChanged && !buttonText.equals("Change Password")) {
                JOptionPane.showMessageDialog(this,
                        "Please change your password first.",
                        "Action Restricted",
                        JOptionPane.WARNING_MESSAGE);
                return; // Prevent other buttons from functioning
            }
            switch (buttonText) {
                case "Change Password":
                    openChangePasswordForm();
                    break;
                case "Add New Vendor":
                    openAddNewVendorForm();
                    break;
                case "Add New Product":
                    openAddNewProductForm();
                    break;
                case "View Products":
                    openViewProductsForm();
                    break;
                case "View Vendor":
                    openViewVendorForm();
                    break;
            }
        },dataEntryListener);
        // Initialize Right Panel Header with icon and label for Data Entry Operator
        rightPanelHeader = new RightPanelHeader(Values.DATA_ENTRY_ICON, "Data Entry Operator");

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white
        contentPanel.setLayout(new CardLayout()); // CardLayout for switching between forms

        // Add Left Panel and Right Panel Header to the main panel
        add(leftPanel, BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);


        openChangePasswordForm();


    }
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.decode(Values.BG_COLOR)); // Match main panel background

        rightPanel.add(rightPanelHeader, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);

        return rightPanel;
    }

    private void openChangePasswordForm() {
        changePassword = new CustomChangePassword(() -> {
            // Callback when password is changed
            isPasswordChanged = true; // Allow other buttons to function
            JOptionPane.showMessageDialog(this,
                    "Password changed successfully. You can now access other features.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        },dataEntryListener);
        changePassword.display(contentPanel);
    }
    private void openAddNewVendorForm() {

        addVendor = new DataEntryOperatorAddVendor(dataEntryListener,dataEntryOperatorController);
        addVendor.display(contentPanel);
    }

    private void openAddNewProductForm() {
        addProduct = new DataEntryOperatorAddProduct(dataEntryListener,dataEntryOperatorController);
        addProduct.display(contentPanel);
    }

    private void openViewProductsForm() {
        viewProduct =new DataEntryOperatorViewProduct(dataEntryListener,dataEntryOperatorController,this);
       viewProduct.display();
    }

    private void openViewVendorForm() {
         viewVendor=new DataEntryOperatorViewVendor(dataEntryListener,dataEntryOperatorController,this);
        viewVendor.display();
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


    public String getNewPassword() {
        return changePassword.password();
    }

    public String getConfirmPassword() {
        return changePassword.Confpassword();
    }


    public String getVendorName() {
        return addVendor.getVendorName();
    }

    public String getVendorAddress() {
        return addVendor.getVendorName();
    }

    public String getVendorPhone() {
        return addVendor.getPhone();
    }

    public String getVendorStartDate() {
        return addVendor.getStartDate();
    }

    public String getVendorEndDate() {
        return addVendor.getEndDate();
    }
    public String getVendorEmail() {
        return addVendor.getEmail();
    }

    //no need for this getter
    public int getProductIdtoAddProduct() {return 1;}

    public int getVendorIdToaddProduct() {return Integer.parseInt(addProduct.getVendor());}
    public int getStockQtyToAddProduct() {return Integer.parseInt(addProduct.getQuantity());}
    public String getCategorieToAddProduct() {
        return addProduct.getCategory();
    }
    public float getCostByUnitToAddProduct() {
        return Float.parseFloat(addProduct.getPriceByUnit());
    }
    public float getSellingPriceToAddProduct() {
        return Float.parseFloat(addProduct.getSalePrice());
    }
    public float getOrigianlPriceToAddProduct() {return Float.parseFloat(addProduct.getOriginalPrice());}

    //no need for this getter
    public float getCartonPriceToAddProduct() {
        return Float.parseFloat(addProduct.getPriceByCarton());
    }
    public int getCartonQtyToAddProduct() {return 10;}
    public String getProductNameToAddProduct() {return addProduct.getProductName();}

    public int getProductIdToUpdate() {
        return Integer.parseInt(viewProduct.getProductId());
    }

    // Fetch the product name to update
    public String getProductNameToUpdate() {
        return viewProduct.getProductName();
    }

    // Fetch the stock quantity to update
    public int getProductStockQtyToUpdate() {
        return Integer.parseInt(viewProduct.getProductQuantityInStock());
    }

    // Fetch the product category to update
    public String getProductCategoryToUpdate() {
        return viewProduct.getProductCategory();
    }

    // Fetch the cost per unit to update
    public float getProductCostByUnitToUpdate() {
        return Float.parseFloat(viewProduct.getProductOriginalPrice());
    }

    // Fetch the selling price per unit to update
    public float getProductSellingPriceToUpdate() {
        return Float.parseFloat(viewProduct.getProductSalePricePerUnit());
    }

    // Fetch the selling price per carton to update
    public float getProductCartonPriceToUpdate() {
        return Float.parseFloat(viewProduct.getProductSalePricePerCarton());
    }
    public int getProductVendorToUpdate() {
        return Integer.parseInt(viewProduct.getProductVendor());
    }
    public String getVendorNameToUpdate() {
        return viewVendor.getVendorName();
    }

    public String getVendorPhoneToUpdate() {
        return viewVendor.getPhone();
    }

    public int getVendorIdToUpdateVendor() {
        return Integer.parseInt(viewVendor.getVendorId());
    }
}
