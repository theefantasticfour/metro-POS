package Views.Components;

import Utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomChangePassword {
    public void display(JPanel parentPanel) {

        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Change Password",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Name Label and Text Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 8, 4, 8);
        formPanel.add(nameLabel, gbc);

        JTextField nameField = CustomTextFieldDashboard.createCustomTextField();
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(nameField, gbc);

        // New Password Label and Text Field (using CustomPasswordField)
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(newPasswordLabel, gbc);

        CustomPasswordField newPasswordField = new CustomPasswordField(20);
        gbc.gridy = 3;
        formPanel.add(newPasswordField, gbc);

        // Confirm Password Label and Text Field (using CustomPasswordField)
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(confirmPasswordLabel, gbc);

        CustomPasswordField confirmPasswordField = new CustomPasswordField(20);
        gbc.gridy = 5;
        formPanel.add(confirmPasswordField, gbc);

        // Save Button (using CustomButton)
        CustomButton saveButton = new CustomButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 8, 8, 8);
        formPanel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String newPassword = newPasswordField.getPasswordString().trim();
                String confirmPassword = confirmPasswordField.getPasswordString().trim();

                if (name.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields are required.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "New Password and Confirm Password do not match.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(parentPanel,
                        "Password changed successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);


                nameField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");
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
}