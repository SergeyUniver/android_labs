package google.com.univer;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Sergey on 16.11.2016.
 */
public class DictionaryMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0){
            migrate0stTime(schema);
            oldVersion++;
        }else if(oldVersion == 1){
            migrate1stTime(schema);
            oldVersion++;
        }
    }

    private void migrate1stTime(RealmSchema schema) {

    }

    private void migrate0stTime(RealmSchema schema) {
        schema.get("WordEntity").addField("startForm", String.class);
    }
}
