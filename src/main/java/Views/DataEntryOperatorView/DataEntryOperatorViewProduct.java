package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;
import Entites.Product;
import Utils.Values;
import Views.Components.ButtonEditor;
import Views.Components.ButtonRenderer;
import Views.Components.CustomTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DataEntryOperatorViewProduct {
    private final ActionListener dataEntryOperatorListener;
    private final DataEntryOperatorController dataEntryOperatorController;
    private JTable table;
    private DefaultTableModel tableModel;
    public JPanel mainPanel;
    private final String[] columnNames = {
            "Product ID", "Product Name", "Category", "Original Price", "Sale Price per unit",
            "Sale Price Per Carton", "Quantity In Stock", "Vendor", "Update", "Delete"
    };

    public DataEntryOperatorViewProduct(ActionListener listener, DataEntryOperatorController instance ,DataEntryOperatorView panel) {
        this.dataEntryOperatorController = instance;
        this.dataEntryOperatorListener = listener;
        mainPanel = panel.getContentPanel();
        System.out.println("DataEntryOperatorViewProduct initialized");
    }

    public void display() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F8F9FA"));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        searchLabel.setPreferredSize(new Dimension(60, 30));

        CustomTextField searchField = new CustomTextField(20);

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

    private JTable createTable() {
        ArrayList<Product> productData = this.dataEntryOperatorController.getProductsTodisplay();

        // Prepare the data for the table
        Object[][] productTableData = new Object[productData.size()][columnNames.length];

        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            productTableData[i][0] = product.productId;          // Product ID
            productTableData[i][1] = product.name;               // Product Name
            productTableData[i][2] = product.categorie;          // Category
            productTableData[i][3] = product.costByUnit;         // Original Price
            productTableData[i][4] = product.sellingPrice;       // Sale Price per unit
            productTableData[i][5] = product.cartonPrice;        // Sale Price per Carton
            productTableData[i][6] = product.stockQty;           // Quantity In Stock
            productTableData[i][7] = product.vendorId;           // Vendor
            productTableData[i][8] = "Update";                   // Update action
            productTableData[i][9] = "Delete";                   // Delete action
        }

        tableModel = new DefaultTableModel(productTableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 1 && column < 8; // Make only data fields editable (excluding id , "Update" and "Delete")
            }
        };

        // Create table
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        table.getTableHeader().setBackground(Color.decode(Values.BUTTON_GCOLOR));
        table.getTableHeader().setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        table.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));

        // Add custom button renderers and editors
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Update").setCellEditor(new ButtonEditor("Update", row -> {
            System.out.println("Update clicked for row: " + row);
            dataEntryOperatorController.UpdateProduct(); // Call controller method
            display(); // Refresh the table
        }));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Delete").setCellEditor(new ButtonEditor("Delete", row -> {
            System.out.println("Delete clicked for row: " + row);
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this branch?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                dataEntryOperatorController.DeleteProduct(); // Call controller method
                display(); // Refresh the table
            }
        }));

        return table;
    }

    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    // Getter methods for product attributes

    public String getProductId() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 0);
    }

    public String getProductName() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 1);
    }

    public String getProductCategory() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 2);
    }

    public String getProductOriginalPrice() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 3);
    }

    public String getProductSalePricePerUnit() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 4);
    }

    public String getProductSalePricePerCarton() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 5);
    }

    public String getProductQuantityInStock() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 6);
    }

    public String getProductVendor() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 7);
    }

}
