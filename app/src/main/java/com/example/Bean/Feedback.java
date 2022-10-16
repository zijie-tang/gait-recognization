package com.example.Bean;

public class Feedback {
    private int id;
    private int UserId;
    private String info;

    public Feedback(int userId, String info) {
        UserId = userId;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", UserId=" + UserId +
                ", info='" + info + '\'' +
                '}';
    }
}
