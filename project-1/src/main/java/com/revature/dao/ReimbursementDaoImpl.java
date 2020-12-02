package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.PGTimestamp;

import com.revature.models.Reimbursement;
import com.revature.servlets.HomeServlet;
import com.revature.util.HibernateUtil;
import com.revature.util.miscUtil;

public class ReimbursementDaoImpl {
	
	private static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	
	public boolean create(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		reimbursement.setSubmitted(miscUtil.getCurrentTime());
		ses.save(reimbursement); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			log.info("created reimbursement " + reimbursement + ".");
			return true;
		} catch (Exception e) {
			log.info("failed to create reimbursement " + reimbursement + ".");
			e.printStackTrace();
		}
		return false;
	}
	
	public Reimbursement selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimbursmentList = ses.createQuery("from Reimbursement where reimbursmentid = " + id, Reimbursement.class).list();
		
		if(reimbursmentList.size()==0) return null;
		return reimbursmentList.get(0);
	}
	
	//TODO
	public boolean update(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession();
	
		Transaction tx = ses.beginTransaction();
		ses.update(reimbursement);
		try {
			tx.commit();
			log.info("updated reimbursement " + reimbursement + ".");
			return true;
		} catch (Exception e) {
			log.debug("failed to update reimbursement " + reimbursement + ".");
			return false;
		}
	}
	
	public boolean delete(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete Reimbursement where id = :ID");
		query.setParameter("ID", reimbursement.getReimbursmentid());
		query.executeUpdate();
		try {
			transaction.commit();
			log.info("deleted reimbursement " + reimbursement + ".");
			return true;
		} catch (Exception e) {
			log.debug("failed to delete reimbursement " + reimbursement + ".");
			return false;
		}
	}
}
