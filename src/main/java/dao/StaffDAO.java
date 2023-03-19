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
                staff.setPhone(rs.getString("phone"));
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
}
