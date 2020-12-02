package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImpl;
import com.revature.models.Role;
import com.revature.util.miscUtil;

public class LoginServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(LoginServlet.class);
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		log.debug("doGet(LoginServlet)");
		
		if (session != null && session.getAttribute("username") != null) request.getRequestDispatcher("home").forward(request, response);
		String redirect_reason = null;
		if (session != null) redirect_reason = (String) session.getAttribute("redirect_reason");
		
		request.getRequestDispatcher("login.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		UserDaoImpl userDao = new UserDaoImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (miscUtil.login(username, password)) {
			session.setAttribute("username", username);
			Role userRole = userDao.selectByUsername(username).getRole();
			session.setAttribute("role", userRole.getRole());
			log.info("user " + username + " of role " + userRole + " has logged in.");
			response.sendRedirect("home");
		} else {
			log.debug("login failed - likely due to invalid credentials");
			response.setStatus(401);
			response.sendRedirect("login");
		}
	}
}