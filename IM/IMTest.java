package bilibili.IM;

import com.bilibili.friend.Friend;
import com.bilibili.friend.User;

import java.util.ArrayList;
import java.util.List;

public class IMTest {
    public static void main(String[] args) {
        System.out.println("站内私信功能测试：");
        SessionFactory sf = new SessionFactory();
        Friend f1 = new Friend("gxy");
        Friend f2 = new Friend("xdj");
        Friend f3 = new Friend("hjh");
        List<User> friend_list = new ArrayList<User>();
        friend_list.add(f1);
        friend_list.add(f2);
        Session is = sf.createSession(f3);
        Session gs = sf.createSession(friend_list);

        System.out.println("IndividualSession Test");
        is.sendMessage("您有一条新消息.");
        System.out.println();
        System.out.println("GroupSession Test");
        gs.sendMessage("您有一条新消息.");
    }
}
