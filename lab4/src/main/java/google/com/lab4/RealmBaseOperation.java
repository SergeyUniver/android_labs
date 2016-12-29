package google.com.lab4;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sergey on 12.12.2016.
 */

public class RealmBaseOperation {

    private static final String TAG = RealmBaseOperation.class.getSimpleName();

    public interface RealmBaseOperationCallback {
        void onFinished(boolean isSuccess);
    }

    public interface WriteRealmMethod {
        void execute(Realm realm);
    }

    public interface ReadRealmMethod {
        RealmResults execute(Realm realm);
    }

    public static void makeWriteRequest(WriteRealmMethod realmMethod) {
        Realm r = Realm.getDefaultInstance();
        try {
            r.beginTransaction();
            realmMethod.execute(r);
            r.commitTransaction();
        } catch (Exception ex) {
            Log.e(TAG, "onResponse: ", ex);
            ex.printStackTrace();
            if (r.isInTransaction())
                r.cancelTransaction();
        } finally {
            r.close();
        }
    }
}