package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Reports {
    private JPanel mainPanel;

    public Reports() {
        // Set up the main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Create the upper and lower panels
        JPanel upperPanel = createUpperPanel();
        JPanel lowerPanel = createLowerPanel();

        // Add components to the main panel
        mainPanel.add(upperPanel, BorderLayout.NORTH); // Ensure it appears at the top
        mainPanel.add(lowerPanel, BorderLayout.CENTER); // Takes remaining space

        // Add a bottom separator (line) at the bottom
        JSeparator bottomSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        bottomSeparator.setForeground(Color.decode(Values.BUTTON_COLOR)); // Customize the color of the line
        bottomSeparator.setBackground(Color.decode(Values.BUTTON_COLOR));
        mainPanel.add(bottomSeparator, BorderLayout.SOUTH); // Add the line at the bottom
    }

    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridBagLayout());
        upperPanel.setBackground(Color.decode(Values.BG_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Branch Code Label and Text Field
        upperPanel.add(new JLabel("Branch Code:"), gbc);
        gbc.gridx = 1;
        JTextField branchCodeField = new JTextField(15);
        upperPanel.add(branchCodeField, gbc);

        // Report Slider
        gbc.gridx = 0;
        gbc.gridy = 1;
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
        gbc.gridy = 1;
        gbc.gridwidth = 1;
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
            String startRange = startRangeField.getText();
            String endRange = endRangeField.getText();
            JOptionPane.showMessageDialog(null, "Start: " + startRange + "\nEnd: " + endRange);
        });

        // Label to show the selected report range
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel label = new JLabel();
        upperPanel.add(label, gbc);

        reportSlider.addChangeListener(e -> {
            String reportText = labels.get(reportSlider.getValue()).getText();
            label.setText("Report: " + reportText);
        });

        // Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        JPanel buttonPanel = createButtonPanel();
        upperPanel.add(buttonPanel, gbc);

        return upperPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Create Buttons
        JButton salesButton = createIconButton("Sales", Values.PRINT_ICON);
        JButton stockButton = createIconButton("Remaining Stock", Values.PRINT_ICON);
        JButton profitButton = createIconButton("Profit", Values.PRINT_ICON);

        // Add buttons to the panel
        buttonPanel.add(salesButton);
        buttonPanel.add(stockButton);
        buttonPanel.add(profitButton);

        return buttonPanel;
    }

    private JButton createIconButton(String text, String iconPath) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Icon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, resizedIcon);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(Color.decode(Values.BUTTON_COLOR));
        button.setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    private JPanel createLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.setBackground(Color.decode(Values.BG_COLOR));

        JLabel graphPlaceholder = new JLabel("Graph Placeholder", SwingConstants.CENTER);
        graphPlaceholder.setForeground(Color.decode(Values.TEXT_COLOR));
        graphPlaceholder.setFont(new Font("Arial", Font.BOLD, 16));
        lowerPanel.add(graphPlaceholder, BorderLayout.CENTER);

        return lowerPanel;
    }

    public void display(JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout()); // Explicitly set layout to BorderLayout
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
