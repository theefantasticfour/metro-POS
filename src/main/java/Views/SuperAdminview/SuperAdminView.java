package Views.SuperAdminview;

import Utils.Values;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.MenuPanel;
import Views.SideBarAndHeader.RightPanelHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SuperAdminView extends JPanel {
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display
    private SuperAdminView superAdminView;
    ActionListener superAdminListener;
    SuperAdminAddBranchpanel createBranch;
    SuperAdminAddBranchManagerpanel createBranchManager;
    SuperAdminViewUpdateDeleteBranchespanel viewUpdateDelete;
    SuperAdminReportsGraphspanel reports;

    public SuperAdminView() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize SuperAdminView
        //superAdminView = new SuperAdminView(e -> handleAction(e));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuPanel("Create Branch",Values.CREATION_ICON),
                new MenuPanel("Create Branch Manager",Values.CREATION_ICON),
                new MenuPanel("View/Update/Delete",Values.VIEW_ICON),
                new MenuPanel("Report",Values.REPORT_ICON)
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
        rightPanelHeader = new RightPanelHeader(Values.SUPER_ADMIN_ICON, "Super Admin Dashboard");

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
         createBranch = new SuperAdminAddBranchpanel(superAdminListener);
    }

    private void openCreateBranchManagerForm() {
         createBranchManager= new SuperAdminAddBranchManagerpanel(superAdminListener);
        createBranchManager.display(contentPanel);
    }

    private void openViewUpdateDeleteForm() {
        viewUpdateDelete = new SuperAdminViewUpdateDeleteBranchespanel(superAdminListener);

    }

    private void openReportForm() {
        contentPanel.removeAll();
         reports = new SuperAdminReportsGraphspanel();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Getters and Setters for the data passed from SuperAdminView
    public SuperAdminView getSuperAdminView() {
        return superAdminView;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    // Implement logic for getters from SuperAdminView if needed (for testing or integration purposes)
    public int getBranchIdToUpdate() {
        return superAdminView.getBranchIdToUpdate();
    }

    public int getBranchIdToShowReports() {
        return superAdminView.getBranchIdToShowReports();
    }

    public String getTypeToShowReports() {
        return superAdminView.getTypeToShowReports();
    }

    public String getCity() {
        return superAdminView.getCity();
    }

    public String getAddress() {
        return superAdminView.getAddress();
    }

    public String getPhoneNo() {
        return superAdminView.getPhoneNo();
    }

    public int getNoOfEmployees() {
        return superAdminView.getNoOfEmployees();
    }

    public Boolean getStatus() {
        return superAdminView.getStatus();
    }

    public String getManagerName() {
        return superAdminView.getManagerName();
    }

    public Float getManagerSalary() {
        return superAdminView.getManagerSalary();
    }

    public int getManagerId() {
        return superAdminAddBranchManagerpanel.getManagerId();
    }

    public String getManagerEmail() {
        return superAdminView.getManagerEmail();
    }

    public int getBranchIdToRegister() {
        return superAdminView.getBranchIdToRegister();
    }

    public int getBranchIdToCreateManager() {
        return superAdminView.getBranchIdToCreateManager();
    }
}
