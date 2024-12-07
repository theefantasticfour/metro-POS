package Views.BranchManagerView;

import Controllers.BranchManagerController;
import Entites.Branch;
import Entites.Employee;
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

public class BranchManagerAddUpdateEmployeepanel extends JPanel {
    private final ActionListener branchManagerListener;
    private final BranchManagerController branchManagerController;
    private JTable table;
    private DefaultTableModel tableModel;
    public JPanel parentPanel;
    JPanel contentPanel;
    private final String[] columnNames = {"Employee Type", "Employee Name", "Email Address", "Salary", "Status", "Update", "Delete"};

    public BranchManagerAddUpdateEmployeepanel(ActionListener branchManagerListener, BranchManagerController instance,BranchManagerView branchManagerPanel) {
        this.branchManagerListener = branchManagerListener;
        this.branchManagerController = instance;
        contentPanel = branchManagerPanel.getContentPanel();
        System.out.println("Branch Manager Add Update Employee Panel initialized");
    }
    public void inIt() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#F8F9FA"));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
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
        // Fetch employee data from the controller
        ArrayList<Employee> employees = this.branchManagerController.getAllEmployees();
        Object[][] employeeData = new Object[employees.size()][columnNames.length];

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeeData[i][0] = employee.getEmployee_id();  // Employee ID
            employeeData[i][1] = employee.getName();         // Employee Name
            employeeData[i][2] = employee.getEmail();        // Employee Email
            employeeData[i][3] = employee.getRole();         // Employee Role
            employeeData[i][4] = employee.getSalary();       // Employee Salary
            employeeData[i][5] = employee.isIs_password_changed() ? "Yes" : "No";  // Password changed status
            employeeData[i][6] = "Update";                   // Update action
            employeeData[i][7] = "Delete";                   // Delete action
        }


        // Create table model
        tableModel = new DefaultTableModel(employeeData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 0; // Make only "Update" and "Delete" columns editable
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
            branchManagerController.UpdateEmployee(); // Call controller method
            inIt(); // Refresh the table
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
                branchManagerController.deleteEmployee(); // Call controller method
                inIt(); // Refresh the table
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

    // Getter methods for employee attributes

    public String getEmployeeType() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 0);
    }

    public String getEmployeeName() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 1);
    }

    public String getEmployeeEmail() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 2);
    }

    public String getEmployeeSalary() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 3);
    }

    public String getEmployeeStatus() {
        int row = table.getSelectedRow();
        return (String) table.getValueAt(row, 4);
    }

}

