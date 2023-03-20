import dao.AccountDAO;
import dao.DeptDAO;
import dao.StaffDAO;
import model.Account;
import model.Department;
import model.Staff;
import subApp.LoginAndRegister;
import subApp.Option3;
import subApp.ShowAllStaffAndDept;

import java.util.*;

public class App {

    private static void mainMenu(){
        System.out.println("\n--------BẠN HÃY CHỌN ĐIỀU BẠN MONG MUỐN--------");
        System.out.println("1. Danh sách tất cả nhân viên trong công ty hoặc tìm kiếm 1 nhân viên bất kỳ");
        System.out.println("2. Danh sách các phòng ban trong công ty");
        System.out.println("3. Thêm, sửa, xóa thông tin cho nhân viên hoặc phòng ban");
        System.out.println("4. Thêm, xóa nhân viên khỏi phòng ban");
        System.out.println("5. Chuyển phòng ban cho nhân viên");
        System.out.println("6. Thuế thu nhập cá nhân của nhân viên theo mã nhân viên");
        System.out.println("7. Xem thông tin 5 người mới vào công ty");
        System.out.println("8. Số lượng và tất cả thành viên của 1 phòng ban");
        System.out.println("9. Hiển thị tất cả trưởng phòng");
        System.out.println("0. Thoát");
        System.out.printf("\tMời nhập lựa chọn: ");
    }


    public static void main(String[] args) {

        //Login and Register
        LoginAndRegister logAndReg = new LoginAndRegister();
        logAndReg.menuLoginAndRegister();
        boolean loginSuccess = logAndReg.loginSuccess;

        //function after login

        ShowAllStaffAndDept opt1A2 = new ShowAllStaffAndDept();
        Option3 option3 = new Option3();
        if(loginSuccess){
            Scanner in = new Scanner(System.in);
            int option = -1;

            do {
                mainMenu();
                try {
                    option = Integer.parseInt(in.nextLine());

                } catch (Exception e) {
                    System.out.println("Nhập sai định dạng");
                    continue;
                }
                if (option < 0 || option > 9) {
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
                }
                switch (option) {
                    case 1:
                        opt1A2.option1(in);
                        break;
                    case 2:
                        opt1A2.option2();
                        break;
                    case 3:
                        option3.menu(in);
                        break;
                }
            }
            while (option != 0 );
            in.close();
        }

    }

}
