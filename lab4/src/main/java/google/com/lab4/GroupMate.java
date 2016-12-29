package google.com.lab4;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey on 12.12.2016.
 */

public class GroupMate extends RealmObject {

    @PrimaryKey
    public int id;
    private String name;
    private String secondName;
    private String patronymic;
    private Date createDate;

    public GroupMate() {
        this.id = getNextPrimaryKey();
        this.createDate = new Date();
    }

    public GroupMate(String name, String secondName, String patronymic) {
        this.id = getNextPrimaryKey();
        this.name = name;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.createDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public int getNextPrimaryKey() {
        Realm realm = Realm.getDefaultInstance();
        Number number = realm.where(GroupMate.class).max("id");
        int nextId;
        if(number == null){
            nextId = 0;
        }else {
            nextId = number.intValue() + 1;
        }
        realm.close();
        return nextId;
    }
}
