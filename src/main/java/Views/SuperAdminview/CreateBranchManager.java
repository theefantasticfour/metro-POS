package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBranchManager {
    public void display(JPanel parentPanel) {

        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Branch Manager",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        gbc.insets = new Insets(25, 8, 4, 8);
        //logic to get branch manger id from database
        addLabelAndComponent(formPanel, "Branch Manager ID", labelFont, gbc, 0, 0);
        JTextField managerIdField = createDisabledTextField("0000");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8); // Padding
        formPanel.add(managerIdField, gbc);

        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Branch Code", labelFont, gbc, 1, 0);
        //logic to get  branch code from database
        CustomComboBox branchCodeComboBox = new CustomComboBox(new String[]{"BC001", "BC002", "BC003", "BC004"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(branchCodeComboBox, gbc);


        addLabelAndComponent(formPanel, "Manager's Name", labelFont, gbc, 0, 2);
        JTextField managerNameField = createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(managerNameField, gbc);


        addLabelAndComponent(formPanel, "Salary", labelFont, gbc, 1, 2);
        JTextField salaryField = createCustomTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(salaryField, gbc);


        addLabelAndComponent(formPanel, "Email", labelFont, gbc, 0, 4);
        JTextField emailField = createCustomTextField();
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(emailField, gbc);

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

                String managerId = managerIdField.getText().trim();
                String branchCode = (String) branchCodeComboBox.getSelectedItem();
                String managerName = managerNameField.getText().trim();
                String salary = salaryField.getText().trim();
                String email = emailField.getText().trim();

                if (managerName.isEmpty() || salary.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields except 'Branch Manager ID' must be filled out.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Logic to save to the database

                managerNameField.setText("");
                salaryField.setText("");
                emailField.setText("");
                branchCodeComboBox.setSelectedIndex(0);
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
