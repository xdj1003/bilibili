package bilibili.pay;

public interface BankCard extends PayTools {
	
	void cardPay(double money);
	void bind();
	int getPw();
	void setBalance(double balance);
	double getBalance();
	int getCardID();
}
