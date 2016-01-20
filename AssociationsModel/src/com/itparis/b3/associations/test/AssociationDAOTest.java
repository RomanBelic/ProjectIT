package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.apprun.Entry;
import com.itparis.b3.associations.metier.AssociationMetier;

public class AssociationDAOTest {
	
	@Parameter
	Entry e = new Entry ();
	
	@Before
	public void SetConnParams () {
		e.setConnectionArgs();
	}
	
	@Test
	public void getObjectAssociationDAO() {
		assertEquals("Microsoft" ,AssociationMetier.getAssociation(1).getLibelle());
		
		assertEquals(null ,AssociationMetier.getAssociation(0).getId());
	}
		
    @Test
	public void getListAssociationDAO() {
	    assertEquals(true, AssociationMetier.getListAssociations(0, "", "", "").size() > 0);
	    
	    assertEquals(0,AssociationMetier.getListAssociations(-1, "", "", "").size());
	}
    
    @Test
    public void updateAssociations() {
    	assertEquals (1, AssociationMetier.insertAssociation("BDA", 3, "Bureau des arts", 50, 
    			     "Association des arts et creativite"));
    	
    	assertEquals (1, AssociationMetier.deleteAssociationAndDesc(11));
    	  
    }
    
    
    
    
    
    
   
		
}
