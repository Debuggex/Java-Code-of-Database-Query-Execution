package com.company.model;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_PATH = "jdbc:sqlite:C:\\Users\\Sahbaan Alam\\Desktop\\JavaCodes\\Music_DB\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTISTS = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_NAME = "name";
    public static final String COLUMN_SONGS_ALBUMS = "album";

    private Connection conn;

    public boolean OPEN() {
        try {
            conn = DriverManager.getConnection(CONNECTION_PATH);
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR: "
                    + e.getMessage());
            return false;
        }
    }

    public void CLOSED() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG \n" + e.getMessage());

        }
    }

    public List<Artist> QueryArtist(String Order) {

        StringBuilder Query = new StringBuilder("SELECT * FROM ");
        Query.append(TABLE_ARTISTS);

        if (Order != null) {
            Query.append(" ORDER BY " + COLUMN_ARTISTS_NAME);
            Query.append(" COLLATE NOCASE " + Order);
        }


        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.toString());) {


            List<Artist> ARTISTLIST = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(COLUMN_ARTISTS_ID));
                artist.setName(resultSet.getString(COLUMN_ARTISTS_NAME));
                ARTISTLIST.add(artist);
            }

            return ARTISTLIST;

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public List<String> QueryAlbumForArtist(String artistname, String order){
        StringBuilder Query =new StringBuilder("SELECT ");
        Query.append(TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" FROM "+TABLE_ALBUMS+" INNER JOIN "+TABLE_ARTISTS+" ON "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTISTS+"="+TABLE_ARTISTS
        +"."+COLUMN_ARTISTS_ID+" WHERE "+TABLE_ARTISTS+"."+COLUMN_ARTISTS_NAME+"="+"\""+artistname+"\"");

        if (order!=null){
            Query.append(" ORDER BY "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" COLLATE NOCASE "+order);

        }
        System.out.println("SQL Statement : "+Query+" has been executed Successfully!");

        try(Statement statement=conn.createStatement();
        ResultSet resultSet=statement.executeQuery(Query.toString())){

            List<String> albums=new ArrayList<>();
            while (resultSet.next()){
                albums.add(resultSet.getString(1));
            }

            return albums;

        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
            return null;
        }

    }
}
