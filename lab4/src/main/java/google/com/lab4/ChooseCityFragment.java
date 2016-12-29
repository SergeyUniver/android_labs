package google.com.lab4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Sergey on 18.12.2016.
 */

public class ChooseCityFragment extends Fragment {

    @BindView(R.id.sort_by)
    Spinner sortBySpinner;

    @BindView(R.id.region_population_et)
    EditText regionPopulationET;

    @BindView(R.id.group_region_switch)
    Switch group_region_switch;

    @BindView(R.id.agrgegagate_spinner)
    Spinner agregateSpinner;

    @BindView(R.id.population_et)
    EditText populationET;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose_region, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {

    }

    @OnClick(R.id.show_button)
    public void onShowButtonClicked(){
        RealmQuery<City> query;
        int population;
        try {
            population = Integer.parseInt(populationET.getText().toString());
        }catch (NumberFormatException e){
            population = 0;
        }
        query = getRealm().where(City.class);
        if(population > 0) {
            query = query.greaterThan("population", population);
        }
        if(!agregateSpinner.getSelectedItem().toString().equals(getResources().getStringArray(R.array.aggregate_state)[0])){
            switch ((int) agregateSpinner.getSelectedItemId()) {
                case 1:
                    showToast("Population sum = " + query.sum("population"));
                    break;
                case 2:
                    showToast("Min population = " + query.min("population").toString());
                    break;
                case 3:
                    showToast("Max population = " + query.max("population").toString());
                    break;
                case 4:
                    showToast("Count = " + query.count());
                    break;
            }
            return;
        }
        RealmResults<City> result;
        if(group_region_switch.isChecked()) {
            result = query.findAllSorted("region", Sort.ASCENDING, sortBySpinner.getSelectedItem().toString().toLowerCase(),
                    sortBySpinner.getSelectedItemId() == 1 ? Sort.DESCENDING : Sort.ASCENDING);
        }else{
            result = query.findAllSorted(sortBySpinner.getSelectedItem().toString().toLowerCase(),
                    sortBySpinner.getSelectedItemId() == 1 ? Sort.DESCENDING : Sort.ASCENDING);
        }
        ArrayList<City> preFinalResult = new ArrayList<>();
        preFinalResult.addAll(result);
        int regionPopulation;
        try {
            regionPopulation = Integer.parseInt(regionPopulationET.getText().toString());
        }catch (NumberFormatException e){
            regionPopulation = 0;
        }
        if(regionPopulation > 0){
            ArrayList<City> finalResult = new ArrayList<>();
            for(City city : preFinalResult){
                if(((CityActivity)getActivity()).getRegionPopulation(city.getRegion()) >= regionPopulation){
                    finalResult.add(city);
                }
            }
            showListCity(finalResult);
        }else{
            showListCity(preFinalResult);
        }

    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void showListCity(ArrayList<City> finalResult) {
        ShowCityFragment fragment = ShowCityFragment.createInstance(finalResult);
        ViewHelper.showFragmentLeftRight(getFragmentManager(), fragment, true, null);
    }

    private Realm getRealm() {
        return ((CityActivity)getActivity()).getRealm();
    }

}
