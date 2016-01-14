package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itparis.b3.associations.metier.AssociationMetier;

public class AssociationDAOTest {
	
		@Test
		public void getObjectAssociationDAO() {
		    assertEquals(1 ,AssociationMetier.getAssociation(1).getId());
		}
		
	    @Test
		public void getListAssociationDAO() {
		    assertEquals(2 ,AssociationMetier.getListAssociations(0, "", "").size());
		}
		
		
}
