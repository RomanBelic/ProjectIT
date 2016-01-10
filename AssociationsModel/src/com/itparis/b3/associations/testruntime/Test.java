package com.itparis.b3.associations.testruntime;

import java.util.List;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.dao.AssociationDAO;
import com.itparis.b3.associations.dao.UserDAO;

public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		
		List<User> lstAssoc = UserDAO.class.newInstance().getListUser("");
		
		lstAssoc = (List<User>) Utilities.FindInList(lstAssoc, "Nom", "Gates");
		
		for (User a : lstAssoc) {
			System.out.println(a.getNom());
			
		}
		
		List<Association> lstass = AssociationDAO.class.newInstance().getListAssociation("");
		System.out.println(lstass.get(0).getId());
		
		int rows = ReqMetier.ExecuteUpdate(" Update utilisateurs SET nomUtilisateur = 0; asdasd");
		System.out.println(rows);
		

	}

}
