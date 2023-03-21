package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MemberDept {
    private DeptDAO deptDAO = new DeptDAO();
    private StaffDAO staffDAO = new StaffDAO();

    //private List<Department> listDept = deptDAO.getAll();

    public void memberDepartment(Scanner in) {

        System.out.print("\t\tNhập mã phòng ban: ");
        int id;
        try {
            id = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        Department dept = deptDAO.getById(id);
        if(dept==null){
            System.out.println("Không có phòng ban này!!!");
            return;
        }

        int option = -1;

        do {
            System.out.println("\t\t\t1. Xem tất cả thành viên trong phòng ban");
            System.out.println("\t\t\t2. Đổi trưởng phòng");
            System.out.println("\t\t\t0. Thoát");
            System.out.printf("\t\t\tMời nhập lựa chọn: ");

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
                    List<Staff> staffList = staffDAO.innerJoinMemberDept(id);
                    Staff sLead = null;

                    for (Staff s:staffList) {
                        if(s.getStaffId() == s.getLeadDept()){
                            sLead = s;
                        }
                    }

                    System.out.format("+------------------------------------------------------------------------------------------------------------------------------------------------+%n");
                    System.out.format("|                                                        Tất cả nhân viên trong phòng ban                                                        |%n");
                    String leftAlignFormat = "| %-11d | %-22s | %-9d | %-28s | %-25d | %-21s | %-12d | %n";
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");
                    System.out.format("|Mã nhân viên |     Tên nhân viên      | Giới tính |             Mail             |       Số điện thoại       |        Ngày vào       |    Lương     |%n");
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");
                    for (int i = 0; i < staffList.size(); i++) {
                        System.out.format(leftAlignFormat, staffList.get(i).getStaffId(), staffList.get(i).getFullName(), staffList.get(i).getGender(),
                                staffList.get(i).getMail(), staffList.get(i).getPhone(), staffList.get(i).getHireDate(), staffList.get(i).getSalary());
                    }
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");

                    System.out.printf("\tTổng có %d nhân viên phòng %s\n", staffList.stream().count(), staffList.get(0).getNameDept());
                    if (sLead == null) {
                        System.out.println("Phòng này chưa có trưởng phòng");
                        break;
                    }
                    System.out.println("\tTrưởng phòng là: "+ sLead.getFullName());
                    break;
                case 2:

                    break;
            }
        }
        while (option != 0 );

    }

}
