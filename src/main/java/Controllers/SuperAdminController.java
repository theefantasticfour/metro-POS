package Controllers;

import Entites.Branch;
import Models.SuperAdmin;
import Views.Mainscreen;
import Views.SuperAdminview.SuperAdminView;

import javax.swing.*;
import java.util.ArrayList;

public class SuperAdminController {
    SuperAdmin superAdminModel;
    SuperAdminView superAdminView;
// ------- Constructor ------
    public SuperAdminController() {
        System.out.println("Super Admin Controller called.");
    }
    public void start(String username, String password) {
        System.out.println("Super Admin Controller started.");
        // initialed View
        Mainscreen.getInstance().showSuperAdmin();
        // Initialised model
        superAdminModel = new SuperAdmin(username, password);
    }
// ------- GUI operations ------


        //------- DB operations ------
    public void RegisterBranch() {
        // logic to register branch in DB
        Object[] data = getData();
        Boolean isRegistered = superAdminModel.RegisterBranch((Integer) data[0], (String) data[1], (String) data[2], (String) data[3], (Integer) data[4], (Boolean) data[5]);
        if (isRegistered) {
            System.out.println("Branch Registered");
        } else {
            System.out.println("Branch Not Registered");
        }
    }
    public void createManagerOfBranch() {
        // logic to create manager in DB
        int branchId = superAdminView.getBranchId();
        int managerId = superAdminView.getManagerId();
        String name = superAdminView.getManagerName();
        Float Salary = superAdminView.getManagerSalary();
        String email = superAdminView.getManagerEmail();
        String password = "123";

        Boolean isCreated = superAdminModel.createManagerOfBranch(branchId, managerId, name, Salary, email, password);
        if (isCreated) {
            System.out.println("Manager Created");
        } else {
            System.out.println("Manager Not Created");
        }
    }
    public void getBranches() {
        // logic to get all branches from DB
         superAdminModel.getBranches();// forward it to view
    } // to Show in table
    public void getUniqueBranchId() {
        // logic to get unique branch id either from DB or txt file
        superAdminModel.getUniqueBranchId();
    }
    public void getUniqueManagerId() {
        // logic to get unique manager id either from DB or txt file
        superAdminModel.getUniqueManagerId();
    }
    public void UpdateBranch() {
        // logic to update branch in DB
        Object[] data = getData();
        String managerName = superAdminView.getManagerName();
        Float managerSalary = superAdminView.getManagerSalary();
        int managerId = superAdminView.getManagerId();
        Boolean isUpdated = superAdminModel.updateBranch((Integer) data[0], (String) data[1], (String) data[2], (String) data[3], (Integer) data[4], (Boolean) data[5], managerName, managerSalary, managerId);
        if (isUpdated) {
            JOptionPane.showMessageDialog(null, "Branch Updated");
        } else {
        JOptionPane.showMessageDialog(null, "Branch Not Updated");
        }
    }
    public void DeleteBranch() {
        int branchId = superAdminView.getBranchId();
        // logic to delete branch in DB
        Boolean isDeleted = superAdminModel.deleteBranch(branchId);
        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Branch Deleted");
        } else {
        JOptionPane.showMessageDialog(null, "Branch Not Deleted");
        }
    }
   // ---------- Action Listners -------
    // 1 APPLY FOR BRANCH CREATION
    // 2 APPLY FOR MANAGER CREATION
    // 3 uPDATE BRANCH
    // 4 DELETE BRANCH
    // 3 buttons of report

   // ------- Helper functions ------
    public Object[] getData() {
        int branchId = superAdminView.getBranchId();
        String city = superAdminView.getCity();
        String Address = superAdminView.getAddress();
        String phoneNo = superAdminView.getPhoneNo();
        int noOfEmployees = superAdminView.getNoOfEmployees();
        Boolean Status = superAdminView.getStatus();
        // store in array
        return new Object[]{branchId, city, Address, phoneNo, noOfEmployees, Status};
    }
}
