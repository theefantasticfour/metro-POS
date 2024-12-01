package Models;

import Entites.Branch;
import Entites.Product;
import Entites.Transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class SuperAdmin {
    private String username;
    private String password;
    public SuperAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // ------- Getters and Setters -------
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    //  ---------- database operations ----------
    public int getUniqueBranchId() {
        // logic to get unique branch id either from DB or txt file
        return 0;
    }
    public int getUniqueManagerId() {
        // logic to get unique manager id either from DB or txt file

        return 0;
    }
    // DB operations
    public Boolean RegisterBranch(int branchId,String city,String Address,String phoneNo,int noOfEmployees,Boolean Status) {
        Boolean isRegistered = false; // if duplicate exsits or due to some other reason we cannot register it

        // logic to register branch in DB


        return isRegistered;
    }
    public Boolean createManagerOfBranch(int branchId,int managerId,String name,Float Salary , String email,String password) {
        Boolean isCreated = false; // if duplicate exsits or due to some other reason we cannot create it

        // logic to create manager in DB


        return isCreated;
    }
    public ArrayList<Branch> getBranches() {
        ArrayList<Branch> branches = new ArrayList<Branch>();

        // logic to get all branches from DB

        // Note : Branch have manager name, manager salry and managerid that would be set by calling
        //        setManagerName and setManagerSalary setManagerId methods of Branch class
        //        because there is a possible case that branch have no manager assigned yet but branch has been created


        return branches;
    }
    public Boolean updateBranch(int branchId,String city,String Address,String phoneNo,int noOfEmployees,Boolean Status,String managerName,Float managerSalary,int managerId) {
        Boolean isUpdated = false; // if branch not found or due to some other reason we cannot update it

        // logic to update branch in DB
        // user can update  anything in the branch so make sure that you update all the fields including manager data recieved in parameters

        return isUpdated;
    }
    public Boolean deleteBranch(int branchId) {
        Boolean isDeleted = false; // if branch not found or due to some other reason we cannot delete it

        // logic to delete branch in DB
        // NOTE : we will not delete the branch we will just change the status of the branch to false

        return isDeleted;
    }
    // reports + graphs
    // of Sales || Remaining Stock || Profit
    // All this to be calculated daily, weekly, monthly, yearly
    public static ArrayList<Transactions> getTransactions(int branchId , String type) {
        ArrayList<Transactions> transactions = new ArrayList<Transactions>();

        // logic to get all transactions from DB
        // type tells Daily monthly or yearly


        return transactions;
    }
    public static  ArrayList<Product> getRemainingStock(int branchId) {
        ArrayList<Product> products = new ArrayList<Product>();
        // if branch id is -1 then we will get the remaining stock of the product
        // phir ham sirf product id ki base pai search karien ga
        // or agar branch id ki koi value ho to phir ham us branch ki stock nikalie ga
        // phir ham prodcutid+branchid ki base pai search karien ga
        // logic to get remaining stock of a particlular id from DB
        // product kai constructor mai sari details hain sirf branchid ko set karna pare ga
        // agar to branch id -1 nahi hai to branch id set karni hai or remaining stock us
        // branch ki nikalni hai
        // agar branch id -1 hai to hamain sari branches ki stock nikalni hai in that case branch id set hi nahi karien ga
        return products;
    }
    public static ArrayList<Integer> getAllBranchIds() {
        ArrayList<Integer> branches = new ArrayList<Integer>();
        // logic to get all branch ids
        // getBranches() sai sari branches mil jain gi or jese tumhe sahi lage
        // phir unki ids nikal kar forward karna hai
        return branches;
    }

    // -------- Logical Operations -------

    public static Float CalculateProfit(int BranchId,String type) {
        // logic to generate profit report by using getTransactions and type = daily, monthly, yearly
        // if -1 then we will get the profit of all branches combined
        // logic to calculate profit from transactions
        // Danish this will be implemented as selling price - cost price of all products invloveld in the transaction
        return 0.0f;
    }

}
