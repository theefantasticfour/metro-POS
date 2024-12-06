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
    Boolean isPasswordChanged = false; // other buttons will only work once password is changed
    // you can try some disabled colours like light grey or something and change it once
    // isPasswordChanged is true
    BranchManagerController branchManagerController;
    private LeftPanel leftPanel;
    private RightPanelHeader rightPanelHeader;
    private JPanel contentPanel;
    BranchManagerAddEmployeepanel addEmployeeForm;
    ActionListener branchMangerListener;
    BranchManagerAddUpdateEmployeepanel viewUpdateDeleteEmployee;
    BranchManagerReportsPanel reports;


    public BranchManagerView(ActionListener LISTNER, BranchManagerController instance) {
        branchManagerController = instance;
        LISTNER =branchMangerListener;
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

        // Separate method to display the Change Password form
        private void openChangePasswordForm() {
            CustomChangePassword changePasswordForm = new CustomChangePassword();
            changePasswordForm.display(contentPanel);
        }

        // Method to display the Add Employee form
        private void openAddEmployeeForm() {
            contentPanel.removeAll();
             addEmployeeForm = new BranchManagerAddEmployeepanel(branchMangerListener,branchManagerController);
            addEmployeeForm.display(this);
            contentPanel.revalidate();
            contentPanel.repaint();
        }

        // Method to display the View/Update/Delete form
        private void openViewUpdateDeleteForm() {
            contentPanel.removeAll();
             viewUpdateDeleteEmployee = new BranchManagerAddUpdateEmployeepanel(branchMangerListener,branchManagerController);
            viewUpdateDeleteEmployee.display(this); // Pass the parent panel for the form
            contentPanel.revalidate();
            contentPanel.repaint();
        }


        // Method to display the Report form
        private void openReportForm() {
            contentPanel.removeAll();
            //contentPanel.add(new JLabel("Report Form")); // Replace with actual form panel
            contentPanel.revalidate();
            contentPanel.repaint();
            reports = new BranchManagerReportsPanel(branchMangerListener,branchManagerController);
            reports.display(contentPanel);
        }

        // Getter for the content panel
        public JPanel getContentPanel() {
            return contentPanel;
        }
    }
}
