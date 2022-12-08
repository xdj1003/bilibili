package bilibili.Live;

import bilibili.audience.Audience;
import bilibili.audience.User;

import java.util.ArrayList;
import java.util.List;

public class LivestreamingTest {
    public static void main(String[] args) {
        SessionFactory sf = new SessionFactory();
        Audience f1 = new Audience("观众1");
        Audience f2 = new Audience("观众2");
        Audience f3 = new Audience("主播");
        List<User> audience_list = new ArrayList<User>();
        audience_list.add(f1);
        audience_list.add(f2);
        Session is = sf.createSession(f3);
        Session gs = sf.createSession(audience_list);
        System.out.println("直播功能测试：");
        System.out.println("LiveStreamingMessage Test");
        is.sendMessage("主播打得不错.");
        System.out.println();
        System.out.println("LiveStreaming Test");
        gs.sendMessage("主播在打游戏.");
    }
}
