package Models;

public class Login {
    private String username, password, typeOfUser;

    public Login() {
        System.out.println("Login model initialized");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Boolean validateUser() {
        // validate the type of user from db using username(email) and password
        return true;
    }
}
