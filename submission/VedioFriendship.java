package com.bilibili.submission;

public interface VedioSubmission {
	// String fsname="";
	// boolean ifLike=false;
	// String fscontent="";
	String user_name="";
	String fsname="";
	String SubmitTime ="";
	String fscontent="";
	public void publish(String usrname,String name,String time);
	public void delete() ;
	public void edit(String content);
	public String getNews();
}

//ssdfksdjf
