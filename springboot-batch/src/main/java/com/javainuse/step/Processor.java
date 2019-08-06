package com.javainuse.step;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		
		String encryptData = encrypt(data).toString();
		return encryptData;
	}

	 public static StringBuffer encrypt(String text) 
	    { 
	    	 int s = 4;
	        StringBuffer result= new StringBuffer(); 
	  
	        for (int i=0; i<text.length(); i++) 
	        { 
	            if (Character.isUpperCase(text.charAt(i))) 
	            { 
	                char ch = (char)(((int)text.charAt(i) + 
	                                  s - 65) % 26 + 65); 
	                result.append(ch); 
	                
	            } 
	            else
	            { 
	                char ch = (char)(((int)text.charAt(i) + 
	                                  s - 97) % 26 + 97); 
	                result.append(ch); 
	            } 
	        } 
	        return result; 
	    } 
}
