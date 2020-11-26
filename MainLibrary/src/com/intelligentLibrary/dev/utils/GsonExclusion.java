package com.intelligentLibrary.dev.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusion implements ExclusionStrategy {
	
	String[] attr; 
	
	public GsonExclusion(String[] attr) {
		this.attr = attr;
	}

	

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		for (String attr : attr) {  
            if (attr.equals(arg0.getName())) {  
                return true;  
            }  
        }  
        return false; 
	}



	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
