package Views.SuperAdminview;

import java.awt.event.ActionListener;

    // this listner is for the 3 download buttons
    // download button ki action command will be Values.SALES_REPORT
    // download button ki action command will be Values.STOCK_REPORT
    // download button ki action command will be Values.PROFIT_REPORT
    // --------- for graphs -----------
    // to get the data for the graph
    // all of their 3rd parameter will be false because we are not downloading them
    // for sales report
    // ArrayList<Transaction> transactions = SuperAdminController.downloadSalesReport();
    // for product report
    // ArrayList<Product> products = SuperAdminController.downloadProductReport();
    // will get products that you would then have to manipulate to show in the graph
    // its class contain the attribute to maanipulate
    // for profit report
    // Float profit = SuperAdminController.downloadProfitReport();
    // you will get Arraylist of class transaction That you would then have to manipulate to show in the graph

import Controllers.SuperAdminController;
import Utils.Values;
import Views.Components.CustomButton;

import javax.swing.*;
        import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class SuperAdminReportsGraphspanel {
   ActionListener superAdminListener;
   private SuperAdminController superAdminController;
   private JPanel mainPanel;
   public String Branchid;
   public DateTimeFormatter formatter;
   public LocalDate startDate;
   public LocalDate endDate;
   public String SliderText;



   public SuperAdminReportsGraphspanel(ActionListener superAdminListener, SuperAdminController instance) {
      // Set up the main panel with BorderLayout
      this.superAdminController = instance;
      this.superAdminListener = superAdminListener;
      System.out.println("SuperAdminReportPanel initialized");

      formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      SliderText=null;
      startDate=null;
      endDate=null;
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
         Branchid = branchCodeField.getText().trim();
         String startRange = startRangeField.getText().trim();
         String endRange = endRangeField.getText().trim();
         boolean isRangeEntered = !startRange.isEmpty() && !endRange.isEmpty(); // Check if both range fields are filled
         int sliderValue = reportSlider.getValue();

         // Validate the branch ID
         if (Branchid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Branch Code");
            return; // Exit the action listener if branch code is not entered
         }

         // Validate if both slider and range are used
         if (isRangeEntered && sliderValue != 0) {
            JOptionPane.showMessageDialog(null, "Error: You cannot use both slider and range fields at the same time.");
         } else if (isRangeEntered) {
            // Validate range values
            try {
               startDate = LocalDate.parse(startRange, formatter);
               endDate = LocalDate.parse(endRange, formatter);


               if (startDate.isAfter(endDate)) {
                  JOptionPane.showMessageDialog(null, "Error: Start range cannot be after end range.");
               } else {
                  JOptionPane.showMessageDialog(null, "Start Range: " + startDate + "\nEnd Range: " + endDate + " saved!");
               }
            } catch (DateTimeParseException ex) {
               JOptionPane.showMessageDialog(null, "Error: Dates must be in the format DD/MM/YYYY.");}
         } else if (sliderValue != 0) {
          SliderText=labels.get(sliderValue).getText();
            JOptionPane.showMessageDialog(null, "Report Range: " + SliderText + " saved!");
         } else {
            JOptionPane.showMessageDialog(null, "Error: Enter a range or select a slider value.");
         }
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

      salesButton.setActionCommand(Values.SALES_REPORT);
      stockButton.setActionCommand(Values.STOCK_REPORT);
      profitButton.setActionCommand(Values.PROFIT_REPORT);
      salesButton.addActionListener(superAdminListener);
      stockButton.addActionListener(superAdminListener);
      profitButton.addActionListener(superAdminListener);

      stockButton.addActionListener(e -> {
         JOptionPane.showMessageDialog(null, "Stock Report Downloading " );
      });

      profitButton.addActionListener(e -> {
         JOptionPane.showMessageDialog(null, "Profit Report Downloading" );
      });

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
   public String getBranchIdToShowreports() {
      return Branchid;
   }
   public String getReportType() {
      return SliderText;
   }
   public String getStartDate() {
      return startDate != null ? startDate.format(formatter) : null;
   }
   public String getEndDate() {
      return endDate != null ? endDate.format(formatter) : null;
   }

}
