package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.StatusDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.util.miscUtil;

public class ResolvedReimbursementServlet {
	
	private static Logger log = Logger.getLogger(ResolvedReimbursementServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession(false);
		PrintWriter printwriter = response.getWriter();
		StatusDaoImpl statusDao = new StatusDaoImpl();
		
		if (session != null && session.getAttribute("username") != null && miscUtil.getUserAccessLevel((String)session.getAttribute("role")) == 2) {
			Status status = statusDao.selectByStatus("Resolved");
			System.out.println("Test");
			List<Reimbursement> reimbursements = status.getReimbursements();
			response.setStatus(200);
			response.setContentType("application/json");
			
			log.info("displaying all resolved reimbursements belonging to " + (String) session.getAttribute("username"));
			
			for (int i = 0; i < reimbursements.size(); i++) {
				if (reimbursements.get(i).getAuthor().getUsername().equals((String)session.getAttribute("username"))) {
					String s = mapper.writeValueAsString(reimbursements.get(i));
					JsonNode jsonObject = mapper.readTree(s);
					printwriter.print(jsonObject);
					printwriter.flush();
				}
			}
		} else if (session != null && session.getAttribute("username") != null && miscUtil.getUserAccessLevel((String)session.getAttribute("role")) >= 3) {
			Status status = statusDao.selectByStatus("Resolved");
			
			List<Reimbursement> reimbursements = status.getReimbursements();
			response.setStatus(200);
			response.setContentType("application/json");
			
			log.info("displaying all resolved reimbursements - manager access");
			
			for (int i = 0; i < reimbursements.size(); i++) {
				String s = mapper.writeValueAsString(reimbursements.get(i));
				JsonNode jsonObject = mapper.readTree(s);
				printwriter.print(jsonObject);
				printwriter.flush();
			}
		}
	}
}
