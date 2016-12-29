package google.com.lab4;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey on 18.12.2016.
 */

public class City extends RealmObject{

    @PrimaryKey
    private long id;
    private String name;
    private String region;
    private int population;

    public City(){

    }

    public City(long id, String name, String region, int population) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.population = population;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
