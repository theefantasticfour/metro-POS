package Views.SuperAdminview;

import Utils.Values;
import Views.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

    //not completed yet
public class Reports {
    private JPanel mainPanel;

    public Reports() {
        // Initialize the main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Create upper and lower panels
        JPanel upperPanel = createUpperPanel();
        JPanel lowerPanel = createLowerPanel();

        // Add a separator line between the panels
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.decode(Values.TEXT_COLOR));

        // Use BorderLayout to center-align panels while maintaining the 30%-70% split
        JPanel upperWrapper = new JPanel(new BorderLayout());
        upperWrapper.setBackground(Color.decode(Values.BG_COLOR));
        upperWrapper.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        upperWrapper.add(upperPanel, BorderLayout.CENTER);

        JPanel lowerWrapper = new JPanel(new BorderLayout());
        lowerWrapper.setBackground(Color.decode(Values.BG_COLOR));
        lowerWrapper.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        lowerWrapper.add(lowerPanel, BorderLayout.CENTER);

        // Add components to the main panel with proper proportions
        mainPanel.add(upperWrapper, BorderLayout.NORTH);
        mainPanel.add(separator, BorderLayout.CENTER);
        mainPanel.add(lowerWrapper, BorderLayout.SOUTH);
    }

        private JPanel createUpperPanel() {
            JPanel upperPanel = new JPanel();
            upperPanel.setLayout(new GridBagLayout());
            upperPanel.setBackground(Color.decode(Values.BG_COLOR));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            upperPanel.add(new JLabel("Branch Code:"), gbc);

            gbc.gridx = 1;
            JTextField branchCodeField = new JTextField(15);
            upperPanel.add(branchCodeField, gbc);


            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2; // Slider spans two columns
            JSlider reportSlider = new JSlider(0, 3, 0);
            reportSlider.setMajorTickSpacing(1);
            reportSlider.setPaintTicks(true);
            reportSlider.setPaintLabels(true);

            // Labels for slider values
            Hashtable<Integer, JLabel> labels = new Hashtable<>();
            labels.put(0, new JLabel("Today"));
            labels.put(1, new JLabel("Weekly"));
            labels.put(2, new JLabel("Monthly"));
            labels.put(3, new JLabel("Yearly"));
            reportSlider.setLabelTable(labels);
            reportSlider.setPreferredSize(new Dimension(300, 50));
            upperPanel.add(reportSlider, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1; // On the same row as the slider
            gbc.gridwidth = 1; // Reset gridwidth to 1
            upperPanel.add(new JLabel("Start Range:"), gbc);

            gbc.gridx = 3; // Next column for the text field
            JTextField startRangeField = new JTextField(10); // Wider text field
            startRangeField.setPreferredSize(new Dimension(100, 25));
            upperPanel.add(startRangeField, gbc);

            // End Range Label and TextField
            gbc.gridx = 4; // Next column after Start Range
            upperPanel.add(new JLabel("End Range:"), gbc);

            gbc.gridx = 5; // Next column for the text field
            JTextField endRangeField = new JTextField(10); // Wider text field
            endRangeField.setPreferredSize(new Dimension(100, 25));
            upperPanel.add(endRangeField, gbc);

            // Apply Button
            gbc.gridx = 6; // Place the Apply button after the End Range text field
            CustomButton applyButton = new CustomButton("Apply");
            upperPanel.add(applyButton, gbc);
            applyButton.addActionListener(e -> {
                String startRange = startRangeField.getText();
                String endRange = endRangeField.getText();
                JOptionPane.showMessageDialog(null, "Start: " + startRange + "\nEnd: " + endRange);
            });


            // Label to show the selected report range
            gbc.gridx = 0;
            gbc.gridy = 2; // Move below the slider
            gbc.gridwidth = 7; // Span across all fields
            gbc.anchor = GridBagConstraints.CENTER; // Center alignment
            JLabel label = new JLabel();
            upperPanel.add(label, gbc);

            // Add a ChangeListener to the slider
            reportSlider.addChangeListener(e -> {
                String reportText = labels.get(reportSlider.getValue()).getText();
                label.setText("Report: " + reportText);
            });

            // Buttons Panel
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 7; // Span across all columns
            JPanel buttonPanel = createButtonPanel();
            upperPanel.add(buttonPanel, gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
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
        // Load and scale the icon
        ImageIcon originalIcon = new ImageIcon(iconPath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Icon resizedIcon = new ImageIcon(scaledImage);

        // Create button with text and icon
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
        lowerPanel.setLayout(new GridLayout(1, 1, 10, 10)); // Single placeholder panel
        lowerPanel.setBackground(Color.decode(Values.BG_COLOR));

        JLabel graphPlaceholder = new JLabel("Graph Placeholder", SwingConstants.CENTER);
        graphPlaceholder.setForeground(Color.decode(Values.TEXT_COLOR));
        graphPlaceholder.setFont(new Font("Arial", Font.BOLD, 16));
        lowerPanel.add(graphPlaceholder);

        return lowerPanel;
    }

    public void display(JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.add(mainPanel);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
