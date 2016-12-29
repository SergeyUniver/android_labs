package google.com.lab5.network;

import google.com.lab5.model.GetTrackEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sergey on 07.09.2016.
 */
public interface ApiService {

    @GET("/2.0/?method=artist.gettoptracks")
    Call<GetTrackEntity> getTopTracks(@Query("artist") String artist, @Query("api_key") String apiKey, @Query("format") String format);
}
