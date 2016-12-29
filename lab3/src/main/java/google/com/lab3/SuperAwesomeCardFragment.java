package google.com.lab3;

/**
 * Created by Sergey on 12.12.2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuperAwesomeCardFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RandomTextAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_best_ui_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        ButterKnife.bind(this, root);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new RandomTextAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @OnClick(R.id.count_button)
    void onCountButtonClicked(){
        int count = adapter.getCheckedCount();
        Toast.makeText(getActivity(), "Checked: %s" + count, Toast.LENGTH_SHORT).show();
    }

}