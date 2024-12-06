package Entites;

public class Employee {
    private int branch_id;
    private int employee_id;
    private String name;
    private String email;
    private String password;
    private String role;
    private float salary;
    private boolean is_password_changed;

    public Employee(int branch_id, int employee_id, String name, String email, String password, String role, float salary, boolean is_password_changed) {
        this.branch_id = branch_id;
        this.employee_id = employee_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.salary = salary;
        this.is_password_changed = is_password_changed;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public boolean isIs_password_changed() {
        return is_password_changed;
    }

    public void setIs_password_changed(boolean is_password_changed) {
        this.is_password_changed = is_password_changed;
    }
}
