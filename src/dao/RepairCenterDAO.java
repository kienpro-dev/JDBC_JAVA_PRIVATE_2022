package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RepairCenter;

public class RepairCenterDAO {
    public void add(RepairCenter rc) throws SQLException {
        String sql = "INSERT INTO RepairCenter VALUES (? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, rc.getRcId());
        ps.setString(2, rc.getRcName());
        ps.setInt(3, rc.getRcPrice());
        ps.executeUpdate();

    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM RepairCenter WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public void update(RepairCenter rc, String ID) throws SQLException {
        String sql = "UPDATE Equipment SET Name = ?, Price = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, rc.getRcName());
        ps.setInt(2, rc.getRcPrice());
        ps.setString(3, ID);
        ps.executeUpdate();

    }

    public List<RepairCenter> findAll() throws SQLException {
        List<RepairCenter> list = new ArrayList<>();
        String sql = "SELECT * FROM RepairCenter";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            RepairCenter rc = new RepairCenter();
            rc.setRcId(rs.getString("ID"));
            rc.setRcName(rs.getString("Name"));
            rc.setRcPrice(rs.getInt("Price"));

            list.add(rc);
        }
        return list;
    }

    public RepairCenter find(String ID) throws SQLException {
        String sql = "SELECT * FROM RepairCenter WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            RepairCenter rc = new RepairCenter();
            rc.setRcId(rs.getString("ID"));
            rc.setRcName(rs.getString("Name"));
            rc.setRcPrice(rs.getInt("Price"));

            return rc;
        }

        return null;
    }
}


