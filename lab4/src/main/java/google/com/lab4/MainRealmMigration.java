package google.com.lab4;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Sergey on 12.12.2016.
 */
public class MainRealmMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0){
            migrate0stTime(schema);
            oldVersion++;
        }
    }

    private void migrate0stTime(RealmSchema schema) {
        schema.get("GroupMate").addField("firstName", String.class)
                .addField("secondName", String.class)
                .addField("patronymic", String.class)
                .close();
    }
}
