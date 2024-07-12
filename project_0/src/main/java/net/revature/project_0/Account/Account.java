package net.revature.project_0.Account;

public class Account {
    //Atributes
    //OOP -Encapsulation
    private String userName;
    private int userNumID;
    private String password;
    private String firstName;
    private String lastName;

    public Account(){}
    public Account(String userName, String password, int userNumID) {
        this.userName = userName;
        this.password = password;
        this.userNumID = userNumID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserNumID() {
        return userNumID;
    }

    public void setUserNumID(int userNumID) {
        this.userNumID = userNumID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
