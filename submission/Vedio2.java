package com.bilibili.submission;

public class Vedio2 implements VedioSubmission{
	String user_name="";
	String fsname="";
	String SubmitTime ="";
	String fscontent="";
	public void publish(String usrname,String name,String time) {
		// fsname=name;
		user_name=usrname;
		fsname=name;
		SubmitTime=time;
		System.out.println("Publish Successful");
	}
	public void delete() {
		fscontent="";
		System.out.println("Delete Successful");
	}
	public void edit(String content) {
		fscontent=content;
		System.out.println("Edit Successful");
	}
	public String getNews() {
		return "Vedio2 is beautiful"; 
	}
}

//dfsioadsl
