package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Status;
import com.revature.util.HibernateUtil;

public class StatusDaoImpl {
	public boolean create(Status status) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(status); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		transaction.commit();
		return true;
	}
}
