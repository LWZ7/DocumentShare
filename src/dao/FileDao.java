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
import util.DateUtil;

public class FileDao {
	public int getTotal(int uid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from file where uid="+uid;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
 
    public Document add(Document bean) {
 
        String sql = "insert into file values(null,?,?,?,?,?,null)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setInt(1, bean.getUid());
            ps.setDate(2, DateUtil.util2sql(bean.getDate()));
            ps.setString(3, bean.getType());
            ps.setString(4, bean.getFileName());
            ps.setString(5, bean.getUserName());
            ps.execute();
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    public void update(Document bean) {
 
        String sql = "update file set name= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setString(1, bean.fileName);
            ps.setInt(2, bean.getId());
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateRealFileName(Document bean) {
    	 
        String sql = "update file set realFileName= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setString(1, bean.getRealName());
            ps.setInt(2, bean.getId());
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from file where id = " + id;
 
            s.execute(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String filename) {
    	
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
        	String sql = "delete from file where realfilename = '"+filename+"'";
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public Document get(int id) {
    	Document bean = null;
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from file where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                bean = new Document();
                Date uploadtime = rs.getDate(3);
                String filetype = rs.getString(4);
                String name = rs.getString(5);
                String ownername = rs.getString(6);
                String realfilename = rs.getString(7);
                
                bean.setDate(uploadtime);
                bean.setType(filetype);
                bean.setFileName(name);
                bean.setUserName(ownername);
                bean.setRealName(realfilename);
                bean.setId(id);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<Document> list(int start, int count, int uid) {
        List<Document> beans = new ArrayList<Document>();
 
        String sql = "select * from file where uid = ? order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	Document bean = new Document();
            	
                int id = rs.getInt(1);
                Date uploadtime = rs.getDate(3);
                String filetype = rs.getString(4);
                String name = rs.getString(5);
                String ownername = rs.getString(6);
                String realfilename = rs.getString(7);
               // System.out.println(realfilename);
                
                bean.setId(id);
                bean.setFileName(name);
                bean.setUid(uid);
                bean.setDate(uploadtime);
                bean.setType(filetype);
                bean.setUserName(ownername);
                bean.setRealName(realfilename);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
