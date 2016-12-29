package google.com.univer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import google.com.univer.fragments.FullTextFragment;
import google.com.univer.fragments.StartFragment;
import google.com.univer.fragments.stat.StatisticFragment;
import google.com.univer.fragments.alphavit.Alphavit;
import google.com.univer.fragments.alphavit_sorted.AlphavitSortedFragment;
import google.com.univer.model.WordEntity;
import google.com.univer.operation.GenerateWordsMapOperation;
import google.com.univer.utils.text_utils.DictionaryReader;
import google.com.univer.view.ViewHelper;
import io.realm.Realm;
import io.realm.Sort;


public class MainActivity extends AppCompatActivity {

    public static final String ALPHAVIT_SORT_ACTION = "repetition_action";
    public static final String DESCENDING_SORT_ACTION = "descending_action";
    public static final String ASCENDING_SORT_ACTION = "ascending_action";
    private static final int FILE_SELECT_CODE = 23;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private HashMap<String, Integer> wordsMap;
    private StartFragment startFragment;
    public boolean isWordProcessing;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFirstFragment();
//        generateMap();
    }

    protected void showFirstFragment() {
        startFragment = StartFragment.createInstance();
        ViewHelper.showFirstFragment(getFragmentManager(), startFragment);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    public void generateMap(){
        isWordProcessing = true;
        GenerateWordsMapOperation operation = new GenerateWordsMapOperation(new GenerateWordsMapOperation.OnFinishedGeneratedCallback() {
            @Override
            public void onFinished(HashMap<String, Integer> wordMap) {
//                saveAllWord(wordMap);
                MainActivity.this.wordsMap = wordMap;
                isWordProcessing = false;
                startFragment.onWordMapGenerated();
            }
        });
        operation.execute();
    }

    private void saveAllWord(HashMap<String, Integer> wordMap) {
        List<WordEntity> list = new ArrayList<>();
        for(HashMap.Entry<String, Integer> entry : wordMap.entrySet()){
            list.add(new WordEntity(entry.getKey(), entry.getValue()));
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        realm.close();
    }

    private void setupAdapter() {
        initAdapter();
        setAdapter();
        mainAdapter.notifyDataSetChanged();
    }

    private void setAdapter() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mainAdapter);
    }

    private void initAdapter() {
//        mainAdapter = new MainAdapter(this, wordsMap, new MainAdapter.WordListListener() {
//            @Override
//            public void onEditClicked(String word) {
//
//            }
//
//            @Override
//            public void onDeleteClicked(String word) {
////                wordsMap.remove(word);
////                mainAdapter.updateWords(wordsMap);
//            }
//        }, getIntent().getAction().equals(ALPHAVIT_SORT_ACTION));
    }

    public void showAlphavitSortedFragment(String letter) {
        ViewHelper.showFragmentLeftRight(getFragmentManager(), AlphavitSortedFragment.createInstance(letter), true, null);
    }

    public void showCountSortedFragment(boolean isUpend) {
        ViewHelper.showFragmentLeftRight(getFragmentManager(), AlphavitSortedFragment.createInstance(isUpend ? Sort.ASCENDING : Sort.DESCENDING), true, null);
    }

    public HashMap<String, Integer> getWordsMap() {
        return wordsMap;
    }

    public void showAlphavitFragment() {
        ViewHelper.showFragmentLeftRight(getFragmentManager(), Alphavit.createInstance(), true, null);
    }

    public void showText() {
        startFileChooser();
    }

    private void startFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
               startShowFragment();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startShowFragment() {
        showProgressBar();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = DictionaryReader.getDefaultText(30);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hideProgressDialog(list);
            }
        }).run();

    }

    private void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please, wait");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    private void hideProgressDialog(final ArrayList<String> list) {
        if(progressDialog != null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    ViewHelper.showFragmentLeftRight(getFragmentManager(), FullTextFragment.createInstance(list), true, null);
                }
            });
        }
    }

    public void showStatFragment() {
        ViewHelper.showFragmentLeftRight(getFragmentManager(), StatisticFragment.createInstance(), true, null);

    }
}
