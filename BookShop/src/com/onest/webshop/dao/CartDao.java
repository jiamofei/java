package com.onest.webshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onest.webshop.bean.Book;
import com.onest.webshop.bean.BookType;
import com.onest.webshop.bean.Cart;
import com.onest.webshop.common.DbConnection;

public class CartDao {
	
	public List<Cart> selectMyCart(Integer userId) {
		List<Cart> list = new ArrayList<Cart>();
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps = connection.prepareStatement("select * from cart where userId=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setUserId(userId);
				cart.setBookId(rs.getInt("bookId"));
				cart.setBookName(rs.getString("bookName"));
				cart.setCount(rs.getInt("count"));
				cart.setPrice(rs.getDouble("price"));
				cart.setAmount();
				list.add(cart);
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
	
	public boolean addCart(Integer userId,Integer bookId,String bookName,Integer count,Double price,Double amount){
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("insert into cart(userId,bookId,bookName,count,price,amount)value(?,?,?,?,?,?)");
			ps.setInt(1,userId);
			ps.setInt(2,bookId);
			ps.setString(3,bookName);
			ps.setInt(4,count);
			ps.setDouble(5,price);
			ps.setDouble(6, amount);
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
	
	public boolean dropOrder(Integer bookId,Integer userId) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("delete from cart where bookId = ? and userId = ?");
			ps.setInt(1, bookId);
			ps.setInt(2, userId);
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
