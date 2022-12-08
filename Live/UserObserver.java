package bilibili.Live;

import bilibili.audience.User;

import java.util.List;

public class UserObserver extends Observer{
    public User user;
    public UserObserver(User user, Session session) {
        this.user = user;
        this.session = session;
    }

    public void update() {
        List<String> list = session.getHistory();
        System.out.printf("%s 查看直播内容: %s%n", user.getUsername(), list.get(list.size() - 1));
    }
}
