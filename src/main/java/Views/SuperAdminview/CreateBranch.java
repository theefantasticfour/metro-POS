package Views.SuperAdminview;

import Utils.Values;
import javax.swing.*;
import java.awt.*;

// not completed yet

public class CreateBranch {
    public void display(SuperAdminPanel superAdminPanel) {
        // Get the content panel
        JPanel contentPanel = superAdminPanel.getContentPanel();

        // Clear any existing components in the content panel
        contentPanel.removeAll();

        // Set layout for the content panel to center the form
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE); // Set background color to white

        // Create the main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE); // Set background color to white
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch",
                0, 0, new Font("Arial", Font.BOLD, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left within the form

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        // Branch Code (disabled text field)
        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(branchCodeLabel, gbc);

        JTextField branchCodeField = new JTextField("0000");
        branchCodeField.setFont(fieldFont);
        branchCodeField.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // Allow text field to resize horizontally
        formPanel.add(branchCodeField, gbc);

        // City (text field)
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(cityLabel, gbc);

        JTextField cityField = new JTextField();
        cityField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        formPanel.add(cityField, gbc);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(addressLabel, gbc);

        JTextField addressField = new JTextField();
        addressField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        formPanel.add(addressField, gbc);

        // Branch Status (combo box)
        JLabel statusLabel = new JLabel("Branch Status:");
        statusLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(statusLabel, gbc);

        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{
                "Open", "Closed", "Temporarily Closed", "Frozen", "Terminated"
        });
        statusComboBox.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(statusComboBox, gbc);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField();
        phoneField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(phoneField, gbc);

        // No. of Employees
        JLabel employeesLabel = new JLabel("No. of Employees:");
        employeesLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(employeesLabel, gbc);

        JTextField employeesField = new JTextField();
        employeesField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(employeesField, gbc);

        // Apply Button
        JButton applyButton = new JButton("Apply");
        applyButton.setFont(new Font("Arial", Font.BOLD, 18));
        applyButton.setBackground(Color.decode(Values.TEXT_COLOR)); // Use the color from Values
        applyButton.setForeground(Color.decode(Values.TEXT_COLOR)); // Set text color to white
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(applyButton, gbc);

        // Add form panel to content panel (centered)
        GridBagConstraints contentGbc = new GridBagConstraints();
        contentGbc.gridx = 0;
        contentGbc.gridy = 0;
        contentGbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(formPanel, contentGbc);

        // Revalidate and repaint to apply changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
