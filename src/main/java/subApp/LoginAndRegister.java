package subApp;

import dao.AccountDAO;
import dao.CodeResDAO;
import model.Account;
import model.CodeRegister;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LoginAndRegister {
    private AccountDAO accountDAO = new AccountDAO();
    private CodeResDAO codeResDAO = new CodeResDAO();
    public boolean loginSuccess = false;

    public String userNameNow ="";
    public String passWordNow ="";

    private boolean statusOff = false;

    private void login(Scanner in) {

        String userName = "";
        String passWord = "";
        String status = "off";

        int count = 3;

        while (true) {
            if (count > 0) {
                System.out.print("\tNhập Username: ");
                userName = in.nextLine();
                System.out.print("\tNhập PassWord: ");
                passWord = in.nextLine();
                count--;

                if (checkLogin(userName, passWord, status)) {
                    loginSuccess = true;
                    userNameNow = userName;
                    passWordNow = passWord;
                    break;
                }
                if(statusOff){
                    System.out.printf("Tài khoản đang được sử dụng. Bạn còn %d lần nhập\n", count);
                    statusOff = false;
                }else{
                    System.out.printf("Bạn nhập sai tài khoản mật khẩu. Bạn còn %d lần nhập\n", count);
                }
            } else {
                System.out.print("Bạn đã nhập quá số lần quy định! Mời bạn thoát app và đợi 5 phút.");
                break;
            }
        }
    }

    public boolean checkLogin(String userName, String passWord, String status){
        List<Account> accountList = accountDAO.getAll();
        for (Account a : accountList) {
            if (userName.equalsIgnoreCase(a.getUserName())
                    && passWord.equalsIgnoreCase(a.getPassWord())
                    && status.equalsIgnoreCase(a.getStatus())) {
                System.out.print("Chào mừng bạn đến với hệ thống nhân viên của công ty");
                a.setUserName(a.getUserName());
                a.setPassWord(a.getPassWord());
                a.setStatus("onl");
                accountDAO.update(a,a.getUserName());
                return true;
            }else if (userName.equalsIgnoreCase(a.getUserName())
                    && passWord.equalsIgnoreCase(a.getPassWord())
                    && !status.equalsIgnoreCase(a.getStatus())){
                statusOff = true;
                return false;
            }
        }
        return false;
    }

    private void register(Scanner in) {
        String userName = "";
        String passWord = "";

        List<Account> accountList = accountDAO.getAll();

        boolean checkUserName = false;
        while(true){
            System.out.print("\tNhập Username : ");
            userName = in.nextLine();
            checkUserName = false;
            for (Account a : accountList) {
                if(userName.equalsIgnoreCase(a.getUserName())){
                    System.out.println("Tài khoản này đã tồn tại");
                    checkUserName = true;
                    break;
                }
            }
            if(!checkUserName)break;
        }
        if(!checkUserName ){
            System.out.print("\tNhập PassWord: ");
            passWord = in.nextLine();

            if(checkRegister(userName, passWord, in))   loginSuccess = true;

        }
    }
    public int randomNumberCode(){
        Random rd = new Random();
        //int codeResgister = rd.nextInt(2000)+1000;
        int codeResgister = 1234;
        return codeResgister;
    }

    public boolean checkRegister(String userName, String passWord, Scanner in){
        int codeResgister = randomNumberCode();
        Account a = new Account();
        CodeRegister codeRes = new CodeRegister();
        codeRes.setUserName(userName);
        codeRes.setPassWord(passWord);
        codeRes.setCode(codeResgister);
        codeResDAO.insert(codeRes);

        System.out.print("Mã kích hoạt đã gửi về số điện thoại của hr. Vui lòng nhập mã: ");
        int code = 0;
        try {
            code = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return false;
        }

        if (userName.equalsIgnoreCase(codeRes.getUserName())
                && passWord.equalsIgnoreCase(codeRes.getPassWord())
                && code == codeRes.getCode()) {

            codeResDAO.delete(userName);

            a.setUserName(userName);
            a.setPassWord(passWord);
            a.setStatus("onl");

            System.out.print("\tĐăng ký thành công!!! ");
            accountDAO.insert(a);

        }else{
            System.out.print("\tNhập mã sai. Đăng ký thất bại!!! ");
            codeResDAO.delete(userName);
            return false;
        }
        return true;
    }


    public void menuLoginAndRegister(){
        boolean stopLogin = false;
        Scanner in = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("-----CHÀO MỪNG BẠN ĐẾN VỚI APP QUẢN LÝ NHÂN VIÊN----------");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí tài khoản");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựu chọn: ");

            try {
                option = Integer.parseInt(in.nextLine());

            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }
            if (option < 0 || option > 2) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }
            switch (option) {
                case 1:
                    System.out.println("\t\t\tĐăng nhập");
                    stopLogin = true;
                    login(in);
                    break;
                case 2:
                    System.out.println("\t\t\tĐăng ký tài khoản");
                    stopLogin = true;
                    register(in);
                    break;
            }
        }
        while (option != 0 && !stopLogin);
    }

}
