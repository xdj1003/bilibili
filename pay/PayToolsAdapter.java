package bilibili.pay;

import java.util.Scanner;

public class PayToolsAdapter {
	
	private PayTools paytools = null;
	
	Scanner in = new Scanner(System.in);
	
	public PayToolsAdapter(String type){
		if(type.equalsIgnoreCase("B币")) {
			paytools = new Change();
		}
		else if(type.equalsIgnoreCase("银行卡")) {
		System.out.println("请选择银行卡：1-农业银行，2-中国银行，3-交通银行");
		int x = in.nextInt();
		switch(x) {
		case 1: paytools = new AgriculturalBankCard();
				break;
		case 2: paytools = new ChinaBankCard();
				break;
		case 3: paytools = new CommunicationsBankCard();
				break;
		default:System.out.println("非法输入！");
				System.exit(1);
			}
		}
	}
	
	public void pay(String type, double money){
		if(type.equalsIgnoreCase("B币")) {
			paytools.balancePay(money);
		}
		else if(type.equalsIgnoreCase("银行卡")) {
			paytools.cardPay(money);
		}
	}
}
