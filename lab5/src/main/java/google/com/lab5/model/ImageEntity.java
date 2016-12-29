package google.com.lab5.model;

/**
 * Created by Sergey on 19.12.2016.
 */
public class ImageEntity {

    private String text;
    private String size;

    public ImageEntity() {

    }

    public ImageEntity(String text, String size) {
        this.text = text;
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
