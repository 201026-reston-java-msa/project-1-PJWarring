package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Role;
import com.revature.servlets.HomeServlet;
import com.revature.util.HibernateUtil;

public class RoleDaoImpl {
	
	private static Logger log = Logger.getLogger(RoleDaoImpl.class);
	
	public boolean create(Role role) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(role); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			log.info("created role " + role + ".");
			return true;
		} catch (Exception e) {
			log.debug("failed to create role " + role + ".");
			e.printStackTrace();
		}
		return false;
	}
	
	public Role selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<Role> userList = ses.createQuery("from Role where roleid = " + id, Role.class).list();
		
		if(userList.size()==0) return null;
		return userList.get(0);
	}
	
	public boolean delete(Role role) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete Role where id = :ID");
		query.setParameter("ID", role.getRoleid());
		query.executeUpdate();
		try {
			transaction.commit();
			log.info("deleted role " + role + ".");
			return true;
		} catch (Exception e) {
			log.info("failed to delete role " + role + ".");
			return false;
		}
	}
}
