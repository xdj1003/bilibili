package com.wechat.official.account;

import com.wechat.friend.Friend;
import com.wechat.ApplicationContext;
import com.wechat.database.IndexedDB;
import com.wechat.official.account.context.FriendContext;
import com.wechat.official.account.context.OfficialAccountContext;
import com.wechat.official.account.entity.Article;
import com.wechat.official.account.entity.OfficialAccount;
import com.wechat.official.account.service.OfficialAccountService;
import com.wechat.util.Logger;
import com.wechat.util.LoggerFactory;

/**
 * 方法调用 tutorial
 */
public class OfficialAccountShowCase {
	public static void main(String[] args) {

		ApplicationContext ctx = ApplicationContext.getInstance();

		IndexedDB<Integer, Friend> friendDB = ctx.getFriendDB();
		IndexedDB<Integer, FriendContext> friendContextDB = ctx.getFriendContextDB();
		IndexedDB<Integer, OfficialAccountContext> officialContextDB = ctx.getOfficialAccountContextDB();
		IndexedDB<Integer, Article> articleDB = ctx.getArticleDB();
		IndexedDB<Integer, OfficialAccount> officialAccountDB = ctx.getOfficialAccountDB();

		Logger logger = LoggerFactory.getLogger(OfficialAccount.class);

		// === 系统操作 ===
		// 系统设计的方案：
		
		// 使用 FriendContext，OfficialContext 进行操作
		// 具体内容都存入了数据库中，需要数据时，从 ApplicationContext 取得数据库，并直接进行增删改查操作。
		
		// 系统：添加用户
		friendDB.insert(new Friend("张三"));
		friendDB.insert(new Friend("李四"));
		friendDB.insert(new Friend("王麻"));

		// 系统：添加视频
		officialAccountDB.insert(new OfficialAccount("account1"));
		officialAccountDB.insert(new OfficialAccount("account2"));
		officialAccountDB.insert(new OfficialAccount("account3"));

		// 系统：添加用户操作上下文
		friendContextDB.insert(new FriendContext(friendDB.findById(0)));
		friendContextDB.insert(new FriendContext(friendDB.findById(1)));
		friendContextDB.insert(new FriendContext(friendDB.findById(2)));

		// 系统：添加简介上下文
		officialContextDB.insert(new OfficialAccountContext(officialAccountDB.findById(0)));
		officialContextDB.insert(new OfficialAccountContext(officialAccountDB.findById(1)));
		officialContextDB.insert(new OfficialAccountContext(officialAccountDB.findById(2)));

		// === 用户和视频操作 ===

		// 用户：订阅
		friendContextDB.findById(0).subscribe(officialContextDB.findById(0));
		friendContextDB.findById(0).subscribe(officialContextDB.findById(1));
		friendContextDB.findById(0).subscribe(officialContextDB.findById(2));

		// 文章发表
		officialContextDB.findById(0).postArticle(new Article("title1"));
		officialContextDB.findById(0).postArticle(new Article("title2"));
		officialContextDB.findById(0).postArticle(new Article("title3"));

		logger.INFO(friendContextDB.findById(0).getArticles());
		System.out.println("//收发信息");
		// 用户：点赞操作
		friendContextDB.findById(0).postLike(0);
		friendContextDB.findById(0).postLike(1);
		friendContextDB.findById(0).postLike(2);
		friendContextDB.findById(1).postLike(1);
		friendContextDB.findById(2).postLike(2);

		// 用户：进行评论
		friendContextDB.findById(0).postComment("content0", 0);
		friendContextDB.findById(0).postComment("content1", 0);
		friendContextDB.findById(0).postComment("content2", 1);
		friendContextDB.findById(0).postComment("content3", 1);

		// 用户：获得该用户的所有点赞
		logger.INFO(friendContextDB.findById(0).findAllLikes());
		System.out.println("//喜欢、评论测试及统计");
		// 用户：获得该用户的所有评论
		logger.INFO(friendContextDB.findById(0).findAllComments());
		System.out.println("//获得该用户的所有评论");
		// 文章删除
		officialContextDB.findById(0).deleteArticle(articleDB.findById(0));
		System.out.println("//文章删除");
		// 用户：获得用户订阅的所有视频
		logger.INFO(friendContextDB.findById(0).getArticles());
		System.out.println("//获得用户订阅的所有视频信息");
		// === 公共操作 ===

		// 所有人：查看某视频的具体信息
		logger.INFO(OfficialAccountService.getInstance().getArticleDetail(1));
		System.out.println("//查看某视频的具体信息");
		// 所有人：查看某视频的所有评论
		logger.INFO(OfficialAccountService.getInstance().findAllCommentsByArticleId(1));
		System.out.println("//查看某视频的所有评论");
		// 所有人：查看视频的所有点赞
		logger.INFO(OfficialAccountService.getInstance().findAllLikesByArticleId(1));
		System.out.println("//查看视频的所有点赞");
	}
}
