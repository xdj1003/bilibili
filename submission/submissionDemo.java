package com.bilibili.submission;
import java.util.*;  
public class submissionDemo {
 public static void main(String[] args){  
	   AbstractSubmission a=ManuscriptResources.getsubmission("VedioFriendship");
	   VedioSubmission b=a.getVedioSubmission("Vedio2");
	   AbstractSubmission d=ManuscriptResources.getsubmission("PhotoFriendship");
	   ColumnSubmission c=d.getColumnSubmission("Photo2");
	   //b.publish("dasdas");
	   Observer user1=new User("20051810",b,c);
	   user1.update();
    }  
}
