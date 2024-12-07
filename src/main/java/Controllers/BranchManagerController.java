package Controllers;

import Entites.Employee;
import Entites.Product;
import Entites.Transactions;
import Models.BranchManager;
import Session.Session;
import Utils.Values;
import Views.BranchManagerView.BranchManagerView;
import Views.Mainscreen;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BranchManagerController {
    String username;
    String password;
    Session session;
    BranchManager branchManagermodel;
    BranchManagerView branchManagerView;
   public ActionListener branchManagerListener; // can be access by the instance of this class

    public BranchManagerController(String username, String password, Session instance) {
        this.session = instance;
        System.out.println("Branch Manager Controller initialized");
        this.username = username;
        this.password = password;
        setActionListeners();

    }

    public void start() {
        System.out.println("Branch Manager Controller started");
        // setting up the model
        branchManagermodel = new BranchManager(username, password);
        // setting up this panel in the mainscreen
        Mainscreen.getInstance().showBranchManager(branchManagerListener,this);
        // getting instance for further use
        branchManagerView = Mainscreen.getBranchManagerView();

    }


    // to change password
    public Boolean changePassword() {
        // see if the passwords match
        String newPassword = branchManagerView.getNewPassword();
        String confirmPassword = branchManagerView.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        return branchManagermodel.changePassword(newPassword);
    }

    // to see if the password has been changed since first login
    public boolean isPasswordChanged() {
        return branchManagermodel.isPasswordChanged();
    }

    // Action Listeners
    public void setActionListeners() {
        branchManagerListener = e -> {
            // logout
            // apply for changepassword
            // add employee
            // update
            // delete
            // sales
            // stock
            // profit
            if (e.getActionCommand().equals(Values.LOGOUT))
            {
                session.showLogin();
            }
            else if (e.getActionCommand().equals(Values.CHANGE_PASSWORD))
            {
                changePassword();
            }
            else if (e.getActionCommand().equals(Values.ADD_EMPLOYEE))
            {
                addNewEmployee();
            }
            else if (e.getActionCommand().equals(Values.UPDATE_EMPLOYEE))
            {
                UpdateEmployee();
            }
            else if (e.getActionCommand().equals(Values.DELETE_EMPLOYEE))
            {
                deleteEmployee();
            }
            else if (e.getActionCommand().equals(Values.SALES_REPORT))
            {
                downloadSalesReport(branchManagerView.getType(), true);
            }
            else if (e.getActionCommand().equals(Values.STOCK_REPORT))
            {
                downloadStockReport(false);
            }
            else if (e.getActionCommand().equals(Values.PROFIT_REPORT))
            {
                downloadProfitReport(branchManagerView.getType(), true);
            }
        };
    }

    // OPERATIONS
    // to add new employee
    public void addNewEmployee() {
        String employeeType = branchManagerView.getEmployeeType();
        String EmployeeName = branchManagerView.getEmployeeName();
        String EmployeeEmail = branchManagerView.getEmployeeEmail();
        Float EmployeeSalary = branchManagerView.getEmployeeSalary();
        if (branchManagermodel.addNewEmployee(employeeType, EmployeeName, EmployeeEmail, EmployeeSalary)) {
            JOptionPane.showMessageDialog(null, EmployeeName + " - " + employeeType + "  added successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error adding employee");
        }
    }

    // to get all employees for the view/update/delete
    public ArrayList<Employee> getAllEmployees() {
        return branchManagermodel.getAllEmployees();
    }

    // to update employee
    public void UpdateEmployee() {
        String employeeType = branchManagerView.getEmployeeTypeToUpdate();
        String EmployeeName = branchManagerView.getEmployeeNameToUpdate();
        String EmployeeEmail = branchManagerView.getEmployeeEmailToUpdate();
        Float EmployeeSalary = branchManagerView.getEmployeeSalaryToUpdate();
        Boolean status = branchManagerView.getEmployeeStatusToUpdate();

        if (branchManagermodel.updateEmployee(employeeType, EmployeeName, EmployeeEmail, EmployeeSalary, status)) {
            JOptionPane.showMessageDialog(null, EmployeeName + " - " + employeeType + "  updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error updating employee");
        }
    }

    // to change the status of the employee to inactive
    public void deleteEmployee() {
        String employeEmail = branchManagerView.getEmployeeEmailToUpdate();
        if (branchManagermodel.deleteEmployee(employeEmail)) {
            JOptionPane.showMessageDialog(null, "Employee deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error deleting employee");
        }
    }
    // Sales button
    public ArrayList<Transactions> downloadSalesReport(String type, Boolean isDownload) {
        // logic to get sales report and make it downloadable
        ArrayList<Transactions> sales = branchManagermodel.getTransactions(type);
        if (isDownload) {
            // download the report
        }
        return sales;
    }

    // Stock button
    public ArrayList<Product> downloadStockReport(Boolean isDownload) {
        // logic to get profit report and make it downloadable
        ArrayList<Product> profit = branchManagermodel.getStockReport();
        if (isDownload) {
            // download the report
        }
        return profit;
    }

    // Profit button
    public Float downloadProfitReport(String type, Boolean isDownload) {
        // logic to get profit report and make it downloadable
        Float profit = branchManagermodel.getProfitReport(type);
        if (isDownload) {
            // download the report
        }
        return profit;
    }
}
