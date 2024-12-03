package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Entites.Branch;
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

public class SuperAdminViewUpdateDeleteBranchespanel extends JPanel {
    private ActionListener superAdminListener; // Listener for buttons
    private JTable table;
    private DefaultTableModel tableModel;

    // Column names for the table
    private final String[] columnNames = {
            "Branch Code", "Branch City", "Branch Address", "Branch Ph#", "Branch Status",
            "No. of Empl.", "Branch Manager", "Manager's Salary", "Update", "Delete"
    };

    public SuperAdminViewUpdateDeleteBranchespanel(ActionListener superAdminListener) {
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminViewUpdateDeleteBranchespanel initialized");
        inIt();
    }

    public void inIt() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR)); // Use constant for background color

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Table Panel
        JScrollPane tableScrollPane = new JScrollPane(createTable());
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Use constant for panel background

        // Create search label
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        searchLabel.setPreferredSize(new Dimension(60, 30));

        // Create search field
        CustomTextField searchField = new CustomTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
        searchField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));

        // Add search filter functionality
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
        // Fetch branch data from the controller
        ArrayList<Branch> branches = SuperAdminController.getBranches();
        Object[][] branchData = new Object[branches.size()][columnNames.length];

        // Populate data for the table
        for (int i = 0; i < branches.size(); i++) {
            Branch branch = branches.get(i);
            branchData[i][0] = branch.getBranchId();
            branchData[i][1] = branch.getCity();
            branchData[i][2] = branch.getAddress();
            branchData[i][3] = branch.getPhoneNo();
            branchData[i][4] = branch.getStatus();
            branchData[i][5] = branch.getNoOfEmployees();
            branchData[i][6] = branch.getManagerName();
            branchData[i][7] = branch.getManagerSalary();
            branchData[i][8] = "Update";
            branchData[i][9] = "Delete";
        }

        // Create table model
        tableModel = new DefaultTableModel(branchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 8; // Only data columns are editable
            }
        };

        // Create table
        table = new JTable(tableModel);

        // Add custom button renderers and editors
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR), e -> {
            int row = table.getSelectedRow();
            // Handle update action here for the selected row
            System.out.println("Update clicked for row: " + row);
        }));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR), e -> {
            int row = table.getSelectedRow();
            // Handle delete action here for the selected row
            System.out.println("Delete clicked for row: " + row);
        }));
// Example usage in table setup:
        table.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), row -> {
            // Handle update action here for the selected row
            System.out.println("Update clicked for row: " + row);
        }));

        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), row -> {
            // Handle delete action here for the selected row
            System.out.println("Delete clicked for row: " + row);
        }));

        return table;
    }

    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null); // Show all rows if search text is empty
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
        }
    }
}
