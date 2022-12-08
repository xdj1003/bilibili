package bilibili.pay;

public abstract class Changer implements Decorate{
	
	public Decorate decorate;
	
	public Changer(Decorate decorate) {
		this.decorate = decorate;
	}
	
	public void redEnvelopePay(double money) {
		decorate.redEnvelopePay(money);
	}
}
