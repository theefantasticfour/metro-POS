package Views.SuperAdminview;

import Utils.Values;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SuperAdminView extends JPanel {
    ActionListener superAdminListener;
    // jis panel mai yai chaie isko pass kardo
    // pr yai logout button kai liye bhi chale ga
    // iska action command hoga Values.LOGOUT

    // Side Panel yahn bane ga or yahn sai baqi panels kholien gai
    // wo wali ratio or navigation ap nai khud set karni hai kai kia click ho to konsa panel khole
    // panels ki classes mene bana di hain.
    // Getters or setter bhi set kar dena hain
    // you have to see kai konse panel wo mai wo particluar textfeild hai or phir wahn sai uska data uthana hai
    // i.e for getCity
    //  getCity(){
    //  SuperAdminAddBranchpanel panel = new SuperAdminAddBranchpanel();
    // panel.getCity();
    // }

    public SuperAdminView(ActionListener superAdminListener) {
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminView initialized");
    }

    // Getters and
    public int getBranchIdtoupdate() {
    return 0;
    } // table sai branch id to update ect
    public int getBranchidtoshowreports() { // combo box sai id
    return 0;
    }
    public String getTypetoShowReports() {
    return null;
    // type can be daily, weekly, monthly
    }


    public String getCity() {

    return null;
    }

    public String getAddress() {
    return null;
    }

    public String getPhoneNo() {
        return null;
    }

    public int getNoOfEmployees() {
        return 0;
    }

    public Boolean getStatus() {
        return null;
    }


    public String getManagerName() {
        return null;
    }

    public Float getManagerSalary() {
        return null;
    }

    public int getManagerId() {
        return 0;
    }

    public String getManagerEmail() {
        return null;
    }

    public int getBranchidtoRegister() { // addbranch wale panel sai
    return 0;
    }

    public int getBranchIdtoCreateManager() { // add manager wale panel sai
    return 0;
    }
}

