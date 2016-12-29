package google.com.lab4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;

/**
 * Created by Sergey on 18.12.2016.
 */

public class CityActivity extends AppCompatActivity {

    private Realm realm;
    private HashMap<String, Integer> regionPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        showFirstFragment();
        realm = Realm.getDefaultInstance();
        saveDefaultData();
    }

    public void showFirstFragment() {
        ChooseCityFragment fragment = new ChooseCityFragment();
        ViewHelper.showFragmentWithoutAnimation(getFragmentManager(), fragment, false, null);
    }

    private void saveDefaultData() {
        ArrayList<City> cityList = new ArrayList<>();
        cityList.add(new City(1, "Минск", "Минск", 1959781));
        cityList.add(new City(2, "Гомель", "Минск", 521452));
        cityList.add(new City(3, "Могилев", "Минск", 378077));
        cityList.add(new City(4, "Витебск", "Витебск", 368574));
        cityList.add(new City(5, "Гродно", "Гродно", 365610));
        cityList.add(new City(6, "Брест", "Брест", 340141));
        cityList.add(new City(7, "Бобруйск", "Брест", 217975));
        cityList.add(new City(8, "Барановичи", "Брест", 179122));
        cityList.add(new City(9, "Борисов", "Минск", 143919));
        cityList.add(new City(10, "Пинск", "Брест", 138415));
        cityList.add(new City(11, "Орша", "Витебск", 116552));
        cityList.add(new City(12, "Мозырь", "Гомель", 112003));
        cityList.add(new City(13, "Солигорс", "Минск", 106503));
        cityList.add(new City(14, "Новополоцк", "Витебск", 102394));
        cityList.add(new City(15, "Лида", "Гродно", 100443));
        cityList.add(new City(16, "Молодечно", "Минск", 94922));
        cityList.add(new City(17, "Полоцк", "Витебск", 85078));
        cityList.add(new City(18, "Жлобин", "Гомель", 75956));
        cityList.add(new City(19, "Светлогорск", "Гомель", 69011));
        cityList.add(new City(20, "Речица", "Гомель", 66172));
        initRegionPopulation(cityList);
        if (DataProvider.isCityBasEmpty(realm)) {
            DataProvider.saveCity(cityList);
        }
    }

    private void initRegionPopulation(ArrayList<City> cityList) {
        regionPopulation = new HashMap<>();
        for(City city : cityList){
            if(regionPopulation.containsKey(city.getName())){
                regionPopulation.put(city.getName(), regionPopulation.get(city.getName()) + city.getPopulation());
            }else{
                regionPopulation.put(city.getName(), city.getPopulation());
            }
        }
    }

    public int getRegionPopulation(String region){
        return regionPopulation.get(region);
    }

    public Realm getRealm() {
        return realm;
    }
}
