package dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Member;

public class MemberDAO {
    public void add(Member mem, String username) throws SQLException {
        String sql = "INSERT INTO Member VALUES(? , ? , ? , ? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, mem.getMemberId());
        ps.setString(2, mem.getMemberName());
        ps.setInt(3, mem.getMemberAge());
        ps.setDate(4, (java.sql.Date) mem.getMemberDayStart());
        ps.setDate(5, (java.sql.Date) mem.getMemberDayEnd());
        ps.setString(6, username);
        ps.executeUpdate();

    }

    public void update(Member mem, String ID) throws SQLException {
        String sql = "UPDATE Member SET Name = ? , Age = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, mem.getMemberName());
        ps.setInt(2, mem.getMemberAge());
        ps.setString(3, ID);
        ps.executeUpdate();

    }

    public void updateDate(Date end, String ID) throws SQLException {
        String sql = "UPDATE Member SET DayEnd = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setDate(1, end);
        ps.setString(2, ID);
        ps.executeUpdate();
    }

    public void updateUsername(String username, String ID) throws SQLException {
        String sql = "UPDATE Member SET Username = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, ID);
        ps.executeUpdate();
    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM Member WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public List<Member> findAll() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM Member";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Member mem = new Member();
            mem.setMemberId(rs.getString("ID"));
            mem.setMemberName(rs.getString("Name"));
            mem.setMemberAge(rs.getInt("Age"));
            mem.setMemberDayStart(rs.getDate("DayStart"));
            mem.setMemberDayEnd(rs.getDate("DayEnd"));
            mem.setUsername(rs.getString("Username"));

            list.add(mem);
        }
        return list;
    }

    public Member find(String ID) throws SQLException {
        String sql = "SELECT * FROM Member WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();
        Member mem = new Member();
        while (rs.next()) {
            mem.setMemberId(rs.getString("ID"));
            mem.setMemberName(rs.getString("Name"));
            mem.setMemberAge(rs.getInt("Age"));
            mem.setMemberDayStart(rs.getDate("DayStart"));
            mem.setMemberDayEnd(rs.getDate("DayEnd"));
            mem.setUsername(rs.getString("Username"));
            return mem;
        }

        return null;
    }

    public static String getMemberName(String username) throws SQLException {
        String sql = "SELECT Name FROM Member WHERE Username = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return rs.getString("Name");
        }

        return null;

    }
}
