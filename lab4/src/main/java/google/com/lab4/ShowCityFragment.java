package google.com.lab4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sergey on 18.12.2016.
 */
public class ShowCityFragment extends Fragment{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<City> cityList;
    private CityAdapter adapter;

    public static ShowCityFragment createInstance(ArrayList<City> finalResult){
        ShowCityFragment fragment = new ShowCityFragment();
        fragment.cityList = finalResult;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_show_city, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {
        setupAdatper();
    }

    private void setupAdatper() {
        adapter = new CityAdapter(cityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
