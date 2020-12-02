package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Type;
import com.revature.util.HibernateUtil;

public class TypeDaoImpl {
	public boolean create(Type type) {
		Session ses = HibernateUtil.getSession(); // capture the session
		Transaction tx = ses.beginTransaction(); // perform an operation on DB
		
		ses.save(type); // use the save() session method to perform an insert operation
		try {
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Type selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<Type> typeList = ses.createQuery("from Type where typeid = " + id, Type.class).list();
		
		if(typeList.size()==0) return null;
		
		return typeList.get(0);
	}
	
	public Type selectByType(String type) {
		Session ses = HibernateUtil.getSession();
		
		List<Type> typeList = ses.createQuery("from Type where type like '" + type + "'", Type.class).list();
		
		if(typeList.size()==0) return null;
		return typeList.get(0);
	}
	
	public boolean delete(Type type) {
		Session ses = HibernateUtil.getSession();
		Transaction transaction = ses.beginTransaction();
		Query query = ses.createQuery("delete Type where id = :ID");
		query.setParameter("ID", type.getTypeid());
		query.executeUpdate();
		transaction.commit();
		return true;
	}
}
