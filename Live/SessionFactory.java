package bilibili.Live;

import bilibili.audience.User;

import java.util.List;

public class SessionFactory {
    public Session createSession(User user) {
        LiveStreaming session = new LiveStreaming();
        UserObserver observer = new UserObserver(user, session);
        session.attach(observer);
        return session;
    }

    public Session createSession(List<User> users) {
        AudienceSession session = new AudienceSession();
        for(User user: users) {
            UserObserver observer = new UserObserver(user, session);
            session.attach(observer);
        }
        return session;
    }
}
