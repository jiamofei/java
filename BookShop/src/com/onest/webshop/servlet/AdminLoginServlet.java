package com.onest.webshop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onest.webshop.bean.Admin;
import com.onest.webshop.dao.AdminDao;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.login(adminName, password);
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect("admin_success.jsp");
		} else {
			request.setAttribute("errorMsg", "�û������������");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}

	}

}
