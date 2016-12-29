package google.com.univer.fragments.alphavit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import google.com.univer.MainActivity;
import google.com.univer.R;

/**
 * Created by Sergey on 15.11.2016.
 */

public class Alphavit extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private AlphavitAdapter adapter;

    public static Alphavit createInstance(){
        Alphavit fragment = new Alphavit();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alphavit, container, false);
        this.root = root;
        initView();
        setupView();
        return root;
    }

    private void setupView() {
        setAdapter();
    }

    private void initView() {
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
    }

    private void setAdapter() {
        adapter = new AlphavitAdapter(new AlphavitAdapter.OnLetterSelected() {
            @Override
            public void onLetterSelected(String letter) {
                ((MainActivity)getActivity()).showAlphavitSortedFragment(letter);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

}
