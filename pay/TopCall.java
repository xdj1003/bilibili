package bilibili.pay;

import java.util.Scanner;

public class TopCall {
	
	Scanner in = new Scanner(System.in);
	
	public void pay() {
		System.out.println("请选择您的服务:1-购买商品，2-B币充值，3-B币提现，4-银行卡绑定");
		int x = in.nextInt();
		switch(x){
			case 1:{
				ServerUser user = new ServerUser();
				System.out.println("请输入商品价格：");
				double price = in.nextInt();
				double newprice = 0;
				System.out.println("请选择是否使用优惠：1-使用大会员优惠，2-不使用优惠");
				int choice = in.nextInt();
				if (choice == 1) {
					DiscountStrategy smallstrategy = new Discount();
					newprice = user.buy(price, smallstrategy);
				}
				else if(choice == 2) {
					newprice = price;
				}
				else {
					System.out.println("非法输入！");
					System.exit(1);
				}
				System.out.println("您需要支付的金额为："+newprice);
				Wallet wallet = new Wallet();
				System.out.println("请输入支付类型：银行卡/B币");
				String type = in.next();
				type += in.nextLine();
				wallet.pay(type,newprice);
				break;
			}
			case 2:{
				Change change = new Change();
				change.recharge();
				break;
			}
			case 3:{
				Change change = new Change();
				change.withDrawal();
				break;
			}
			case 4:{
				BankCardFactory bankfactory = new BankCardFactory();
				BankCard bankcard = null;
				System.out.println("请选择绑定：:1-农业银行卡，2-中国银行卡，3-交通银行卡");
				int choice = in.nextInt();
				switch(choice) {
				case 1:{
					bankcard = bankfactory.getCard("agriculturalbankcard");
					break;
					}
				case 2:{
					bankcard = bankfactory.getCard("chinabankcard");
					break;
					}
				case 3:{
					bankcard = bankfactory.getCard("communicationsbankcard");
					break;
					}
				default:
					System.out.println("非法输入!");
					System.exit(1);
				}
				bankcard.bind();
				break;
			}
			default:System.out.println("非法输入!");
					System.exit(1);
		}
	}
}
