package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Entites.Branch;
import Utils.Values;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SuperAdminViewUpdateDeleteBranchespanel extends JPanel {
    ActionListener superAdminListener;
    // this listner is for the update and delete button
    // update button ki action command will be values.UPDATE_BRANCH
    // delete button ki action command will be values.DELETE_BRANCH


    public SuperAdminViewUpdateDeleteBranchespanel(ActionListener superAdminListener) {
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminViewUpdateDeleteBranchespanel initialized");
        inIt();
    }
    public void inIt() {
        // create the panel here
        // this is temporary code
        JLabel branchId = new JLabel("Branch Id");
        JTextField branchIdField = new JTextField(20);
        JButton update = new JButton("Update");
        update.setActionCommand(Values.UPDATE_BRANCH);
        update.addActionListener(superAdminListener);
        JButton delete = new JButton("Delete");
        delete.setActionCommand(Values.DELETE_BRANCH);
        delete.addActionListener(superAdminListener);
        this.add(branchId);
        this.add(branchIdField);
        this.add(update);
        this.add(delete);
        // to get branch data
        // to get all branch data here how it will be done
        ArrayList<Branch> branches = SuperAdminController.getBranches();
        // to show in table
        // create a table and show the data in it
        for (Branch branch : branches) {
            System.out.println(branch.getBranchId());
            System.out.println(branch.getCity());
            System.out.println(branch.getAddress());
            System.out.println(branch.getPhoneNo());
            System.out.println(branch.getNoOfEmployees());
            System.out.println(branch.getStatus());
        }
        // temporary code ends here
    }

}
