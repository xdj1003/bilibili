package bilibili.subm;

public class AbstractColumn extends AbstractSubmission{
	@Override
	public VedioSubmission getVedioSubmission(String name) {
		///
		return null;
	}
	@Override
	public ColumnSubmission getColumnSubmission(String name) {
		System.out.println("This is CL");
		if(name.equalsIgnoreCase("Column1")) {
			System.out.println("Column1 is ok");
			return new Column1();
		}else if(name.equalsIgnoreCase("Column2")) {
			System.out.println("Column2 is ok");
			return new Column2();
		}
		else {
			System.out.println("No true Vedio");
			return null;
		}

	}
}



//finish

//finish