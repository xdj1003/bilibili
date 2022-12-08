package bilibili.official.account.entity;

import java.util.concurrent.atomic.AtomicInteger;

import bilibili.database.DataBaseAware;
import bilibili.official.account.context.OfficialAccountContext;

public class OfficialAccount implements DataBaseAware<Integer, OfficialAccount> {

	private String accountName;
	private Integer accountId = autoIncrement.getAndIncrement();
	private final OfficialAccountContext context;

	private static final AtomicInteger autoIncrement = new AtomicInteger(0);

	public OfficialAccount(String accountName) {
		this.context = new OfficialAccountContext(this);
	}

	// === Getter ===

	public Integer getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public OfficialAccountContext getContext() {
		return context;
	}

	// === Setter ===

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public Integer getKey() {
		return this.accountId;
	}

}
