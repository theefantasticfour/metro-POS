package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;
import Views.Components.CustomComboBox;
import Utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class DataEntryOperatorAddProduct extends JPanel {
    private final ActionListener dataEntryOperatorListener;
    private final DataEntryOperatorController dataEntryOperatorController;

    // Fields to store input values
    private String vendor;
    private String productName;
    private String category;
    private String quantity;
    private String originalPrice;
    private String salePrice;
    private String priceByUnit;
    private String priceByCarton;

    public DataEntryOperatorAddProduct(ActionListener listener, DataEntryOperatorController instance) {
        this.dataEntryOperatorController = instance;
        this.dataEntryOperatorListener = listener;
        System.out.println("DataEntryOperatorAddProduct initialized");
    }

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
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font(Values.LABEL_FONT, Font.PLAIN, 14);

        // Vendor Dropdown
        addLabelAndComponent(formPanel, "Vendor:", labelFont, gbc, 0, 0);
        // Fetch Vendor IDs and names using the controller
        ArrayList<Integer> vendorIds = dataEntryOperatorController.getVendorIds();
        CustomComboBox vendorComboBox = new CustomComboBox(vendorIds.stream()
                .map(id -> "Vendor " + id)
                .toArray(String[]::new));
        gbc.gridy = 1;
        formPanel.add(vendorComboBox, gbc);

        // Product Name Field
        addLabelAndComponent(formPanel, "Product Name:", labelFont, gbc, 0, 2);
        // Fetch Product Names using the controller
        Map<Integer, String> productNames = dataEntryOperatorController.getProductNames();
        JComboBox<String> productNameComboBox = new JComboBox<>(productNames.values().toArray(new String[0]));
        productNameComboBox.setEditable(true);
        gbc.gridy = 3;
        formPanel.add(productNameComboBox, gbc);

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
        addLabelAndComponent(formPanel, "Original Price:", labelFont, gbc, 1, 0);
        JTextField originalPriceField = createCustomTextField();
        gbc.gridy = 1;
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

        // Add Action Listener to Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch values
                vendor = (String) vendorComboBox.getSelectedItem();
                productName = productNameComboBox.getSelectedItem().toString();
                category = categoryField.getText().trim();
                quantity = quantityField.getText().trim();
                originalPrice = originalPriceField.getText().trim();
                salePrice = salePriceField.getText().trim();
                priceByUnit = priceByUnitField.getText().trim();
                priceByCarton = priceByCartonField.getText().trim();

                // Validate fields
                if (productName.isEmpty() || category.isEmpty() || quantity.isEmpty() || originalPrice.isEmpty()
                        || salePrice.isEmpty() || priceByUnit.isEmpty() || priceByCarton.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel,
                            "All fields are required.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                e.getActionCommand().equals(Values.ADD_NEW_PRODUCT);
                JOptionPane.showMessageDialog(parentPanel,
                        "Product information added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear form fields
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

    // Getters
    public String getVendor() {
        return vendor;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getPriceByUnit() {
        return priceByUnit;
    }

    public String getPriceByCarton() {
        return priceByCarton;
    }
}
