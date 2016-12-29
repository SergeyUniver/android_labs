package google.com.lab5.network;

import java.io.IOException;

import google.com.lab5.model.GetTrackEntity;
import retrofit2.Response;

/**
 * Created by Sergey on 19.12.2016.
 */

public class GetTopTrackOperation extends BaseAsyncOperation<GetTrackEntity> {

    private final String artistName;

    public GetTopTrackOperation(ApiAsyncCallback<GetTrackEntity> callback, String artistName) {
        super(callback);
        this.artistName = artistName;
    }

    @Override
    protected ServerAnswer<GetTrackEntity> doInBackground(Void... params) {
        try {
            Response<GetTrackEntity> response = ApiUtils.createGetInfoService(ApiService.class).getTopTracks(artistName, getApiKey(), getDefaultFormat()).execute();
            if(response.isSuccessful()) {
                return new ServerAnswer<>(response.body(), response.code());
            }else{
                return new ServerAnswer<>(response.code());
            }
        } catch (IOException e) {
            return new ServerAnswer<>(ServerAnswer.INTERNET_CONNECTION_ERROR_CODE);
        }
    }
}
