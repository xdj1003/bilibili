package bilibili.subm;

public class Column1 implements ColumnSubmission{
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
		return "Column1 is good";
	}
}


//finish