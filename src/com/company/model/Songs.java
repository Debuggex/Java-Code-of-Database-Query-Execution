package com.company.model;

public class Songs {

    private int id;
    private int track;
    private String name;
    private int albums_id;

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

    public int getAlbums_id() {
        return albums_id;
    }

    public void setAlbums_id(int albums_id) {
        this.albums_id = albums_id;
    }
}
