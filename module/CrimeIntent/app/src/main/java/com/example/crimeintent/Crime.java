package com.example.crimeintent;

public class Crime {
    String title;
    Integer id;
    String data;
    boolean isSolved;

    public Crime(String title, Integer id, String data, boolean isSolved) {
        this.title = title;
        this.id = id;
        this.data = data;
        this.isSolved = isSolved;
    }

    public Crime() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
