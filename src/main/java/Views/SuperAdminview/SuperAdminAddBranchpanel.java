package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Utils.Values;
import Views.Components.CustomComboBox;
import Views.Components.CustomTextFieldDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SuperAdminAddBranchpanel extends JPanel {
    ActionListener SuperAdminListener;
    SuperAdminController superAdminController;
    public String branchId;   // Added branchId
    public String city;
    public String address;
    public String phoneNumber;
    public String numberOfEmployees;
    public String status;
    public JPanel parentPanel;

    public SuperAdminAddBranchpanel(ActionListener SuperAdminListener,SuperAdminController instance,JPanel parentPanel) {
        this.superAdminController=instance;
        this.parentPanel = parentPanel;
        this.SuperAdminListener = SuperAdminListener;
        System.out.println("SuperAdminAddBranchpanel initialized");
        inIt();
    }

    public void inIt() {
        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Branch Code (Disabled)
        branchId=String.valueOf(superAdminController.getUniqueBranchId());
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Branch ID", labelFont, gbc, 0, 0);
        JTextField branchCodeField = createDisabledTextField(
                String.valueOf(superAdminController.getUniqueBranchId())
        );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(branchCodeField, gbc);

        // City
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "City", labelFont, gbc, 1, 0);
        JTextField cityField = createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(cityField, gbc);

        // Address
        addLabelAndComponent(formPanel, "Address", labelFont, gbc, 0, 2);
        JTextField addressField = createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(addressField, gbc);

        // Phone Number
        addLabelAndComponent(formPanel, "Phone No", labelFont, gbc, 0, 4);
        JTextField phoneNumberField = createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(phoneNumberField, gbc);

        // Number of Employees
        addLabelAndComponent(formPanel, "No. of Employees", labelFont, gbc, 1, 4);
        JTextField employeesField = createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(employeesField, gbc);

        // Active Status
        addLabelAndComponent(formPanel, "Status", labelFont, gbc, 1, 2);
        CustomComboBox activeStatusComboBox = new CustomComboBox(
                new String[]{"Open", "Close", "Temporary Closed"}
        );
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(activeStatusComboBox, gbc);

        // Apply Button
        JButton applyButton = createApplyButton();
        applyButton.setActionCommand(Values.REGISTER_BRANCH);
        applyButton.addActionListener(SuperAdminListener);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 8, 4, 8);
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(applyButton, gbc);

        applyButton.addActionListener(e -> {
            branchId = branchCodeField.getText().trim(); // Branch ID (disabled field)
            city = cityField.getText().trim();
            address = addressField.getText().trim();
            phoneNumber = phoneNumberField.getText().trim();
            numberOfEmployees = employeesField.getText().trim();
            status = (String) activeStatusComboBox.getSelectedItem();

            if (city.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || numberOfEmployees.isEmpty() || status == null) {
                JOptionPane.showMessageDialog(
                        formPanel,
                        "All fields must be filled out.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            e.getActionCommand().equals(Values.CREATE_MANAGER);
            JOptionPane.showMessageDialog(
                    formPanel,
                    "Branch added successfully!\nBranch ID: " + branchId,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            cityField.setText("");
            addressField.setText("");
            phoneNumberField.setText("");
            employeesField.setText("");
            activeStatusComboBox.setSelectedIndex(0);

        });


        GridBagConstraints parentGbc = new GridBagConstraints();
        parentGbc.gridx = 0;
        parentGbc.gridy = 0;
        parentGbc.weightx = 1;
        parentGbc.weighty = 1;
        parentGbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(formPanel, parentGbc);
        parentPanel.add(formPanel, parentGbc);
        parentPanel.revalidate();
        parentPanel.repaint();
    }

    private void addLabelAndComponent(JPanel panel, String labelText, Font labelFont, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);
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

    // Getter methods for branchId, city, address, phoneNumber, numberOfEmployees, and status
    public String getBranchId() {
        return branchId;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getStatus() {
        return status;
    }
    public String getBranchName()
    {
        // if we decide to name our branch
        return "DHA branch";
    }
}
