package bilibili.pay;

import java.util.Scanner;

public class Change implements PayTools{
	private double balance = 300;
	private int password = 123456;
	
	BankCardFactory bankfactory = new BankCardFactory();
	BankCard bankcard = null;
	
	Scanner in = new Scanner(System.in);
	
	public void recharge() {
		System.out.println("请选择银行卡:1-农业银行卡，2-中国银行卡，3-交通银行卡");
		int x = in.nextInt();
		switch(x) {
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
		if(bankcard.getCardID() != 0) {
			System.out.println("请输入需要充值金额：");
			double money = in.nextDouble();
			System.out.println("请输入银行卡密码：");
			int pw = in.nextInt();
			if(pw == bankcard.getPw()) {
				if(money <= bankcard.getBalance()) {
					System.out.println("当前零钱余额为:"+balance);
					balance += money;
					bankcard.setBalance(bankcard.getBalance()-money);
					System.out.println("零钱余额更新为:"+balance);
				}
				else {
					System.out.println("余额不足！");
				}
			}
			else {
				System.out.println("密码错误！");
			}
		}
		else {
			System.out.println("您未绑定银行卡");
		}
	}
	
	public void withDrawal() {
		System.out.println("请选择提现到:1-农业银行卡，2-中国银行卡，3-交通银行卡");
		int x = in.nextInt();
		switch(x) {
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
		if(bankcard.getCardID() != 0) {
			System.out.println("请输入需要提现金额：");
			double money = in.nextDouble();
			System.out.println("请输入零钱密码：");
			int pw = in.nextInt();
			if(pw == password) {
				if(money <= balance) {
					System.out.println("当前零钱余额为:"+balance);
					balance -= money;
					System.out.println("零钱余额更新为:"+balance);
					bankcard.setBalance(bankcard.getBalance()+money);
					System.out.println("银行卡到账:"+money+"元");
				}
				else {
					System.out.println("当前零钱余额为:"+balance);
					System.out.println("余额不足！");
				}
			}
			else {
				System.out.println("密码错误！");
			}
		}
		else {
			System.out.println("您未绑定银行卡");
		}
		
	}
	
	public void balancePay(double money) {
		System.out.println("请输入零钱密码:");
		int pw = in.nextInt();
		if(pw == password) {
			if(money <= balance) {
				balance -= money;
				System.out.println("支付成功，零钱余额为："+balance);
			}
			else {
				System.out.println("当前零钱余额为:"+balance);
				System.out.println("余额不足");
			}
		}
		else {
			System.out.println("密码错误！");
		}
	}
	
	public int getPw() {
		return password;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void cardPay(double money) {
		
	}
}
