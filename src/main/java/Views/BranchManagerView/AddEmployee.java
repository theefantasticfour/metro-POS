package Views.BranchManagerView;

import Utils.Values;
import Views.Components.CustomComboBox;

import javax.swing.*;
import java.awt.*;

import static Views.Components.CustomTextFieldDashboard.createCustomTextField;

public class AddEmployee {

    public void display(BranchManagerPanel branchManagerPanel) {
        // Get the content panel from the BranchManagerPanel instance
        JPanel contentPanel = branchManagerPanel.getContentPanel();

        // Clear any existing components in the content panel
        contentPanel.removeAll();

        // Set layout for the content panel to center the form
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Create the main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Employee",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Employee Type
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Employee Type", labelFont, gbc, 0, 0);
        CustomComboBox employeeTypeComboBox = new CustomComboBox(new String[]{"Cashier", "Data Entry Operator"});
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(employeeTypeComboBox, gbc);

        // Employee Name
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Employee Name", labelFont, gbc, 1, 0);
        JTextField employeeNameField = createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(employeeNameField, gbc);

        // Email Address
        addLabelAndComponent(formPanel, "Email Address", labelFont, gbc, 0, 2);
        JTextField emailField = createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailField, gbc);

        // Salary
        addLabelAndComponent(formPanel, "Salary", labelFont, gbc, 1, 2);
        JTextField salaryField = createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(salaryField, gbc);


        // Apply Button
        JButton applyButton = createApplyButton();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 8, 4, 8);
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(applyButton, gbc);

        // Button Action Listener
        applyButton.addActionListener(e -> {
            String empType = employeeTypeComboBox.getSelectedItem().toString();
            String empName = employeeNameField.getText().trim();
            String email = emailField.getText().trim();
            String salary = salaryField.getText().trim();

            // Validate inputs
            if (empName.isEmpty() || email.isEmpty() || salary.isEmpty()) {
                JOptionPane.showMessageDialog(contentPanel,
                        "All fields must be filled out.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display a success message
            JOptionPane.showMessageDialog(contentPanel,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Add logic to save data to a database or file (Placeholder)
        });

        // Add form panel to content panel
        GridBagConstraints parentGbc = new GridBagConstraints();
        parentGbc.gridx = 0;
        parentGbc.gridy = 0;
        parentGbc.weightx = 1;
        parentGbc.weighty = 1;
        parentGbc.fill = GridBagConstraints.HORIZONTAL;

        contentPanel.add(formPanel, parentGbc);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void addLabelAndComponent(JPanel panel, String labelText, Font labelFont, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);
    }

    private JButton createApplyButton() {
        JButton applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(100, 35));
        applyButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        applyButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        applyButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, 12));
        return applyButton;
    }
}
