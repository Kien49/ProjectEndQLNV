package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

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
        String mail = in.nextLine();

        if(!util.checkMail(mail, staffList)){
            return;
        }
        else{
            s.setMail(mail);
        }

        System.out.print("\t\t\tNhập tên sinh viên: ");
        String name = in.nextLine();
        if(!util.checkNameScanner(name)){
            return;
        }
        else{
            s.setFullName(name);
        }

        System.out.print("\t\t\tNhập giới tính: ");
        String gender = in.nextLine();
        int numberGender = util.checkGenderScanner(gender);
        if(numberGender == -1){
            return;
        }
        s.setGender(numberGender);

        System.out.print("\t\t\tNhập số điện thoại: ");
        int sdt = 0;
        try {
            sdt = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setPhone(sdt);

        Date date = util.checkDateScanner(in);
        if(date == null){
            return;
        }
        s.setHireDate(date);


        System.out.print("\t\t\tNhập lương nhân viên: ");
        int salary;
        try {
            salary = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if(salary<=0){
            System.out.println("Lương không là số âm");
            return;
        }
        s.setSalary(salary);

        System.out.println("\t\tDanh phòng ban của công ty");
        System.out.println("0: Để trống");
        int numberDept = util.chooseDepartment(in);
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        int idDept = deptList.get(numberDept-1).getDeptId();
        if(numberDept!=0){
            s.setDepartmentId(idDept);
            staffDAO.insert(s);
        }
        else{
            s.setDepartmentId(idDept);
            staffDAO.insertDeptIdNull(s);
        }

        System.out.println("Thêm nhân viên thành công");
    }

    private void fixEmployee(Scanner in){
        List<Staff> staffList = staffDAO.getAll();
        System.out.print("\t\t\tNhập mã nhân viên người bạn cần sửa: ");

        Staff sId = util.checkStaffId(in);
        if(sId == null){
            return;
        }
        System.out.println("\t\t\tThông tin của nhân viên này: ");
        System.out.println(sId);
        System.out.println("\tNhập thông tin mới cho nhân viên!");

        Staff s = new Staff();

        System.out.print("\t\tNhập email muốn sửa : ");
        String mail = in.nextLine();

        if(!util.checkMailHaveID(mail, sId, staffList)){
            return;
        }
        else{
            s.setMail(mail);
        }

        System.out.print("\t\tNhập tên sinh viên: ");
        String name = in.nextLine();
        if(!util.checkNameScanner(name)){
            return;
        }
        else{
            s.setFullName(name);
        }

        System.out.print("\t\tNhập giới tính: ");
        String gender = in.nextLine();
        int numberGender = util.checkGenderScanner(gender);
        if(numberGender == -1){
            return;
        }
        s.setGender(numberGender);

        System.out.print("\t\tNhập số điện thoại: ");
        int sdt;
        try {
            sdt = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setPhone(sdt);

        Date date = util.checkDateScanner(in);
        if(date == null){
            return;
        }
        s.setHireDate(date);


        System.out.print("\t\tNhập lương nhân viên: ");
        int salary;
        try {
            salary = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if(salary <=0 ){
            System.out.println("Lương không là số âm");
            return;
        }
        s.setSalary(salary);


        staffDAO.updateIdDeptNull(s, sId.getStaffId());
        System.out.println("Cập nhật thông tin nhân viên thành công");
    }

    private void deleteEmployee(Scanner in){
        System.out.print("\t\t\tNhập mã nhân viên muốn xóa khỏi công ty: ");

        Staff sId = util.checkStaffId(in);
        if(sId == null){
            return;
        }

        staffDAO.delete(sId.getStaffId());
        System.out.print("Xóa thành công!");
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

        if(!util.checkNameDept(name, deptList)){
            return;
        }
        else{
            d.setDeptName(name);
        }
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
        if(numberDept == 0){
            return;
        }
        int idDept = deptList.get(numberDept-1).getDeptId();

        Department d = new Department();
        System.out.print("\t\t\tNhập tên phòng ban: ");
        String name = in.nextLine();
        if(!util.checkNameDept(name, deptList)){
            return;
        }
        else{
            d.setDeptName(name);
        }
        deptDAO.updateIdName(d, idDept);

        System.out.println("Cập nhật thông tin phòng ban thành công");
    }
    private void deleteDepartment(Scanner in){
        List<Department> deptList = deptDAO.getAll();
        System.out.println("\t\tDanh phòng ban của công ty");
        System.out.println("0: Thoát");
        int numberDept = util.chooseDepartment(in);
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        if(numberDept == 0){
            return;
        }
        int idDept = deptList.get(numberDept-1).getDeptId();

        deptDAO.delete(idDept);
        System.out.print("Xóa thành công!");
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
