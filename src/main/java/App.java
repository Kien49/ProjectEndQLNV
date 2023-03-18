import dao.AccountDAO;
import model.Account;
import subApp.LoginAndRegister;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        //Login and Register
        LoginAndRegister logAndReg = new LoginAndRegister();
        logAndReg.menuLoginAndRegister();
        boolean loginSuccess = logAndReg.loginSuccess;

        Scanner in = new Scanner(System.in);

        if(loginSuccess){
            int optio = -1;

            do {
                System.out.println("\n-----kkkkkkkkk ----------");
                System.out.println("1. Đăng nhập");
                System.out.println("2. Đăng kí tài khoản");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựu chọn: ");

                try {
                    optio = Integer.parseInt(in.nextLine());

                } catch (Exception e) {
                    System.out.println("Nhập sai định dạng");
                    continue;
                }
                if (optio < 0 || optio > 2) {
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
                }
                switch (optio) {
                    case 1:

                        break;
                    case 2:
                        break;
                }
            }
            while (optio != 0 );
        }

    }

}
