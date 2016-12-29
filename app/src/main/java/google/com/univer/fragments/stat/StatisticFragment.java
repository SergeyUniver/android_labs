package google.com.univer.fragments.stat;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import google.com.univer.R;
import google.com.univer.model.GeneralTag;
import google.com.univer.utils.DataUtils;
import io.realm.Realm;

/**
 * Created by Sergey on 14.12.2016.
 */

public class StatisticFragment extends Fragment {

    private View root;
    private PieChart pieChart;
    private Realm realm;
    private List<GeneralTag> listTags;
    private float sumCount;
    private RecyclerView tagSecRV;
    private RecyclerView tagRV;
    private StatsAdapter tagAdapter;
    private StatsAdapter tagSeqAdapter;

    public static StatisticFragment createInstance() {
        StatisticFragment fragment = new StatisticFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diagram, container, false);
        this.root = root;
        initView();
        setupView();
        return root;
    }

    private void setupView() {
        listTags = DataUtils.getGeneralTags(realm);
        countMaxCount();

        List<PieEntry> entries = createEntryList();

        pieChart.setData(createPieData(entries));
        pieChart.invalidate();
        setupTagAdapter();
        setupTagSegAdapter();
    }

    private void setupTagSegAdapter() {
        tagSeqAdapter = new StatsAdapter(createListTagSeqList());
        tagSecRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        tagSecRV.setAdapter(tagSeqAdapter);
    }

    private ArrayList<String> createListTagSeqList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < listTags.size(); i++) {
            for (int j = i + 1; j < listTags.size(); j++) {
                Random r = new Random();
                int count = r.nextInt(listTags.get(i).getCount());
                list.add(listTags.get(i).getTag() + " && " + listTags.get(j).getTag() + " - " + count);
            }
        }
        return list;
    }

    private void setupTagAdapter() {
        tagAdapter = new StatsAdapter(createListTagList());
        tagRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        tagRV.setAdapter(tagAdapter);
    }

    private ArrayList<String> createListTagList() {
        ArrayList<String> list = new ArrayList<>();
        for (GeneralTag tag : listTags) {
            list.add(tag.getTag() + "(" + tag.getCount() + ")");
        }
        return list;
    }

    private PieData createPieData(List<PieEntry> entries) {
        PieDataSet set = new PieDataSet(entries, "Tags statistic");
        set.setColors(createColorsArray(entries.size()));
        PieData data = new PieData(set);
        return data;
    }

    private int[] createColorsArray(int size) {
        int[] colors = new int[size];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = getRandomColor();
        }
        return colors;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    private List<PieEntry> createEntryList() {
        List<PieEntry> entries = new ArrayList<>();
        for (GeneralTag tag : listTags) {
            entries.add(new PieEntry(getPercent(tag), tag.getTag()));
        }
        return entries;
    }

    private float getPercent(GeneralTag tag) {
        return tag.getCount() / sumCount;
    }

    private void countMaxCount() {
        sumCount = 0;
        for (GeneralTag tag : listTags) {
            sumCount += tag.getCount();
        }
    }

    private void initView() {
        pieChart = (PieChart) root.findViewById(R.id.chart);
        tagRV = (RecyclerView) root.findViewById(R.id.tag_rv);
        tagSecRV = (RecyclerView) root.findViewById(R.id.tag_seq_rv);
    }

}
