package dao;

import connection.MyConnection;
import model.Account;
import model.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public List<Staff> getAll() {
        final String sql = "SELECT * FROM `staff`";

        List<Staff> staffList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setFullName(rs.getString("full_name"));
                staff.setGender(rs.getInt("gender"));
                staff.setMail(rs.getString("mail"));
                staff.setPhone(rs.getInt("phone"));
                staff.setHireDate(rs.getString("hire_date"));
                staff.setSalary(rs.getInt("salary"));
                staff.setDepartmentId(rs.getInt("department_id"));

                staffList.add(staff);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public Staff getById(int id) {
        final String sql = "SELECT * FROM `staff` WHERE  `staff_id` = '" + id + "'";
        Staff staff = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setFullName(rs.getString("full_name"));
                staff.setGender(rs.getInt("gender"));
                staff.setMail(rs.getString("mail"));
                staff.setPhone(rs.getInt("phone"));
                staff.setHireDate(rs.getString("hire_date"));
                staff.setSalary(rs.getInt("salary"));
                staff.setDepartmentId(rs.getInt("department_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public void insert(Staff s) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("insert into `staff` (`staff_id`,`full_name`,`gender`,`mail`,`phone`, `hire_date`, `salary`, `department_id`) VALUES ('%d','%s','%d','%s', '%s','%s','%d','%d') ",
                    s.getStaffId(), s.getFullName(), s.getGender(), s.getMail(), s.getPhone(), s.getHireDate(), s.getSalary(), s.getDepartmentId()
            );
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertDeptIdNull(Staff s) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("insert into `staff` (`staff_id`,`full_name`,`gender`,`mail`,`phone`, `hire_date`, `salary`) VALUES ('%d','%s','%d','%s', '%s','%s','%d') ",
                    s.getStaffId(), s.getFullName(), s.getGender(), s.getMail(), s.getPhone(), s.getHireDate(), s.getSalary()
            );
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
