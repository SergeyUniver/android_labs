package google.com.lab4;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sergey on 18.12.2016.
 */

public class DataProvider {

    public static ArrayList<GroupMate> getAllGroupList(Realm realm){
        RealmResults<GroupMate> result = realm.where(GroupMate.class).findAll();
        ArrayList<GroupMate> finalResult = new ArrayList<>();
        finalResult.addAll(result);
        return finalResult;
    }

    public static void saveNewUser(final List<GroupMate> list) {
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(list);
            }
        });
    }

    public static void saveNewUser(final GroupMate groupMate) {
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(groupMate);
            }
        });
    }

    public static void deleteAllUsers() {
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<GroupMate> results = realm.where(GroupMate.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }


    public static boolean isCityBasEmpty(Realm realm) {
        return realm.where(City.class).findAll().isEmpty();
    }

    public static void saveCity(final ArrayList<City> cityList) {
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(cityList);
            }
        });
    }
}
