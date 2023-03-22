package dao;

import connection.MyConnection;
import model.Department;
import model.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                staff.setHireDate(rs.getDate("hire_date"));
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
                staff.setHireDate(rs.getDate("hire_date"));
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
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String tmp = dateFormat.format(s.getHireDate());

            Connection conn = MyConnection.getConnection();
            String sql = String.format("insert into `staff` (`staff_id`,`full_name`,`gender`,`mail`,`phone`, `hire_date`, `salary`, `department_id`) VALUES ('%d','%s','%d','%s', '%s','%s','%d','%d') ",
                    s.getStaffId(), s.getFullName(), s.getGender(), s.getMail(), s.getPhone(), tmp, s.getSalary(), s.getDepartmentId()
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
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String tmp = dateFormat.format(s.getHireDate());

            Connection conn = MyConnection.getConnection();
            String sql = String.format("insert into `staff` (`staff_id`,`full_name`,`gender`,`mail`,`phone`, `hire_date`, `salary`) VALUES ('%d','%s','%d','%s', '%s','%s','%d') ",
                    s.getStaffId(), s.getFullName(), s.getGender(), s.getMail(), s.getPhone(), tmp, s.getSalary()
            );
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateIdDeptNull(Staff s, int id) {

        Staff tmp = getById(id);
        if (tmp == null) {
            System.out.println("Cập nhật thất bại do không có id = " + id);
            return;
        }
        try {
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String tmpS = dateFormat.format(s.getHireDate());

            Connection conn = MyConnection.getConnection();
            String sql = String.format("UPDATE `staff` SET `full_name`='%s', `gender`='%d', `mail`='%s', `phone` = '%s',`hire_date` = '%s', `salary` = '%d'  WHERE `staff_id` = '%d'",
                    s.getFullName(), s.getGender(), s.getMail(), s.getPhone(), tmpS, s.getSalary(),id
            );

            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Staff staff = getById(id);
        if (staff == null) {
            throw new RuntimeException("Nhân viên không tồn tại!");
        }

        final String sql = "DELETE FROM `staff` WHERE  `staff_id` = '" + id + "'";
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Staff> innerJoinMemberDept(int id) {
        List<Staff> staffList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            String sql = "select s.staff_id, s.full_name, s.gender, s.mail, s.phone, s.hire_date, s.salary, d.department_name, d.department_head_id " +
                    " from staff s " +
                    " inner join department d " +
                    " on s.department_id= d.department_id " +
                    " where s.department_id = '" + id + "'";


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("s.staff_id"));
                staff.setFullName(rs.getString("s.full_name"));
                staff.setGender(rs.getInt("s.gender"));
                staff.setMail(rs.getString("s.mail"));
                staff.setPhone(rs.getInt("s.phone"));
                staff.setHireDate(rs.getDate("s.hire_date"));
                staff.setSalary(rs.getInt("s.salary"));
                //staff.setDepartmentId(rs.getInt("department_id"));
                staff.setNameDept(rs.getString("d.department_name"));
                staff.setLeadDept(rs.getInt("d.department_head_id"));

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

    public List<Staff> lastHireDate(){
        List<Staff> staffList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            String sql = "select * " +
                    " from staff " +
                    " ORDER  BY hire_date DESC limit 5 " ;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setFullName(rs.getString("full_name"));
                staff.setGender(rs.getInt("gender"));
                staff.setMail(rs.getString("mail"));
                staff.setPhone(rs.getInt("phone"));
                staff.setHireDate(rs.getDate("hire_date"));
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
