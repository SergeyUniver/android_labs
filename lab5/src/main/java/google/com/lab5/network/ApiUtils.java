package google.com.lab5.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sergey on 07.09.2016.
 */
public class ApiUtils {
    public static final String GET_INFO_HOST = "http://ws.audioscrobbler.com/";
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();

    public static <T> T createGetInfoService(Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GET_INFO_HOST).build();

        return retrofit.create(serviceClass);
    }


}
