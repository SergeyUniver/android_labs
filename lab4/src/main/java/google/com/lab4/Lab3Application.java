package google.com.lab4;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Sergey on 12.12.2016.
 */
public class Lab3Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .migration(new MainRealmMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
