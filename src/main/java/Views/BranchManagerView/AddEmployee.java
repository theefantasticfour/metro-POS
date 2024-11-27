package Views.BranchManagerView;

import Utils.Values;
import Views.Components.CustomTextField;
import Views.Components.CustomComboBox;
import Views.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee {
    public void display(BranchManagerPanel branchManagerPanel) {
        // Get the content panel from the BranchManagerPanel instance
        JPanel contentPanel = branchManagerPanel.getContentPanel();

        // Clear any existing components in the content panel
        contentPanel.removeAll();

        // Set layout for the content panel to center the form
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color

        // Create the main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Employee",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 18);

        // Employee Type ComboBox
        JLabel empTypeLabel = new JLabel("Employee Type:");
        empTypeLabel.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(empTypeLabel, gbc);

        CustomComboBox empTypeCombo = new CustomComboBox(new String[]{"Cashier", "Data Entry Operator"});
        gbc.gridx = 1; gbc.weightx = 1;
        formPanel.add(empTypeCombo, gbc);

        // Employee Name
        JLabel empNameLabel = new JLabel("Employee Name:");
        empNameLabel.setFont(labelFont);
        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(empNameLabel, gbc);

        CustomTextField empNameField = new CustomTextField(25);
        gbc.gridx = 3; gbc.weightx = 1;
        formPanel.add(empNameField, gbc);

        // Email Address
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(emailLabel, gbc);

        CustomTextField emailField = new CustomTextField(25);
        gbc.gridx = 1; gbc.weightx = 1;
        formPanel.add(emailField, gbc);

        // Branch Code
        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(labelFont);
        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(branchCodeLabel, gbc);

        CustomTextField branchCodeField = new CustomTextField(25);
        gbc.gridx = 3; gbc.weightx = 1;
        formPanel.add(branchCodeField, gbc);

        // Salary
        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(salaryLabel, gbc);

        CustomTextField salaryField = new CustomTextField(25);
        gbc.gridx = 1; gbc.weightx = 1;
        formPanel.add(salaryField, gbc);

        // Apply Button
        CustomButton applyButton = new CustomButton("Apply");
        applyButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 3; gbc.gridy = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(applyButton, gbc);

        // Button Action Listener
        applyButton.addActionListener(e -> {
            String empType = empTypeCombo.getSelectedItem().toString();
            String empName = empNameField.getText();
            String email = emailField.getText();
            String branchCode = branchCodeField.getText();
            String salary = salaryField.getText();

            // Display a success message
            JOptionPane.showMessageDialog(contentPanel,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Add logic to save data to a database or file
        });

        // Add form panel to content panel
        contentPanel.add(formPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
