package google.com.lab5.model;

/**
 * Created by Sergey on 19.12.2016.
 */
public class Artist {

    private String name;
    private String mbid;

    public Artist() {
    }

    public Artist(String name, String mbid) {
        this.name = name;
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
