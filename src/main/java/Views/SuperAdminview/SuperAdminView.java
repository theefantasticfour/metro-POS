package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Models.SuperAdmin;
import Utils.Values;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.MenuPanel;
import Views.SideBarAndHeader.RightPanelHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SuperAdminView extends JPanel {
    SuperAdminController superAdminController;
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel; // Panel for dynamic content display
    private SuperAdminView superAdminView;
    ActionListener superAdminListener;
    SuperAdminAddBranchpanel createBranch;
    SuperAdminAddBranchManagerpanel createBranchManager;
    SuperAdminViewUpdateDeleteBranchespanel viewUpdateDelete;
    SuperAdminReportsGraphspanel reports;

    public SuperAdminView(ActionListener LISTNER,SuperAdminController instance) {
        this.superAdminController = instance;
         superAdminListener = LISTNER;
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize SuperAdminView
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
        createBranch = new SuperAdminAddBranchpanel(superAdminListener,superAdminController,contentPanel);
    }

    private void openCreateBranchManagerForm() {
        createBranchManager= new SuperAdminAddBranchManagerpanel(superAdminListener,superAdminController);
        createBranchManager.display(contentPanel);
    }

    private void openViewUpdateDeleteForm() {
        contentPanel.removeAll();
        viewUpdateDelete = new SuperAdminViewUpdateDeleteBranchespanel(superAdminListener,superAdminController,contentPanel);
        //  viewUpdateDelete.display(this);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openReportForm() {
        contentPanel.removeAll();
        reports = new SuperAdminReportsGraphspanel();
        // reports.display(contentPanel);
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

    public int getManagerId() {
        if (createBranchManager != null) {
            return Integer.parseInt(createBranchManager.getmanagerId());
        }
        throw new IllegalStateException("Create Branch Manager form is not initialized.");
    }

    public String getManagerName() {
        if (createBranchManager != null) {
            return createBranchManager.getmanagerName();
        }
        throw new IllegalStateException("Create Branch Manager form is not initialized.");
    }

    public Float getManagerSalary() {
        if (createBranchManager != null) {
            return Float.parseFloat(createBranchManager.getSalary());
        }
        throw new IllegalStateException("Create Branch Manager form is not initialized.");
    }

    public String getManagerEmail() {
        if (createBranchManager != null) {
            return createBranchManager.getEmail();
        }
        throw new IllegalStateException("Create Branch Manager form is not initialized.");
    }

    public int getBranchIdtoCreateManager() {
        if (createBranchManager != null) {
            return Integer.parseInt(createBranchManager.BranchIdtoCreatemanager());
        }
        throw new IllegalStateException("Create Branch Manager form is not initialized.");
    }
    public int getBranchidtoRegister() {
        if (createBranch != null) {
            return  Integer.parseInt(createBranch.getBranchId());
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }

    public String getCity() {
        if (createBranch != null) {
            return createBranch.getCity();
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }

    public String getAddress() {
        if (createBranch != null) {
            return createBranch.getAddress();
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }

    public String getPhoneNo() {
        if (createBranch != null) {
            return createBranch.getPhoneNumber();
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }

    public int getNoOfEmployees() {
        if (createBranch != null) {
            return Integer.parseInt(createBranch.getNumberOfEmployees());
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }

    public Boolean getStatus() {
        if (createBranch != null) {
            return Boolean.parseBoolean(createBranch.getStatus());
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }
    public String getbranchName()
    {
        if (createBranch != null) {
            return createBranch.getBranchName();
        }
        throw new IllegalStateException("Create Branch form is not initialized.");
    }


}
