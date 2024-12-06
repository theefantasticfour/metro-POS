package Controllers;

import Entites.Branch;
import Entites.Product;
import Entites.Transactions;
import Models.SuperAdmin;
import Session.Session;
import Utils.Values;
import Views.Mainscreen;
import Views.SuperAdminview.SuperAdminView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SuperAdminController {
    SuperAdmin superAdminModel;
    SuperAdminView superAdminView;
    Session session;

    // ------- Constructor ------
    public SuperAdminController(Session instance) {
        System.out.println("Super Admin Controller called.");
        this.session = instance;
    }

    public void start(String username, String password) {
        System.out.println("Super Admin Controller started.");
        // initialed View
        // Initialised model
        superAdminModel = new SuperAdmin(username, password);
        Mainscreen.getInstance().showSuperAdmin(setActionListeners(), this); // returns the action listner

        superAdminView = Mainscreen.getSuperAdminView();
    }

// ------- GUI operations ------


    //------- DB operations ------
    public void RegisterBranch() {
        // logic to register branch in DB
        System.out.println("reached hereeeeee");
        int branchId = superAdminView.getBranchidtoRegister();

        String name = superAdminView.getbranchName();
        System.out.println("name of branch = " + name);
        Object[] data = getData();
        Boolean isRegistered = superAdminModel.RegisterBranch(branchId, name, (String) data[0], (String) data[1], (String) data[2], (Integer) data[3], (Boolean) data[4]);
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

    public ArrayList<Branch> getBranches() {
        // logic to get all branches from DB
        return superAdminModel.getBranches(); // forward it to view
    } // to Show in table

    public void UpdateBranch() {
        // logic to update branch in DB
        System.out.println("reached hereeeeee324");
        int branchid = superAdminView.getBranchIdToUpdate();
        String city = superAdminView.getCityToUpdate();
        String Adress = superAdminView.getAdressToUpdate();
        String phoneNo = superAdminView.getPhonenoToUpdate();
        int noOfEmployees = superAdminView.getNoofEmployeesToUpdate();
        Boolean status = superAdminView.getStatusToUpdate();
        String Managername = superAdminView.getManagerNameToUpdate();
        Float ManagerSalary = Float.parseFloat(superAdminView.getManagerSalaryToUpdate());
        Boolean isUpdated = superAdminModel.updateBranch(branchid, city, Adress, phoneNo, noOfEmployees, status, Managername, ManagerSalary);
        if (isUpdated) {
            JOptionPane.showMessageDialog(null, "Branch Updated");
        } else {
            JOptionPane.showMessageDialog(null, "Branch Not Updated");
        }
    }

    public void DeleteBranch() {
        int branchId = superAdminView.getBranchIdToUpdate();
        // logic to delete branch in DB
        Boolean isDeleted = superAdminModel.deleteBranch(branchId);
        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Branch Deleted");
        } else {
            JOptionPane.showMessageDialog(null, "Branch Not Deleted");
        }
    }

    public int getUniqueBranchId() {
        // logic to get unique branch id either from DB or txt file
        return superAdminModel.getUniqueBranchId();
    }

    public int getUniqueManagerId() {
        // logic to get unique manager id either from DB or txt file
        return superAdminModel.getUniqueManagerId();
    }

    public ArrayList<Integer> getAllBranchIds() {
        // logic to get all branch ids
        ArrayList<Integer> branches = SuperAdmin.getAllBranchIds();
        // simulation

        return branches;
    }

    public ArrayList<Transactions> downloadSalesReport(int branchId, String type, Boolean isDownload) {
        // logic to get sales report and make it downloadable
        if (isDownload) {
            // download the report
        }
        return SuperAdmin.getTransactions(branchId, type);
    }

    public ArrayList<Product> downloadProductsReport(int branchId, Boolean isDownload) {
        // logic to get remaining products
        if (isDownload) {
            // download the report
        }
        return SuperAdmin.getRemainingStock(branchId);
    }

    public Float downloadProfitreport(int branchId, String type, Boolean isDownload) {
        // logic to get profit
        if (isDownload) {
            // download the report
        }
        return SuperAdmin.CalculateProfit(branchId, type);
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
                //  downloadSalesReport(superAdminView.getBranchidtoshowreports(),superAdminView.getTypetoShowReports(),true);
            } else if (e.getActionCommand().equals(Values.STOCK_REPORT)) {
                //downloadProductsReport(superAdminView.getBranchidtoshowreports(),true);
            } else if (e.getActionCommand().equals(Values.PROFIT_REPORT)) {
                //downloadProfitreport(superAdminView.getBranchidtoshowreports(),superAdminView.getTypetoShowReports(),true);
            } else if (e.getActionCommand().equals(Values.LOGOUT)) {
                // logout and go to login screen
                JOptionPane.showMessageDialog(null, "Logging out......");
                session.showLogin();
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
        return new Object[]{city, Address, phoneNo, noOfEmployees, Status};
    }
}
