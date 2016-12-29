package google.com.lab5.model;

import java.util.ArrayList;

/**
 * Created by Sergey on 19.12.2016.
 */

public class TopTracks {

    private ArrayList<TrackEntity> track;

    public TopTracks() {
    }

    public TopTracks(ArrayList<TrackEntity> track) {
        this.track = track;
    }

    public ArrayList<TrackEntity> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<TrackEntity> track) {
        this.track = track;
    }
}
