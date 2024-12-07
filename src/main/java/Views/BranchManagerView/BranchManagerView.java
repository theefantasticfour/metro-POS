package Views.BranchManagerView;

import Controllers.BranchManagerController;
import Controllers.SuperAdminController;
import Views.Components.CustomChangePassword;
import Utils.Values;
import Views.SideBarAndHeader.LeftPanel;
import Views.SideBarAndHeader.RightPanelHeader;
import Views.SideBarAndHeader.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class BranchManagerView extends JPanel {
    private Boolean isPasswordChanged = false; // Other buttons only work once the password is changed
    private BranchManagerController branchManagerController;
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel;
    private BranchManagerAddEmployeepanel addEmployeeForm;
    private ActionListener branchMangerListener;
    private BranchManagerAddUpdateEmployeepanel viewUpdateDeleteEmployee;
    private BranchManagerReportsPanel reports;
    private CustomChangePassword changePassword;

    public BranchManagerView(ActionListener LISTENER, BranchManagerController instance) {
        branchManagerController = instance;
        branchMangerListener = LISTENER;
        System.out.println("Branch Manager View initialized");
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Initialize Left Panel with dynamic menu items and actions
        leftPanel = new LeftPanel(Arrays.asList(
                new MenuPanel("Change Password", Values.CHANGE_PASSWORD_ICON),
                new MenuPanel("Add Employee", Values.CREATION_ICON),
                new MenuPanel("View/Update/Delete", Values.VIEW_ICON),
                new MenuPanel("Report", Values.REPORT_ICON)
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

            // Handle button actions
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
        },branchMangerListener);

        rightPanelHeader = new RightPanelHeader(Values.BRANCH_MANAGER_ICON, "Branch Manager");

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(Values.BG_COLOR));
        contentPanel.setLayout(new CardLayout());

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

    private void openChangePasswordForm() {
        changePassword = new CustomChangePassword(() -> {
            // Callback when password is changed
            isPasswordChanged = true; // Allow other buttons to function
            JOptionPane.showMessageDialog(this,
                    "Password changed successfully. You can now access other features.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        },branchMangerListener);
        changePassword.display(contentPanel);
    }


    private void openAddEmployeeForm() {
        contentPanel.removeAll();
        addEmployeeForm = new BranchManagerAddEmployeepanel(branchMangerListener, branchManagerController);
        addEmployeeForm.display(this);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openViewUpdateDeleteForm() {
        contentPanel.removeAll();
       viewUpdateDeleteEmployee = new BranchManagerAddUpdateEmployeepanel(branchMangerListener, branchManagerController,this);
        viewUpdateDeleteEmployee.display();
       contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openReportForm() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        reports = new BranchManagerReportsPanel(branchMangerListener, branchManagerController);
        reports.display(contentPanel);
    }
    public JPanel getContentPanel() {
        return contentPanel;
    }

    // only one input at a time
    //if date fields are filled, slider input is null.
    //If slider is moved,  date fields are null.
    public String getType() {
        return reports.getReportRangeType();
    }
    public String getTypeToShowReportStartDate()
    {
        return reports.getStartDate();
    }
    public String getTypeToShowReportEndDate()
    {
        return reports.getEndDate();
    }


    public String getEmployeeType()
   {return addEmployeeForm.getEmpType();}
    public String getEmployeeName()
    {return addEmployeeForm.getEmpName();}
    public String getEmployeeEmail()
    {return addEmployeeForm.getEmail();}
    public Float getEmployeeSalary()
    {return Float.parseFloat(addEmployeeForm.getSalary());}
    public String getNewPassword()
    {return changePassword.password(); }
    public String getConfirmPassword()
    {return changePassword.Confpassword();}


    public String getEmployeeTypeToUpdate(){return viewUpdateDeleteEmployee.getEmployeeType();}
    public String getEmployeeNameToUpdate(){return viewUpdateDeleteEmployee.getEmployeeName();}
    public String getEmployeeEmailToUpdate(){return viewUpdateDeleteEmployee.getEmployeeEmail();}
    public Float getEmployeeSalaryToUpdate(){return Float.parseFloat(viewUpdateDeleteEmployee.getEmployeeSalary());}
    public Boolean getEmployeeStatusToUpdate(){return Boolean.parseBoolean(viewUpdateDeleteEmployee.getEmployeeStatus());}

}
