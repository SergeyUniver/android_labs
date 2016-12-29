package google.com.univer.fragments.alphavit_sorted;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import google.com.univer.MainAdapter;
import google.com.univer.R;
import google.com.univer.fragments.word_info.WordInfoFragment;
import google.com.univer.model.WordEntity;
import google.com.univer.utils.DataUtils;
import google.com.univer.view.ViewHelper;
import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by Sergey on 09.11.2016.
 */

public class AlphavitSortedFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private List<WordEntity> words;
    private AlphavitSortedAdapter adapter;
    private Realm realm;
    private String firstWord;
    private Sort sort;
    private OnWordSelectedListener onWordSelectedListener;
    private EditText searchView;
    private Button clearButton;

    public interface OnWordSelectedListener{
        void onWordSelected(WordEntity wordEntity);
    }

    public static AlphavitSortedFragment createInstance(String firstWord) {
        AlphavitSortedFragment fragment = new AlphavitSortedFragment();
        fragment.firstWord = firstWord;
        return fragment;
    }

    public static AlphavitSortedFragment createInstance(Sort sort) {
        AlphavitSortedFragment fragment = new AlphavitSortedFragment();
        fragment.sort = sort;
        return fragment;
    }

    public static AlphavitSortedFragment createInstance(OnWordSelectedListener onWordSelectedListener){
        AlphavitSortedFragment fragment = new AlphavitSortedFragment();
        fragment.onWordSelectedListener = onWordSelectedListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWords();
    }

    private void initWords() {
        realm = Realm.getDefaultInstance();
        if (firstWord != null) {
            words = DataUtils.getAlphavitSortedWords(realm, firstWord);
//            tryGetGramma();
        } else if (sort != null){
            words = DataUtils.getNumberSortedWords(realm, sort);
        } else if (onWordSelectedListener != null){
            words = DataUtils.getAlphavitSortedWords(realm);
        }
    }

    private void tryGetGramma() {
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < i + 10 && j < words.size(); j++) {
                if (checkEqualsGramma(words.get(i), words.get(j))) {
                    addGrammaWord(words.get(i), words.get(j));
                }
            }
            if (i % 10 == 0) {
                Log.d("Word: ", i + " all: " + words.size());
            }
        }
    }

    private void addGrammaWord(WordEntity first, WordEntity second) {
        realm.beginTransaction();
        first.addForm(second);
        second.addForm(first);
        realm.commitTransaction();
    }

    private boolean checkEqualsGramma(WordEntity first, WordEntity second) {
        String firstWord = first.getWord();
        String secondWord = second.getWord();
        for (int i = 0; i < firstWord.length(); i++) {
            int size = 2;
            boolean result = tryGetRoot(i, i + size, firstWord, secondWord);
            if (result) {
                return true;
            }
        }
        return false;
    }

    private boolean tryGetRoot(int start, int finish, String firstWord, String secondWord) {
        if (finish < firstWord.length() && secondWord.contains(firstWord.substring(start, finish))) {
            return tryGetRoot(start, finish + 1, firstWord, secondWord);
        } else {
            return finish - start > 2;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alphavit_sorted, container, false);
        this.root = root;
        initView();
        setupView();
        return root;
    }

    private void setupView() {
        setupAdapter();
        setupListener();
    }

    private void setupListener() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = searchView.getText().toString();
                adapter.setSearchText(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setText("");
            }
        });
    }

    private void setupAdapter() {
        adapter = new AlphavitSortedAdapter(getActivity(), words, new MainAdapter.WordListListener() {
            @Override
            public void onEditClicked(WordEntity wordEntity, String word) {
                changeWord(wordEntity, word);
            }

            @Override
            public void onDeleteClicked(final WordEntity word) {
                new AlertDialog.Builder(getActivity())
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteWord(word);
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setMessage("Вы действительно хотите удалить это слово? Это действие нельзя будет отменить")
                        .create()
                        .show();
            }

            @Override
            public void onWordClicked(WordEntity word) {
                if(onWordSelectedListener != null){
                    onWordSelectedListener.onWordSelected(word);
                    getActivity().onBackPressed();
                    return;
                }
                ViewHelper.showFragmentLeftRight(getFragmentManager(), WordInfoFragment.createInstance(word.getWord()), true, null);
            }
        });
        setAdapter();
    }

    private void changeWord(WordEntity wordEntity, String word) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        wordEntity.setWord(word);
        realm.commitTransaction();
        realm.close();
//        initWords();
        adapter.notifyDataSetChanged();
    }

    private void deleteWord(WordEntity word) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        word.deleteFromRealm(word);
        realm.commitTransaction();
        realm.close();
//        initWords();
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        clearButton = (Button) root.findViewById(R.id.clear_button);
        searchView = (EditText) root.findViewById(R.id.search_view);
    }

    private void setAdapter() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

}
