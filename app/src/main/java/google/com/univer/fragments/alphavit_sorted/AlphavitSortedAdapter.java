package google.com.univer.fragments.alphavit_sorted;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import google.com.univer.MainAdapter;
import google.com.univer.R;
import google.com.univer.model.WordEntity;

/**
 * Created by Sergey on 09.11.2016.
 */

public class AlphavitSortedAdapter extends RecyclerView.Adapter<AlphavitSortedViewHolder> {

    private final Context context;
    private final List<WordEntity> wordList;
    private final MainAdapter.WordListListener listener;
    private ArrayList<WordEntity> additionalList;

    public AlphavitSortedAdapter(Context context, List<WordEntity> wordList, MainAdapter.WordListListener listener){
        this.context = context;
        this.wordList = wordList;
        this.additionalList = new ArrayList<>();
        additionalList.addAll(wordList);
        this.listener = listener;
    }

    @Override
    public AlphavitSortedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        AlphavitSortedViewHolder viewHolder = new AlphavitSortedViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlphavitSortedViewHolder holder, int position) {
        holder.bind(additionalList.get(position), new MainAdapter.WordListListener() {
            @Override
            public void onEditClicked(WordEntity wordEntity, String word) {
                listener.onEditClicked(wordEntity, word);
            }

            @Override
            public void onDeleteClicked(WordEntity word) {
                listener.onDeleteClicked(word);
            }

            @Override
            public void onWordClicked(WordEntity word) {
                listener.onWordClicked(word);
            }
        });
    }

    @Override
    public int getItemCount() {
        return additionalList.size();
    }

    public void setSearchText(String text) {
        text = text.toLowerCase();
        additionalList = new ArrayList<>();
        for(WordEntity entity : wordList){
            if(entity.getWord().contains(text)){
                additionalList.add(entity);
            }
        }
        notifyDataSetChanged();
    }
}
