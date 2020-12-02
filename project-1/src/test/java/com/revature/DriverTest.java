package com.revature;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.dao.RoleDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Role;
import com.revature.models.User;

public class DriverTest {

	private UserDaoImpl userDao;
	private RoleDaoImpl roleDao;
	
	@Before
	public void setUp() throws Exception {
		userDao = mock(UserDaoImpl.class);
		roleDao = mock(RoleDaoImpl.class);
		//reimbursmentDao
		//reimbursmentTypeDao
		//reimbursmentStatusDao
	}

	//TODO: VV this doesnt currently do anything - revisit upon adding services VV
	@Test
	public void newRoleAndUserTest() {
		Role testRole1 = new Role("TestRole1");
		User testUser1 = new User("newTestUsername1", "changeme", "test", "test", "test1_email", testRole1, null, null);
		
		when(roleDao.create(testRole1)).thenReturn(true);
		when(userDao.create(testUser1)).thenReturn(true);
	
		assertEquals(true, roleDao.create(testRole1));
		assertEquals(true, userDao.create(testUser1));
		
		verify(roleDao).create(testRole1);
		verify(userDao).create(testUser1);
	}

}
