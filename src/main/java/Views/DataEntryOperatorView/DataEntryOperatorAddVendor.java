package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;
import Views.Components.CustomTextFieldDashboard;
import Utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataEntryOperatorAddVendor extends JPanel {
    private final ActionListener dataEntryOperatorListener;
    private final DataEntryOperatorController dataEntryOperatorController;

    // Fields to store input values
    private String vendorName;
    private String email;
    private String phone;
    private String startDate;
    private String endDate;

    public DataEntryOperatorAddVendor(ActionListener listener, DataEntryOperatorController instance) {
        this.dataEntryOperatorController = instance;
        this.dataEntryOperatorListener = listener;
        System.out.println("DataEntryOperatorAddVendor initialized");
    }

    public void display(JPanel parentPanel) {
        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Vendor",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Reduced padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Vendor Name
        JLabel vendorNameLabel = new JLabel("Vendor Name:");
        vendorNameLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 8, 4, 8);
        formPanel.add(vendorNameLabel, gbc);

        // Use the custom text field
        JTextField vendorNameField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(vendorNameField, gbc);

        // Email Field
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(emailLabel, gbc);

        JTextField emailField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailField, gbc);

        // Phone Field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);

        JTextField phoneField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(phoneField, gbc);

        // Start Date Field
        JLabel startDateLabel = new JLabel("Starting Date:");
        startDateLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(startDateLabel, gbc);

        JTextField startDateField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(startDateField, gbc);

        // End Date Field
        JLabel endDateLabel = new JLabel("Ending Date:");
        endDateLabel.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(endDateLabel, gbc);

        JTextField endDateField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(endDateField, gbc);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 35));
        addButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        addButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        addButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, 12));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 8, 8, 8);
        formPanel.add(addButton, gbc);

        addButton.setActionCommand(Values.ADD_NEW_VENDOR);
        addButton.addActionListener(dataEntryOperatorListener);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch values
                vendorName = vendorNameField.getText().trim();
                email = emailField.getText().trim();
                phone = phoneField.getText().trim();
                startDate = startDateField.getText().trim();
                endDate = endDateField.getText().trim();

                // Validate fields
                if (vendorName.isEmpty() || email.isEmpty() || phone.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields are required.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                e.getActionCommand().equals(Values.ADD_NEW_VENDOR);

                JOptionPane.showMessageDialog(parentPanel,
                        "Vendor information added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear form
                vendorNameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                startDateField.setText("");
                endDateField.setText("");
            }
        });

        GridBagConstraints parentGbc = new GridBagConstraints();
        parentGbc.gridx = 0;
        parentGbc.gridy = 0;
        parentGbc.weightx = 1;
        parentGbc.weighty = 1;
        parentGbc.fill = GridBagConstraints.HORIZONTAL;
        parentPanel.add(formPanel, parentGbc);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

    // Getters
    public String getVendorName() {
        return vendorName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
