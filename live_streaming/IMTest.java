package com.wechat.IM;

import com.wechat.friend.Friend;
import com.wechat.friend.User;

import java.util.ArrayList;
import java.util.List;

public class IMTest {
    public static void main(String[] args) {
        SessionFactory sf = new SessionFactory();
        Friend f1 = new Friend("lss");
        Friend f2 = new Friend("wyx");
        Friend f3 = new Friend("jhw");
        List<User> friend_list = new ArrayList<User>();
        friend_list.add(f1);
        friend_list.add(f2);
        Session is = sf.createSession(f3);
        Session gs = sf.createSession(friend_list);

        System.out.println("IndividualSession Test");
        is.sendMessage("A new message.");
        System.out.println();
        System.out.println("GroupSession Test");
        gs.sendMessage("A new message again.");
    }
}
