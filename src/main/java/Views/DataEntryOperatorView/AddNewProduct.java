package Views.DataEntryOperatorView;

import Views.Components.CustomComboBox;
import Utils.Values;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProduct {
    public void display(JPanel parentPanel) {

        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode(Values.BG_COLOR));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Add New Product",
                0, 0, new Font(Values.LABEL_FONT, Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Vendor Dropdown
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Vendor:", labelFont, gbc, 0, 0);
        CustomComboBox vendorComboBox = new CustomComboBox(new String[]{"Vendor 1", "Vendor 2", "Vendor 3"});
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(vendorComboBox, gbc);

        // Product Name Field
        addLabelAndComponent(formPanel, "Product Name:", labelFont, gbc, 0, 2);
        JTextField productNameField = createCustomTextField();
        gbc.gridy = 3;
        formPanel.add(productNameField, gbc);

        // Product Category
        addLabelAndComponent(formPanel, "Product Category:", labelFont, gbc, 0, 4);
        JTextField categoryField = createCustomTextField();
        gbc.gridy = 5;
        formPanel.add(categoryField, gbc);

        // Product Quantity
        addLabelAndComponent(formPanel, "Product Quantity:", labelFont, gbc, 0, 6);
        JTextField quantityField = createCustomTextField();
        gbc.gridy = 7;
        formPanel.add(quantityField, gbc);

        // Original Price
        gbc.insets = new Insets(25, 8, 4, 8);
        addLabelAndComponent(formPanel, "Original Price:", labelFont, gbc, 1, 0);
        JTextField originalPriceField = createCustomTextField();
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
        formPanel.add(originalPriceField, gbc);

        // Sale Price
        addLabelAndComponent(formPanel, "Sale Price:", labelFont, gbc, 1, 2);
        JTextField salePriceField = createCustomTextField();
        gbc.gridy = 3;
        formPanel.add(salePriceField, gbc);

        // Price By Unit
        addLabelAndComponent(formPanel, "Price By Unit:", labelFont, gbc, 1, 4);
        JTextField priceByUnitField = createCustomTextField();
        gbc.gridy = 5;
        formPanel.add(priceByUnitField, gbc);

        // Price By Carton
        addLabelAndComponent(formPanel, "Price By Carton:", labelFont, gbc, 1, 6);
        JTextField priceByCartonField = createCustomTextField();
        gbc.gridy = 7;
        formPanel.add(priceByCartonField, gbc);

        // Save Button
        JButton saveButton = createSaveButton();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = new Insets(25, 8, 4, 8);
        formPanel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collecting data
                String vendor = (String) vendorComboBox.getSelectedItem();
                String productName = productNameField.getText().trim();
                String category = categoryField.getText().trim();
                String quantity = quantityField.getText().trim();
                String originalPrice = originalPriceField.getText().trim();
                String salePrice = salePriceField.getText().trim();
                String priceByUnit = priceByUnitField.getText().trim();
                String priceByCarton = priceByCartonField.getText().trim();

                if (productName.isEmpty() || category.isEmpty() || quantity.isEmpty() || originalPrice.isEmpty()
                        || salePrice.isEmpty() || priceByUnit.isEmpty() || priceByCarton.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields are required.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Logic for saving product information (database or file handling)
                JOptionPane.showMessageDialog(parentPanel,
                        "Product information added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear the form
                productNameField.setText("");
                categoryField.setText("");
                quantityField.setText("");
                originalPriceField.setText("");
                salePriceField.setText("");
                priceByUnitField.setText("");
                priceByCartonField.setText("");
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

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 35));
        saveButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        saveButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        saveButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, 12));
        return saveButton;
    }
}
