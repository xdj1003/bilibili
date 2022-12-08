package com.wechat.official.account.context;

import java.util.ArrayList;
import java.util.List;

import com.wechat.ApplicationContext;
import com.wechat.database.DataBaseAware;
import com.wechat.database.IndexedDB;
import com.wechat.official.account.entity.Article;
import com.wechat.official.account.entity.Comment;
import com.wechat.official.account.entity.Like;
import com.wechat.official.account.entity.OfficialAccount;
import com.wechat.official.account.service.OfficialAccountService;
import com.wechat.official.account.util.CommandEnum;

public class OfficialAccountContext implements DataBaseAware<Integer, OfficialAccountContext> {

	private final OfficialAccount account;

	private final IndexedDB<Integer, Article> articleDB = ApplicationContext.getInstance().getArticleDB();
	private final IndexedDB<Integer, Comment> commentDB = ApplicationContext.getInstance().getCommentDB();
	private final IndexedDB<Integer, Like> likeDB = ApplicationContext.getInstance().getLikeDB();


	public OfficialAccountContext(OfficialAccount oa) {
		this.account = oa;
	}

	private final List<Integer> subscribers = new ArrayList<>();

	// == getter ==

	public OfficialAccount getAccount() {
		return account;
	}

	public List<Integer> getSubscribers() {
		return subscribers;
	}

	// == common ==

	public void postArticle(Article article) {
		article.setAuthorId(account.getAccountId());
		articleDB.insert(article);
		IndexedDB<Integer, FriendContext> friendContextDb = ApplicationContext.getInstance().getFriendContextDB();
		for (Integer id : subscribers) {
			friendContextDb.findById(id).onUpdate(CommandEnum.ADD, article.getId());
		}
	}
	
	public void deleteArticle(Article article) {
		Integer articleId = article.getId();
		articleDB.delete(articleId);
		// delete likes and comments as well
		for (Comment comment : OfficialAccountService.getInstance().findAllCommentsByArticleId(articleId)) {
			commentDB.delete(comment.getId());
		}
		for (Like like : OfficialAccountService.getInstance().findAllLikesByArticleId(articleId)) {
			likeDB.delete(like.getId());
		}

		IndexedDB<Integer, FriendContext> friendDb = ApplicationContext.getInstance().getFriendContextDB();
		for (Integer id : subscribers) {
			friendDb.findById(id).onUpdate(CommandEnum.DELETE, id);
		}
	}

	public void register(Integer friendId) {
		subscribers.add(friendId);
	}

	public void unregister(Integer friendId) {
		subscribers.remove(friendId);
	}

	@Override
	public Integer getKey() {
		return account.getAccountId();
	}
}