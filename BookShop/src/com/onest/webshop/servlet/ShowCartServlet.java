package com.onest.webshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onest.webshop.bean.Cart;
import com.onest.webshop.bean.User;
import com.onest.webshop.dao.CartDao;

/**
 * Servlet implementation class ShowCartServlet
 */
@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		Object totalAmount =  request.getSession().getAttribute("totalAmount");
//		request.setAttribute("totalAmount", totalAmount);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
		} else {
			Object cart = session.getAttribute("cart");
			CartDao cartDao = new CartDao();
			List<Cart> list = cartDao.selectMyCart(user.getUserId());
			Double totalAmount = 0.0;
			for (Cart cartlist:list) {		
				totalAmount += cartlist.getAmount().doubleValue();
			}
			request.setAttribute("totalAmount", totalAmount);
			request.setAttribute("cartList", list);
			request.getRequestDispatcher("showCart.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
