package dao;

import connection.MyConnection;
import model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public List<Account> getAll() {
        final String sql = "SELECT * FROM `account`";

        List<Account> accountList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("user_name"));
                account.setPassWord(rs.getString("pass_word"));
                account.setStatus(rs.getString("status"));
                accountList.add(account);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accountList;
    }

    public void update(Account account, String userName) {

        final String sql = String.format(
                "UPDATE `account` SET `user_name`='%s', `pass_word`='%s', `status`='%s' WHERE `user_name` = '%s'",
                account.getUserName(), account.getPassWord(), account.getStatus(), userName
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insert(Account a) {
        final String sql = String.format("INSERT  INTO `account` VALUES ( '%s','%s','%s') ",
                a.getUserName(), a.getPassWord(), a.getStatus()
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

}
