import dao.StaffDAO;

import model.Staff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subApp.LoginAndRegister;
import subApp.Util;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin {
    private Util util = new Util();
    @Test
    @DisplayName("Test register")
    public void testRegister(){
        LoginAndRegister logAndReg = new LoginAndRegister();
        String input = String.valueOf(logAndReg.randomNumberCode());
        Scanner in = new Scanner(input);

        boolean expectedValue = true;
        boolean actualValue = logAndReg.checkRegister("kvk", "kvk", in);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Test login tk 1")
    public void login1(){
        LoginAndRegister logAndReg = new LoginAndRegister();
        boolean expectedValue = true;
        boolean actualValue = logAndReg.checkLogin("admin", "admin", "off");
        assertEquals(expectedValue, actualValue);
    }
    @Test
    @DisplayName("Test login tk 1 sau khi đăng nhập")
    public void login2(){
        LoginAndRegister logAndReg = new LoginAndRegister();
        boolean expectedValue = false;
        boolean actualValue = logAndReg.checkLogin("admin", "admin", "off");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Test mail khi thêm nhân viên")
    public void testMail(){
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> staffList = staffDAO.getAll();
        String mail = "123";
        String excptedMail = "123@gmail.com";
        String actualMail = util.checkMail(mail, staffList);
        assertEquals(excptedMail, actualMail);
    }




}
