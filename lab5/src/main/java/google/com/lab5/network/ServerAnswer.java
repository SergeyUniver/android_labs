package google.com.lab5.network;

/**
 * Created by Sergey on 19.12.2016.
 */

public class ServerAnswer<T> {

    public static final int INTERNET_CONNECTION_ERROR_CODE = -1;
    public static final int NOTHING_FOUND_ERROR_CODE = -2;
    public static final int SUCCESS_CODE = 200;

    private int responseCode;
    private T result;

    public ServerAnswer(T result, int responseCode) {
        this.responseCode = responseCode;
        this.result = result;
    }

    public ServerAnswer(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess(){
        return responseCode == SUCCESS_CODE;
    }
}