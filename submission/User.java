package com.bilibili.submission;
// public class WechatUser extends Observer
public class user extends Observer{
	public user(String name,VedioSubmission v1,ColumnSubmission c1) {
		this.name=name;
		this.subColumn1=v1;
		//this.subPhoto2=p2;
		this.subVedio1=c1;
		//this.subVedio2=v2;
	}
	@Override
	public void update() {
		System.out.printf("%s 已获得专栏Column1的信息:%s%n",name,subPhoto1.getNews());
		//System.out.printf("%s 已获得朋友圈Photo2的信息:%s%n",name,subPhoto2.getNews());
		System.out.printf("%s 已获得视频Vedio1的信息:%s%n",name,subVedio1.getNews());
		//System.out.printf("%s 已获得朋友圈Vedio2的信息:%s%n",name,subVedio2.getNews());
	}
}



//finish