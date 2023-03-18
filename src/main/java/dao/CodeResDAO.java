package dao;

import connection.MyConnection;
import model.Account;
import model.CodeRegister;

import java.sql.Connection;
import java.sql.Statement;

public class CodeResDAO {
    public void insert(CodeRegister code) {
        final String sql = String.format("INSERT  INTO `code_register` VALUES ( '%s','%s','%d') ",
                code.getUserName(), code.getPassWord(), code.getCode()
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(String userName) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = String.format("DELETE FROM code_register WHERE `user_name` = '%s' ",userName);

            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
