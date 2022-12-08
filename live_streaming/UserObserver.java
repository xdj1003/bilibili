package com.wechat.IM;

import com.wechat.friend.User;

import java.util.List;

public class UserObserver extends Observer{
    public User user;
    public UserObserver(User user, Session session) {
        this.user = user;
        this.session = session;
    }

    public void update() {
        List<String> list = session.getHistory();
        System.out.printf("%s 已收到消息: %s%n", user.getUsername(), list.get(list.size() - 1));
    }
}
