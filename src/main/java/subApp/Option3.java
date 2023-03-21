package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.CodeRegister;
import model.Department;
import model.Staff;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Option3 {
    private StaffDAO staffDAO = new StaffDAO();
    private DeptDAO deptDAO = new DeptDAO();
    private List<Staff> staffList = staffDAO.getAll();
    private List<Department> deptList = deptDAO.getAll();
    private UtilCheckEmpty checkEmpty = new UtilCheckEmpty();


//Emloyee
    private void addEmployee(Scanner in) {
        Staff s = new Staff();


        System.out.print("\t\t\tNhập email muốn thêm : ");
        String mail = in.nextLine();
        for (Staff st: staffList) {
            if(st.getMail().equalsIgnoreCase(mail)){
                System.out.println("Đã có mail này");
                return;
            }
        }
        if(checkEmpty.isEmpty(mail)){
            System.out.println("Email không được để trống");
            return;
        }
        else{
            s.setMail(mail);
        }

        System.out.print("\t\t\tNhập tên sinh viên: ");
        String name = in.nextLine();
        if(checkEmpty.isEmpty(name)){
            System.out.println("Tên không được để trống");
            return;
        }
        else{
            s.setFullName(name);
        }

        System.out.print("\t\t\tNhập giới tính: ");
        String gender = in.nextLine();
        if(gender.equalsIgnoreCase("nam")){
            s.setGender(1);
        } else if(gender.equalsIgnoreCase("nữ") || gender.equalsIgnoreCase("nu")){
            s.setGender(0);
        }else{
            System.out.println("Nhập sai định dạng!!!");
            return;
        }

        System.out.print("\t\t\tNhập số điện thoại: ");
        int sdt = 0;
        try {
            sdt = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setPhone(sdt);

        System.out.print("\t\t\tNhập lần lượt ngày, tháng, năm gia nhập công ty dd/mm/yy: ");

        int dd, mm, yy;

        try {
            System.out.print("\n\t\t\t\tNgày: ");
            dd = Integer.parseInt(in.nextLine());

            System.out.print("\t\t\t\tTháng: ");
            mm = Integer.parseInt(in.nextLine());

            System.out.print("\t\t\t\tNăm: ");
            yy = Integer.parseInt(in.nextLine());


        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if((dd<0 || dd>31) || (mm<0 || mm>12) || (yy<1900 || yy>2023)){
            System.out.println("Nhập sai ngay thang nam");
            return;
        }
        String date = null;
        date = " "+dd+"/"+" "+mm+"/"+" "+yy;
        s.setHireDate(date);


        System.out.print("\t\t\tNhập lương nhân viên: ");
        int salary = 0;
        try {
            salary = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setSalary(salary);


        System.out.print("\t\t\tNhập id phòng ban: ");
        int idDept = 0;
        String dept_id ;
        try {
            dept_id = in.nextLine();
            if(checkEmpty.isEmpty(dept_id)){
                idDept = 0;
            }else{
                idDept = Integer.parseInt(dept_id);
            }

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if(idDept!=0){
            Department deptId = null;
            for (Department dept: deptList) {
                if(dept.getDeptId() == idDept){
                    deptId = dept;
                    break;
                }
            }
            if(deptId == null){
                System.out.println("Không có phòng ban này");
                return;
            }
            else{
                s.setDepartmentId(idDept);
                staffDAO.insert(s);
            }
        }
        else{
            s.setDepartmentId(idDept);
            staffDAO.insertDeptIdNull(s);
        }

        System.out.println("Thêm nhân viên thành công");
    }

    private void fixEmployee(Scanner in){
        System.out.print("\t\t\tNhập mã nhân viên người bạn cần sửa: ");
        int id;
        try {
            id = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        Staff sId = staffDAO.getById(id);
        if(sId==null){
            System.out.println("Không có nhân viên này!!!");
            return;
        }
        System.out.println("\t\t\tThông tin của nhân viên này: ");
        System.out.println(sId);
        System.out.println("\tNhập thông tin mới cho nhân viên!");

        Staff s = new Staff();

        System.out.print("\t\tNhập email muốn sửa : ");
        String mail = in.nextLine();
        if(!sId.getMail().equalsIgnoreCase(mail)){
            for (Staff st: staffList) {
                if(st.getMail().equalsIgnoreCase(mail)){
                    System.out.println("Đã có mail này");
                    return;
                }
            }
        }
        if(checkEmpty.isEmpty(mail)){
            System.out.println("Email không được để trống");
            return;
        }
        else{
            s.setMail(mail);
        }

        System.out.print("\t\tNhập tên sinh viên: ");
        String name = in.nextLine();
        if(checkEmpty.isEmpty(name)){
            System.out.println("Tên không được để trống");
            return;
        }
        else{
            s.setFullName(name);
        }

        System.out.print("\t\tNhập giới tính: ");
        String gender = in.nextLine();
        if(gender.equalsIgnoreCase("nam")){
            s.setGender(1);
        } else if(gender.equalsIgnoreCase("nữ") || gender.equalsIgnoreCase("nu")){
            s.setGender(0);
        }else{
            System.out.println("Nhập sai định dạng!!!");
            return;
        }

        System.out.print("\t\tNhập số điện thoại: ");
        int sdt = 0;
        try {
            sdt = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setPhone(sdt);

        System.out.print("\t\tNhập lần lượt ngày, tháng, năm gia nhập công ty dd/mm/yy: ");

        int dd, mm, yy;

        try {
            System.out.print("\n\t\t\tNgày: ");
            dd = Integer.parseInt(in.nextLine());

            System.out.print("\t\t\tTháng: ");
            mm = Integer.parseInt(in.nextLine());

            System.out.print("\t\t\tNăm: ");
            yy = Integer.parseInt(in.nextLine());


        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if((dd<0 || dd>31) || (mm<0 || mm>12) || (yy<1900 || yy>2023)){
            System.out.println("Nhập sai ngay thang nam");
            return;
        }
        String date = null;
        date = " "+dd+"/"+" "+mm+"/"+" "+yy;
        s.setHireDate(date);


        System.out.print("\t\tNhập lương nhân viên: ");
        int salary = 0;
        try {
            salary = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        s.setSalary(salary);


        staffDAO.updateIdDeptNull(s, id);
        System.out.println("Cập nhật thông tin nhân viên thành công");
    }

    private void deleteEmployee(Scanner in){
        System.out.print("\t\t\tNhập mã nhân viên muốn xóa khỏi công ty: ");
        int id;
        try {
            id = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        Staff sId = staffDAO.getById(id);
        if(sId==null){
            System.out.println("Không có nhân viên này!!!");
            return;
        }

        staffDAO.delete(id);
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
