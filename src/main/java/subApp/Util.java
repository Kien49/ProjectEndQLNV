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

public class Util{
    private StaffDAO staffDAO = new StaffDAO();
    private DeptDAO deptDAO = new DeptDAO();

    public  boolean isEmpty(String input){
        return input == null || input.trim().isEmpty();
    }

    public int scannerIdStaff(Scanner in){
        int id;
        try {
            id = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return -1;
        }

        return id;
    }
    public Staff checkStaffId(Scanner in){
        Staff sId = staffDAO.getById(scannerIdStaff(in));
        if(sId==null){
            System.out.println("Không có nhân viên này!!!");
            return null;
        }
        return sId;
    }

    public String checkMailHaveID(Scanner in, Staff sId, List<Staff> staffList){
        String mail = in.nextLine();
        if(!sId.getMail().equalsIgnoreCase(mail)){
            for (Staff st: staffList) {
                if(st.getMail().equalsIgnoreCase(mail)){
                    System.out.println("Đã có mail này");
                    return null;
                }
            }
        }
        if(isEmpty(mail)){
            System.out.println("Email không được để trống");
            return null;
        }
        if(mail.contains(".")){
            return mail;
        }
        return mail+"@gmail.com";

    }
    public String checkMail(Scanner in, List<Staff> staffList){
        String mail = in.nextLine();
        for (Staff st: staffList) {
            if(st.getMail().equalsIgnoreCase(mail)){
                System.out.println("Đã có mail này");
                return null;
            }
        }
        if(isEmpty(mail)){
            System.out.println("Email không được để trống");
            return null;
        }
        if(mail.contains(".")){
            return mail;
        }
        return mail+"@gmail.com";
    }
    public String checkNameScanner(Scanner in){
        String name = in.nextLine();
        if(isEmpty(name)){
            System.out.println("Tên không được để trống");
            return null;
        }
        return name;
    }

    public String checkSdtScanner(Scanner in){
        System.out.print("\t\tNhập số điện thoại: ");
        String sdtS = in.nextLine();
        int sdtN;
        try {
            sdtN = Integer.parseInt(sdtS);

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return null;
        }
        if(sdtN <=0 ){
            System.out.println("Nhập sai định dạng");
            return null;
        }
        if(sdtS.length() != 10){
            System.out.println("Chưa đủ số điện thoại");
            return null;
        }
        return sdtS;
    }

    public int checkSalaryScanner(Scanner in){
        System.out.print("\t\t\tNhập lương nhân viên: ");
        int salary;
        try {
            salary = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return 0;
        }
        if(salary<=0){
            System.out.println("Lương không là số âm");
            return 0;
        }
        return salary;
    }
    public int checkGenderScanner(String gender){
        if(gender.equalsIgnoreCase("nam")){
            return 1;
        } else if(gender.equalsIgnoreCase("nữ") || gender.equalsIgnoreCase("nu")){
            return 0;
        }
        System.out.println("Nhập sai định dạng!!!");
        return -1;
    }
    public Date checkDateScanner(Scanner in){
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
            System.out.println("Nhập sai định dạng!!!");
            return null;
        }
        if((dd<0 || dd>31) || (mm<0 || mm>12) || (yy<1900 || yy>2023)){
            System.out.println("Nhập sai ngay thang nam!!!");
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String dateS = null;
        dateS = " "+yy+"-"+" "+mm+"-"+" "+dd;
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return date;
    }

    public int chooseDepartment(Scanner in){
        int idDeptScanner;
        List<Department> deptList = deptDAO.getAll();
        for (int i = 0; i < deptList.size(); i++) {
            System.out.println(i+1 + " : " + deptList.get(i).getDeptName());
        }
        System.out.print("\t\tBạn chọn phòng ban: ");

        try {
            idDeptScanner = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng!!!");
            return -1;
        }
        return idDeptScanner;
    }

    public boolean checkNameDept(String name, List<Department> deptList){
        for (Department d: deptList) {
            if(d.getDeptName().equalsIgnoreCase(name)){
                System.out.println("Đã có phòng này!!!");
                return false;
            }
        }
        if(isEmpty(name)){
            System.out.println("Tên phòng không được để trống");
            return false;
        }
        return true;
    }

}
