package google.com.univer.model;

import io.realm.RealmObject;

/**
 * Created by Sergey on 14.12.2016.
 */

public class GeneralTag extends RealmObject {

    private String tag;
    private int count;

    public GeneralTag() {
    }

    public GeneralTag(String tag, int count) {
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
