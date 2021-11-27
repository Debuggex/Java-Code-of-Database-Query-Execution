package com.company.model;

public class Albums {

    private int id;
    private String name;
    private int artists_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtists_id() {
        return artists_id;
    }

    public void setArtists_id(int artists_id) {
        this.artists_id = artists_id;
    }
}
