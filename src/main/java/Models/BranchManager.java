package Models;

public class BranchManager {
    String Email;
    String password;
    int branchId;
    int managerId;
    public BranchManager() {
        System.out.println("Branch Manager Model initialized");
    }
    // DB operations
    public Boolean changePassword(String Password) {
        System.out.println("Branch Manager Password changed");
        // sirf update karna hai against the id/email no checks to be performed
        return true;
    }
}
