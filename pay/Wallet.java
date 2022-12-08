package bilibili.pay;

public class Wallet {

	private PayToolsAdapter adapter = null;
	
	public void pay(String type, double money) {
		if(type.equalsIgnoreCase("B币")||type.equalsIgnoreCase("银行卡")) {
			adapter = new PayToolsAdapter(type);
			adapter.pay(type,money);
		}
		else {
			System.out.println("无效的支付类型"+type);
			System.exit(1);
		}
	}
}
