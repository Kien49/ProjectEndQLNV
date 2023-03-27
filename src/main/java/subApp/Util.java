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
    public Staff checkStaffId(int id){

        Staff sId = staffDAO.getById(id);
        if(sId==null){
            System.out.println("Không có nhân viên này!!!");
            return null;
        }
        return sId;
    }

    public String checkMailHaveID(String mail, Staff sId, List<Staff> staffList){

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
    public String checkMail(String mail, List<Staff> staffList){
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
    public String checkNameScanner(String name){
        if(isEmpty(name)){
            System.out.println("Tên không được để trống");
            return null;
        }
        return name;
    }

    public String checkSdtScanner(String sdtS){
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

    public int checkSalaryScanner(String slaryS){
        int salary;
        try {
            salary = Integer.parseInt(slaryS);

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
    public Date checkDateScanner(String ddS, String mmS, String yyS){
        int dd, mm, yy;
        try {
            dd = Integer.parseInt(ddS);
            mm = Integer.parseInt(mmS);
            yy = Integer.parseInt(yyS);
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
