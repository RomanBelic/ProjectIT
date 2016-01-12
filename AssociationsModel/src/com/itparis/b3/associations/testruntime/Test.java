package com.itparis.b3.associations.testruntime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.dao.AssociationDAO;
import com.itparis.b3.associations.dao.UserDAO;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
	HashMap <String,String> params = Utilities.LoadConfig("config.txt",">");
	
		
	System.out.println(params.get("Log"));	
	
		
		

	}

}
