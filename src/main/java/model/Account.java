package model;

public class Account {
    private String userName;
    private String passWord;
    private String status;

    public Account(){

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
