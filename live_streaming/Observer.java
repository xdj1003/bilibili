package com.wechat.IM;

public abstract class Observer {
    public Session session;

    public abstract void update();
}
