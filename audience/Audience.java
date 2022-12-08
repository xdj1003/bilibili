package bilibili.audience;

import java.util.concurrent.atomic.AtomicInteger;

import bilibili.database.DataBaseAware;

public class Audience extends User implements Relationship,DataBaseAware<Integer, Audience> {
    
    private String tel;
    private String photo;

    private static AtomicInteger autoIncrement = new AtomicInteger(0);

    public Audience() {
        this.userId = autoIncrement.getAndIncrement();
    }
    
    public Audience(String username) {
        this.userId = autoIncrement.getAndIncrement();
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }
    public String getTel() {
        return tel;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public Integer getKey() {
        return this.userId;
    }
    
    public void add() {
    	System.out.println("关注主播");
    }
}