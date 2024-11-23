package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomTextField;
import Views.Components.CustomComboBox;
import Views.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBranch {
    public void display(SuperAdminPanel superAdminPanel) {
        // Get the content panel
        JPanel contentPanel = superAdminPanel.getContentPanel();

        // Clear any existing components in the content panel
        contentPanel.removeAll();

        // Set layout for the content panel to center the form
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white

        // Create the main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR)); // Set background color to white
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left within the form

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 18);
        Font fieldFont = new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, 16);

        // Branch Code (disabled text field)
        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(labelFont);
        gbc.gridx = 0; // Left-most column
        gbc.gridy = 0; // First row
        gbc.weightx = 0; // No extra horizontal space
        formPanel.add(branchCodeLabel, gbc);

        //implement login to get branch code

        CustomTextField branchCodeField = new CustomTextField(10);
        branchCodeField.setText("0000");
        branchCodeField.setEnabled(false);
        gbc.gridx = 1; // Next to the label
        gbc.weightx = 1; // Expand horizontally
        formPanel.add(branchCodeField, gbc);

        // City (text field)
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(labelFont);
        gbc.gridx = 2; // Start a new column on the right
        gbc.gridy = 0; // Same row as Branch Code
        gbc.weightx = 0;
        formPanel.add(cityLabel, gbc);

        CustomTextField cityField = new CustomTextField(10);
        gbc.gridx = 3; // Next to the label
        gbc.weightx = 1;
        formPanel.add(cityField, gbc);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        gbc.gridx = 0; // New row
        gbc.gridy = 1; // Second row
        gbc.weightx = 0;
        formPanel.add(addressLabel, gbc);

        CustomTextField addressField = new CustomTextField(10);
        gbc.gridx = 1; // Next to the label
        gbc.gridwidth = 3; // Spans across three columns
        gbc.weightx = 1;
        formPanel.add(addressField, gbc);

        // Branch Status (combo box)
        JLabel statusLabel = new JLabel("Branch Status:");
        statusLabel.setFont(labelFont);
        gbc.gridx = 0; // Left-most column
        gbc.gridy = 2; // Third row
        gbc.gridwidth = 1; // Reset to one column
        formPanel.add(statusLabel, gbc);

        CustomComboBox statusComboBox = new CustomComboBox(new String[]{
                "Open", "Closed", "Temporarily Closed", "Frozen", "Terminated"
        });
        gbc.gridx = 1; // Next to the label
        gbc.weightx = 1;
        formPanel.add(statusComboBox, gbc);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFont);
        gbc.gridx = 2; // Start a new column on the right
        gbc.gridy = 2; // Same row as Branch Status
        gbc.weightx = 0;
        formPanel.add(phoneLabel, gbc);

        CustomTextField phoneField = new CustomTextField(10);
        gbc.gridx = 3; // Next to the label
        gbc.weightx = 1;
        formPanel.add(phoneField, gbc);

        // No. of Employees
        JLabel employeesLabel = new JLabel("No. of Employees:");
        employeesLabel.setFont(labelFont);
        gbc.gridx = 0; // Left-most column
        gbc.gridy = 3; // Fourth row
        formPanel.add(employeesLabel, gbc);

        CustomTextField employeesField = new CustomTextField(10);
        gbc.gridx = 1; // Next to the label
        gbc.gridwidth = 1; // Reset to one column
        formPanel.add(employeesField, gbc);

        // Apply Button
        JButton applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(180, 40));
        applyButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        applyButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        applyButton.setFont(new Font(Values.BUTTON_FONT, Font.PLAIN, Values.BUTTON_FONT_SIZE));

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(applyButton, gbc);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect data from the form
                String branchCode = branchCodeField.toString();
                String city = cityField.getText();
                String address = addressField.getText();
                String branchStatus = statusComboBox.getSelectedItem().toString();
                String phoneNumber = phoneField.getText();
                String noOfEmployees = employeesField.getText();

                JOptionPane.showMessageDialog(contentPanel,
                        "Data Saved",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Add additional logic to save data to a database or perform other actions
            }
        });

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
