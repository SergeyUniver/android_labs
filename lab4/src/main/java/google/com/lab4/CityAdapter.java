package google.com.lab4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sergey on 18.12.2016.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final ArrayList<City> cityList;

    public CityAdapter(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cityList.get(position));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder{

        private TextView populationET;
        private TextView nameET;

        public CityViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            nameET = (TextView) itemView.findViewById(R.id.name_text_view);
            populationET = (TextView) itemView.findViewById(R.id.population_text_view);
        }

        public void bind(City city) {
            nameET.setText(city.getName() + " (" + city.getRegion() + ")");
            populationET.setText(String.valueOf(city.getPopulation()));
        }
    }
}
