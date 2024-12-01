package Views.DataEntryOperatorView;

import Utils.Values;
import Views.Components.CustomChangePassword;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DataEntryOperatorPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display

    public DataEntryOperatorPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuItem("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuItem("Add New Vendor", Values.CREATION_ICON),
                new MenuItem("Add New Product", Values.CREATION_ICON),
                new MenuItem("View Products", Values.VIEW_ICON),
                new MenuItem("View Vendor", Values.VIEW_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
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
        });

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
        CustomChangePassword addchangePasswordForm = new CustomChangePassword();
        addchangePasswordForm.display(contentPanel);
    }

    private void openAddNewVendorForm() {

        AddNewVendor addNewVendorForm = new AddNewVendor();
        addNewVendorForm.display(contentPanel);
    }


    private void openAddNewProductForm() {
        AddNewProduct addNewProductForm = new AddNewProduct();
        addNewProductForm.display(contentPanel);
    }

    private void openViewProductsForm() {

        contentPanel.removeAll();
        ViewProducts viewProducts=new ViewProducts();
        viewProducts.display(contentPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openViewVendorForm() {
        // Placeholder for View Vendor form (replace with actual form)
        contentPanel.removeAll();
     ViewVendor viewVendor=new ViewVendor();
     viewVendor.display(contentPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
