package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MemberDept {
    private DeptDAO deptDAO = new DeptDAO();
    private StaffDAO staffDAO = new StaffDAO();
    private Util util = new Util();

    public void memberDepartment(Scanner in) {
        List<Department> listDept = deptDAO.getAll();
        System.out.println("\t\tDanh phòng ban của công ty");
        int numberDept = util.chooseDepartment(in);

        if(numberDept< 1 || numberDept> listDept.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        int idDept = listDept.get(numberDept-1).getDeptId();

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
            List<Staff> staffList = staffDAO.innerJoinMemberDept(idDept);
            switch (option) {
                case 1:
                    if(staffList.size() == 0){
                        System.out.println("Phòng này chưa có nhân viên!!!");
                        break;
                    }
                    Staff sLead = null;
                    for (Staff s:staffList) {
                        if(s.getStaffId() == s.getLeadDept()){
                            sLead = s;
                        }
                    }
                    System.out.format("+----------------------------------------------------------------------------------------------------------------------------------------------------+%n");
                    System.out.format("|                                                        Tất cả nhân viên trong phòng ban                                                            |%n");
                    String leftAlignFormat = "| %-11d | %-22s | %-9d | %-28s | %-25s | %-21s | %-12d | %n";
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");
                    System.out.format("|Mã nhân viên |     Tên nhân viên      | Giới tính |             Mail             |       Số điện thoại       |        Ngày vào       |    Lương     |%n");
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");
                    for (int i = 0; i < staffList.size(); i++) {
                        DateFormat dateFormat = null;
                        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        String tmp = dateFormat.format(staffList.get(i).getHireDate());

                        System.out.format(leftAlignFormat, staffList.get(i).getStaffId(), staffList.get(i).getFullName(), staffList.get(i).getGender(),
                                staffList.get(i).getMail(), staffList.get(i).getPhone(), tmp , staffList.get(i).getSalary());
                    }
                    System.out.format("+-------------+------------------------+-----------+------------------------------+---------------------------+-----------------------+--------------+%n");

                    System.out.printf("\tTổng có %d nhân viên phòng '%s'\n", staffList.stream().count(), staffList.get(0).getNameDept());
                    if (sLead == null) {
                        System.out.println("Phòng này chưa có trưởng phòng");
                        break;
                    }
                    System.out.println("\tTrưởng phòng là: "+ sLead.getFullName());
                    break;
                case 2:
                    swapLeadDept(in, idDept);
            }
        }
        while (option != 0 );

    }
    public boolean swapLeadDept(Scanner in, int idDept){
        List<Staff> staffList = staffDAO.innerJoinMemberDept(idDept);
        System.out.print("\t\t\t\t\tNhập mã nhân viên của trưởng phòng mới: ");
        int idLeadNew;

        try {
            idLeadNew = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return false;
        }
        Staff sChecklead = null;
        for (Staff s:staffList) {
            if(s.getStaffId() == idLeadNew){
                sChecklead = s;
            }
        }
        if(sChecklead==null){
            System.out.println("Nhân viên không có trong phòng ban này!!!");
            return false;
        }
        Department deptChangeLead = new Department();
        deptChangeLead.setDeptHeadId(idLeadNew);

        deptDAO.updateIdLead(deptChangeLead, idDept);
        System.out.println("Đổi trưởng phòng thành công!!!");
        return true;
    }

}
