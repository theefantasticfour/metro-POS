package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomTextField;
import Views.Components.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBranchManager {
    public void display(SuperAdminPanel superAdminPanel) {
        JPanel contentPanel = superAdminPanel.getContentPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch Manager",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left within the form

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 18);

        // Manager ID (disabled text field)
        JLabel managerIdLabel = new JLabel("Manager ID:");
        managerIdLabel.setFont(labelFont);
        gbc.gridx = 0; // Left-most column
        gbc.gridy = 0; // First row
        formPanel.add(managerIdLabel, gbc);

        //implement login to get Manager ID

        CustomTextField managerIdField = new CustomTextField(10);
        managerIdField.setText("0000");
        managerIdField.setEnabled(false);
        gbc.gridx = 1; // Next to the label
        gbc.weightx = 1;
        formPanel.add(managerIdField, gbc);


        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(labelFont);
        gbc.gridx = 2; // Start a new column on the right
        gbc.weightx = 0; // No extra horizontal weight
        formPanel.add(branchCodeLabel, gbc);

        //implement login to get branch Code

        int[] branchCodes = {101, 102, 103, 104, 105}; // Example array of branch codes
        String[] branchCodeStrings = new String[branchCodes.length];
        for (int i = 0; i < branchCodes.length; i++) {
            branchCodeStrings[i] = String.valueOf(branchCodes[i]); // Convert integers to Strings
        }

        CustomComboBox branchCodeComboBox = new CustomComboBox(branchCodeStrings);
        gbc.gridx = 3; // Next to the label
        gbc.weightx = 1;
        formPanel.add(branchCodeComboBox, gbc);
        // Manager Name
        JLabel managerNameLabel = new JLabel("Manager's Name:");
        managerNameLabel.setFont(labelFont);
        gbc.gridx = 0; // New row
        gbc.gridy = 1; // Second row
        gbc.weightx = 0;
        formPanel.add(managerNameLabel, gbc);

        CustomTextField managerNameField = new CustomTextField(10);
        gbc.gridx = 1; // Next to the label
        gbc.gridwidth = 3; // Spans across three columns
        formPanel.add(managerNameField, gbc);

        // Salary
        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(labelFont);
        gbc.gridx = 0; // New row
        gbc.gridy = 2; // Third row
        gbc.weightx = 0;
        formPanel.add(salaryLabel, gbc);

        CustomTextField salaryField = new CustomTextField(10);
        salaryField.setText("0000");
        gbc.gridx = 1; // Next to the label
        gbc.gridwidth = 3;
        formPanel.add(salaryField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        gbc.gridx = 0; // New row
        gbc.gridy = 3; // Fourth row
        formPanel.add(emailLabel, gbc);

        CustomTextField emailField = new CustomTextField(10);
        gbc.gridx = 1; // Next to the label
        gbc.gridwidth = 3; // Spans across three columns
        gbc.weightx = 1;
        formPanel.add(emailField, gbc);

        // Apply Button
        JButton applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(180, 40));
        applyButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        applyButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        applyButton.setFont(new Font(Values.BUTTON_FONT, Font.PLAIN, Values.BUTTON_FONT_SIZE));

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(applyButton, gbc);
        // Add action listener to Apply button
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerId = managerIdField.getText();
                String branchCode = (String) branchCodeComboBox.getSelectedItem();
                String managerName = managerNameField.getText();
                String salary = salaryField.getText();
                String email = emailField.getText();
                JOptionPane.showMessageDialog(contentPanel,
                        "Data Saved",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);


                // Add additional logic to save data
            }
        });


        // Add form panel to content panel
        GridBagConstraints contentGbc = new GridBagConstraints();
        contentGbc.gridx = 0;
        contentGbc.gridy = 0;
        contentGbc.weightx = 1; // Allow the panel to expand horizontally
        contentGbc.weighty = 1; // Allow the panel to expand vertically
        contentGbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        contentPanel.add(formPanel, contentGbc);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
