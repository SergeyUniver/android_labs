package google.com.univer.model;

import io.realm.RealmObject;

/**
 * Created by Sergey on 30.11.2016.
 */

public class Tag extends RealmObject{

    private String tag;
    private int count;

    public Tag() {
    }

    public Tag(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
