package dao;

import connection.MyConnection;
import model.Department;
import model.Staff;

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
}
