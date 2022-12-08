package bilibili.pay;

public class ServerUser {
	
	public double buy(double price,DiscountStrategy...strategy) {
		Server server = new Server();
		double newprice = price;
		for(DiscountStrategy str : strategy) {
			server.setStrategy(str);
			newprice = server.getPrice(newprice);
		}
		return newprice;
	}
}
