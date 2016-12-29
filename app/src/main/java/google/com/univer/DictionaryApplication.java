package google.com.univer;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Sergey on 15.11.2016.
 */
public class DictionaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .migration(new DictionaryMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
