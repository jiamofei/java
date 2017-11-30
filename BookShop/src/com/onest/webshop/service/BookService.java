package com.onest.webshop.service;

import java.util.List;

import com.onest.webshop.bean.Book;
import com.onest.webshop.dao.BookDao;

public class BookService {
	public List<Book> getBooks() {
		BookDao bookDao = new BookDao();
		List<Book> list = bookDao.selectAll();
		return list;
	}
}
