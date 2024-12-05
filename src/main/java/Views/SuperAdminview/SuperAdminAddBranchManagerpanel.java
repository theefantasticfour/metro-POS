package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Utils.Values;
import Views.Components.CustomComboBox;
import Views.Components.CustomTextFieldDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class SuperAdminAddBranchManagerpanel {
    private final ActionListener superAdminListener;
    private SuperAdminController superAdminController;
    public String managerName;
    public String salary;
    public String email;
    public String managerId;  // Add managerId
    public String branchId;   // Add branchId

    public SuperAdminAddBranchManagerpanel(ActionListener superAdminListener,SuperAdminController instance) {
        this.superAdminController = instance;
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminAddBranchManagerPanel initialized");
    }

    public void display(JPanel parentPanel) {
        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = createFormPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        parentPanel.add(formPanel, gbc);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch Manager",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Branch Manager ID (Read-only)
        addLabel(formPanel, "Branch Manager ID", labelFont, gbc, 0, 0);
        JTextField managerIdField = createDisabledTextField(
                String.valueOf(superAdminController.getUniqueBranchId()) // Auto-generated Branch ID
        );
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.insets=new Insets(4,8,4,8);
        formPanel.add(managerIdField, gbc);

        gbc.insets=new Insets(25,8,4,8);
        addLabel(formPanel, "Branch Code", labelFont, gbc, 1, 0);
        // Fetch all branch IDs from the controller (using the getAllBranchIds method)
        ArrayList<Integer> branchIdsList = superAdminController.getAllBranchIds();
        String[] branchIds = new String[branchIdsList.size()];
        for (int i = 0; i < branchIdsList.size(); i++) {
            branchIds[i] = String.valueOf(branchIdsList.get(i));  // Convert the Integer ID to String
        }
        CustomComboBox branchCodeComboBox = new CustomComboBox(branchIds);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        addComponent(formPanel, branchCodeComboBox, gbc, 1, 1);

        // Manager's Name
        addLabel(formPanel, "Manager's Name", labelFont, gbc, 0, 2);
        JTextField managerNameField = createCustomTextField();
        addComponent(formPanel, managerNameField, gbc, 0, 3);

        // Salary
        addLabel(formPanel, "Salary", labelFont, gbc, 1, 2);
        JTextField salaryField = createCustomTextField();
        addComponent(formPanel, salaryField, gbc, 1, 3);

        // Email
        addLabel(formPanel, "Email", labelFont, gbc, 0, 4);
        JTextField emailField = createCustomTextField();
        addComponent(formPanel, emailField, gbc, 0, 5);

        // Apply Button
        JButton applyButton = createApplyButton();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 8, 4, 8);
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(applyButton, gbc);

        applyButton.addActionListener(e -> {
            managerId = managerIdField.getText().trim(); // Assign value to managerId
            branchId = (String) branchCodeComboBox.getSelectedItem(); // Assign selected branch ID
            managerName = managerNameField.getText().trim();
            salary = salaryField.getText().trim();
            email = emailField.getText().trim();

            if (managerName.isEmpty() || salary.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(
                        formPanel,
                        "All fields except 'Branch Manager ID' must be filled out.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            JOptionPane.showMessageDialog(formPanel, "Branch Manager added successfully!");

            e.getActionCommand().equals(Values.CREATE_MANAGER);

            // Clear the form after submission
            managerNameField.setText("");
            salaryField.setText("");
            emailField.setText("");
            branchCodeComboBox.setSelectedIndex(0);
        });

        return formPanel;
    }

    private void addLabel(JPanel panel, String text, Font font, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private JTextField createCustomTextField() {
        return CustomTextFieldDashboard.createCustomTextField();
    }

    private JTextField createDisabledTextField(String text) {
        JTextField textField = createCustomTextField();
        textField.setText(text);
        textField.setEnabled(false);
        textField.setDisabledTextColor(Color.GRAY);
        return textField;
    }

    private JButton createApplyButton() {
        JButton applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(100, 35));
        applyButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        applyButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        applyButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, 12));
        return applyButton;
    }

    // Getter methods for the new variables
    public String getmanagerId() {
        return managerId;
    }
    public String BranchIdtoCreatemanager() {
        return branchId;
    }
    public String getmanagerName() {
        return managerName;
    }
    public String getSalary() {
        return salary;
    }
    public String getEmail() {
        return email;
    }
}
