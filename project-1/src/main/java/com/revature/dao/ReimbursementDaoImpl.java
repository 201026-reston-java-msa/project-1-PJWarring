package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.PGTimestamp;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;
import com.revature.util.miscUtil;

public class ReimbursementDaoImpl {
	public boolean create(Reimbursement reimbursment) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		reimbursment.setSubmitted(miscUtil.getCurrentTime());
		ses.save(reimbursment); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		tx.commit();
		return true;
	}
	
	public boolean delete(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete Reimbursement where id = :ID");
		query.setParameter("ID", reimbursement.getReimbursmentid());
		query.executeUpdate();
		transaction.commit();
		return true;
	}
}
