package com.nicootech.procoregit;

public class Model {
    private String title;
    private String number;
    private String id;
    private String state;
    private String diff_url;

    public Model(String id, String diff_url, String state, String number, String title) {
        this.id = id;
        this.state = state;
        this.number = number;
        this.title = title;
        this.diff_url = diff_url;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDiff_url() { return diff_url; }
}
