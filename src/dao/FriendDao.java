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
import bean.Friend;
import util.DBUtil;

public class FriendDao {
	public void add(Friend bean) {
		 
        String sql = "insert into friend values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setInt(1, bean.uid);
            ps.setInt(2, bean.fid);
            ps.setInt(3, bean.verify);
            ps.setString(4, bean.requestName);
            //ps.setString(3, bean.email_address);
 
            ps.execute();
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
	
	public void update(int id) {
		 
        String sql = "update friend set verify = 1 where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setInt(1, id);
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public Friend get(int id) {
		Friend bean = null;
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from Friend where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                bean = new Friend();
                int uid = rs.getInt(2);
                int fid = rs.getInt(3);
                int verify = rs.getInt(4);
                bean.setUid(uid);
                bean.setFid(fid);
                bean.setVerify(verify);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
	
	public List<Friend> list(int start, int count, int uid , int verify) {
        List<Friend> beans = new ArrayList<Friend>();
 
        String sql = "select * from friend where uid = ? and verify = ? order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, uid);
        	ps.setInt(2, verify);
            ps.setInt(3, start);
            ps.setInt(4, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	Friend bean = new Friend();
            	
                int id = rs.getInt(1);
                int fid = rs.getInt(3);
                String requestName = rs.getString(5);
                
                bean.setId(id);
                bean.setUid(uid);
                bean.setFid(fid);
                bean.setVerify(verify);
                bean.setRequestName(requestName);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
	
	public int getTotal(int uid) {
        int total = 0;
        
        String sql = "select count(*) from friend where uid = ? and verify = 0";
        
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, uid);

        	ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

	public void delete(int id) {
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			 
            String sql = "delete from friend where id = " + id;
 
            s.execute(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void delete(int uid, int fid) {
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "delete from friend where uid= " +uid+ " and fid = " +fid;
			s.execute(sql);
			
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public List<Friend> list(int start, int count, int uid) {
		// TODO Auto-generated method stub
		List<Friend> beans = new ArrayList<Friend>();
		 
        String sql = "select * from friend where uid = ? order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	Friend bean = new Friend();
            	
                int id = rs.getInt(1);
                int fid = rs.getInt(3);
                String requestName = rs.getString(5);
                
                bean.setId(id);
                bean.setUid(uid);
                bean.setFid(fid);
                bean.setRequestName(requestName);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
	}
}
