package Models;

import Entites.Employee;
import Entites.Product;
import Entites.Transactions;

import java.util.ArrayList;

public class BranchManager {
    String Email;
    String password;
    int branchId;
    int managerId;
    int TotalEmployees;

    public BranchManager(String Email, String password) {
        this.Email = Email;
        this.password = password;
        setManagerId();
        setBranchId();
        setTotalEmployeesinitially();

        System.out.println("Branch Manager Model initialized");
    }
    // setters
    public void setManagerId()
    {
        // set the manager id from the db after looking up against email
    }
    public void setBranchId()
    {
        // set the branch id from the db after looking up against email
    }
    public void setTotalEmployeesinitially()
    {
        // set the total employees from the db after looking up against branch id
    }
    public void updateTotalEmployees(Boolean isAdded)
    {
//        if (isAdded)
//        {
//            TotalEmployees++;
//        }
//        else {
//            TotalEmployees--;
//        }
        // update the total employees after adding a new employee
        // increase by 1
    }
    // DB operations
    public Boolean changePassword(String Password)
    {
        System.out.println("Branch Manager Password changed");
        // sirf update karna hai against the id no checks to be performed
        return true;
    }
    public boolean isPasswordChanged() {
        // check if the password has been changed or not
        return false;
    }
    public int getUniqueEmployeeId()
    {
        // get the unique employee id
        return 0;
    }

    public Boolean addNewEmployee(String employeeType,String EmployeeName, String EmployeeEmail, Float EmployeeSalary) {
        // add new employee

        System.out.println("New Employee added");

        // 4 data to be included coming from parameters
        // branch id from class attribute
        String password = "123";
        int employeeId = getUniqueEmployeeId();
        Boolean isPasswordChanged = false;
        if (true) // if added successfully
        {
            updateTotalEmployees(true); // increase by 1

        }
        return true;

    }

    public ArrayList<Employee> getAllEmployees() {
        // get all employees from the db where branch id is this.branchId
        return new ArrayList<Employee>();
    }

    public boolean updateEmployee(String employeeType, String employeeName, String employeeEmail, Float employeeSalary,Boolean status) {
        // update employee
        System.out.println("Employee updated");
        return true;
    }
    public boolean deleteEmployee( String employeeEmail) {
        // delete employee
        updateTotalEmployees(false); // decrease by 1
        System.out.println("Employee deleted");
        // set status to inactive;
        return true;
    }

    public ArrayList<Transactions> getTransactions(String type) {
        return SuperAdmin.getTransactions(branchId,type);
    }

    public ArrayList<Product> getStockReport() {
        return SuperAdmin.getRemainingStock(branchId);
    }

    public Float getProfitReport(String type) {
        return SuperAdmin.CalculateProfit(branchId,type);
    }
}
