package com.test.bean;

public class Academy {
    private String id;
    private String academy;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Academy() {
    }

    public Academy(String id, int count) {
        this.id = id;
        this.count = count;
    }
}
