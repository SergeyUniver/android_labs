package google.com.univer.fragments.word_info;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import google.com.univer.R;
import google.com.univer.model.Tag;
import io.realm.RealmList;

/**
 * Created by Sergey on 16.11.2016.
 */

public class WordInfoAdapter extends RecyclerView.Adapter<WordInfoAdapter.WordInfoViewHolder> {

    private RealmList<Tag> listWord;

    public WordInfoAdapter(RealmList<Tag> listWord){
        this.listWord = listWord;
    }

    @Override
    public WordInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alphavit, parent, false);
        WordInfoViewHolder viewHolder = new WordInfoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WordInfoViewHolder holder, int position) {
        holder.bind(listWord.get(position));
    }

    @Override
    public int getItemCount() {
        return listWord.size();
    }

    public void setListWord(RealmList<Tag> tags){
        listWord = tags;
    }

    protected class WordInfoViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public WordInfoViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            tv = (TextView)itemView.findViewById(R.id.letter_text);
        }

        public void bind(Tag tag) {
            tv.setText(tag.getTag() + "(" + tag.getCount() + ")");
        }
    }



}
