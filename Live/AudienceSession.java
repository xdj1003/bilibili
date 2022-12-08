package bilibili.Live;

import java.util.ArrayList;
import java.util.List;

public class AudienceSession implements Session{
    private UserObserver observer;
    private List<String> history = new ArrayList<String>();

    public void sendMessage(String message) {
        history.add(message);
        System.out.printf("观众发送弹幕: %s%n", message);
        notifyAllObservers();
    }

    public UserObserver getObserver() {
        return observer;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void attach(UserObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyAllObservers() {
        observer.update();
    }
}
