package Views.BranchManagerView;

import Controllers.BranchManagerController;
import Controllers.SuperAdminController;
import Utils.Values;
import Views.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class BranchManagerReportsPanel extends JPanel{
    private ActionListener branchManagerListener;
    private final BranchManagerController branchManagerController;
    private JPanel mainPanel;
    private DateTimeFormatter formatter;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sliderText;

    public BranchManagerReportsPanel(ActionListener branchManagerListener, BranchManagerController instance) {
        this.branchManagerListener = branchManagerListener;
        this.branchManagerController = instance;
        System.out.println("Branch Manager Reports Panel initialized");

        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        sliderText = null;
        startDate = null;
        endDate = null;

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Create the upper and lower panels
        JPanel upperPanel = createUpperPanel();
        JPanel lowerPanel = createLowerPanel();

        // Add components to the main panel
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(lowerPanel, BorderLayout.CENTER);

        // Add a bottom separator
        JSeparator bottomSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        bottomSeparator.setForeground(Color.GRAY);
        bottomSeparator.setBackground(Color.GRAY);
        mainPanel.add(bottomSeparator, BorderLayout.SOUTH);
    }
    public void display(JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout()); // Explicitly set layout to BorderLayout
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel(new GridBagLayout());
        upperPanel.setBackground(Color.decode(Values.BG_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Report Slider
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JSlider reportSlider = new JSlider(0, 3, 0);
        reportSlider.setMajorTickSpacing(1);
        reportSlider.setPaintTicks(true);
        reportSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("Today"));
        labels.put(1, new JLabel("Weekly"));
        labels.put(2, new JLabel("Monthly"));
        labels.put(3, new JLabel("Yearly"));
        reportSlider.setLabelTable(labels);
        reportSlider.setPreferredSize(new Dimension(300, 50));
        upperPanel.add(reportSlider, gbc);

        // Start Range Label and Text Field
        gbc.gridx = 2;
        gbc.gridy = 0;
        upperPanel.add(new JLabel("Start Range:"), gbc);

        gbc.gridx = 3;
        JTextField startRangeField = new JTextField(10);
        upperPanel.add(startRangeField, gbc);

        // End Range Label and Text Field
        gbc.gridx = 4;
        upperPanel.add(new JLabel("End Range:"), gbc);

        gbc.gridx = 5;
        JTextField endRangeField = new JTextField(10);
        upperPanel.add(endRangeField, gbc);

        // Apply Button
        gbc.gridx = 6;
        CustomButton applyButton = new CustomButton("Apply");
        upperPanel.add(applyButton, gbc);

        applyButton.addActionListener(e -> {
            String startRange = startRangeField.getText().trim();
            String endRange = endRangeField.getText().trim();
            boolean isRangeEntered = !startRange.isEmpty() && !endRange.isEmpty();
            int sliderValue = reportSlider.getValue();

            if (isRangeEntered) {
                try {
                    startDate = LocalDate.parse(startRange, formatter);
                    endDate = LocalDate.parse(endRange, formatter);
                    if (startDate.isAfter(endDate)) {
                        JOptionPane.showMessageDialog(null, "Error: Start range cannot be after end range.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Start Range: " + startDate + "\nEnd Range: " + endDate + " saved!");
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Dates must be in the format DD/MM/YYYY.");
                }
            } else {
                sliderText = labels.get(sliderValue).getText();
                JOptionPane.showMessageDialog(null, "Report Range: " + sliderText + " saved!");
            }
        });

        // Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 7;
        JPanel buttonPanel = createButtonPanel();
        upperPanel.add(buttonPanel, gbc);

        return upperPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.decode(Values.BG_COLOR));

        JButton salesButton = new JButton("Sales Report");
        JButton stockButton = new JButton("Stock Report");
        JButton profitButton = new JButton("Profit Report");

        salesButton.addActionListener(branchManagerListener);
        salesButton.setActionCommand(Values.SALES_REPORT);

        stockButton.addActionListener(branchManagerListener);
        stockButton.setActionCommand(Values.STOCK_REPORT);

        profitButton.addActionListener(branchManagerListener);
        profitButton.setActionCommand(Values.PROFIT_REPORT);

        buttonPanel.add(salesButton);
        buttonPanel.add(stockButton);
        buttonPanel.add(profitButton);

        return buttonPanel;
    }

    private JPanel createLowerPanel() {
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBackground(Color.decode(Values.BG_COLOR));

        JLabel graphPlaceholder = new JLabel("Graph Placeholder", SwingConstants.CENTER);
        graphPlaceholder.setForeground(Color.decode(Values.TEXT_COLOR));
        graphPlaceholder.setFont(new Font("Arial", Font.BOLD, 16));
        lowerPanel.add(graphPlaceholder, BorderLayout.CENTER);

        return lowerPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getReportRangeType() {
        return sliderText;
    }

    public String getStartDate() {
        return startDate != null ? startDate.format(formatter) : null;
    }

    public String getEndDate() {
        return endDate != null ? endDate.format(formatter) : null;
    }
}