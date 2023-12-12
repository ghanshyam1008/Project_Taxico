package com.incapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incapp.modal.DAO;

/**
 * Servlet implementation class Enquiry
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			DAO db=new DAO();
			String result=db.checkAdminLogin(id, password);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result==null) {
				session.setAttribute("msg", "Admin Login Failed!");
				response.sendRedirect("index.jsp");
			}else {
				session.setAttribute("adminName",result );
				response.sendRedirect("AdminHome.jsp");
			}
		}catch (Exception e) {
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
