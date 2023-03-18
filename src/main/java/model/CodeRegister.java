package model;

public class CodeRegister {
    private String userName;
    private String passWord;
    private int code;
    public CodeRegister(){

    }

    public CodeRegister(String userName, String passWord, int code) {
        this.userName = userName;
        this.passWord = passWord;
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
