package server.entity;

import java.io.Serializable;

public class OrderRecord implements Serializable {
    int user_id;
    String user_name; // Joined from database via SELECT query
    int content_id;
    String content_name; // Joined from database via SELECT query
    int amount;

    public int getUserId() {
        return user_id;
    }

    public int getContentId() {
        return content_id;
    }

    public int getAmount() {
        return amount;
    }

    public String getUserName() {
        return user_name;
    }

    public String getContent_name() {
        return content_name;
    }

    public OrderRecord(int user_id, String user_name, int content_id, String content_name, int amount) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.content_id = content_id;
        this.content_name = content_name;
        this.amount = amount;
    }
}