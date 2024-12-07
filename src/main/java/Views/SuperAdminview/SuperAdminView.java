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
        },superAdminListener);


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
        reports = new SuperAdminReportsGraphspanel(superAdminListener,superAdminController);
         reports.display(contentPanel);
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
        return viewUpdateDelete.getBranchIdtoUpdate();
    }

    public int getBranchIdToShowReports() {
        return  Integer.parseInt(reports.getBranchIdToShowreports()); // will be -1 branci id if nothing is selected
    }
    // only one input at a time
    //if date fields are filled, slider input is null.
    //If slider is moved,  date fields are null.
    public String getTypeToShowReports() {
        return reports.getReportType();
    }
    public String getTypeToShowReportStartDate()
    {
        return reports.getStartDate();
    }
    public String getTypeToShowReportEndDate()
    {
        return reports.getEndDate();
    }

    public int getManagerId() {

            return Integer.parseInt(createBranchManager.getmanagerId());

    }

    public String getManagerName() {

            return createBranchManager.getmanagerName();

    }

    public Float getManagerSalary() {

            return Float.parseFloat(createBranchManager.getSalary());

    }

    public String getManagerEmail() {

            return createBranchManager.getEmail();

    }

    public int getBranchIdtoCreateManager() {

            return Integer.parseInt(createBranchManager.BranchIdtoCreatemanager());

    }
    public int getBranchidtoRegister() {

            return  Integer.parseInt(createBranch.getBranchId());

    }

    public String getCity() {

            return createBranch.getCity();

    }

    public String getAddress() {

            return createBranch.getAddress();

    }

    public String getPhoneNo() {

            return createBranch.getPhoneNumber();

    }

    public int getNoOfEmployees() {

            return Integer.parseInt(createBranch.getNumberOfEmployees());

    }

    public Boolean getStatus() {

            return Boolean.parseBoolean(createBranch.getStatus());

    }
    public String getbranchName()
    {
            return createBranch.getBranchName();

    }


    public String getCityToUpdate() {
        return viewUpdateDelete.getCityToUpdate();
    }

    public String getPhonenoToUpdate() {
        return  viewUpdateDelete.getPhonenoToUpdate();
    }

    public String getAdressToUpdate() {
        return viewUpdateDelete.getAdressToUpdate();
    }

    public int getNoofEmployeesToUpdate() {
        return viewUpdateDelete.getNoofEmployeesToUpdate();
    }

    public Boolean getStatusToUpdate() {
        return viewUpdateDelete.getStatusToUpdate();
    }

    public String getManagerNameToUpdate() {
        return viewUpdateDelete.getManagerNameToUpdate();
    }

    public String getManagerSalaryToUpdate() {
        return viewUpdateDelete.getManagerSalaryToUpdate();
    }
}