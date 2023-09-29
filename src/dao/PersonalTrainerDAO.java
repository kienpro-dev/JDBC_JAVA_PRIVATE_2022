package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.PersonalTrainer;

public class PersonalTrainerDAO {
    public void add(PersonalTrainer pt) throws SQLException {
        String sql = "INSERT INTO PersonalTrainer VALUES(? , ? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, pt.getPtId());
        ps.setString(2, pt.getPtName());
        ps.setString(3, pt.getPtGender());
        ps.setInt(4, pt.getPtHirePricePerDay());
        ps.executeUpdate();

    }

    public void update(PersonalTrainer pt, String ID) throws SQLException {
        String sql = "UPDATE PersonalTrainer SET Name = ? , Gender = ? , HirePricePerDay = ? WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, pt.getPtName());
        ps.setString(2, pt.getPtGender());
        ps.setInt(3, pt.getPtHirePricePerDay());
        ps.setString(4, ID);
        ps.executeUpdate();

    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM PersonalTrainer WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public List<PersonalTrainer> findAll() throws SQLException {
        List<PersonalTrainer> list = new ArrayList<>();
        String sql = "SELECT * FROM PersonalTrainer";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PersonalTrainer pt = new PersonalTrainer();
            pt.setPtId(rs.getString("ID"));
            pt.setPtName(rs.getString("Name"));
            pt.setPtGender(rs.getString("Gender"));
            pt.setPtHirePricePerDay(rs.getInt("HirePricePerDay"));

            list.add(pt);
        }
        return list;
    }

    public PersonalTrainer find(String ID) throws SQLException {
        String sql = "SELECT * FROM PersonalTrainer WHERE ID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PersonalTrainer pt = new PersonalTrainer();
            pt.setPtId(rs.getString("ID"));
            pt.setPtName(rs.getString("Name"));
            pt.setPtGender(rs.getString("Gender"));
            pt.setPtHirePricePerDay(rs.getInt("HirePricePerDay"));
            return pt;
        }

        return null;
    }
}
