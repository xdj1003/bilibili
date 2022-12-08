package bilibili.official.account.service;

import java.util.List;
import java.util.stream.Collectors;

import bilibili.ApplicationContext;
import bilibili.database.IndexedDB;
import bilibili.friend.Friend;
import bilibili.official.account.context.FriendContext;
import bilibili.official.account.context.OfficialAccountContext;
import bilibili.official.account.entity.Article;
import bilibili.official.account.entity.Comment;
import bilibili.official.account.entity.Like;
import bilibili.official.account.entity.OfficialAccount;

/**
 * 一些调用接口的方法，避免繁琐的数据库交互。单例。
 * 先获得 Context ，然后调用 Context 里面的方法进行操作
 */
public class OfficialAccountService {

	private static class SingletonHolder {
		private static final OfficialAccountService instance = new OfficialAccountService();
	}

	ApplicationContext ctx = ApplicationContext.getInstance();

	// Dao Objects
	private final IndexedDB<Integer, Like> likeDB = ApplicationContext.getInstance().getLikeDB();
	private final IndexedDB<Integer, Comment> commentDB = ApplicationContext.getInstance().getCommentDB();
	private final IndexedDB<Integer, Friend> friendDB = ctx.getFriendDB();
	private final IndexedDB<Integer, FriendContext> friendContextDB = ctx.getFriendContextDB();
	private final IndexedDB<Integer, OfficialAccountContext> officialContextDB = ctx.getOfficialAccountContextDB();
	private final IndexedDB<Integer, Article> articleDB = ctx.getArticleDB();
	private final IndexedDB<Integer, OfficialAccount> officialAccountDB = ctx.getOfficialAccountDB();

	public static OfficialAccountService getInstance() {
		return SingletonHolder.instance;
	}
	
	/**
	 * 插入朋友，同时插入对应的上下文
	 */
	public void insertFriend(Friend friend) {
		friendDB.insert(friend);
		friendContextDB.insert(new FriendContext(friend));
	}

	/**
	 * 同时插入对应的上下文
	 */
	public void insertOfficialAccount(OfficialAccount officialAccount) {
		OfficialAccount insert = officialAccountDB.insert(officialAccount);
		officialContextDB.insert(new OfficialAccountContext(officialAccount));
	}

	/**
	 * 根据 id 获得用户操作面板上下文
	 */
	public FriendContext getFriendContextById(int id) {
		return friendContextDB.findById(id);
	}

	/**
	 * 根据 id 获得操作面板上下文
	 */
	public OfficialAccountContext getOfficialContextById(int id) {
		return officialContextDB.findById(id);
	}

	/**
	 * 获得指定文章下的所有点赞
	 */
	public List<Like> findAllLikesByArticleId(Integer articleId) {
		return likeDB.findAll().stream().filter(x->{return x.getArticleId() == articleId;}).collect(Collectors.toList());
	}

	/**
	 * 获得指定文章下的所有评论
	 */
	public List<Comment> findAllCommentsByArticleId(Integer articleId) {
		return commentDB.findAll().stream().filter(x->{return x.getArticleId() == articleId;}).collect(Collectors.toList());
	}

	/**
	 * 获得文章的具体信息
	 */
	public String getArticleDetail(Integer articleId) {
		Article article = articleDB.findById(articleId);
		return String.format("\nTitle: %s\nContent: %s\nLike: %s\nComments: %s\n",
			article.getId(),
			article.getTitle(),
			findAllLikesByArticleId(articleId),
			findAllCommentsByArticleId(articleId));
	}
}
