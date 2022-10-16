package com.example.Bean;

public class ACC_info {
    private int id;
    private String Timestamp;
    private String Acc_x;
    private String Acc_y;
    private String Acc_z;
    private int UserId;

    public ACC_info(String timestamp,String acc_x, String acc_y, String acc_z, int userId) {
        Timestamp=timestamp;
        Acc_x = acc_x;
        Acc_y = acc_y;
        Acc_z = acc_z;
        UserId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.Timestamp = timestamp;
    }

    public String getAcc_x() {
        return Acc_x;
    }

    public void setAcc_x(String acc_x) {
        Acc_x = acc_x;
    }

    public String getAcc_y() {
        return Acc_y;
    }

    public void setAcc_y(String acc_y) {
        Acc_y = acc_y;
    }

    public String getAcc_z() {
        return Acc_z;
    }

    public void setAcc_z(String acc_z) {
        Acc_z = acc_z;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "ACC_info{" +
                "id=" + id +
                ",Timestamp='" + Timestamp +'\''+
                ", Acc_x='" + Acc_x + '\'' +
                ", Acc_y='" + Acc_y + '\'' +
                ", Acc_z='" + Acc_z + '\'' +
                ", UserId=" + UserId +
                '}';
    }
}
