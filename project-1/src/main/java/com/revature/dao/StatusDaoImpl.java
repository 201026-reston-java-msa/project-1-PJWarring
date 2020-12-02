package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Status;
import com.revature.util.HibernateUtil;

public class StatusDaoImpl {
	
	private static Logger log = Logger.getLogger(StatusDaoImpl.class);
	
	public boolean create(Status status) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(status); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			log.info("created status " + status + ".");
			return true;
		} catch (Exception e) {
			log.info("failed to create status " + status + ".");
			e.printStackTrace();
		}
		return false;
	}
	
	public Status selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<Status> statusList = ses.createQuery("from Status where statusid = " + id, Status.class).list();
		
		if(statusList.size()==0) return null;
		return statusList.get(0);
	}
	
	public Status selectByStatus(String status) {
		Session ses = HibernateUtil.getSession();
		
		List<Status> statusList = ses.createQuery("from Status where status like '" + status + "'", Status.class).list();
		
		if(statusList.size()==0) return null;
		return statusList.get(0);
	}
	
	public boolean delete(Status status) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete Status where id = :ID");
		query.setParameter("ID", status.getStatusid());
		query.executeUpdate();
		try {
			transaction.commit();
			log.info("deleted status " + status + ".");
			return true;
		} catch (Exception e) {
			log.info("failed to delete status " + status + ".");
			return false;
		}
	}
}
