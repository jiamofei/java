package com.onest.webshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onest.webshop.bean.User;
import com.onest.webshop.common.DbConnection;

public class UserDao {
	public List<User> selectAll() {
		List<User> list = new ArrayList<User>();
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps = connection.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				list.add(user);
			} 
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
				try {
					rs.close();
					ps.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public User login(String userName,String password) {
		User user = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from user where userName=? and password=?");
			ps.setString(1,userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public boolean regist(String userName,String password,String email) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("insert into user (userName,password,email)value(?,?,?)");
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3,email);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public User selectUser(Integer userId) {
		User user = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from user where userId=?");
			ps.setInt(1,userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public boolean updateUser(Integer userId,String userName,String password,String email) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("update user set userName=?,password=?,email=? where userId=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3,email);
			ps.setInt(4, userId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
