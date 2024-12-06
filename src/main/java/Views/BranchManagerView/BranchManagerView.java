package Views.BranchManagerView;

import Controllers.BranchManagerController;

import javax.swing.*;

public class BranchManagerView extends JPanel {

    // set from db
     // other buttons will only work once password is changed
    // you can try some disabled colours like light grey or something and change it once
    // isPasswordChanged is true
    // only allow change of panels it the password is changed
    // ----------------------- ACTION LISTNER TO BES USED IN -------------
            // logout
            // apply for changepassword
            // add employee
            // update
            // delete
            // sales
            // stock
            // profit
    // -------------------------------------------------------------------


    Boolean isPasswordChanged ; // this will automatically be set when constructor is called
    BranchManagerController branchManagerController;
    public BranchManagerView(BranchManagerController instance) {
        System.out.println("Branch Manager View initialized");
        branchManagerController = instance;
        setIsPasswordChanged(); // set the password changed status
         }

    public String getEmployeeType() {
        // from add employee panel
        return null;
    }
    public  String getEmployeeName() {
        // from add employee panel
    return null;
    }

    public  String getEmployeeEmail() {
        // from add employee panel
        return null;
    }

    public  Float getEmployeeSalary() {
        // from add employee panel
        return null;
    }

    public  String getEmployeeTypeToUpdate() {
     // from view/update/delete employee panel
        return null;
    }

    public  String getEmployeeNameToUpdate() {
        // from view/update/delete employee panel
        return null;
    }

    public  String getEmployeeEmailToUpdate() {
        // from view/update/delete employee panel
        return null;
    }

    public  Float getEmployeeSalaryToUpdate() {
        // from view/update/delete employee panel
        return null;
    }

    public  Boolean getEmployeeStatusToUpdate() {
        // from view/update/delete employee panel
        return null;
    }
    public String getType() {
        // from reports employee panel
        return null;
    }
    public void setIsPasswordChanged()
    {
        isPasswordChanged = branchManagerController.isPasswordChanged();
    }


    public String getNewPassword() {
        // from change password panel
        return null;
    }

    public String getConfirmPassword() {
        // from change password panel
        return null;
    }
}
