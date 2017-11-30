package com.onest.webshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onest.webshop.bean.Book;
import com.onest.webshop.bean.BookType;
import com.onest.webshop.common.DbConnection;

public class BookDao {

	public List<Book> selectAll() {
		List<Book> list = new ArrayList<Book>();
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps = connection.prepareStatement("select * from book,booktype where book.typeId=booktype.typeId");
			rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setPrice(rs.getDouble("price"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setImgUrl(rs.getString("imgUrl"));
				BookType bookType = new BookType();
				bookType.setTypeId(rs.getInt("typeId"));
				bookType.setTypeName(rs.getString("typeName"));
				book.setBookType(bookType);
				list.add(book);
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

	public boolean addBook(String bookName,String typeName,Double price,String author,String publisher ) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("insert into book(typeId,bookName,price,author,publisher)value((select typeId from booktype where typeName=?),?,?,?,?)");
			ps.setString(1,typeName);
			ps.setString(2,bookName);
			ps.setDouble(3,price);
			ps.setString(4,author);
			ps.setString(5,publisher);
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
	public boolean dropBook(int bookId) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("delete from book where bookId = ?");
			ps.setInt(1, bookId);
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
	public boolean alterBook(Integer bookId,String bookName,String typeName,Double price,String author,String publisher) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("update book set bookName=?,typeId=(select typeId from booktype where typeName=?),price=?,author=?,publisher=? where bookId=?");
			ps.setString(1, bookName);
			ps.setString(2, typeName);
			ps.setDouble(3,price);
			ps.setString(4,author);
			ps.setString(5, publisher);
			ps.setInt(6, bookId);
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
	
	public Book selectOneBook(Integer bookId) {
		Connection connection = DbConnection.getConnection();
		Book book = null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps = connection.prepareStatement("select * from book,booktype where book.typeId=booktype.typeId && bookId=?");
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			if(rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setPrice(rs.getDouble("price"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setImgUrl(rs.getString("imgUrl"));
				BookType bookType = new BookType();
				bookType.setTypeId(rs.getInt("typeId"));
				bookType.setTypeName(rs.getString("typeName"));
				book.setBookType(bookType);
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
		return book;
	}
}
