package com.itparis.b3.associations.common;

public class DBSpecialValues {
	
      public static class PreparedLike {
    	  
    	private String value;
    	
    	public PreparedLike (String newValue) {
    		value = newValue;
    	}
    	
		@Override
		public String toString() {
			return "%"+value+"%";
		}  
    	  
      }
}
