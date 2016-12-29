package google.com.lab5.network;

import android.os.AsyncTask;

/**
 * Created by Sergey on 19.12.2016.
 */

public abstract class BaseAsyncOperation<T> extends AsyncTask<Void, Void, ServerAnswer<T>> {

    private static final String API_KEY = "3736fd8b419101f1e319c2046dfa767f";
    private static final String DEFAULT_FORMAT = "json";

    protected final ApiAsyncCallback<T> callback;

    protected BaseAsyncOperation(ApiAsyncCallback<T> callback) {
        this.callback = callback;
    }

    public interface ApiAsyncCallback<T>{
        void onOperationSuccess(T answer);
        void onOperationFailed(int responseCode);
    }

    @Override
    protected void onPostExecute(ServerAnswer<T> serverAnswer) {

        super.onPostExecute(serverAnswer);
        if(serverAnswer.isSuccess()){
            callback.onOperationSuccess(serverAnswer.getResult());
        }else{
            callback.onOperationFailed(serverAnswer.getResponseCode());
        }
    }

    protected String getApiKey(){
        return API_KEY;
    }

    protected String getDefaultFormat() {
        return DEFAULT_FORMAT;
    }

}

