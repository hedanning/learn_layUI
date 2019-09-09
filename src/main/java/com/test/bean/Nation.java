package com.test.bean;

public class Nation {
    private String id;
    private String nation;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Nation(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public Nation() {
    }
}
