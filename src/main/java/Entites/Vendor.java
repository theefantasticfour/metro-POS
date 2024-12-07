package Entites;

import java.util.Date;

public class Vendor {
//    vendor_id (INT, Primary Key): Unique identifier for each vendor.
//    name (VARCHAR): Vendor name.
//    address (VARCHAR): Vendor address.
//    phone (VARCHAR): Vendor contact number.
//            branch_id (INT, Foreign Key): Links to the Branch table.
//            Creator_id(INT, Foreign Key): Links to EMployee Table, for the reference which Data entry operator added the respective vendor

    public int vendor_id;
    public String name;
    public String address;
    public String phone;
    int branch_id;
    int creator_id;
    public Date StartDate;
    public Date EndDate;
    public Float totalPayment;
    public int Totalproduct;
    public Vendor()
    {

    }
    public Vendor(int vendor_id, String name, String address, String phone, int branch_id, int creator_id, Date startDate, Date endDate) {
        this.vendor_id = vendor_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.branch_id = branch_id;
        this.creator_id = creator_id;
        StartDate = startDate;
        EndDate = endDate;
    }
    //make all setters
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setTotalProduct(int totalproduct) {
        Totalproduct = totalproduct;
    }



}
