package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Option3 {
    private StaffDAO staffDAO = new StaffDAO();
    private DeptDAO deptDAO = new DeptDAO();
    private Util util = new Util();


//Emloyee
    private void addEmployee(Scanner in) {
        Staff s = new Staff();
        List<Staff> staffList = staffDAO.getAll();
        List<Department> deptList = deptDAO.getAll();

        System.out.print("\t\t\tNhập email muốn thêm : ");
        String mailScanner = in.nextLine();
        String mail = util.checkMail(mailScanner, staffList);
        if(mail == null) return;
        s.setMail(mail);

        System.out.print("\t\t\tNhập tên sinh viên: ");
        String nameScanner = in.nextLine();
        String name = util.checkNameScanner(nameScanner);
        if(name == null) return;
        s.setFullName(name);

        System.out.print("\t\t\tNhập giới tính: ");
        String gender = in.nextLine();
        int numberGender = util.checkGenderScanner(gender);
        if(numberGender == -1) return;
        s.setGender(numberGender);

        System.out.print("\t\t\tNhập số điện thoại: ");
        String sdtScanner = in.nextLine();
        String sdtS = util.checkSdtScanner(sdtScanner);
        if(sdtS == null) return;
        s.setPhone(sdtS);

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
        s.setHireDate(date);

        System.out.print("\t\t\tNhập lương nhân viên: ");
        String salaryS = in.nextLine();
        int salary = util.checkSalaryScanner(salaryS);
        if(salary==0) return;
        s.setSalary(salary);

        System.out.println("\t\tDanh phòng ban của công ty");
        System.out.println("0: Để trống");
        int numberDept = util.chooseDepartment(in);
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        if(numberDept!=0){
            int idDept = deptList.get(numberDept-1).getDeptId();
            s.setDepartmentId(idDept);
            staffDAO.insert(s);
            return;
        }
        staffDAO.insertDeptIdNull(s);

        System.out.println("Thêm nhân viên thành công");
    }

    private void fixEmployee(Scanner in){
        List<Staff> staffList = staffDAO.getAll();
        System.out.print("\t\t\tNhập mã nhân viên người bạn cần sửa: ");
        int id = util.scannerIdStaff(in);
        if(id < 1) return;
        Staff sId = util.checkStaffId(id);
        if(sId == null) return;

        System.out.println("\t\t\tThông tin của nhân viên này: ");
        String gender1 = null;
        if(sId.getGender() ==1 ) gender1= "Nam";
        if(sId.getGender() ==0 ) gender1 = "Nữ";
        DateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tmp = dateFormat.format(sId.getHireDate());
        System.out.println();
        System.out.printf("%-10s %-30s %-10s %-30s %-20s %-20s %-10s %-10s", "Mã ", "Tên", "Giới tính", "Mail", "Phone","Ngày gia nhập", "Lương", "Mã phòng ban");
        System.out.println();
        System.out.printf("%-10d %-30s %-10s %-30s %-20s %-20s %-10d %-10d \n", sId.getStaffId(), sId.getFullName(), gender1, sId.getMail(), sId.getPhone(), tmp, sId.getSalary(), sId.getDepartmentId());
        System.out.println();

        System.out.println("\tNhập thông tin mới cho nhân viên!");

        Staff s = new Staff();

        System.out.print("\t\tNhập email muốn sửa : ");
        String mailScanner = in.nextLine();
        String mail = util.checkMailHaveID(mailScanner, sId, staffList);
        if(mail == null) return;
        s.setMail(mail);

        System.out.print("\t\tNhập tên sinh viên: ");
        String nameScanner = in.nextLine();
        String name = util.checkNameScanner(nameScanner);
        if(name == null) return;
        s.setFullName(name);

        System.out.print("\t\tNhập giới tính: ");
        String gender = in.nextLine();
        int numberGender = util.checkGenderScanner(gender);
        if(numberGender == -1) return;
        s.setGender(numberGender);

        System.out.print("\t\tNhập số điện thoại: ");
        String sdtScanner = in.nextLine();
        String sdtS = util.checkSdtScanner(sdtScanner);
        if(sdtS == null) return;
        s.setPhone(sdtS);

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
        s.setHireDate(date);

        System.out.print("\t\t\tNhập lương nhân viên: ");
        String salaryS = in.nextLine();
        int salary = util.checkSalaryScanner(salaryS);
        if(salary==0) return;
        s.setSalary(salary);

        staffDAO.updateIdDeptNull(s, sId.getStaffId());
        System.out.println("Cập nhật thông tin nhân viên thành công");
    }

    private void deleteEmployee(Scanner in){
        System.out.print("\t\t\tNhập mã nhân viên muốn xóa khỏi công ty: ");
        int id = util.scannerIdStaff(in);
        if(id < 1) return;
        Staff sId = util.checkStaffId(id);
        if(sId == null) return;
        if(sId.getDepartmentId() == 0){
            staffDAO.delete(sId.getStaffId());
            System.out.print("Xóa thành công!");
            return;
        }
        List<Staff> staffList = staffDAO.innerJoinMemberDept(sId.getDepartmentId());
        Staff sLead = null;

        for (Staff s:staffList) {
            if(s.getStaffId() == s.getLeadDept()){
                sLead = s;
            }
        }
        if(sLead.getStaffId() != sId.getStaffId()){
            staffDAO.delete(sId.getStaffId());
            System.out.print("Xóa thành công!");
        }
        else{
            System.out.println("Người này là trưởng phòng bạn có muốn xóa không");
            System.out.println("\t\t\t1: Có");
            System.out.println("\t\t\t2: Không");
            System.out.print("\t\t\tNhập lựa chọn: ");
            int option;
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                return;
            }
            if(option ==2) return;

            if(option!=1 && option!=2 ) {
                System.out.println("Không có lựa chọn này!!!");
                return;}
            //System.out.println(sLead);
            deptDAO.deleteLead(sLead.getDepartmentId());
            staffDAO.delete(sId.getStaffId());
            System.out.print("Xóa thành công!");
        }


    }

    private void employee(Scanner in) {
        int option = -1;

        do {
            System.out.println("\n\t\t-----------LÀM VIỆC VỚI NHÂN VIÊN-----------");
            System.out.println("\t\t1. Thêm nhân viên");
            System.out.println("\t\t2. Sửa thông tin nhân viên");
            System.out.println("\t\t3. Xóa nhân viên khỏi công ty");
            System.out.println("\t\t0. Thoát");
            System.out.printf("\t\tMời nhập lựa chọn: ");

            try {
                option = Integer.parseInt(in.nextLine());

            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }
            if (option < 0 || option > 3) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }
            switch (option) {
                case 1:
                    addEmployee(in);
                    break;
                case 2:
                    fixEmployee(in);
                    break;
                case 3:
                    deleteEmployee(in);
                    break;

            }
        }
        while (option != 0 );
    }

//Department
    private void addDepartment(Scanner in) {
        List<Department> deptList = deptDAO.getAll();
        Department d = new Department();

        System.out.print("\t\t\tNhập tên phòng ban mới : ");
        String name = in.nextLine();

        if(!util.checkNameDept(name, deptList)) return;
        d.setDeptName(name);
        deptDAO.insert(d);
        System.out.println("Thêm phòng ban thành công");
    }
    private void fixDepartment(Scanner in){
        List<Department> deptList = deptDAO.getAll();
        System.out.println("\t\tDanh phòng ban của công ty");
        System.out.println("0: Thoát");
        int numberDept = util.chooseDepartment(in);
        System.out.println(deptList.size());
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        if(numberDept == 0) return;
        int idDept = deptList.get(numberDept-1).getDeptId();

        Department d = new Department();
        System.out.print("\t\t\tNhập tên phòng ban: ");
        String name = in.nextLine();
        if(!util.checkNameDept(name, deptList)) return;
        d.setDeptName(name);
        deptDAO.updateIdName(d, idDept);

        System.out.println("Cập nhật thông tin phòng ban thành công");
    }
    public boolean deleteDepartment(Scanner in){
        List<Department> deptList = deptDAO.getAll();
        System.out.println("\t\tDanh phòng ban của công ty");
        System.out.println("0: Thoát");
        int numberDept = util.chooseDepartment(in);
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return false;
        }
        if(numberDept == 0) return false;
        int idDept = deptList.get(numberDept-1).getDeptId();

        List<Staff> staffList = staffDAO.innerJoinMemberDept(idDept);
        if(staffList.size() == 0){
            deptDAO.delete(idDept);
            System.out.print("Xóa phòng ban thành công!");
            return false;
        }
        if(staffList.get(0).getLeadDept() != 0){
            deptDAO.deleteLead(idDept);
        }
        for (int i = 0; i < staffList.size(); i++) {
            staffDAO.deleteDept(staffList.get(i).getStaffId());
        }
        deptDAO.delete(idDept);
        System.out.println("Xóa phòng ban thành công");
        return true;
    }
    private void department(Scanner in) {
        int option = -1;

        do {
            System.out.println("\n\t\t-----------LÀM VIỆC VỚI PHÒNG BAN-----------");
            System.out.println("\t\t1. Thêm phòng ban mới");
            System.out.println("\t\t2. Sửa thông tin phòng ban");
            System.out.println("\t\t3. Xóa phòng ban khỏi công ty");
            System.out.println("\t\t0. Thoát");
            System.out.printf("\t\tMời nhập lựa chọn: ");

            try {
                option = Integer.parseInt(in.nextLine());

            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }
            if (option < 0 || option > 3) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }
            switch (option) {
                case 1:
                    addDepartment(in);
                    break;
                case 2:
                    fixDepartment(in);
                    break;
                case 3:
                    deleteDepartment(in);
                    break;
            }
        }
        while (option != 0 );
    }

    public void menu(Scanner in){
        int option = -1;

        do {
            System.out.println("\n\t--------LÀM VIỆC VỚI NHÂN VIÊN HAY PHÒNG BAN--------");
            System.out.println("\t1. Thêm, sửa, xóa 1 nhân viên bất kì");
            System.out.println("\t2. Thêm, sửa, xóa phòng ban");
            System.out.println("\t0. Thoát");
            System.out.printf("\tMời nhập lựa chọn: ");

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
                    employee(in);
                    break;
                case 2:
                    department(in);
                    break;
            }
        }
        while (option != 0 );
    }
}
