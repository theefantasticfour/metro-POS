package Views.CashierView;

import Utils.Values;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class GenerateSale extends JPanel {
    private JTable productTable;
    private JTable cartTable;
    private JLabel totalLabel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel cartTableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private Map<String, Integer> cartItems;

    public GenerateSale() {
        cartItems = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        // Search and product panel
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        // Add search panel above product table
        productPanel.add(createSearchPanel(), BorderLayout.NORTH);

        JScrollPane productScrollPane = new JScrollPane(createProductTable());
        productPanel.add(productScrollPane, BorderLayout.CENTER);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        addToCartButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        addToCartButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        addToCartButton.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR)));
        addToCartButton.addActionListener(e -> addSelectedProductsToCart());
        productPanel.add(addToCartButton, BorderLayout.SOUTH);

        add(productPanel, BorderLayout.CENTER);

        // Cart panel
        JPanel cartPanelContainer = new JPanel(new BorderLayout());
        JScrollPane cartScrollPane = new JScrollPane(createCartPanel());
        cartPanelContainer.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));
        cartPanelContainer.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0)); // Add spacing between panels
        cartPanelContainer.add(cartScrollPane, BorderLayout.CENTER);

        add(cartPanelContainer, BorderLayout.EAST);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));

        JTextField searchField = new JTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
        searchField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));
        searchField.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        return searchPanel;
    }

    private void filterTable(String searchText) {
        if (rowSorter == null) return;

        if (searchText.trim().isEmpty()) {
            rowSorter.setRowFilter(null); // Show all rows
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
        }
    }

    private JTable createProductTable() {
        String[] columns = {"Product ID", "Product Name", "Category", "Price per Unit", "Stock", "Quantity"};
        Object[][] data = {
                {"101", "Product A", "Category 1", "500", "10", "0"},
                {"102", "Product B", "Category 2", "700", "15", "0"},
                {"103", "Product C", "Category 1", "300", "20", "0"}
        };

        productTableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Allow editing only in the "Quantity" column
            }
        };

        productTable = new JTable(productTableModel);
        productTable.setRowHeight(30);
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.getTableHeader().setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        productTable.getTableHeader().setBackground(Color.decode(Values.BUTTON_GCOLOR));
        productTable.getTableHeader().setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        productTable.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));

        // Attach row sorter
        rowSorter = new TableRowSorter<>(productTableModel);
        productTable.setRowSorter(rowSorter);

        return productTable;
    }

    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        JLabel cartHeader = new JLabel("Cart", JLabel.CENTER);
        cartHeader.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SIZE));
        cartHeader.setForeground(Color.decode(Values.LABEL_COLOR));
        cartPanel.add(cartHeader, BorderLayout.NORTH);

        String[] cartColumns = {"Product", "Quantity", "Price"};
        cartTableModel = new DefaultTableModel(cartColumns, 0);
        cartTable = new JTable(cartTableModel);
        cartTable.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));
        cartTable.setRowHeight(25);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        totalLabel = new JLabel("Total: 0 Rs", JLabel.RIGHT);
        totalLabel.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        totalLabel.setForeground(Color.decode(Values.LABEL_COLOR));

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        generateBillButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        generateBillButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        generateBillButton.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR)));
        generateBillButton.addActionListener(e -> generateBill());

        footerPanel.add(totalLabel, BorderLayout.CENTER);
        footerPanel.add(generateBillButton, BorderLayout.SOUTH);

        cartPanel.add(footerPanel, BorderLayout.SOUTH);

        return cartPanel;
    }

    private void addSelectedProductsToCart() {
        for (int row = 0; row < productTable.getRowCount(); row++) {
            int quantity = Integer.parseInt(productTableModel.getValueAt(row, 5).toString());
            if (quantity > 0) {
                String productId = productTableModel.getValueAt(row, 0).toString();
                String productName = productTableModel.getValueAt(row, 1).toString();
                double price = Double.parseDouble(productTableModel.getValueAt(row, 3).toString());
                updateCart(productId, productName, quantity, price);
            }
        }
    }

    private void updateCart(String productId, String productName, int quantity, double price) {
        if (quantity > 0) {
            cartItems.put(productId, quantity);
            boolean exists = false;
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                if (cartTableModel.getValueAt(i, 0).equals(productName)) {
                    cartTableModel.setValueAt(quantity, i, 1);
                    cartTableModel.setValueAt(price * quantity, i, 2);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                cartTableModel.addRow(new Object[]{productName, quantity, price * quantity});
            }
        } else {
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                if (cartTableModel.getValueAt(i, 0).equals(productName)) {
                    cartTableModel.removeRow(i);
                    break;
                }
            }
            cartItems.remove(productId);
        }
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < cartTableModel.getRowCount(); i++) {
            total += Double.parseDouble(cartTableModel.getValueAt(i, 2).toString());
        }
        totalLabel.setText("Total: " + total + " Rs");
    }

    private void generateBill() {
        //JOptionPane.showMessageDialog(this, "Bill Generated! Total: " + totalLabel.getText(), Values.APP_NAME, JOptionPane.INFORMATION
                JOptionPane.showMessageDialog(this, "Bill Generated! Total: " + totalLabel.getText(), Values.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
        cartTableModel.setRowCount(0); // Clear cart
        cartItems.clear(); // Clear cartItems map
        totalLabel.setText("Total: 0 Rs"); // Reset the total label
    }

    public void display(JPanel parentPanel) {
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(this, BorderLayout.CENTER);
    }
}
