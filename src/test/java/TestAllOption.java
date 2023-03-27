
import dao.DeptDAO;
import model.Department;
import model.Staff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subApp.*;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestAllOption {
    @Test
    @DisplayName("Test tax(option 6)")
    public void testTax(){
        String input ="0";
        Scanner in = new Scanner(input);
        App app = new App();
        double excpted = 22.22;
        double actual = app.taxOfStaff(in,110);
        assertEquals(excpted, actual);
    }

    @Test
    @DisplayName("Test option 2")
    public void testShowAllDept(){
        ShowAllStaffAndDept opt1A2 = new ShowAllStaffAndDept();
        int excpted = 19;
        int actual = opt1A2.option2();
        assertEquals(excpted, actual);
    }

    @Test
    @DisplayName("Test option 8")
    public void testShowAllMemberDept(){
        String input ="119";
        Scanner in = new Scanner(input);
        MemberDept memberDept = new MemberDept();
        boolean excpted = true;
        boolean actual = memberDept.swapLeadDept(in, 180);
        assertEquals(excpted, actual);
    }
    @Test
    @DisplayName("Test show all dept(otion 9)")
    public void testShowAllLead(){
        App app = new App();
        int excpted = 18;
        int actual = app.showLeadDepartment();
        assertEquals(excpted, actual);
    }
    @Test
    @DisplayName("Test xoa phong ban option 3")
    public void testDeleteDept(){
        String input ="19";
        Scanner in = new Scanner(input);
        Option3 otion3 = new Option3();
        boolean excpted = true;
        boolean actual = otion3.deleteDepartment(in);
        assertEquals(excpted, actual);
    }
    @Test
    @DisplayName("Test chuyen phong ban option 5")
    public void testSwapDept(){
        Util util = new Util();
        Staff sId = util.checkStaffId(110);

        DeptDAO deptDAO = new DeptDAO();
        List<Department> deptList = deptDAO.getAll();

        String input ="19";
        Scanner in = new Scanner(input);

        StaffAndDept staffAndDept = new StaffAndDept();
        boolean excpted = true;
        boolean actual = staffAndDept.subSwap(sId, deptList, in);
        assertEquals(excpted, actual);
    }
}
