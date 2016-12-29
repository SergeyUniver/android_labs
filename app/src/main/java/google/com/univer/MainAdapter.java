package google.com.univer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.TreeMap;

import google.com.univer.model.WordEntity;

/**
 * Created by Sergey on 21.09.2016.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final WordListListener listener;
    private final boolean isAlphavitSort;
    private ArrayList<String> wordList;

    public void updateWords(TreeMap<String, Integer> wordsMap) {
        this.wordsMap = wordsMap;
        generateListWord();
        notifyDataSetChanged();
    }


    public interface WordListListener{
        void onEditClicked(WordEntity wordEntity, String word);
        void onDeleteClicked(WordEntity word);
        void onWordClicked(WordEntity word);
    }

    private final Context context;
    private TreeMap<String, Integer> wordsMap;

    public MainAdapter(Context context, TreeMap<String, Integer> wordsMap, WordListListener listener, boolean isAlphavitSort){
        this.context = context;
        this.wordsMap = wordsMap;
        this.listener = listener;
        this.isAlphavitSort = isAlphavitSort;
        generateListWord();
    }

    private void generateListWord() {
        wordList = new ArrayList<>();
        for(String word : wordsMap.keySet()){
            wordList.add(word);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MainViewHolder)holder).bind(wordList.get(position), listener, wordsMap.get(wordList.get(position)));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
