package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.MemberPT;
import model.PersonalTrainer;

public class MemberPTDAO {
    public void add(MemberPT mpt) throws SQLException {
        String sql = "INSERT INTO MemberPT VALUES(? , ? , ? , ?)";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, mpt.getMem().getMemberId());
        ps.setString(2, mpt.getPt().getPtId());
        ps.setInt(3, mpt.getTimeLeft());
        ps.setInt(4, mpt.getAllPrice());
        ps.executeUpdate();

    }

    public void delete(String ID) throws SQLException {
        String sql = "DELETE FROM MemberPT WHERE MemberID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public void deletePT(String ID) throws SQLException {
        String sql = "DELETE FROM MemberPT WHERE PTID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ps.executeUpdate();

    }

    public void update(MemberPT mpt, String ID) throws SQLException {
        String sql = "UPDATE MemberPT SET timeLeft = ? WHERE MemberID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, mpt.getTimeLeft());
        ps.setString(2, ID);
        ps.executeUpdate();

    }

    public List<PersonalTrainer> findPT() throws SQLException {
        List<PersonalTrainer> list = new ArrayList<>();
        String sql = "SELECT PTID FROM MemberPT";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        PersonalTrainer pt = new PersonalTrainer();
        while (rs.next()) {
            pt.setPtId(rs.getString("PTID"));

            list.add(pt);
        }
        return list;
    }

    public List<Member> findMember() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT MemberID FROM MemberPT";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        Member mem = new Member();
        while (rs.next()) {
            mem.setMemberId(rs.getString("MemberID"));

            list.add(mem);
        }
        return list;
    }

    public List<MemberPT> findAll() throws SQLException {
        List<MemberPT> list = new ArrayList<>();
        String sql = "SELECT * FROM MemberPT";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        MemberPT mpt = new MemberPT();
        Member mem = new Member();
        PersonalTrainer pt = new PersonalTrainer();

        while (rs.next()) {
            mem.setMemberId(rs.getString("MemberID"));
            mpt.setMem(mem);
            pt.setPtId(rs.getString("PTID"));
            mpt.setPt(pt);
            mpt.setTimeLeft(rs.getInt("timeLeft"));

            list.add(mpt);
        }
        return list;
    }

    public MemberPT find(String ID) throws SQLException {
        String sql = "SELECT * FROM MemberPT WHERE MemberID = ?";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MemberPT mpt = new MemberPT();
            Member mem = new Member();
            PersonalTrainer pt = new PersonalTrainer();

            mem.setMemberId(rs.getString("MemberID"));
            mpt.setMem(mem);
            pt.setPtId(rs.getString("PTID"));
            mpt.setPt(pt);
            mpt.setTimeLeft(rs.getInt("timeLeft"));

            return mpt;
        }

        return null;
    }

    public List<MemberPT> statisticPTIncome() throws SQLException {
        List<MemberPT> list = new ArrayList<>();

        String sql = "SELECT PersonalTrainer.Name, PersonalTrainer.Gender, SUM(MemberPT.allPrice) AS Income FROM MemberPT INNER JOIN PersonalTrainer ON MemberPT.PTID = PersonalTrainer.ID GROUP BY PersonalTrainer.Name, PersonalTrainer.Gender";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MemberPT mpt = new MemberPT();
            PersonalTrainer pt = new PersonalTrainer();

            pt.setPtName(rs.getString("Name"));
            pt.setPtGender(rs.getString("Gender"));
            mpt.setPt(pt);
            mpt.setIncome(rs.getInt("Income"));

            list.add(mpt);
        }
        return list;
    }

    public List<MemberPT> statisticNumberMemberOfPT() throws SQLException {
        List<MemberPT> list = new ArrayList<>();

        String sql = "SELECT PersonalTrainer.Name, COUNT(memid) AS NumberOfMember FROM MemberPT "
        		+ "INNER JOIN PersonalTrainer ON MemberPT.PTID = PersonalTrainer.ID GROUP BY PersonalTrainer.Name";

        PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MemberPT mpt = new MemberPT();
            PersonalTrainer pt = new PersonalTrainer();

            pt.setPtName(rs.getString("Name"));
            mpt.setPt(pt);
            mpt.setNumberOfMember(rs.getInt("NumberOfMember"));

            list.add(mpt);
        }
        return list;
    }

}
