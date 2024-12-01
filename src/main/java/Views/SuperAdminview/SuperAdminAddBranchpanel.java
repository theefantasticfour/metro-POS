package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Utils.Values;

import javax.swing.*;
import java.awt.event.ActionListener;


public class SuperAdminAddBranchpanel extends JPanel {
    ActionListener SuperadminListner;
    // add this listner to the apply button that you will create here
    // to display the branch id int this panel use
    // int i = SuperAdminController.getUniqueBranchId();
    // Create the button as you usually do but just add the action command because it is used in
    // the controller to identify the action
    // JButton apply = new JButton("Apply"); // create the button using custom class
    //  apply.setActionCommand(Values.REGISTER_BRANCH); // ADDITIONAL STEP
    public SuperAdminAddBranchpanel(ActionListener SuperadminListner) {
        this.SuperadminListner = SuperadminListner;
        System.out.println("SuperAdminAddBranchpanel initialized");
        inIt();
    }
    public void inIt() {
        // create the panel here
        // this is temporary code
        JLabel branchId = new JLabel("Branch Id");
        JTextField branchIdField = new JTextField(20);
        JLabel city = new JLabel("City");
        JTextField cityField = new JTextField(20);
        JLabel address = new JLabel("Address");
        JTextField addressField = new JTextField(20);
        JLabel phoneNo = new JLabel("Phone No");
        JTextField phoneNoField = new JTextField(20);
        JLabel noOfEmployees = new JLabel("No of Employees");
        JTextField noOfEmployeesField = new JTextField(20);
        JLabel status = new JLabel("Status");
        JCheckBox statusField = new JCheckBox();
        JButton apply = new JButton("Apply");
        apply.setActionCommand(Values.REGISTER_BRANCH);
        apply.addActionListener(SuperadminListner);
        this.add(branchId);
        this.add(branchIdField);
        this.add(city);
        this.add(cityField);
        this.add(address);
        this.add(addressField);
        this.add(phoneNo);
        this.add(phoneNoField);
        this.add(noOfEmployees);
        this.add(noOfEmployeesField);
        this.add(status);
        this.add(statusField);
        this.add(apply);
    }
}
