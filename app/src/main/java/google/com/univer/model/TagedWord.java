package google.com.univer.model;

/**
 * Created by Sergey on 30.11.2016.
 */


public class TagedWord{
    String word;
    String tag;

    public TagedWord(String word, String tag) {
        this.word = word;
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}