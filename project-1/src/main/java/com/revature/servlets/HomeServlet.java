package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.Role;
import com.revature.util.miscUtil;

import jdk.internal.org.jline.utils.Log;

public class HomeServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(HomeServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		if (session != null) {
			Role userRole = new Role((String) session.getAttribute("role"));
			if (miscUtil.getUserAccessLevel(userRole.getRole()) == 2) {
				log.debug("user accessed the employee home page.");
				request.getRequestDispatcher("employeeHome.html");
			} else if (miscUtil.getUserAccessLevel(userRole.getRole()) >= 3) {
				log.debug("user accessed the manager home page.");
				request.getRequestDispatcher("managerHome.html").forward(request, response);
			} else {
				log.warn("user has no designated home - the role " + userRole.getRole() + " is unknown.");
				request.getRequestDispatcher("noHome.html").forward(request, response);
			}
		} else {
			Log.debug("user tried to access home without logging in");
			response.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
