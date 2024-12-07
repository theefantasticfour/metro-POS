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
}
