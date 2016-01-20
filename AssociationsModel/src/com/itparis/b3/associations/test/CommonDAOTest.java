package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.apprun.Entry;
import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.metier.CommonMetier;

public class CommonDAOTest {
	
	@Parameter
	Entry e = new Entry ();
	
	@Before
	public void SetConnParams () {
		e.setConnectionArgs();
	}

	@Test
	public void DAOTest () {
	    
		assertEquals(1, CommonMetier.checkExistingField(DB.TypeUtilisateurs, "id", 1));
		
		assertEquals(0, CommonMetier.checkExistingField(DB.TypeUtilisateurs, "id", -1));
		
		
	}

}
