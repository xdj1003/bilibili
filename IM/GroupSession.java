package com.bilibili.IM;

import com.bilibili.friend.User;

import java.util.ArrayList;
import java.util.List;

public class GroupSession implements Session{
    private List<UserObserver> observers = new ArrayList<UserObserver>() ;
    private List<String> history = new ArrayList<String>();

    public void sendMessage(String message) {
        history.add(message);
        System.out.printf("已发送消息: %s%n", message);
        notifyAllObservers();
    }

    public List<UserObserver> getObservers() {
        return observers;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void attach(UserObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observer: observers) {
            observer.update();
        }
    }
}
