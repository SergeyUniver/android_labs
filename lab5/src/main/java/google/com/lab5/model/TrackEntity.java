package google.com.lab5.model;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Sergey on 19.12.2016.
 */
public class TrackEntity {

    private String name;
    private int playcount;
    private int listeners;
    private String mbid;
    private Artist artist;
    private ArrayList<JsonObject> image;

    public TrackEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ArrayList<String> getImage() {
        ArrayList<String> list = new ArrayList<>();
        for(JsonObject jsonObject : image){
            list.add(jsonObject.get("#text").getAsString());
        }
        return list;
    }

    public void setImage(ArrayList<JsonObject> image) {
        this.image = image;
    }
}
