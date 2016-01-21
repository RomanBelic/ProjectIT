package com.itparis.b3.associations.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.AssociationDesc;
import com.itparis.b3.associations.beans.AssociationEvent;
import com.itparis.b3.associations.beans.Authentification;
import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.ParticipantEvents;
import com.itparis.b3.associations.beans.TypeUser;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.common.DBSpecialValues.PreparedLikeClause;

public class Utilities {
	
	public static String convertDBDateToFRDate (String dateString, char delimiter) {
		String formattedDate = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    try {
			Date date = formatter.parse(dateString);
			formatter = new SimpleDateFormat("dd"+delimiter+"MM"+delimiter+"yyyy");
		    formattedDate = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return formattedDate;
	}
	
	public static String convertFRDateToDBDate (String dateString, char delimiter) {
		String formattedDate = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd"+delimiter+"MM"+delimiter+"yyyy");
	    try {
			Date date = formatter.parse(dateString);
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			formattedDate = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return formattedDate;
	}
	
	public static boolean isNullOrEmptyList (List<?> lstObj) {
		if (lstObj == null){
			return true;
		}
		if (lstObj != null) {
			if (lstObj.size() <= 0) {
		     	return true;
			}
		}
		return false;
	}
	
	public static boolean isNullOrEmptyString (String str) {
		if (str == null){
			return true;
		}
		if (str != null) {
			str = str.trim();
			if (str.equals("")) {
		     	return true;
			}
		}
		return false;
	}
	
	public static String encodeStringTo64Base (String str) {
		byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
        return new String(bytesEncoded);
	}
	
	public static String decodeStringFrom64Base (String str) {
		byte[] valueDecoded = Base64.getDecoder().decode(str);
		return new String (valueDecoded);
	}
	
	public static void setError (Object o) {
		int errCode = -1;
		
		if (o instanceof Association){
		    ((Association) o).setId(errCode);
		}
		if (o instanceof User){
            ((User) o).setId(errCode);
		}
		if (o instanceof AssociationEvent){
		    ((AssociationEvent) o).setId(errCode);
		}
		if (o instanceof Authentification){
			((Authentification)o).setLog(errCode+"");
			((Authentification)o).setPass(errCode+"");
			((Authentification)o).setIdUser(errCode);
		}
		if (o instanceof FicheParticipant){
			((FicheParticipant)o).setId(errCode);
		}
		if (o instanceof ParticipantEvents){
			((ParticipantEvents)o).setIdUser(errCode);
		}
		if (o instanceof TypeUser){
			((TypeUser)o).setId(errCode);
		}
		if (o instanceof AssociationDesc){
			((AssociationDesc)o).setId(errCode);
		}
	}
	
	public static void setListError (List<? extends Object> lstObj) {
		
		if (!isNullOrEmptyList(lstObj)) {
			for (Object obj : lstObj) {
				setError (obj);
			}
		}
	}
	
	public static HashMap <Integer,Object> putParams (List<Object> lstPValues) {
		HashMap<Integer, Object> params = new HashMap <Integer, Object>();
		for (int i = 0; i < lstPValues.size(); i++ ) {
			params.put(i + 1, lstPValues.get(i));
		}
		return params;
	}
	/**
	 * 
	 * 
	 * @author Roman
	 * @
	 * @param st
	 * @param params
	 */
	public static void setSQLParams (PreparedStatement st, HashMap<Integer, Object> params) {
    	try {
		    for (Map.Entry<Integer, Object> p : params.entrySet()) {
		    	
		    	if (p.getValue() instanceof String)
		    	    st.setString(p.getKey(), (String)p.getValue());
		    	
		    	if (p.getValue() instanceof Integer)
			    	 st.setInt(p.getKey(), (Integer)p.getValue());
		    	
		    	if (p.getValue() instanceof Float)
			    	 st.setFloat(p.getKey(), (Float)p.getValue());
		    	
		    	if (p.getValue() instanceof Double)
			    	 st.setDouble(p.getKey(), (Double)p.getValue());

		    	if (p.getValue() instanceof Short)
			    	 st.setShort(p.getKey(), (Short)p.getValue());
		    	
		    	if (p.getValue() instanceof PreparedLikeClause){
			    	st.setString(p.getKey(), p.getValue().toString());
		    	}
		    }
    	}
    	catch (Exception e){e.printStackTrace();}
    }
	
	
	/**
	 * Filtre la liste d'objets
	 * par la valeur d'une propriete. <br>
	 * Dans les listes generiques il faut utiliser "value" comme argument de propriete
	 * @param  lstIn la liste d'entree
	 * @param  fieldName la propriete de classe 
	 * @param  value la valeur de la propriete recherchee
	 * 
	 * @return List <? extends Object> la liste de sortie
	 * 
	 */
	public static List <? extends Object> findInList (List<? extends Object> lstIn, String fieldName, Object value) {
		List <Object> tempList = new ArrayList<Object> ();
		if (!isNullOrEmptyList(lstIn)) {
			Field[] fields = lstIn.get(0).getClass().getDeclaredFields();
			
	        for (Object o : lstIn ){		
	        	try {
	        		for (Field f : fields) {
	        			f.setAccessible(true);
	        			if (f.getName().equals(fieldName)) {
	            			if (f.get(o) instanceof char[]){
	            				if (o == value){
	            					tempList.add(o);
	            				}
	            			}
	            			
	            			if (f.get(o).equals(value)) {
	        					tempList.add(o);
	        				}
	        			}
	        		}
	        	}
	        	catch (Exception e) {
	        		e.getMessage();
	        		e.printStackTrace();
	        	}
	        }
			lstIn = tempList;
		}
		return lstIn;
	}
	
    public static HashMap <String,String> loadConfig (String fileName, String separator) {
		
		BufferedReader br = null;
		HashMap<String,String> params = new HashMap <String,String> ();
	    try {
	    	String sCurrentLine;
	        br = new BufferedReader(new FileReader("./"+fileName));//file name with path
	        while ((sCurrentLine = br.readLine()) != null) {
	               String[] strArr = sCurrentLine.split(separator);
	               if (strArr [1].equals("Null"))
	            	   strArr [1] = "";
	               params.put (strArr[0],strArr[1]);
	        }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (br != null)br.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    return params;
	}
	
	
	
	
}
