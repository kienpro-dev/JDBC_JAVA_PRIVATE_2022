package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Equipment;
import model.RepairCenter;
import model.RepairEquipment;

public class RepairEquipmentDAO {
    public void add(RepairEquipment re) throws SQLException {
        String sql = "INSERT INTO RepairEquipment VALUES(? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, re.getE().getEquipmentId());
        ps.setString(2, re.getRc().getRcId());
        ps.setInt(3, re.getTimeLeft());
        ps.executeUpdate();
    
    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM RepairEquipment WHERE EquipmentID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();
    
    }

    public void update(RepairEquipment re, String ID) throws SQLException {
        String sql = "UPDATE RepairEquipment SET timeLeft = ? WHERE EquipmentID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, re.getTimeLeft());
        ps.setString(2, ID);
        ps.executeUpdate();
    
    }

    public List<RepairEquipment> findAll() throws SQLException {
        List<RepairEquipment> list = new ArrayList<>();
        String sql = "SELECT * FROM RepairEquipment";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            RepairEquipment re = new RepairEquipment();
            Equipment e = new Equipment();
            RepairCenter rc = new RepairCenter();

            e.setEquipmentId(rs.getString("EquipmentID"));
            re.setE(e);
            rc.setRcId(rs.getString("RepairID"));
            re.setRc(rc);
            re.setTimeLeft(rs.getInt("TimeLeft"));
            list.add(re);
        }
        return list;
    }

    public RepairEquipment find(String ID) throws SQLException {
        String sql = "SELECT * FROM RepairEquipment WHERE EquipmentID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            RepairEquipment re = new RepairEquipment();
            Equipment e = new Equipment();
            RepairCenter rc = new RepairCenter();

            e.setEquipmentId(rs.getString("EquipmentID"));
            re.setE(e);
            rc.setRcId(rs.getString("RepairID"));
            re.setRc(rc);
            re.setTimeLeft(rs.getInt("TimeLeft"));
            
            return re;
        }

        return null;
    }
}
