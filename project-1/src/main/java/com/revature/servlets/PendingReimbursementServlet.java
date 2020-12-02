package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StatusDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public class PendingReimbursementServlet extends HttpServlet {
	
	public static int counter = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter printwriter = response.getWriter();
		
		StatusDaoImpl statusDao = new StatusDaoImpl();
		Status status = statusDao.selectByStatus("Pending");
		
		List<Reimbursement> reimbursements = status.getReimbursements();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.setStatus(200);
		response.setContentType("application/json");
		
		for (int i = 0; i < reimbursements.size(); i++) {
			String s = mapper.writeValueAsString(reimbursements.get(i));
			System.out.println("Sent message " + counter);
			counter+=1;
			JsonNode jsonObject = mapper.readTree(s);
			printwriter.print(jsonObject);
			printwriter.flush();
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession(false);
		ReimbursementDaoImpl reimbursementDao = new ReimbursementDaoImpl();
		
		if (session != null && session.getAttribute("username") != null) {
			Reimbursement reimbursement = mapper.readValue(request.getInputStream(), Reimbursement.class);
			if (reimbursementDao.update(reimbursement)) {
				response.setStatus(204);
			} else {
				response.setStatus(400);
			}
		}
	}
}
