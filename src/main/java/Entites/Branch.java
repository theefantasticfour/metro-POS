package Entites;

public class Branch {
    int branchId;
    String city;
    String Address;
    String phoneNo;
    int noOfEmployees;
    Boolean Status;
    int managerId ;
    String managerName = "";
    Float managerSalary = 0.0f;
    public Branch(int branchId, String city, String address, String phoneNo, int noOfEmployees, Boolean status) {
        this.branchId = branchId;
        this.city = city;
        Address = address;
        this.phoneNo = phoneNo;
        this.noOfEmployees = noOfEmployees;
        Status = status;
    }
    public void setManagerId(int managerId)
    {
        this.managerId = managerId;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public void setManagerSalary(Float managerSalary) {
        this.managerSalary = managerSalary;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public Boolean getStatus() {
        return Status;
    }

    public String getManagerName() {
        return managerName;
    }

    public Float getManagerSalary() {
        return managerSalary;
    }
}
