package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;
import util.DBUtil;

public class UserDAO {

//这个带邮箱
	public void add(User bean) {
		 
        String sql = "insert into member values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.email_address);
 
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
	
//	public void add(User bean) {
//		 
//        String sql = "insert into member values(null,?,?,null)";
//        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
// 
//            ps.setString(1, bean.getName());
//            ps.setString(2, bean.getPassword());
//            //ps.setString(3, bean.email_address);
// 
//            ps.execute();
// 
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                int id = rs.getInt(1);
//                bean.setId(id);
//            }
//        } catch (SQLException e) {
// 
//            e.printStackTrace();
//        }
//    }
	
    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;
    }
	
	public User get(String name) {
        User bean = null;

        String sql = "select * from member where name = '"+name+"'";
        
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
        	
        	ResultSet rs = ps.executeQuery(sql);
            
        	if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                String email_address = rs.getString("email_address");
                bean.setId(id);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return bean;
    }
	
	public User get(int id) {
        User bean = null;
        
        String sql = "select * from member where id = ?";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
 
        	ps.setInt(1, id);
 
            ResultSet rs = ps.executeQuery(sql);
 
            if (rs.next()) {
                bean = new User();
                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
    }
	
	public User getUser(String name , String email_address) {
        User bean = null;
        
        String sql = "select * from member where name = '"+name+"'"+"and email_address = '"+email_address+"'";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
        	
        	//System.out.println(ps);
            ResultSet rs = ps.executeQuery(sql);
            
 
            if (rs.next()) {
                bean = new User();
                bean.setName(name);
                String password = rs.getString(3);
                //System.out.println(password);
                bean.setPassword(password);
                bean.setEmail_address(email_address);
                int id = rs.getInt("id");
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
	
	public User get(String name, String password) {
        User bean = null;
          
        String sql = "select * from member where name = ? and password = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
	
	public void updatePassword(User user) {
		String sql = "update member set password = ? where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			 
            ps.setString(1, user.password);
            ps.setInt(2, user.id);
 
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
