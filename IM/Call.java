package com.wechat.IM;

public class Call {
    private static class CallHolder {
        private static Call instance = new Call();
    }

    public static Call getInstance() {
        return CallHolder.instance;
    }

    public void makeCall(IndividualSession session) {
        System.out.printf("与 %s 进行了通话%n", session.getObserver().user.getUsername());
    }

    public void makeCall(GroupSession session) {
        System.out.printf("与 ");
        for(UserObserver observer: session.getObservers()) {
            System.out.printf("%s ", observer.user.getUsername());
        }
        System.out.printf("进行了通话%n");
    }
}
