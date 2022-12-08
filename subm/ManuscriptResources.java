package com.bilibili.subm;
import java.util.*;  
public class ManuscriptResources {
	public static AbstractSubmission getsubmission(String name) {
		if(name.equalsIgnoreCase("vediosubmission")) {
			System.out.println("Produce vediosubmission");
			return new AbstractVedio();
		}
		else if(name.equalsIgnoreCase("Columnsubmission")) {
			System.out.println("Produce Columnsubmission");
			return new AbstractColumn();
			//return null;
		}
		else {
			System.out.println("No Item");
			return null;
		}
	}
}

//finish




