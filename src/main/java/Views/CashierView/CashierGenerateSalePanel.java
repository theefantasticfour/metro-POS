package Views.CashierView;

import Controllers.BranchManagerController;
import Controllers.CashierController;
import Utils.Values;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import static Utils.Values.CLEAR_CART_ICON;

public class CashierGenerateSalePanel extends JPanel {
    private JTable productTable;
    private JTable cartTable;
    private JLabel totalLabel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel cartTableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private static Map<String, Integer> cartItems;
    private final ActionListener actionListener;
    private final CashierController cashierController;
    private double total=0;

    public CashierGenerateSalePanel(ActionListener Listener, CashierController instance) {
        this.cashierController = instance;
        this.actionListener = Listener;
        this.cartItems = new HashMap<>();
        init();

    }
    public void display(JPanel parentPanel)
    {
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(this, BorderLayout.CENTER);
    }
    public void init() {
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
       // addToCartButton.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR)));
        addToCartButton.addActionListener(e -> addSelectedProductsToCart());
        productPanel.add(addToCartButton, BorderLayout.SOUTH);

        add(productPanel, BorderLayout.CENTER);

        // Cart panel
        JPanel cartPanelContainer = new JPanel(new BorderLayout());
        JScrollPane cartScrollPane = new JScrollPane(createCartPanel());
        cartPanelContainer.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));
        cartPanelContainer.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20)); // Add spacing between panels
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
        // Define columns for the product table
        String[] columns = {"Product ID", "Product Name", "Category", "Price per Unit", "Stock", "Quantity"};

        // Fetch products from the controller and populate the data array
        var products = cashierController.getBranchProductsToDisplay();
        Object[][] data = new Object[products.size()][columns.length];

        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);
            data[i][0] = product.getProductId();
            data[i][1] = product.getName();
            data[i][2] = product.getCategory();
            data[i][3] = product.getSalePricePerUnit();
            data[i][4] = product.getStockQuantity();
            data[i][5] = ""; // Quantity to buy, left empty for user input
        }

        // Create a table model with custom cell editing permissions
        productTableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only the "Quantity" column is editable
            }
        };

        // Create the JTable
        productTable = new JTable(productTableModel);
        productTable.setRowHeight(30);
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.getTableHeader().setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        productTable.getTableHeader().setBackground(Color.decode(Values.BUTTON_GCOLOR));
        productTable.getTableHeader().setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        productTable.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));

        // Attach a row sorter to the table for filtering
        rowSorter = new TableRowSorter<>(productTableModel);
        productTable.setRowSorter(rowSorter);

        // Add a listener to validate user input in the "Quantity" column
        productTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 5) { // "Quantity" column
                String input = (String) productTableModel.getValueAt(row, column);
                var product = products.get(row);

                // Skip processing if input is blank
                if (input == null || input.trim().isEmpty()) {
                    return;
                }

                try {
                    int quantity = Integer.parseInt(input);

                    // Validate quantity range
                    if (quantity <= 0 || quantity > product.getStockQuantity()) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Invalid quantity! Please enter a value between 1 and " + product.getStockQuantity(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        productTableModel.setValueAt("", row, column); // Reset invalid input
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid input! Please enter a numeric value.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    productTableModel.setValueAt("", row, column); // Reset invalid input
                }
            }
        });


        return productTable;
    }


    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        // Cart Header with "Cart" text and "Clear Cart" button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        JLabel cartHeader = new JLabel("Cart", JLabel.LEFT);
        cartHeader.setFont(new Font(Values.LABEL_FONT, Font.BOLD, 25));
        cartHeader.setForeground(Color.decode(Values.LABEL_COLOR));
        headerPanel.add(cartHeader, BorderLayout.CENTER);

        // Clear Cart button
        ImageIcon logoIcon = new ImageIcon(Values.CLEAR_CART_ICON);
        Image scaledLogo = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton clearCartButton = new JButton(new ImageIcon(scaledLogo));
        clearCartButton.setBorder(BorderFactory.createEmptyBorder());
        clearCartButton.setContentAreaFilled(false);
        clearCartButton.addActionListener(e -> clearCart());
        headerPanel.add(clearCartButton, BorderLayout.EAST);

        cartPanel.add(headerPanel, BorderLayout.NORTH);

        // Cart Table
        String[] cartColumns = {"Product", "Quantity", "Price"};
        cartTableModel = new DefaultTableModel(cartColumns, 0);
        cartTable = new JTable(cartTableModel);
        cartTable.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));
        cartTable.setRowHeight(25);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        // Footer Panel with Total Label and Buttons
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        totalLabel = new JLabel("Total: 0 Rs", JLabel.RIGHT);
        totalLabel.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        totalLabel.setForeground(Color.decode(Values.LABEL_COLOR));
        footerPanel.add(totalLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonsPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        JButton removeFromCartButton = new JButton("Remove from Cart");
        removeFromCartButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        removeFromCartButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        removeFromCartButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        //removeFromCartButton.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR)));
        removeFromCartButton.addActionListener(e -> removeSelectedFromCart());
        buttonsPanel.add(removeFromCartButton);

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBackground(Color.decode(Values.BUTTON_COLOR));
        generateBillButton.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        generateBillButton.setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        //generateBillButton.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR)));
        generateBillButton.addActionListener(e -> {

            try {
                generateBill();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonsPanel.add(generateBillButton);

        footerPanel.add(buttonsPanel, BorderLayout.SOUTH);
        cartPanel.add(footerPanel, BorderLayout.SOUTH);

        return cartPanel;
    }

    private void clearCart() {
        cartTableModel.setRowCount(0);
        cartItems.clear();
        totalLabel.setText("Total: 0 Rs");
    }

    private void removeSelectedFromCart() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow >= 0) {
            String productName = cartTableModel.getValueAt(selectedRow, 0).toString();
            cartTableModel.removeRow(selectedRow);
            cartItems.entrySet().removeIf(entry -> entry.getKey().equals(productName));
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "No item selected!", Values.APP_NAME, JOptionPane.WARNING_MESSAGE);
        }
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
        //double total = 0;
        for (int i = 0; i < cartTableModel.getRowCount(); i++) {
            total += Double.parseDouble(cartTableModel.getValueAt(i, 2).toString());
        }
        totalLabel.setText("Total: " + total + " Rs");
    }

    private void generateBill() throws SQLException {

        JOptionPane.showMessageDialog(this, "Bill Generated! Total: " + totalLabel.getText(), Values.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
        cashierController.RecordTransactions(cartItems);
        cashierController.updateInventry(cartItems);
        clearCart();
    }
    public double getTotal()
    {
        return total;
    }

    public  static Map<String, Integer> getCartDetails() {
        return cartItems;
    }
}
