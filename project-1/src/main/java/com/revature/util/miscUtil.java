package com.revature.util;

import java.sql.Timestamp;

import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

public class miscUtil {

	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static boolean login(String username, String password) {
		UserDaoImpl userDao = new UserDaoImpl();
		User tempUser = userDao.selectByUsername(username);
		if (tempUser == null) return false;
		else if (tempUser.getPassword().equals(password)) return true;
		else return false;
	}
	
	public static int getUserAccessLevel(String role) {
		if (role == null) role = "role does not exist";
		switch (role.toLowerCase()) {
		case "employee":
			return 2;
		case "manager":
			return 3;
		case "admin":
			return 4;
		default:
			return 0;
		}
	}
}
