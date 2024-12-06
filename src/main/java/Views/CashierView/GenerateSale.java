package Views.CashierView;

import Utils.Values;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GenerateSale extends JPanel {
    private JTable productTable;
    private JTable cartTable;
    private JLabel totalLabel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel cartTableModel;
    private Map<String, Integer> cartItems; // Stores product ID and selected quantity

    public GenerateSale() {
        cartItems = new HashMap<>();
    }

    public void display(JPanel parentPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR));

        // Ensure productTable is initialized before use
        productTable = createProductTable();  // Initialize the product table here
        JScrollPane productScrollPane = new JScrollPane(productTable);

        // Create cart panel
        JPanel cartPanel = createCartPanel();

        // Add components to this panel
        add(productScrollPane, BorderLayout.CENTER);
        add(cartPanel, BorderLayout.EAST);

        // Add this panel to the parent panel
        parentPanel.add(this);
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

        JTable table = new JTable(productTableModel);
        table.setRowHeight(30);

        // Initialize the table header here instead of later
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        table.getTableHeader().setBackground(Color.decode(Values.BUTTON_GCOLOR));
        table.getTableHeader().setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        table.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));

        // Add an "Add to Cart" button to update the cart
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected items with quantity to the cart
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
        });

        // Add the "Add to Cart" button to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        table.getTableHeader().setReorderingAllowed(true);

        // Add the button panel to the product panel
        add(buttonPanel, BorderLayout.SOUTH);

        return table;
    }

    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setPreferredSize(new Dimension(300, getHeight()));
        cartPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        // Cart Header
        JLabel cartHeader = new JLabel("Cart", JLabel.CENTER);
        cartHeader.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SIZE));
        cartHeader.setForeground(Color.decode(Values.LABEL_COLOR));
        cartPanel.add(cartHeader, BorderLayout.NORTH);

        // Table for cart items
        String[] cartColumns = {"Product", "Quantity", "Price"};
        cartTableModel = new DefaultTableModel(cartColumns, 0);
        cartTable = new JTable(cartTableModel);
        cartTable.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));
        cartTable.setRowHeight(25);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        // Footer for total and generate bill button
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

    private void updateCart(String productId, String productName, int quantity, double price) {
        if (quantity > 0) {
            cartItems.put(productId, quantity);
            // Check if product already exists in the cart
            boolean exists = false;
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                if (cartTableModel.getValueAt(i, 0).equals(productName)) {
                    cartTableModel.setValueAt(quantity, i, 1);
                    cartTableModel.setValueAt(price * quantity, i, 2);
                    exists = true;
                    break;
                }
            }
            // If not, add a new row
            if (!exists) {
                cartTableModel.addRow(new Object[]{productName, quantity, price * quantity});
            }
        } else {
            // Remove product from cart if quantity is 0
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
        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Bill Generated! Total: " + totalLabel.getText(), Values.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
        // Clear the cart
        cartTableModel.setRowCount(0);
        cartItems.clear();
        totalLabel.setText("Total: 0 Rs");
    }
}
