package bilibili.pay;

import java.util.Scanner;

public class RedEnvelopeTest {
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
//		Decorate decorate = new RedEnvelope();
//		decorate.redEnvelopePay();
//		System.out.println();
//		
//		SetColor setcolor = new SetColor(decorate);
//		setcolor.redEnvelopePay();
//		setcolor.setColor();
//		System.out.println();
//		
//		SetWords setwords = new SetWords(decorate);
//		setwords.redEnvelopePay();
//		setwords.setWords();
//		System.out.println();
		
		Decorate decorate = new RedEnvelope();
		System.out.println("请输入支付金额：");
		double money =in.nextDouble();
		decorate.redEnvelopePay(money);
		SetColor setcolor = new SetColor(decorate);
		setcolor.setColor();
		SetWords setwords = new SetWords(decorate);
		setwords.setWords();
		
		System.out.println();
		in.close();
	}
	
}
