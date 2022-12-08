package bilibili.pay;

public class Discount implements DiscountStrategy {
	
	public double calprice(double price) {
		
		System.out.println("您为Bilibili大会员，享受9折优惠！");
		return price*0.9;
		
	}
	
}
