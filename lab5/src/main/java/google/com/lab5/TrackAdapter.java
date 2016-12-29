package google.com.lab5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sergey on 19.12.2016.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    private ArrayList<Track> trackList;
    private Context context;

    public TrackAdapter(ArrayList<Track> trackList, Context context){
        this.trackList = trackList;
        this.context = context;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        holder.bind(trackList.get(position));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void setTrackList(ArrayList<Track>  trackList) {
        this.trackList = trackList;
    }

    public void clearItem() {
        trackList = new ArrayList<>();
    }

    class TrackViewHolder extends RecyclerView.ViewHolder{

        private final Context context;
        private ImageView image;
        private TextView trackName;
        private TextView songerName;
        private TextView listeners;
        private TextView playCount;

        public TrackViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            initView();

        }

        private void initView() {
            image = (ImageView) itemView.findViewById(R.id.track_image);
            trackName = (TextView) itemView.findViewById(R.id.track_name_tv);
            songerName = (TextView) itemView.findViewById(R.id.songer_name_tv);
            listeners = (TextView) itemView.findViewById(R.id.listeners_count_tv);
            playCount = (TextView) itemView.findViewById(R.id.play_count_tv);
        }

        public void bind(Track track) {
            if(track.getImageURL() != null || track.getImageURL().length() > 0) {
                Picasso.with(context).load(track.getImageURL()).into(image);
            }
            trackName.setText(track.getName());
            songerName.setText(track.getArtist());
            listeners.setText(String.valueOf(track.getListeners()));
            playCount.setText(String.valueOf(track.getPlaycount()));
        }
    }

}
