package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.apprun.Entry;
import com.itparis.b3.associations.metier.AuthMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthDAOTest {
	
	@Parameter
	Entry e = new Entry ();
	
	@Before
	public void SetConnParams () {
		e.setConnectionArgs();
	}

	@Test
	public void T1_getStatusAuthDAOTest() {
		assertEquals (true, AuthMetier.CheckAuthentification("log", "mdp"));
	}
	
	@Test
	public void T2_getUserAuthDataDAOTest() {
		assertEquals (1, AuthMetier.getUserAuthData("log", "mdp").getIdUser());
	}

}
