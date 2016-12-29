package google.com.lab2.utils;

import android.content.Context;

import google.com.lab2.R;

/**
 * Created by Sergey on 13.11.2016.
 */

public class TextUtils {

    public static String getNumberMessage(int messageType, Context context){
        String[] messageArray = context.getResources().getStringArray(R.array.number_type_message);
        return messageArray[messageType];
    }

}
