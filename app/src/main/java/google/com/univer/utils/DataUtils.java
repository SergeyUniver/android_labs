package google.com.univer.utils;

import java.util.ArrayList;
import java.util.List;

import google.com.univer.model.GeneralTag;
import google.com.univer.model.WordEntity;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Sergey on 15.11.2016.
 */

public class DataUtils {

    public static List<WordEntity> getAlphavitSortedWords(Realm realm){
        return realm.where(WordEntity.class).findAllSorted("word");
    }

    public static List<WordEntity> getNumberSortedWords(Realm realm, Sort sort){
        return realm.where(WordEntity.class).findAllSorted("repeatCount", sort);
    }

    public static List<WordEntity> getAlphavitSortedWords(Realm realm, String firstLetter){
        return realm.where(WordEntity.class).beginsWith("word", firstLetter.toLowerCase()).findAllSorted("word");
    }

    public static WordEntity getWordEntityByWord(Realm realm, String word) {
        return realm.where(WordEntity.class).equalTo("word", word).findFirst();
    }

    public static ArrayList<WordEntity> getWordsByStartForm(Realm realm, WordEntity wordEntity) {
        RealmResults<WordEntity> result = realm.where(WordEntity.class).equalTo("startForm", wordEntity.getWord()).findAll();
        ArrayList<WordEntity> finalList = new ArrayList<>();
        finalList.addAll(result);
        return finalList;
    }

    public static String getCurrentTagByWord(Realm realm, String word) {
        WordEntity wordEntity = realm.where(WordEntity.class).equalTo("word", word).findFirst();
        if(wordEntity == null){
            return "AA";
        }
        if(wordEntity.getTags() != null && wordEntity.getTags().size() > 0){
            return wordEntity.getTags().get(0).getTag();
        }else{
            return "AA";
        }
    }

    public static List<GeneralTag> getGeneralTags(Realm realm) {
        RealmResults<GeneralTag> result = realm.where(GeneralTag.class).findAll();
        ArrayList<GeneralTag> finalList = new ArrayList<>();
        finalList.addAll(result);
        return finalList;
    }
}
