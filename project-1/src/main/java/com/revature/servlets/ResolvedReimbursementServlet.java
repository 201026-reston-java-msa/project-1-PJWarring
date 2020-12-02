package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.StatusDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Status;

public class ResolvedReimbursementServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter printwriter = response.getWriter();
		
		StatusDaoImpl statusDao = new StatusDaoImpl();
		Status status = statusDao.selectByStatus("Resolved");
		
		List<Reimbursement> reimbursements = status.getReimbursements();
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
