package google.com.univer.fragments.word_info;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import google.com.univer.MainAdapter;
import google.com.univer.R;
import google.com.univer.fragments.alphavit_sorted.AlphavitSortedAdapter;
import google.com.univer.fragments.alphavit_sorted.AlphavitSortedFragment;
import google.com.univer.model.WordEntity;
import google.com.univer.utils.DataUtils;
import google.com.univer.view.ViewHelper;
import io.realm.Realm;

/**
 * Created by Sergey on 16.11.2016.
 */

public class WordInfoFragment extends Fragment {

    private View root;
    private TextView wordTV;
    private RecyclerView recyclerView;
    private String word;
    private Realm realm;
    private WordEntity wordEntity;
    private Button addTagButton;
    private EditText editTagET;
    private WordInfoAdapter adapter;
    private TextView infoTextView;
    private RecyclerView formsRecyclerView;
    private TextView formsTitle;
    private ImageView changeFormButton;
    private ImageView addFormButton;
    private Button deleteButton;

    public static WordInfoFragment createInstance(String word){
        WordInfoFragment fragment = new WordInfoFragment();
        fragment.word = word.toLowerCase();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWordEntity();
    }

    private void initWordEntity() {
        realm = Realm.getDefaultInstance();
        wordEntity = DataUtils.getWordEntityByWord(realm, word);
     }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_word_info, container, false);
        this.root = root;
        initView();
        setupView();
        return root;
    }

    private void setupView() {
        wordTV.setText(wordEntity.getWord());
        setupAdapter();
        setupListeners();
        setupForms();
    }

    private void setupForms() {
        if(wordEntity.getStartForm() == null){
            ArrayList<WordEntity> listForms = DataUtils.getWordsByStartForm(realm, wordEntity);
            if(listForms.size() == 0){
                infoTextView.setText("No forms");
                addFormButton.setVisibility(View.VISIBLE);
                changeFormButton.setVisibility(View.VISIBLE);
                formsRecyclerView.setVisibility(View.GONE);
            }else{
                addFormButton.setVisibility(View.VISIBLE);
                changeFormButton.setVisibility(View.GONE);
                setupFormsRV(listForms);
            }
        }else{
            addFormButton.setVisibility(View.GONE);
            changeFormButton.setVisibility(View.VISIBLE);
            formsRecyclerView.setVisibility(View.GONE);
            formsTitle.setText("Start form:");
            infoTextView.setText(wordEntity.getStartForm());
        }
    }

    private void setupFormsRV(ArrayList<WordEntity> listForms) {
        formsTitle.setText("Forms:");
        infoTextView.setVisibility(View.GONE);
        formsRecyclerView.setVisibility(View.VISIBLE);
        AlphavitSortedAdapter adapter = new AlphavitSortedAdapter(getActivity(), listForms, new MainAdapter.WordListListener() {
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
                        .setMessage("Вы действительно хотите удалить эту форму? Это действие нельзя будет отменить")
                        .create()
                        .show();
            }

            @Override
            public void onWordClicked(WordEntity word) {

            }
        });
        formsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        formsRecyclerView.setAdapter(adapter);
    }

    private void changeWord(WordEntity wordEntity, String word) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        wordEntity.setWord(word);
        realm.commitTransaction();
        realm.close();
        updateView();
    }

    private void deleteWord(WordEntity word) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        word.setStartForm(null);
        realm.commitTransaction();
        realm.close();
        updateView();
    }

    private void setupListeners() {
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = editTagET.getText().toString();
                editTagET.setText("");
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                wordEntity.addTag(tag);
                realm.commitTransaction();
                realm.close();
                adapter.setListWord(wordEntity.getTags());
                adapter.notifyDataSetChanged();
            }
        });
        addFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFormClicked();
            }
        });
        changeFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFormClicked();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClicked();
            }
        });
    }

    private void onDeleteButtonClicked() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        wordEntity.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        getActivity().onBackPressed();
    }

    private void changeFormClicked() {
        AlphavitSortedFragment fragment = AlphavitSortedFragment.createInstance(new AlphavitSortedFragment.OnWordSelectedListener() {
            @Override
            public void onWordSelected(WordEntity wordEntity) {
                changeStartForm(wordEntity);
            }
        });
        ViewHelper.showFragmentLeftRight(getFragmentManager(),fragment , true, null);
    }

    private void changeStartForm(WordEntity startForm) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        wordEntity.setStartForm(startForm.getWord());
        realm.commitTransaction();
        realm.close();
        updateView();
    }

    private void updateView() {
        setupForms();
    }

    private void addFormClicked() {
        AlphavitSortedFragment fragment = AlphavitSortedFragment.createInstance(new AlphavitSortedFragment.OnWordSelectedListener() {
            @Override
            public void onWordSelected(WordEntity wordEntity) {
                addForm(wordEntity);
            }
        });
        ViewHelper.showFragmentLeftRight(getFragmentManager(),fragment , true, null);
    }

    private void addForm(WordEntity form) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        form.setStartForm(wordEntity.getWord());
        realm.commitTransaction();
        realm.close();
        updateView();
    }

    private void setupAdapter() {
        adapter = new WordInfoAdapter(wordEntity.getTags());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        wordTV = (TextView)root.findViewById(R.id.word_info_textview);
        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view);
        formsRecyclerView = (RecyclerView)root.findViewById(R.id.forms_rv);
        addTagButton = (Button) root.findViewById(R.id.save_tag_button);
        deleteButton = (Button) root.findViewById(R.id.delete_button);
        changeFormButton = (ImageView) root.findViewById(R.id.change_button);
        addFormButton = (ImageView) root.findViewById(R.id.add_form_button);
        editTagET = (EditText) root.findViewById(R.id.taget);
        infoTextView = (TextView) root.findViewById(R.id.info_text);
        formsTitle = (TextView) root.findViewById(R.id.forms_title);
    }

}
