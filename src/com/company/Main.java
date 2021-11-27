package com.company;

import com.company.model.Artist;
import com.company.model.DataSource;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource=new DataSource();
        dataSource.OPEN();

        List<Artist> artists=dataSource.QueryArtist(null);
        if (artists==null){
            System.out.println("Nothing Found. Try Again!");
        }else {
            for (Artist artist:artists){
                System.out.println("ID: "+artist.getId()+"\t"+"Name: "+artist.getName());
            }
        }

        List<String> albums=dataSource.QueryAlbumForArtist("Iron Maiden","ASC");
        if (albums==null){
            System.out.println("Nothing Found. Try Again!");
        }else {
            for (String albumforartist:albums){
                System.out.println("Name : "+albumforartist.toString());
            }
        }

        dataSource.CLOSED();
    }
}
