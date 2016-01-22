package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.xmlparser.XMLWriter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonTest {
	
	@Parameter
	List <Association> lstAssoc = new ArrayList <Association> ();
	
	@Parameter
	Association a1 = new Association ();

	@Parameter
	Association a2 = new Association ();

	@Parameter
	Association a3 = new Association ();
	
	@Parameter
	Association a4 = new Association ();
	
	@Parameter
	User u = new User ();
	
	@Before
	public void setEnvironement () {
		a1.setId(1);
		a1.setLibelle("Association");
		a2.setId(2);
		a2.setLibelle("Association");
		a3.setId(3);
		a3.setLibelle("Association Dummy");
		a4.setId(4);
		a4.setLibelle("Association Dummy");
		
		u.setId(50);
		u.setNom("Tilquin");
		u.setPrenom("Max");
		u.setTelephone("0623994421");
		u.setAdresse("5 rue de Paris");
		u.setStatut(1);
		u.type.setId(3);
		u.type.setLibelle("Utilisateur");
		
		lstAssoc.add(a1);
		lstAssoc.add(a2);
		lstAssoc.add(a3);
		lstAssoc.add(a4);	
	}

	@SuppressWarnings("unchecked")
	@Test
	public void CommonMethodsTest() {
		assertEquals("24-12-2000", Utilities.convertDBDateToFRDate("2000-12-24", '-'));
		
		assertEquals("2000-12-24", Utilities.convertFRDateToDBDate("24/12/2000", '/'));
		
		assertEquals("test", Utilities.decodeStringFrom64Base(Utilities.encodeStringTo64Base("test")));
		
		assertEquals("2000-12-24", Utilities.convertFRDateToDBDate("24/12/2000", '/'));
		
		lstAssoc = (List<Association>) Utilities.findInList(lstAssoc, "Libelle", "Association");
		
		assertEquals(2, lstAssoc.size());
	}
	
	@Test
	public void TestXMLParser () {
		
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		String fname = dateFormat.format(date);
		XMLWriter parser = new XMLWriter (u);
		
		assertEquals (fname, parser.generateFile());
	}
}
