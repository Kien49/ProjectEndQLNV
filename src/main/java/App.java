
import dao.AccountDAO;
import dao.DeptDAO;
import dao.StaffDAO;
import model.Account;
import model.Department;
import model.Staff;
import subApp.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {
    private static StaffDAO staffDAO= new StaffDAO();
    private static DeptDAO deptDAO= new DeptDAO();
    private static Util util = new Util();


    private static void taxOfStaff(Scanner in){
        System.out.print("\t\tNhập mã nhân viên người bạn cần xem thuế: ");
        Staff sId = util.checkStaffId(in);
        if(sId == null){
            return;
        }
        double tax = 0.2 * (sId.getSalary() - sId.getSalary() * 0.93);
        System.out.format("+-------------------------------------------------------------+%n");
        System.out.format("|                      Thuế của nhân viên                     |%n");
        String leftAlignFormat = "| %-16s | %-12d | %-12d | %-10.1f |%n";
        System.out.format("+------------------+--------------+--------------+------------+%n");
        System.out.format("|  Tên nhân viên   | Mã phòng ban |    Lương     |    Thuế    |%n");
        System.out.format("+------------------+--------------+--------------+------------+%n");
        System.out.format(leftAlignFormat, sId.getFullName(), sId.getDepartmentId(), sId.getSalary(), tax);
        System.out.format("+------------------+--------------+--------------+------------+%n");
    }

    private static void showLeadDepartment(Scanner in){
        System.out.format("+----------------------------------------------------------------------------------------+%n");
        System.out.format("|                              Tất cả trưởng phòng của công ty                           |%n");
        String leftAlignFormat = "| %-12d | %-19s | %-12d | %-21s | %-10d |%n";
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");
        System.out.format("| Mã nhân viên |  Tên trưởng phòng   | Mã phòng ban |     Tên phòng ban     |    Lương   |%n");
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");

        List<Department> deptListInnerJoin = deptDAO.innerJoinIn4HeadDept();
        for (int i = 0; i < deptListInnerJoin.size(); i++) {
            System.out.format(leftAlignFormat, deptListInnerJoin.get(i).getDeptHeadId(), deptListInnerJoin.get(i).getNameLead(),deptListInnerJoin.get(i).getDeptId(), deptListInnerJoin.get(i).getDeptName(), deptListInnerJoin.get(i).getSalaryLead());
        }
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");
    }


    private static void mainMenu(){
        System.out.println("\n--------BẠN HÃY CHỌN ĐIỀU BẠN MONG MUỐN--------");
        System.out.println("1. Danh sách tất cả nhân viên trong công ty hoặc tìm kiếm 1 nhân viên bất kỳ");
        System.out.println("2. Danh sách các phòng ban trong công ty");
        System.out.println("3. Thêm, sửa, xóa thông tin cho nhân viên hoặc phòng ban");
        System.out.println("4. Thêm, xóa nhân viên khỏi phòng ban");
        System.out.println("5. Chuyển phòng ban cho nhân viên");
        System.out.println("6. Thuế thu nhập cá nhân của nhân viên theo mã nhân viên");
        System.out.println("7. Xem thông tin 5 người mới vào công ty");
        System.out.println("8. Tất cả thành viên của 1 phòng ban, đổi trưởng phòng cho phòng ban đó");
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
        MemberDept memberDept = new MemberDept();

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
                    System.out.println("Lựa chọn không hợp lệ!!!");
                    continue;
                }
                switch (option) {
                    case 1:         //done
                        opt1A2.option1(in);
                        break;
                    case 2:         //done
                        opt1A2.option2();
                        break;
                    case 3:         //done
                        option3.menu(in);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:         //done
                        taxOfStaff(in);
                        break;
                    case 7:         //done
                        List<Staff> lastListHireDate =  staffDAO.lastHireDate();
                        for (Staff s: lastListHireDate) {
                            System.out.println(s);
                        }
                        break;
                    case 8:         //done
                        memberDept.memberDepartment(in);
                        break;
                    case 9:         //done
                        showLeadDepartment(in);
                        break;
                }
            }
            while (option != 0 );
            AccountDAO accountDAO= new AccountDAO();
            Account a = new Account();
            a.setUserName(logAndReg.userNameNow);
            a.setPassWord(logAndReg.userNameNow);
            a.setStatus("off");
            accountDAO.update(a, logAndReg.userNameNow);
            in.close();
        }

    }

}
