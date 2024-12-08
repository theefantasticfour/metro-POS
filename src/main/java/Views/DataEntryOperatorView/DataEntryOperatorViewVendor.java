package Views.DataEntryOperatorView;

import Controllers.DataEntryOperatorController;
import Entites.Vendor;
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

public class DataEntryOperatorViewVendor extends JPanel {
    private final ActionListener dataEntryOperatorListener;
    private final DataEntryOperatorController dataEntryOperatorController;
    private JTable table;
    private DefaultTableModel tableModel;
    public JPanel mainPanel;
    private final String[] columnNames = {
            "Vendor ID", "Vendor Name", "Vendor Ph#", "Address", "Total Payment Amount",
            "Total Products", "Contract Start", "Contract End", "Update", "Delete"
    };

    public DataEntryOperatorViewVendor(ActionListener listener, DataEntryOperatorController instance ,DataEntryOperatorView panel) {
        this.dataEntryOperatorController = instance;
        this.dataEntryOperatorListener = listener;
        mainPanel = panel.getContentPanel();
        System.out.println("DataEntryOperatorViewVendor initialized");
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

        // Create search label
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
                // Not needed for plain text fields
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        return searchPanel;
    }
    private JTable createTable()
    {

        ArrayList<Vendor> vendors = this.dataEntryOperatorController.getVendorsTodisplay();
        Object[][] vendorTableData = new Object[vendors.size()][columnNames.length];

        for (int i = 0; i < vendors.size(); i++) {
            Vendor vendor = vendors.get(i);
            vendorTableData[i][0] = vendor.vendor_id;       // Vendor ID
            vendorTableData[i][1] = vendor.name;            // Vendor Name
            vendorTableData[i][2] = vendor.phone;           // Vendor Phone Number
            vendorTableData[i][3] = vendor.address;         // Vendor Address
            vendorTableData[i][4] = vendor.totalPayment; // Total Payment Amount (you need to implement this logic)
            vendorTableData[i][5] = vendor.Totalproduct; // Total Products (you need to implement this logic)
            vendorTableData[i][6] = vendor.StartDate;       // Contract Start Date
            vendorTableData[i][7] = vendor.EndDate;         // Contract End Date
            vendorTableData[i][8] = "Update";               // Update action
            vendorTableData[i][9] = "Delete";               // Delete action
        }


        // Create table model
        tableModel = new DefaultTableModel(vendorTableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2||column==8||column==9; // Make only Vendor Name and Phone editable
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
            dataEntryOperatorController.UpdateVendor(); // Call controller method
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
                dataEntryOperatorController.DeleteVendor(); // Call controller method
                display(); // Refresh the table
            }
        }));

        return table;
    }


    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null); // Show all rows if search text is empty
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }
    public String getVendorId() {
        int row = table.getSelectedRow();
        if (row == -1)
        {
            throw new IllegalStateException("No row is selected.");
        }
        Object value = table.getValueAt(row, 0);
        if (value == null) {
            throw new NullPointerException("The vendor ID value is null.");
        }
        return value.toString(); // Convert the value to a String
    }


    public String getVendorName() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 1);
    }

    public String getPhone() {
        int row = table.getSelectedRow();
        if (row == -1) {
            throw new IllegalStateException("No row is selected.");
        }
        Object value = table.getValueAt(row, 2);
        if (value == null) {
            throw new NullPointerException("The phone value is null.");
        }
        return value.toString(); // Convert the value to a String
    }



}
