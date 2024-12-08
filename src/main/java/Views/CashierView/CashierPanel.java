package Views.CashierView;


import Utils.Values;
import Views.Components.CustomChangePassword;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CashierPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display

    public CashierPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuItem("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuItem("Generate Sale", Values.CREATION_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            switch (buttonText) {
                case "Change Password":
                    openChangePasswordForm();
                    break;
                case "Generate Sale":
                    openGenerateSale();
                    break;
            }
        });

        // Initialize Right Panel Header with icon and label for Data Entry Operator
        rightPanelHeader = new RightPanelHeader(Values.CASHIER_ICON, "Cashier");

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

    public void openGenerateSale() {
        contentPanel.removeAll();
        GenerateSale generateSale = new GenerateSale();
        generateSale.display(contentPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }



    public JPanel getContentPanel() {
        return contentPanel;
    }
}
