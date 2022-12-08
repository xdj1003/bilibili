package bilibili.subm;

public interface ColumnSubmission {
	String user_name="";
	String fsname="";
	String SubmitTime ="";
	String fscontent="";
	public void publish(String usrname,String name,String time);
	public void delete() ;
	public void edit(String content);
	public String getNews();
}




//finish