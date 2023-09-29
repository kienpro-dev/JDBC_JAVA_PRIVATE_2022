package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Equipment;

public class EquipmentDAO {
    public void add(Equipment e) throws SQLException {
        String sql = "INSERT INTO Equipment VALUES(? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, e.getEquipmentId());
        ps.setString(2, e.getEquipmentName());
        ps.setInt(3, e.getEquipmentDurability());
        ps.executeUpdate();

    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM Equipment WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public void update(Equipment e, String ID) throws SQLException {
        String sql = "UPDATE Equipment SET Name = ? , Durability = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, e.getEquipmentName());
        ps.setInt(2, e.getEquipmentDurability());
        ps.setString(3, ID);
        ps.executeUpdate();

    }

    public List<Equipment> findAll() throws SQLException {
        List<Equipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Equipment";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Equipment e = new Equipment();
            e.setEquipmentId(rs.getString("ID"));
            e.setEquipmentName(rs.getString("Name"));
            e.setEquipmentDurability(rs.getInt("Durability"));

            list.add(e);
        }
        return list;
    }

    public Equipment find(String ID) throws SQLException {
        String sql = "SELECT * FROM Equipment WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Equipment e = new Equipment();
            e.setEquipmentId(rs.getString("ID"));
            e.setEquipmentName(rs.getString("Name"));
            e.setEquipmentDurability(rs.getInt("Durability"));
            return e;
        }

        return null;
    }
}
