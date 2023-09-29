package dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
    public void add(Account acc) throws SQLException {
        String sql = "INSERT INTO Account VALUES(? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, acc.getUsername());
        ps.setString(2, acc.getPassword());
        ps.setString(3, acc.getRole());
        ps.executeUpdate();
    }

    public void update(String username, String password) throws SQLException {
        String sql = "UPDATE Account SET Password = ? WHERE Username = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.executeUpdate();

    }

    public void delete(String username) throws SQLException {
        String sql = "DELETE FROM Account WHERE Username = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.executeUpdate();

    }

    public List<Account> getAccounts() throws SQLException {
        List<Account> listAcc = new ArrayList<>();
        String sql = "SELECT * FROM Account";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            Account acc = new Account();
            acc.setUsername(rs.getString("Username"));
            acc.setPassword(rs.getString("Password"));
            acc.setRole(rs.getString("Role"));
            listAcc.add(acc);
        }
        return listAcc;

    }

}
