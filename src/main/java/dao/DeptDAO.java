package dao;

import connection.MyConnection;
import model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class DeptDAO {
    public List<Department> getAll() {
        final String sql = "SELECT * FROM `department`";
        List<Department> deptList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("department_id"));
                dept.setDeptName(rs.getString("department_name"));
                dept.setDeptHeadId(rs.getInt("department_head_id"));

                deptList.add(dept);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptList;
    }

    public List<Department> innerJoinIn4HeadDept() {
        List<Department> deptList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            String sql = "select d.department_head_id, s.full_name, d.department_id, d.department_name, s.salary " +
                    " from staff s " +
                    " inner join department d " +
                    " on s.staff_id = d.department_head_id; ";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Tao doi tuong Person rong
                Department d = new Department();
                d.setDeptHeadId(rs.getInt("d.department_head_id"));
                d.setNameLead(rs.getString("s.full_name"));
                d.setDeptId(rs.getInt("d.department_id"));
                d.setDeptName(rs.getString("d.department_name"));
                d.setSalaryLead(rs.getInt("s.salary"));

                deptList.add(d);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return deptList;
    }

    public void insert(Department d) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("insert into `department` (`department_id`,`department_name`) VALUES ('%d','%s') ",
                    d.getDeptId(), d.getDeptName()
            );
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public Department getById(int id) {
        final String sql = "SELECT * FROM `department` WHERE  `department_id` = '" + id + "'";
        Department dept = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                dept = new Department();
                dept.setDeptId(rs.getInt("department_id"));
                dept.setDeptName(rs.getString("department_name"));
                dept.setDeptHeadId(rs.getInt("department_head_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dept;
    }*/
    public void updateIdLead(Department d, int id) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("UPDATE `department` SET  `department_head_id`='%d'   WHERE `department_id` = '%d'",
                    d.getDeptHeadId(),id
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
    public void deleteLead(int id) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("UPDATE `department` SET  `department_head_id`= NULL   WHERE `department_id` = '%d'",
                    id
            );

            //System.out.println(sql);
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateIdName(Department d, int id) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("UPDATE `department` SET  `department_name`='%s'   WHERE `department_id` = '%d'",
                    d.getDeptName(),id
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
        final String sql = "DELETE FROM `department` WHERE  `department_id` = '" + id + "'";
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
}
