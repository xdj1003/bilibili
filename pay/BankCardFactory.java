package bilibili.pay;

public class BankCardFactory {
		
	public BankCard getCard(String bankcard) {
		
		if(bankcard.equalsIgnoreCase("AgriculturalBankCard")) {
			return new AgriculturalBankCard();
		}
		else if(bankcard.equalsIgnoreCase("CommunicationsBankCard")) {
			return new CommunicationsBankCard();
		}
		else if(bankcard.equalsIgnoreCase("ChinaBankCard")) {
			return new ChinaBankCard();
		}
		return null;
		
	}
}
