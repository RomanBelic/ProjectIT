package com.itparis.b3.associations.common;

public class DBSpecialValues {
	
      public static class PreparedLikeClause {
    	  
    	private String value;
    	
    	public PreparedLikeClause (String newValue) {
    		value = newValue;
    	}
    	
		@Override
		public String toString() {
			return "%"+value+"%";
		}  
    	  
      }
}
