package com.wechat.official.account.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.wechat.ApplicationContext;
import com.wechat.database.DataBaseAware;
import com.wechat.database.IndexedDB;
import com.wechat.friend.Friend;
import com.wechat.official.account.entity.Article;
import com.wechat.official.account.entity.Comment;
import com.wechat.official.account.entity.Like;
import com.wechat.official.account.util.CommandEnum;
import com.wechat.util.Logger;
import com.wechat.util.LoggerFactory;

/**
 * 用户操作上下文。理解为 MVC 应用中的 Service 层。
 */
public class FriendContext implements DataBaseAware<Integer, FriendContext> {

	private static final Logger LOG = LoggerFactory.getLogger(FriendContext.class);

	private Friend friend;

	private final List<Integer> articleIdList = new ArrayList<>();

	// Dao Objects
	private final IndexedDB<Integer, Article> articleDB = ApplicationContext.getInstance().getArticleDB();
	private final IndexedDB<Integer, Friend> friendDB = ApplicationContext.getInstance().getFriendDB();
	private final IndexedDB<Integer, Like> likeDB = ApplicationContext.getInstance().getLikeDB();
	private final IndexedDB<Integer, Comment> commentDB = ApplicationContext.getInstance().getCommentDB();

	public FriendContext(Friend friend) {
		this.friend = friend;
	}
	
	public Friend getFriend() {
		return friend;
	}
	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public List<Article> getArticles() {
		LOG.INFO(String.format("%s 正在接收新信息", friend.getUsername()));
		List<Article> res = new ArrayList<>();
		IndexedDB<Integer, Article> db = ApplicationContext.getInstance().getArticleDB();
		for(Integer id: articleIdList) {
			res.add(db.findById(id));
		}
		return res;
	}

	public void onUpdate(CommandEnum command, Integer id) {
		switch (command) {
			case ADD -> {
				LOG.INFO(String.format(" %s 收到信信息 id = %d", friend.getUsername(), id));
				this.articleIdList.add(id);
			}
			case DELETE -> {
				LOG.INFO(String.format("Name = %s Article id = %d is deleted", friend.getUsername(), id));
				this.articleIdList.remove(id);
			}
		}
	}

	/**
	 * 评论
	 */
	public void postComment(String content, Integer articleId) {

		Comment comment = new Comment(this.getFriend().getUserId(), articleId, content);	

		if (! articleDB.exist(comment.getArticleId())) {
			throw new RuntimeException("Article not found!");
		} else if (! friendDB.exist(comment.getUserId())) {
			throw new RuntimeException("User not found!");
		}

		commentDB.insert(comment);
		LOG.INFO(String.format("Comment %s has been inserted successfully!", comment.toString()));

	}

	/**
	 * 点赞
	 */
	public void postLike(Integer articleId) {

		Like like = new Like(this.getFriend().getUserId(), articleId);

		if (! articleDB.exist(like.getArticleId())) {
			throw new RuntimeException("Article not found!");
		} else if (! friendDB.exist(like.getFriendId())) {
			throw new RuntimeException("User not found!");
		}

		likeDB.insert(like);
		LOG.INFO(String.format("Like Entity %s has been inserted successfully!", like.toString()));
	}

	/**
	 * 无索引，全表扫描找出该用户的所有 like
	 */
	public List<Like> findAllLikes() {
		return likeDB.findAll().stream().filter(x-> Objects.equals(x.getFriendId(), friend.getUserId())).collect(Collectors.toList());
	}

	/**
	 * 无索引，全表扫描找出该用户的所有 comment
	 */
	public List<Comment> findAllComments() {
		return commentDB.findAll().stream().filter(x->{return Objects.equals(x.getUserId(), friend.getUserId());}).collect(Collectors.toList());
	}

	/**
	 * 订阅
	 */
	public void subscribe(OfficialAccountContext context) {
		context.register(this.getFriend().getUserId());
	}

	/**
	 * 取消订阅
	 */
	public void unsubscribe(OfficialAccountContext context) {
		context.unregister(this.getFriend().getUserId());
	}

	@Override
	public Integer getKey() {
		return this.friend.getUserId();
	}
}
