package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StatusDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.miscUtil;

public class PendingReimbursementServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(PendingReimbursementServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession(false);
		PrintWriter printwriter = response.getWriter();
		StatusDaoImpl statusDao = new StatusDaoImpl();
		
		if (session != null && session.getAttribute("username") != null && miscUtil.getUserAccessLevel((String)session.getAttribute("role")) >= 3) {
			Status status = statusDao.selectByStatus("Pending");
			
			List<Reimbursement> reimbursements = status.getReimbursements();
			
			log.info("displaying all pending reimbursements belonging to " + (String) session.getAttribute("username"));
			
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.setStatus(200);
			response.setContentType("application/json");
			
			for (int i = 0; i < reimbursements.size(); i++) {
				if (reimbursements.get(i).getAuthor().getUsername().equals((String)session.getAttribute("username"))) {
					String s = mapper.writeValueAsString(reimbursements.get(i));
					JsonNode jsonObject = mapper.readTree(s);
					printwriter.print(jsonObject);
					printwriter.flush();
				}
			}
		} else if (session != null && session.getAttribute("username") != null && miscUtil.getUserAccessLevel((String)session.getAttribute("role")) >= 3) {
			Status status = statusDao.selectByStatus("Pending");
			
			List<Reimbursement> reimbursements = status.getReimbursements();
			
			log.info("displaying all pending reimbursements - manager access ");
			
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.setStatus(200);
			response.setContentType("application/json");
			
			for (int i = 0; i < reimbursements.size(); i++) {
				String s = mapper.writeValueAsString(reimbursements.get(i));
				JsonNode jsonObject = mapper.readTree(s);
				printwriter.print(jsonObject);
				printwriter.flush();
			}
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession(false);
		ReimbursementDaoImpl reimbursementDao = new ReimbursementDaoImpl();
		
		
		if (session != null && miscUtil.getUserAccessLevel((String) session.getAttribute("role")) >= 3) {
			Reimbursement reimbursement = mapper.readValue(request.getInputStream(), Reimbursement.class);
			if (reimbursementDao.update(reimbursement)) {
				log.info("user updated a reimbursement.");
				response.setStatus(204);
			} else {
				log.debug("user failed to upadate a reimbursement.");
				response.setStatus(400);
			}
		}
	}
}
