package google.com.lab4.show_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.com.lab4.DataProvider;
import google.com.lab4.GroupMate;
import google.com.lab4.R;
import io.realm.Realm;

/**
 * Created by Sergey on 12.12.2016.
 */

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Realm realm;
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        initRealm();
        initAdapter();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    private void initAdapter() {
        adapter = new RecordAdapter(getGroupList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<GroupMate> getGroupList() {
        return DataProvider.getAllGroupList(realm);
    }

}
