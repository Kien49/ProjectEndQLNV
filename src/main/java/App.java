
import dao.AccountDAO;
import dao.DeptDAO;
import dao.StaffDAO;
import model.Account;
import model.Department;
import model.Staff;
import subApp.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {
    private static StaffDAO staffDAO= new StaffDAO();
    private static DeptDAO deptDAO= new DeptDAO();
    private static Util util = new Util();


    public static double taxOfStaff(Scanner in, int id){
        if(id < 1) return -1;
        Staff sId = util.checkStaffId(id);
        if(sId == null) return -1;
        System.out.print("\t\tNhập thu nhập ngoài của người này: ");
        int thuNhapNgoai = 0;
        try {
            thuNhapNgoai = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
        }
        if(thuNhapNgoai < 0){
            System.out.println("Thu nhập ngoài không âm!!!");
            thuNhapNgoai =0;
        }

        double tax = 0.02 * (sId.getSalary() + thuNhapNgoai);
        System.out.format("+-------------------------------------------------------------+%n");
        System.out.format("|                      Thuế của nhân viên                     |%n");
        String leftAlignFormat = "| %-16s | %-12d | %-12d | %-10.1f |%n";
        System.out.format("+------------------+--------------+--------------+------------+%n");
        System.out.format("|  Tên nhân viên   | Mã phòng ban |    Lương     |    Thuế    |%n");
        System.out.format("+------------------+--------------+--------------+------------+%n");
        System.out.format(leftAlignFormat, sId.getFullName(), sId.getDepartmentId(), sId.getSalary(), tax);
        System.out.format("+------------------+--------------+--------------+------------+%n");
        return tax;
    }

    public static int showLeadDepartment(){
        System.out.format("+----------------------------------------------------------------------------------------+%n");
        System.out.format("|                              Tất cả trưởng phòng của công ty                           |%n");
        String leftAlignFormat = "| %-12d | %-19s | %-12d | %-21s | %-10d |%n";
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");
        System.out.format("| Mã nhân viên |  Tên trưởng phòng   | Mã phòng ban |     Tên phòng ban     |    Lương   |%n");
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");

        List<Department> deptListInnerJoin = deptDAO.innerJoinIn4HeadDept();
        if(deptListInnerJoin.size() == 0) return -1;
        for (int i = 0; i < deptListInnerJoin.size(); i++) {
            System.out.format(leftAlignFormat, deptListInnerJoin.get(i).getDeptHeadId(), deptListInnerJoin.get(i).getNameLead(),deptListInnerJoin.get(i).getDeptId(), deptListInnerJoin.get(i).getDeptName(), deptListInnerJoin.get(i).getSalaryLead());
        }
        System.out.format("+--------------+---------------------+--------------+-----------------------+------------+%n");
        return deptListInnerJoin.size();
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
/*        Scanner in = new Scanner(System.in);
        System.out.print("\t\t\tNhập lần lượt ngày, tháng, năm gia nhập công ty dd/mm/yy: ");
        String dd, mm, yy;
        System.out.print("\n\t\t\t\tNgày: ");
        dd = in.nextLine();
        System.out.print("\t\t\t\tTháng: ");
        mm = in.nextLine();
        System.out.print("\t\t\t\tNăm: ");
        yy = in.nextLine();
        Date date = util.checkDateScanner(dd,mm,yy);
        if(date == null) return;
        System.out.println(date);
        DateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String tmp = dateFormat.format(date);
        System.out.println(tmp);*/

        //Login and Register
        LoginAndRegister logAndReg = new LoginAndRegister();
        logAndReg.menuLoginAndRegister();
        boolean loginSuccess = logAndReg.loginSuccess;

        //function after login

        ShowAllStaffAndDept opt1A2 = new ShowAllStaffAndDept();
        Option3 option3 = new Option3();
        MemberDept memberDept = new MemberDept();
        StaffAndDept sAD = new StaffAndDept();

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
                    case 1:
                        opt1A2.option1(in);
                        break;
                    case 2:
                        opt1A2.option2();
                        break;
                    case 3:
                        option3.menu(in);
                        break;
                    case 4:
                        sAD.addDeleteStaffofDept(in);
                        break;
                    case 5:
                        sAD.swapDept(in);
                        break;
                    case 6:
                        System.out.print("\t\tNhập mã nhân viên người bạn cần xem thuế: ");
                        int id = util.scannerIdStaff(in);
                        double tax = taxOfStaff(in, id);
                        if(tax < 0) break;
                        break;
                    case 7:
                        List<Staff> lastListHireDate =  staffDAO.lastHireDate();
                        System.out.printf("%-10s %-30s %-10s %-30s %-20s %-20s %-10s %-10s", "Mã ", "Tên", "Giới tính", "Mail", "Phone","Ngày gia nhập", "Lương", "Mã phòng ban");
                        System.out.println();
                        for (int i = 0; i < lastListHireDate.size(); i++) {
                            Staff s = lastListHireDate.get(i);

                            DateFormat dateFormat = null;
                            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String tmp = dateFormat.format(s.getHireDate());

                            String gender = null;
                            if(s.getGender() ==1 ) gender = "Nam";
                            if(s.getGender() ==0 ) gender = "Nữ";

                            System.out.printf("%-10d %-30s %-10s %-30s %-20s %-20s %-10d %-10d \n", s.getStaffId(), s.getFullName(), gender, s.getMail(), s.getPhone(), tmp, s.getSalary(), s.getDepartmentId());
                        }
                        break;
                    case 8:
                        memberDept.memberDepartment(in);
                        break;
                    case 9:
                        showLeadDepartment();
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
