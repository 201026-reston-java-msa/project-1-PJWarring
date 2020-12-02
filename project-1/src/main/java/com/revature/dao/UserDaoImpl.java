package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl {
	public boolean create(User user) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(user); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("from User where userid = " + id, User.class).list();
		
		if(userList.size()==0) return null;
		return userList.get(0);
	}
	
	public User selectByUsername(String name) {
		Session ses = HibernateUtil.getSession();

		List<User> userList = ses.createQuery("from User where username like'"+name+"'", User.class).list();
		
		if(userList.size()==0) return null;
		return userList.get(0);
	}

	public boolean update(User user) {
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		ses.update(user);
		tx.commit();
		return true;
	}
	
	public boolean delete(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete User where id = :ID");
		query.setParameter("ID", user.getUserid());
		query.executeUpdate();
		transaction.commit();
		return true;
	}
}
