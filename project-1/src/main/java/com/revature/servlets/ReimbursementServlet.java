package com.revature.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StatusDaoImpl;
import com.revature.dao.TypeDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.util.miscUtil;

public class ReimbursementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		if (session != null && miscUtil.getUserAccessLevel((String) session.getAttribute("role")) >= 2) {
			request.getRequestDispatcher("reimbursementForm.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		ReimbursementDaoImpl reimbursementDao = new ReimbursementDaoImpl();
		UserDaoImpl userDao = new UserDaoImpl();
		StatusDaoImpl statusDao = new StatusDaoImpl();
		TypeDaoImpl typeDao = new TypeDaoImpl();
		
		if (session == null) {
			session.setAttribute("redirect_reason", "user doesnt have access.");
			response.sendRedirect("login");
		} else {
			if (miscUtil.getUserAccessLevel((String) session.getAttribute("role")) >= 2) {
				//the user cant access this file
				response.setStatus(403);
				//tell the user they dont have access
			}
		}
		
		//Id is handled by the database
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Timestamp submitted = miscUtil.getCurrentTime();
		//resolved is handled when the reimbursement is resolved
		String description = request.getParameter("description");
		User author = userDao.selectByUsername((String) session.getAttribute("username"));
		//resolver is handled when the reimbursement is resolved
		Status status = statusDao.selectByStatus("Pending");
		Type type = typeDao.selectByType(request.getParameter("type"));
		if (status == null || type == null) response.setStatus(400);
		
		Reimbursement new_reimburesement = new Reimbursement(amount, submitted, null, description, author, null, status, type);
		if (reimbursementDao.create(new_reimburesement)) {
			response.sendRedirect("home");
		} else {
			response.setStatus(400);
		}
	}
}
