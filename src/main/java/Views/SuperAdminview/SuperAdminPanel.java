package Views.SuperAdminview;

import Session.Session;
import Utils.Values;
import Views.BranchManagerView.ViewUpdateDeleteEmployee;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SuperAdminPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display

    public SuperAdminPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuItem("Create Branch", Values.CREATION_ICON),
                new MenuItem("Create Branch Manager", Values.CREATION_ICON),
                new MenuItem("View/Update/Delete", Values.VIEW_ICON),
                new MenuItem("Report", Values.REPORT_ICON)
        ), e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            switch (buttonText) {
                case "Create Branch":
                    openCreateBranchForm();
                    break;
                case "Create Branch Manager":
                    openCreateBranchManagerForm();
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
        rightPanelHeader = new RightPanelHeader(Values.SUPER_ADMIN_ICON, "Super Admin Dashboard ");

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white
        contentPanel.setLayout(new CardLayout()); // CardLayout for switching between forms

        // Add Left Panel and Right Panel Header to the main panel
        add(leftPanel, BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);

        // Initially display the Create Branch form
        openCreateBranchForm();
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.decode(Values.BG_COLOR)); // Match main panel background

        rightPanel.add(rightPanelHeader, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);

        return rightPanel;
    }

    private void openCreateBranchForm() {
        CreateBranch createBranchForm = new CreateBranch();
        createBranchForm.display(contentPanel);
    }

    private void openCreateBranchManagerForm() {
        CreateBranchManager createBranchManagerForm = new CreateBranchManager();
        createBranchManagerForm.display(contentPanel);
    }

    private void openViewUpdateDeleteForm() {
        contentPanel.removeAll();
        ViewUpdateDelete viewUpdateDelete= new ViewUpdateDelete();
        viewUpdateDelete.display(this);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openReportForm() {
        Reports reports =new Reports();
        reports.display(contentPanel);
    }

    // Handle logout action
    private void handleLogoutAction() {
        // Logout Logic (this is where you can add your logout logic)
        JOptionPane.showMessageDialog(this, "Logging out...");
        // Perform any additional actions here (e.g., redirect to login screen, close the session)
        System.exit(0); // Exit the application for now (can be replaced with a redirection to login)
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
