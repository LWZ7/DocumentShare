package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Document;
import util.DBUtil;

public class PrivilegeDao {
	public List<Document> list(int start, int count, int uid , int fid) {
        List<Document> beans = new ArrayList<Document>();
        FileDao filedao = new FileDao();
        String sql = "select did from Privilege where uid = ? and fid = ? and p = 1 order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, fid);
        	ps.setInt(2, uid);
            ps.setInt(3, start);
            ps.setInt(4, count);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	int did = rs.getInt(1);
            	Document bean = filedao.get(did);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
	
	public ArrayList listFid(int start, int count, int uid , int did) {
		ArrayList beans = new ArrayList(); 
        FileDao filedao = new FileDao();
        String sql = "select fid from Privilege where uid = ? and did = ? and p = 1 order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, uid);
        	ps.setInt(2, did);
            ps.setInt(3, start);
            ps.setInt(4, count);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
            	int fid = rs.getInt(1);
                beans.add(fid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

	public void update(int uid, Object object , int did) {
		// TODO Auto-generated method stub
		int fid = (Integer)object;
		String sql = "update privilege set p = 0 where uid = ? and fid = ? and did = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			 
            ps.setInt(1, uid);
            ps.setInt(2, fid);
            ps.setInt(3, did);
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void add(int uid, Object object, int did) {
		// TODO Auto-generated method stub
		int fid = (Integer)object;
		String sql = "insert into privilege values(null,?,?,1,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			 
            ps.setInt(1, did);
            ps.setInt(2, uid);
            ps.setInt(3, fid);
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

    public void delete(int id) {
    	 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from privilege where did = " + id;
 
            s.execute(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
