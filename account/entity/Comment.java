package bilibili.official.account.entity;

import java.util.concurrent.atomic.AtomicInteger;

import bilibili.database.DataBaseAware;

public class Comment implements DataBaseAware<Integer, Comment> {

	private final Integer id;

	private static final AtomicInteger autoIncrement = new AtomicInteger(0);

	private String content;
	private final Integer articleId;
	private final Integer userId;

	public Comment(Integer userId, Integer articleId, String content) {
		this.id = autoIncrement.getAndIncrement();
		this.userId = userId;
		this.articleId = articleId;
		this.content = content;
	}

	@Override
	public Integer getKey() {
		return id;
	}

	public String getContent() {
		return content;
	}
	public Integer getId() {
		return id;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public Integer getUserId() {
		return userId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("评论{UID=%s-ArticleID=%s==>%s}", this.userId, this.articleId, this.content);
	}
}
