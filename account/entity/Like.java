package bilibili.official.account.entity;

import java.util.concurrent.atomic.AtomicInteger;

import bilibili.database.DataBaseAware;

public class Like implements DataBaseAware<Integer, Like>{
	
	private final Integer id = autoIncrement.getAndIncrement();
	private final Integer friendId;
	private final Integer articleId;

	private static final AtomicInteger autoIncrement = new AtomicInteger(0);

	public Like(Integer friendId, Integer articleId) {
		this.friendId = friendId;
		this.articleId = articleId;
	}

	@Override
	public Integer getKey() {
		return this.id;
	}

	public Integer getFriendId() {
		return friendId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("喜欢{UID:%s-FriendID:%s}", articleId, friendId);
	}

}
