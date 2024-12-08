package Views.CashierView;

import Controllers.CashierController;
import Utils.Values;
import Views.Components.CustomChangePassword;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.MenuPanel;
import Views.SideBarAndHeader.RightPanelHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;

public class CashierPanelView extends JPanel {
    private Boolean isPasswordChanged = false; // Other buttons only work once the password is changed
    private CashierController cashierController;
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel;
    private CustomChangePassword changePasswordPanel;
    private CashierGenerateSalePanel generateSalePanel;
    private ActionListener cashierListener;

    public CashierPanelView(ActionListener listener, CashierController controller) {
        this.cashierController = controller;
        this.cashierListener = listener;
        this.isPasswordChanged = cashierController.isPasswordChanged();

        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with menu items
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuPanel("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuPanel("Generate Sale", Values.CREATION_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (!isPasswordChanged && !buttonText.equals("Change Password")) {
                JOptionPane.showMessageDialog(this,
                        "Please change your password first.",
                        "Action Restricted",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            switch (buttonText) {
                case "Change Password":
                    openChangePasswordForm();
                    break;
                case "Generate Sale":
                    openGenerateSaleForm();
                    break;
            }
        }, cashierListener);

        // Initialize Right Panel Header
        rightPanelHeader = new RightPanelHeader(Values.CASHIER_ICON, "Cashier");

        // Initialize Content Panel
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(Values.BG_COLOR));
        contentPanel.setLayout(new CardLayout());

        // Add components to the main layout
        add(leftPanel, BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);

        // Initially display the Change Password form
        openChangePasswordForm();
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.decode(Values.BG_COLOR));

        rightPanel.add(rightPanelHeader, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);

        return rightPanel;
    }

    private void openChangePasswordForm() {
        changePasswordPanel = new CustomChangePassword(() -> {
            isPasswordChanged = true;
            JOptionPane.showMessageDialog(this,
                    "Password changed successfully. You can now access other features.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }, cashierListener);
        changePasswordPanel.display(contentPanel);
    }

    private void openGenerateSaleForm() {
        contentPanel.removeAll();
        generateSalePanel = new CashierGenerateSalePanel(cashierListener, cashierController);
        generateSalePanel.display(contentPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Getters for data retrieval
    public String getNewPassword() {
        return changePasswordPanel != null ? changePasswordPanel.password() : null;
    }

    public String getConfirmPassword() {
        return changePasswordPanel != null ? changePasswordPanel.Confpassword() : null;
    }

    public Map<String, Integer> getSaleDetails() {
        return generateSalePanel != null ? CashierGenerateSalePanel.getCartDetails() : null;
    }
}
