package google.com.univer.model;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey on 15.11.2016.
 */

public class WordEntity extends RealmObject{

    @PrimaryKey
    private String word;
    private int repeatCount;
    private RealmList<WordEntity> formList;
    private RealmList<Tag> tags;
    private String startForm;

    public WordEntity(){}

    public WordEntity(String word, int repeatCount, RealmList<WordEntity> formList) {
        this.word = word;
        this.repeatCount = repeatCount;
        this.formList = formList;
    }

    public WordEntity(String word, int repeatCount) {
        this.word = word;
        this.repeatCount = repeatCount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public RealmList<WordEntity> getFormList() {
        return formList;
    }

    public void setFormList(RealmList<WordEntity> formList) {
        this.formList = formList;
    }

    public RealmList<Tag> getTags() {
        return tags;
    }

    public void setTags(RealmList<Tag> tags) {
        this.tags = tags;
    }

    public void addForm(WordEntity second) {
        if(formList == null){
            formList = new RealmList<>();
        }
        formList.add(second);
    }

    public void addTags(HashMap<String, Integer> value) {
        if(tags == null){
            tags = new RealmList<>();
        }
        for(Map.Entry<String, Integer> tag : value.entrySet()){
            tags.add(new Tag(tag.getKey(), tag.getValue()));
        }
    }

    public void addTag(String tag) {
        Tag tager = new Tag(tag, 1);
        if(tags == null){
            tags = new RealmList<>();
        }
        tags.add(tager);
    }

    public String getStartForm() {
        return startForm;
    }

    public void setStartForm(String startForm) {
        this.startForm = startForm;
    }
}
