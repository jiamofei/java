package com.onest.webshop.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onest.webshop.bean.Book;
import com.onest.webshop.bean.Cart;
import com.onest.webshop.bean.User;
import com.onest.webshop.dao.BookDao;
import com.onest.webshop.dao.CartDao;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		request.setAttribute("bookId", bookId);
		HttpSession session = request.getSession();
		User user = (User)request.getSession().getAttribute("user");
		Integer count = Integer.parseInt(request.getParameter("count"));
		ArrayList<Cart>  cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		Double totalAmount = (Double) session.getAttribute("totalAmount");
		BookDao bookDao = new BookDao(); 
		CartDao cartDao = new CartDao();
		Integer userId = user.getUserId();
		BigDecimal big = null;
		if (cartList == null) {
			totalAmount = (double) 0;
			big = new BigDecimal(totalAmount);
			cartList = new ArrayList<Cart>();
			Book book = bookDao.selectOneBook(bookId);
			Cart cart = new Cart();
			cart.setPrice(book.getPrice());
			cart.setCount(count);
			cart.setAmount();
			cartDao.addCart(userId,book.getBookId(),book.getBookName(),count,book.getPrice(),cart.getAmount().doubleValue());
//			totalAmount = big.add(cart.getAmount()).doubleValue();			
		} else {
			Book book = bookDao.selectOneBook(bookId);
			Cart cart = new Cart();
			cart.setPrice(book.getPrice());
			cart.setCount(count);
			cart.setAmount();
//			big = new BigDecimal(totalAmount);
			cartDao.addCart(userId,bookId,book.getBookName(),count,book.getPrice(),cart.getAmount().doubleValue());
//			totalAmount = big.add(cart.getAmount()).doubleValue();
			cartList.add(cart);
		}
//		request.getSession().setAttribute("totalAmount", totalAmount);
		request.getSession().setAttribute("cartList", cartList);
		request.getRequestDispatcher("ShowCartServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
