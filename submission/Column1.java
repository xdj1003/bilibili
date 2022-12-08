package com.bilibili.submission;

public class Column1 implements ColumnSubmission{
	// String fsname="";
	// boolean ifLike=false;
	// String fscontent="";
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
		return "Column1 is beautiful"; 
	}
}


//finish