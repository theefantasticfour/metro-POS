package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBranch {
    public void display(JPanel parentPanel) {

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
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Branch Code", labelFont, gbc, 0, 0);
        JTextField branchCodeField = createDisabledTextField("0000");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8); // Padding
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
        gbc.gridwidth = 1;

        // Phone Number
        addLabelAndComponent(formPanel, "Phone Number", labelFont, gbc, 0, 4);
        JTextField phoneNumberField = createCustomTextField();
        phoneNumberField.setText("");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(phoneNumberField, gbc);

        // Number of Employees
        addLabelAndComponent(formPanel, "No. of Employees", labelFont, gbc, 1, 4);
        JTextField employeesField = createCustomTextField();
        employeesField.setText("");
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(employeesField, gbc);

        // Active Status
        addLabelAndComponent(formPanel, "Active Status", labelFont, gbc, 1, 2);
        CustomComboBox activeStatusComboBox = new CustomComboBox(new String[]{"Open", "Close", "Temporary Closed"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(activeStatusComboBox, gbc);

        // Apply Button
        JButton applyButton = createApplyButton();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 8, 4, 8);
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(applyButton, gbc);


        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String branchCode = branchCodeField.getText().trim();
                String city = cityField.getText().trim();
                String address = addressField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();
                String numOfEmployees = employeesField.getText().trim();
                String activeStatus = (String) activeStatusComboBox.getSelectedItem();


                if (city.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || numOfEmployees.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields except 'Branch Code' must be filled out.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //logic to save to database

                cityField.setText("");
                addressField.setText("");
                phoneNumberField.setText("");
                employeesField.setText("");
                activeStatusComboBox.setSelectedIndex(0);
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

    private void addLabelAndComponent(JPanel panel, String labelText, Font labelFont, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);
    }

    private JTextField createCustomTextField() {
        return Views.Components.CustomTextFieldDashboard.createCustomTextField();
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
}
