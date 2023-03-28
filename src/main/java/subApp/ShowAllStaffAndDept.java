package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ShowAllStaffAndDept {
    private Util util = new Util();
    public void option1(Scanner in) {
        StaffDAO staffDAO= new StaffDAO();
        List<Staff> staffList = staffDAO.getAll();

        int option = -1;

        do {
            System.out.println("\n\t--------BẠN MUỐN SẮP XẾP DANH SÁCH NHÂN VIÊN THEO--------");
            System.out.println("\t1. ID");
            System.out.println("\t2. Theo tên (Bản chữ cái)");
            System.out.println("\t3. Sắp xếp theo lương");
            System.out.println("\t4. Tìm kiếm 1 nhân viên bất kì theo mã nhân viên");
            System.out.println("\t0. Thoát");
            System.out.printf("\tMời nhập lựa chọn: ");

            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }
            if (option < 0 || option > 4) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }
            switch (option) {
                case 1:
                    System.out.println("Danh sách và thông tin tất cả nhân viên trong công ty");
                    System.out.printf("%-10s %-30s %-10s %-30s %-20s %-20s %-10s %-10s", "Mã ", "Tên", "Giới tính", "Mail", "Phone","Ngày gia nhập", "Lương", "Mã phòng ban");
                    System.out.println();
                    for (int i = 0; i < staffList.size(); i++) {
                        Staff s = staffList.get(i);

                        DateFormat dateFormat = null;
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String tmp = dateFormat.format(s.getHireDate());

                        String gender = null;
                        if(s.getGender() ==1 ) gender = "Nam";
                        if(s.getGender() ==0 ) gender = "Nữ";

                        System.out.printf("%-10d %-30s %-10s %-30s %-20s %-20s %-10d %-10d \n", s.getStaffId(), s.getFullName(), gender, s.getMail(), s.getPhone(), tmp, s.getSalary(), s.getDepartmentId());
                    }
                    break;
                case 2:
                    System.out.println("Danh sách và thông tin tất cả nhân viên trong công ty");
                    staffList.stream().sorted((o1, o2) -> {
                        return  o1.getFullName().compareTo(o2.getFullName());
                    }).forEach(System.out::println);

                    break;
                case 3:
                    System.out.println("Danh sách và thông tin tất cả nhân viên trong công ty");
                    Collections.sort(staffList);
                    System.out.printf("%-10s %-30s %-10s %-30s %-20s %-20s %-10s %-10s", "Mã ", "Tên", "Giới tính", "Mail", "Phone","Ngày gia nhập", "Lương", "Mã phòng ban");
                    System.out.println();
                    for (int i = 0; i < staffList.size(); i++) {
                        Staff s = staffList.get(i);

                        DateFormat dateFormat = null;
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String tmp = dateFormat.format(s.getHireDate());

                        String gender = null;
                        if(s.getGender() ==1 ) gender = "Nam";
                        if(s.getGender() ==0 ) gender = "Nữ";

                        System.out.printf("%-10d %-30s %-10s %-30s %-20s %-20s %-10d %-10d \n", s.getStaffId(), s.getFullName(), gender, s.getMail(), s.getPhone(), tmp, s.getSalary(), s.getDepartmentId());
                    }
                    break;
                case 4:
                    System.out.print("\t\tNhập mã nhân viên người bạn cần tìm: ");
                    int id = util.scannerIdStaff(in);
                    if(id < 1) return;
                    Staff sId = util.checkStaffId(id);
                    if(sId == null) return;
                    System.out.println(sId);
                    break;
            }
        }
        while (option != 0 );
    }

    public int option2() {
        DeptDAO deptDAO= new DeptDAO();
        List<Department> deptList = deptDAO.getAll();
        if(deptList.size() ==0) return -1;
        System.out.format("+--------------------------------------------------------+%n");
        System.out.format("|              Tất cả phòng ban trong công ty            |%n");
        String leftAlignFormat = "| %-11d | %-22s | %-15d | %n";
        System.out.format("+-------------+------------------------+-----------------+%n");
        System.out.format("|Mã phòng ban |     Tên phòng ban      | Id trưởng phòng |%n");
        System.out.format("+-------------+------------------------+-----------------+%n");
        for (int i = 0; i < deptList.size(); i++) {
            System.out.format(leftAlignFormat, deptList.get(i).getDeptId(), deptList.get(i).getDeptName(), deptList.get(i).getDeptHeadId());
        }
        System.out.format("+-------------+------------------------+-----------------+%n");
        return deptList.size();
    }
}
