package com.revature;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.PGTimestamp;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.RoleDaoImpl;
import com.revature.dao.StatusDaoImpl;
import com.revature.dao.TypeDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		RoleDaoImpl roleDao = new RoleDaoImpl();
		StatusDaoImpl statusDao = new StatusDaoImpl();
		TypeDaoImpl typeDao = new TypeDaoImpl();
		ReimbursementDaoImpl reimbursmentDao = new ReimbursementDaoImpl();
		
		System.out.println(roleDao.delete(new Role(4, "testRole")));
	}
}