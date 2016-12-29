package google.com.lab5;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import google.com.lab5.model.GetTrackEntity;
import google.com.lab5.model.TrackEntity;
import google.com.lab5.network.BaseAsyncOperation;
import google.com.lab5.network.GetTopTrackOperation;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.songer_et)
    EditText songerET;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ProgressDialog newProgressDialog;
    private TrackAdapter adapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new TrackAdapter(new ArrayList<Track>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.search_button)
    public void onSearchButtonClicked(){
        final String artist = songerET.getText().toString();
        GetTopTrackOperation operation = new GetTopTrackOperation(new BaseAsyncOperation.ApiAsyncCallback<GetTrackEntity>() {
            @Override
            public void onOperationSuccess(GetTrackEntity answer) {
                hideProgressDialog();
                updateList(answer);
            }

            @Override
            public void onOperationFailed(int responseCode) {
                hideProgressDialog();
                tryLoadFromDB(artist);
                showErrorToast(responseCode);

            }
        }, artist);
        operation.execute();
        showProgressDialog("Please, wait", false);
    }

    private void tryLoadFromDB(String artist) {
        RealmResults<Track> result = realm.where(Track.class).equalTo("artist", artist).findAll();
        ArrayList<Track> list = new ArrayList<>();
        list.addAll(result);
        updateRecyclerView(list);
    }

    private void showErrorToast(int responseCode) {
        Toast.makeText(this, "Please, check your internet connection", Toast.LENGTH_SHORT).show();
    }

    private void updateList(GetTrackEntity track) {
        if(track.getTopTracks() == null || track.getTopTracks().getTrack() == null){
            showNothingFountToast();
            clearRecyclerView();
            return;
        }
        ArrayList<Track> listTrack = makeUsualTrack(track.getTopTracks().getTrack());
        saveNewData(listTrack);
        updateRecyclerView(listTrack);
    }

    private void clearRecyclerView() {
        adapter.clearItem();
        adapter.notifyDataSetChanged();
    }

    private void updateRecyclerView(ArrayList<Track> listTrack) {
        adapter.setTrackList(listTrack);
        adapter.notifyDataSetChanged();
    }

    private void saveNewData(ArrayList<Track> listTrack) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(listTrack);
        realm.commitTransaction();
        realm.close();
    }

    private void showNothingFountToast() {
        Toast.makeText(this, "Nothing found", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Track> makeUsualTrack(ArrayList<TrackEntity> tracks) {
        ArrayList<Track> listTrack = new ArrayList<>();
        for(TrackEntity trackEntity : tracks){
            listTrack.add(new Track(trackEntity.getName(), trackEntity.getPlaycount(), trackEntity.getListeners(),
                    trackEntity.getMbid(), getImageUrl(trackEntity), trackEntity.getArtist().getName()));
        }
        return listTrack;
    }

    private String getImageUrl(TrackEntity trackEntity) {
        if(trackEntity.getImage() != null && trackEntity.getImage().size() > 2) {
            return trackEntity.getImage().get(2);
        }else{
            return "";
        }
    }

    public void showProgressDialog(String message, boolean cancelable) {
        if (newProgressDialog == null) {
            newProgressDialog = new ProgressDialog(this);
            newProgressDialog.setMessage(message);
            newProgressDialog.setCancelable(cancelable);
        }
        newProgressDialog.show();
    }

    public void hideProgressDialog() {
        if(newProgressDialog != null)
            newProgressDialog.dismiss();
    }


}
