package com.bilibili.submission;

public class AbstractVedio extends AbstractFriendship{
	@Override
	public VedioSubmission getVedioSubmission(String name) {
		System.out.println("This is Vedio");
		if(name.equalsIgnoreCase("Vedio1")) {
			System.out.println("Vedio1 is ok");
			return new Vedio1();
		}else if(name.equalsIgnoreCase("Vedio2")) {
			System.out.println("Vedio2 is ok");
			return new Vedio2();
		}
		else {
			System.out.println("No true Vedio");
			return null;
		}
	}
	
	@Override
	public ColumnSubmission getColumnSubmission(String name) {

		return null;
	}
}



//finish
