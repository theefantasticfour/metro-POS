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
//import java.awt.event.ActionEvent;

public class SuperAdminViewUpdateDeleteBranchespanel extends JPanel {

    private final ActionListener superAdminListener;
    private JTable table;
    private DefaultTableModel tableModel;

    private final String[] columnNames = {
            "Branch Code", "Branch City", "Branch Address", "Branch Ph#", "Branch Status",
            "No. of Empl.", "Branch Manager", "Manager's Salary", "Update", "Delete"
    };

    public SuperAdminViewUpdateDeleteBranchespanel(ActionListener superAdminListener) {
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminViewUpdateDeleteBranchespanel initialized");
        init(); // Initialize the panel
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.BG_COLOR)); // Use constant for background color

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Table
        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Use constant for search panel color

        // Create search label
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE)); // Use font constants
        searchLabel.setPreferredSize(new Dimension(60, 30));

        CustomTextField searchField = new CustomTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR)); // Use constants for text field
        searchField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));

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
        ArrayList<Branch> branches = SuperAdminController.getBranches();
        Object[][] branchData = new Object[branches.size()][10];
        int row = 0;

        // Convert branch data into table data format
        for (Branch branch : branches) {
            branchData[row][0] = branch.getBranchId();
            branchData[row][1] = branch.getCity();
            branchData[row][2] = branch.getAddress();
            branchData[row][3] = branch.getPhoneNo();
            branchData[row][4] = branch.getStatus();
            branchData[row][5] = branch.getNoOfEmployees();
            branchData[row][6] = branch.getBranchManager();
            branchData[row][7] = branch.getManagerSalary();
            branchData[row][8] = "Update";
            branchData[row][9] = "Delete";
            row++;
        }

        tableModel = new DefaultTableModel(branchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 8; // Allow editing only for data columns, not actions
            }
        };

        // Create table with model
        JTable table = new JTable(tableModel);

        // Add custom button renderers and editors for "Update" and "Delete"
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR)));

        table.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int branchId = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0)); // Assuming branch ID is in the first column
                JButton source = (JButton) e.getSource();
                source.setActionCommand(Values.UPDATE_BRANCH);
                source.addActionListener(superAdminListener);
                source.doClick(); // Simulate the button click
            }
        }));

        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int branchId = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0)); // Assuming branch ID is in the first column
                JButton source = (JButton) e.getSource();
                source.setActionCommand(Values.DELETE_BRANCH);
                source.addActionListener(superAdminListener);
                source.doClick(); // Simulate the button click
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
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
        }
    }
}