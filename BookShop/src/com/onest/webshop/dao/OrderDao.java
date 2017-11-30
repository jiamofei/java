package com.onest.webshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onest.webshop.bean.Orders;
import com.onest.webshop.common.DbConnection;

public class OrderDao {
	public boolean insertIntoOrderAndOrdeDetail(Integer bookId,Integer count,Integer userId,String time) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs=null;
		try {
			connection.setAutoCommit(false);
			ps1 = connection.prepareStatement("insert into orders(userId,orderTime)value(?,?)");
			ps1.setInt(1,userId);
			ps1.setString(2,time);
			ps1.execute();
			ps2 = connection.prepareStatement("select orderId from orders where userId=?");
			ps2.setInt(1, userId);
			rs = ps2.executeQuery();
			while (rs.next()) {
				ps3 = connection.prepareStatement("insert into orderDetail(bookId,count,orderId)value(?,?,?)");
				ps3.setInt(1, bookId);
				ps3.setInt(2, count);
				ps3.setInt(3, rs.getInt("orderId"));
				ps3.execute();
			}
			connection.commit();
			connection.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps1.close();
				ps2.close();
				ps3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Orders> selectOrder() {
		List<Orders> list = new ArrayList<Orders>();
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps = connection.prepareStatement("select * from orders,orderDetail where orders.orderId = orderDetail.orderId");
			rs = ps.executeQuery();
			while(rs.next()) {
				Orders order = new Orders();
				order = new Orders();
				order.setOrderId(rs.getInt("orderId"));
				order.setOrderDetailId(rs.getInt("orderDetailId"));
				order.setBookId(rs.getInt("bookId"));
				order.setCount(rs.getInt("count"));
				order.setOrderTime(rs.getString("orderTime"));
				order.setUserId(rs.getInt("userID"));
				list.add(order);
				
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
}
