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
    public Branch()
    {

    }
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

    //make all setters
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phoneNo = phone;
    }

    public void setName(String name) {
        this.managerName = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public void setActive(Boolean status)
    {
        Status = status;
    }


}
