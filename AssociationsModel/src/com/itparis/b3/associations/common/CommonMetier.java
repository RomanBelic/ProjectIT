package com.itparis.b3.associations.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.common.DB.Queries;

public class CommonMetier {
	
	public static int checkExistingField (Table table, String field, Object value) {
		String req = Queries.CheckExistingFieldPQuery(table, field);
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(value);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);	
		int rows = 0;
		try {
			rows = CommonDAO.class.newInstance().existsTableField(params, req);
		} 
		catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		return rows;
	}

}
