package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Role;
import com.revature.util.HibernateUtil;

public class RoleDaoImpl {
	public boolean create(Role role) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(role); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		Query query = ses.createQuery("delete Role where id = :ID");
		query.setParameter("ID", role.getRoleid());
		return query.executeUpdate() != 0; //returns true if one or more things were deleted
	}
}
