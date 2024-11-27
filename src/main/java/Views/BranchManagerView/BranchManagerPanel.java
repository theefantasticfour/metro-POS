package Views.BranchManagerView;

import Views.Components.CustomChangePassword;
import Utils.Values;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BranchManagerPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display

    public BranchManagerPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuItem("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuItem("Add Employee", Values.CREATION_ICON),
                new MenuItem("View/Update/Delete", Values.VIEW_ICON),
                new MenuItem("Report", Values.REPORT_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            switch (buttonText) {
                case "Change Password":
                    openChangePasswordForm();
                    break;
                case "Add Employee":
                    openAddEmployeeForm();
                    break;
                case "View/Update/Delete":
                    openViewUpdateDeleteForm();
                    break;
                case "Report":
                    openReportForm();
                    break;
            }
        });

        // Initialize Right Panel Header with icon and label
        rightPanelHeader = new RightPanelHeader(Values.BRANCH_MANAGER_ICON, "Branch Manager");

        // Initialize Content Panel for dynamic form switching
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white
        contentPanel.setLayout(new CardLayout()); // CardLayout for switching between forms

        // Add Left Panel and Right Panel Header to the main panel
        add(leftPanel, BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);

        // Initially display the Change Password form
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

    // Separate method to display the Change Password form
    private void openChangePasswordForm() {
        CustomChangePassword changePasswordForm = new CustomChangePassword();
        changePasswordForm.display(contentPanel);
    }

    // Method to display the Add Employee form
    private void openAddEmployeeForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("Add Employee Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Method to display the View/Update/Delete form
    private void openViewUpdateDeleteForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("View/Update/Delete Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Method to display the Report form
    private void openReportForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("Report Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Getter for the content panel
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
