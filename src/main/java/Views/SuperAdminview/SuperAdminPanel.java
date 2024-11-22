package Views.SuperAdminview;

import Utils.Values;
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
        // Set layout for the main panel
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Values.LOGO_ICON, Arrays.asList(
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

    // Example method to handle "Create Branch" menu action
    private void openCreateBranchForm() {
        // Clear the content panel
        contentPanel.removeAll();

        // Create an instance of CreateBranch and call its display method
        CreateBranch createBranchForm = new CreateBranch();
        createBranchForm.display(this);

        // Revalidate and repaint to apply changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }




    // Example method to handle "Create Branch Manager" menu action
    private void openCreateBranchManagerForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("Create Branch Manager Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Example method to handle "View/Update/Delete" menu action
    private void openViewUpdateDeleteForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("View/Update/Delete Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Example method to handle "Report" menu action
    private void openReportForm() {
        contentPanel.removeAll();
        contentPanel.add(new JLabel("Report Form")); // Replace with actual form panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Getter for the contentPanel
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
