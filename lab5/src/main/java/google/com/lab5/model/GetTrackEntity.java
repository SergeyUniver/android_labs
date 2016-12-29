package google.com.lab5.model;

/**
 * Created by Sergey on 19.12.2016.
 */

public class GetTrackEntity {

    private TopTracks toptracks;

    public GetTrackEntity() {
    }

    public GetTrackEntity(TopTracks topTracks) {
        this.toptracks = topTracks;
    }

    public TopTracks getTopTracks() {
        return toptracks;
    }

    public void setTopTracks(TopTracks topTracks) {
        this.toptracks = topTracks;
    }
}
