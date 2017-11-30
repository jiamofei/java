package com.onest.webshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onest.webshop.bean.Admin;
import com.onest.webshop.common.DbConnection;


public class AdminDao {
	public Admin login(String adminName,String password) {
		Admin admin = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from admin where adminName=? and password=?");
			ps.setString(1,adminName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setPassword(rs.getString("password"));
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
		return admin;
	}
}
