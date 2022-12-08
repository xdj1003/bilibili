package com.bilibili.subm;
import java.util.*;  
public class submissionDemo {
 public static void main(String[] args){  
	   AbstractSubmission a=ManuscriptResources.getsubmission("vediosubmission");
	   VedioSubmission b=a.getVedioSubmission("Vedio2");
	   AbstractSubmission d=ManuscriptResources.getsubmission("Columnsubmission");
	   ColumnSubmission c=d.getColumnSubmission("Photo2");
	   //b.publish("dasdas");
	   Observer user1=new User("hjh",b,c);
	   user1.update();
    }  
}
