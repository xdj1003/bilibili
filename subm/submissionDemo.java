package com.bilibili.subm;
import java.util.*;  

public class submissionDemo {
 public static void main(String[] args){  
	System.out.println("投稿功能测试：");
	   AbstractSubmission a=ManuscriptResources.getsubmission("vediosubmission");
	   VedioSubmission b=a.getVedioSubmission("Vedio2");
	   AbstractSubmission d=ManuscriptResources.getsubmission("Columnsubmission");
	   ColumnSubmission c=d.getColumnSubmission("Column2");
	   //b.publish("dasdas");
	   Observer user1=new user("hjh",b,c);
	   user1.update();
    }  
}
