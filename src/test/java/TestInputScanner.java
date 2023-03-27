import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subApp.Util;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestInputScanner {
    private Util util = new Util();

    @Test
    @DisplayName("Test mail khi sửa thông tin nhân viên")
    public void testMail1(){
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> staffList = staffDAO.getAll();

        Staff sId = util.checkStaffId(1);
        String mail = "Ernst@gmail.com";
        String excptedMail = null;
        String actualMail = util.checkMailHaveID(mail, sId ,staffList);
        assertEquals(excptedMail, actualMail);
    }

    @Test
    @DisplayName("Test nhập mã nhân viên")
    public void testStaffIdScanner(){
        String input ="1";
        Scanner in = new Scanner(input);

        int excptedId = 1;
        int actualId = util.scannerIdStaff(in);
        assertEquals(excptedId, actualId);
    }

    @Test
    @DisplayName("Test mã nhân viên")
    public void testStaffId(){
        Staff excptedId = null;
        Staff actualId = util.checkStaffId(0);
        assertEquals(excptedId, actualId);
    }

    @Test
    @DisplayName("Test tên nhân viên")
    public void testStaffName(){
        String excptedId = "Kiên";
        String actualId = util.checkNameScanner("Kiên");
        assertEquals(excptedId, actualId);
    }

    @Test
    @DisplayName("Test giới tính nam nhân viên")
    public void testStaffGender(){
        int excptedGender = 1;
        int actualGender = util.checkGenderScanner("nam");
        assertEquals(excptedGender, actualGender);
    }
    @Test
    @DisplayName("Test giới tính nữ nhân viên")
    public void testStaffGender1(){
        int excptedGender = 0;
        int actualGender = util.checkGenderScanner("nu");
        assertEquals(excptedGender, actualGender);
    }

    @Test
    @DisplayName("Test phone number nhập sai định dạng ")
    public void testPhoneNumberNo(){
        String excptedNumber = null;
        String actualNumber = util.checkSdtScanner("abc");
        assertEquals(excptedNumber, actualNumber);
    }
    @Test
    @DisplayName("Test phone number nhập đúng định dạng ")
    public void testPhoneNumberYes(){
        String excptedNumber = "0914560531";
        String actualNumber = util.checkSdtScanner("0914560531");
        assertEquals(excptedNumber, actualNumber);
    }

    @Test
    @DisplayName("Test ngày tháng năm nhập sai định dạng ")
    public void testDateNo(){
        Date excptedDate = null;
        Date actualDate = util.checkDateScanner("aa", "09", "2000");
        assertEquals(excptedDate, actualDate);
    }

    @Test
    @DisplayName("Test lương nhập đúng định dạng ")
    public void testSalaryYes(){
        int excptedSalary = 1000;
        int actualSalary = util.checkSalaryScanner("1000");
        assertEquals(excptedSalary, actualSalary);
    }
    @Test
    @DisplayName("Test lương nhập sai định dạng ")
    public void testSalaryNo(){
        int excptedSalary = 0;
        int actualSalary = util.checkSalaryScanner("abc");
        assertEquals(excptedSalary, actualSalary);
    }

    @Test
    @DisplayName("Test chọn phòng ban trong công ty ")
    public void testDepartmentId(){
        String input ="1";
        Scanner in = new Scanner(input);

        int excptedDeptID = 1;
        int actualDeptID = util.chooseDepartment(in);
        assertEquals(excptedDeptID, actualDeptID);
    }

    @Test
    @DisplayName("Test tên phòng ban trong công ty ")
    public void testDepartmentName(){
        DeptDAO deptDAO = new DeptDAO();
        List<Department> deptList = deptDAO.getAll();

        boolean excptedDeptName = true;
        boolean actualDeptName = util.checkNameDept("abc", deptList);
        assertEquals(excptedDeptName, actualDeptName);
    }
}
