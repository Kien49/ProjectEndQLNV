package subApp;

import dao.AccountDAO;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class LoginAndRegister {
    private AccountDAO accountDAO = new AccountDAO();
    public boolean loginSuccess = false;

    private void login(Scanner in) {
        List<Account> accountList = accountDAO.getAll();

        String userName = "";
        String passWord = "";
        String status = "off";

        int count = 3;
        boolean checkOutWhile = true;
        boolean checkLoginFalse = true;
        while (checkOutWhile) {
            if (count > 0) {
                System.out.print("\tNhập Username: ");
                userName = in.nextLine();
                System.out.print("\tNhập PassWord: ");
                passWord = in.nextLine();
                count--;

                checkLoginFalse = true;
                for (Account a : accountList) {
                    if (userName.equalsIgnoreCase(a.getUserName())
                            && passWord.equalsIgnoreCase(a.getPassWord())
                            && status.equalsIgnoreCase(a.getStatus())) {
                        System.out.print("Chào mừng bạn đến với hệ thống nhân viên của công ty");
                        a.setUserName(a.getUserName());
                        a.setPassWord(a.getPassWord());
                        a.setStatus("onl");
                        accountDAO.update(a,a.getUserName());
                        checkOutWhile = false;
                        loginSuccess = true;
                        break;
                    }else if (userName.equalsIgnoreCase(a.getUserName())
                            && passWord.equalsIgnoreCase(a.getPassWord()) && !status.equalsIgnoreCase(a.getStatus()) && checkLoginFalse) {
                        checkLoginFalse = false;
                        System.out.printf("Tài khoản đang được sử dụng. Bạn còn %d lần nhập\n", count);
                        break;
                    }
                }
                if(checkOutWhile && checkLoginFalse){
                    System.out.printf("Bạn nhập sai tài khoản mật khẩu. Bạn còn %d lần nhập\n", count);
                }

            } else {
                System.out.print("Bạn đã nhập quá số lần quy định! Mời bạn thoát app và đợi 5 phút.");
                break;
            }
        }
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
            if(!checkUserName){
                break;
            }
        }
        if(!checkUserName ){
            Account a = new Account();
            System.out.print("\tNhập PassWord: ");
            passWord = in.nextLine();

            a.setUserName(userName);
            a.setPassWord(passWord);
            a.setStatus("onl");

            System.out.print("\tĐăng ký thành công!!! ");
            accountDAO.insert(a);
            loginSuccess = true;
        }
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
