package com.bilibili.subm;

public class Vedio1 implements VedioSubmission{
	String user_name="";
	String fsname="";
	String SubmitTime ="";
	String fscontent="";
	public void publish(String usrname,String name,String time) {
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
		return "Vedio1 is beautiful"; 
	}
}


//fisdfhj