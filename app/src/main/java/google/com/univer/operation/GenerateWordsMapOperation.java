package google.com.univer.operation;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import google.com.univer.model.GeneralTag;
import google.com.univer.model.WordEntity;
import google.com.univer.utils.text_utils.DictionaryReader;
import google.com.univer.utils.text_utils.TextUtils;
import io.realm.Realm;

/**
 * Created by Sergey on 09.11.2016.
 */

public class GenerateWordsMapOperation extends AsyncTask<Void, Void, HashMap<String, Integer>>{

    private static final String TAG = GenerateWordsMapOperation.class.getSimpleName();
    private final OnFinishedGeneratedCallback callback;

    public interface OnFinishedGeneratedCallback{
        void onFinished(HashMap<String, Integer> wordMap);
    }

    public GenerateWordsMapOperation(OnFinishedGeneratedCallback callback){
        this.callback = callback;
    }

    @Override
    protected HashMap<String, Integer> doInBackground(Void... params) {
        ArrayList<String> dictionary = DictionaryReader.read(DictionaryReader.DEFAULT_DICTIONARY);
        HashMap<String, Integer> map = TextUtils.getMapWords(dictionary);
        HashMap<String, HashMap<String, Integer>> mapTagged = DictionaryReader.generateTaggedList(DictionaryReader.DEFAULT_TAGGER);
        saveResult(map, mapTagged);
        return map;
    }

    private void saveResult(HashMap<String, Integer> map, HashMap<String, HashMap<String, Integer>> mapTagged) {
        Log.d(TAG, "save result");
        Map<String, Integer> generalTagCount = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> mapWords = new HashMap<>();
        for(Map.Entry<String, HashMap<String, Integer>> tagedWord : mapTagged.entrySet()){
            if(map.containsKey(tagedWord.getKey())){
                mapWords.put(tagedWord.getKey(), tagedWord.getValue());
            }
        }
        ArrayList<WordEntity> finalResult = new ArrayList<>();
        for(Map.Entry<String, HashMap<String, Integer>> entry : mapWords.entrySet()){
            addTags(generalTagCount, entry.getValue());
            WordEntity wordEntity = new WordEntity();
            wordEntity.setWord(entry.getKey());
            wordEntity.addTags(entry.getValue());
            wordEntity.setRepeatCount(map.get(entry.getKey()));
            finalResult.add(wordEntity);
        }
        saveWords(finalResult);
        saveTagsCount(generalTagCount);

        Log.d(TAG, "Results was saved");
    }

    private void saveTagsCount(Map<String, Integer> generalTagCount) {
        ArrayList<GeneralTag> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : generalTagCount.entrySet()){
            list.add(new GeneralTag(entry.getKey(), entry.getValue()));
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        realm.close();
    }

    private void saveWords(ArrayList<WordEntity> finalResult) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(finalResult);
        realm.commitTransaction();
        realm.close();
    }

    private void addTags(Map<String, Integer> generalTagCount, HashMap<String, Integer> value) {
        for(Map.Entry<String, Integer> entry : value.entrySet()){
            Integer count = 0;
            if(generalTagCount.containsKey(entry.getKey())){
                count = generalTagCount.get(entry.getKey());
            }
            count ++;
            generalTagCount.put(entry.getKey(), count);
        }
    }


    @Override
    protected void onPostExecute(HashMap<String, Integer> wordMap) {
        super.onPostExecute(wordMap);
        callback.onFinished(wordMap);
    }
}
