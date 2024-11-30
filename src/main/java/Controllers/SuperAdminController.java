package Controllers;

import Entites.Branch;
import Entites.Product;
import Entites.Transactions;
import Models.SuperAdmin;
import Utils.Values;
import Views.Mainscreen;
import Views.SuperAdminview.SuperAdminView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class SuperAdminController {
    static SuperAdmin superAdminModel;
    SuperAdminView superAdminView;
// ------- Constructor ------
    public SuperAdminController() {
        System.out.println("Super Admin Controller called.");
    }
    public void start(String username, String password) {
        System.out.println("Super Admin Controller started.");
        // initialed View
        Mainscreen.getInstance().showSuperAdmin(setActionListeners()); // returns the action listner
        // Initialised model
        superAdminModel = new SuperAdmin(username, password);
        superAdminView = Mainscreen.getSuperAdminView();
    }
// ------- GUI operations ------


        //------- DB operations ------
    public void RegisterBranch() {
        // logic to register branch in DB
        int bId = superAdminView.getBranchidtoRegister();
        Object[] data = getData();
        Boolean isRegistered = superAdminModel.RegisterBranch(bId,(String) data[0], (String) data[1], (String) data[2], (Integer) data[3], (Boolean) data[4]);
        if (isRegistered) {
            System.out.println("Branch Registered");
        } else {
            System.out.println("Branch Not Registered");
        }
    }
    public void createManagerOfBranch() {
        // logic to create manager in DB
        int branchId = superAdminView.getBranchIdtoCreateManager();
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
    public static ArrayList<Branch> getBranches() {
        // logic to get all branches from DB
        return superAdminModel.getBranches();// forward it to view
    } // to Show in table
    public static int getUniqueBranchId() {
        // logic to get unique branch id either from DB or txt file
        return superAdminModel.getUniqueBranchId();
    }
    public static int getUniqueManagerId() {
        // logic to get unique manager id either from DB or txt file
        return superAdminModel.getUniqueManagerId();
    }
    public static ArrayList<Integer> getAllBranchIds() {
        // logic to get all branch ids
        ArrayList<Integer> branches = superAdminModel.getAllBranchIds();
       // simulation
        branches.add(0);
        branches.add(1);
        branches.add(2);
        branches.add(3);
        return branches;
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
        int branchId = superAdminView.getBranchIdtoupdate();
        // logic to delete branch in DB
        Boolean isDeleted = superAdminModel.deleteBranch(branchId);
        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Branch Deleted");
        } else {
        JOptionPane.showMessageDialog(null, "Branch Not Deleted");
        }
    }
    public static  ArrayList<Transactions> downloadSalesReport(int branchId,String type,Boolean isDownload) {
        // logic to get sales report and make it downloadable
        if (isDownload) {
            // download the report
        }
        return superAdminModel.getTransactions(branchId,type);
    }
    public static ArrayList<Product> downloadProductsReport(int branchId,Boolean isDownload) {
        // logic to get remaining products
        if (isDownload) {
            // download the report
        }
        return superAdminModel.getRemainingStock(branchId);
    }
    public static Float downloadProfitreport(int branchId, String type,Boolean isDownload) {
        // logic to get profit
        if (isDownload) {
            // download the report
        }
        return superAdminModel.CalculateProfit(branchId,type);
    }
   // ---------- Action Listners -------
    // 1 APPLY FOR BRANCH CREATION
    // 2 APPLY FOR MANAGER CREATION
    // 3 uPDATE BRANCH
    // 4 DELETE BRANCH
    // 3 buttons of salereport || stockreprt || profit
    public ActionListener setActionListeners() {
        return e -> {
            if (e.getActionCommand().equals(Values.REGISTER_BRANCH)) {
                RegisterBranch();
            } else if (e.getActionCommand().equals(Values.CREATE_MANAGER)) {
                createManagerOfBranch();
            } else if (e.getActionCommand().equals(Values.UPDATE_BRANCH)) {
                UpdateBranch();
            } else if (e.getActionCommand().equals(Values.DELETE_BRANCH)) {
                DeleteBranch();
            } else if (e.getActionCommand().equals(Values.SALES_REPORT)) {
                downloadSalesReport(superAdminView.getBranchidtoshowreports(),superAdminView.getTypetoShowReports(),true);
            } else if (e.getActionCommand().equals(Values.STOCK_REPORT)) {
               downloadProductsReport(superAdminView.getBranchidtoshowreports(),true);
            } else if (e.getActionCommand().equals(Values.PROFIT_REPORT)) {
                downloadProfitreport(superAdminView.getBranchidtoshowreports(),superAdminView.getTypetoShowReports(),true);
            } else if (e.getActionCommand().equals(Values.LOGOUT)) {
                // logout and go to login screen
            }
        };
    }

   // ------- Helper functions ------
    public Object[] getData() {
        String city = superAdminView.getCity();
        String Address = superAdminView.getAddress();
        String phoneNo = superAdminView.getPhoneNo();
        int noOfEmployees = superAdminView.getNoOfEmployees();
        Boolean Status = superAdminView.getStatus();
        // store in array
        return new Object[]{ city, Address, phoneNo, noOfEmployees, Status};
    }
}
