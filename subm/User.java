package bilibili.subm;
// public class WechatUser extends Observer
public class user extends Observer{
	public user(String name,VedioSubmission v1,ColumnSubmission c1) {
		this.name=name;
		this.subColumn1=c1;
		this.subVedio1=v1;
	}
	@Override
	public void update() {
		System.out.printf("%s 已获得专栏Column1的信息:%s%n",name,subColumn1.getNews());
		System.out.printf("%s 已获得视频Vedio1的信息:%s%n",name,subVedio1.getNews());
	}
}



//finish