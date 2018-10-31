package com.nicootech.procoregit;

public class Model {
    private String title;
    private String number;
    private String id;
    private String state;

    public Model(String id, String state, String number, String title) {
        this.id = id;
        this.state = state;
        this.number = number;
        this.title = title;
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
}
