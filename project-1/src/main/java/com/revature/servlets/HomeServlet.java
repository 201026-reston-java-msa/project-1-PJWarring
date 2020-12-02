package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Role;
import com.revature.util.miscUtil;

public class HomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		if (session != null) {
			Role userRole = new Role((String) session.getAttribute("role"));
			if (miscUtil.getUserAccessLevel(userRole.getRole()) == 2) {
				request.getRequestDispatcher("employeeHome.html");
			} else if (miscUtil.getUserAccessLevel(userRole.getRole()) >= 3) {
				request.getRequestDispatcher("managerHome.html").forward(request, response);
			} else {
				request.getRequestDispatcher("noHome.html").forward(request, response);
			}
		} else {
			response.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
