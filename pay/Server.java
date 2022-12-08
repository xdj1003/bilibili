package bilibili.pay;

public class Server {
	
	private DiscountStrategy strategy;
	
	public void setStrategy(DiscountStrategy strategy) {
		this.strategy = strategy;
	}
	
	public double getPrice(double price) {
		return strategy.calprice(price);
	}
}
