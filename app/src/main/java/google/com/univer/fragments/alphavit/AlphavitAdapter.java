package google.com.univer.fragments.alphavit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import google.com.univer.R;

/**
 * Created by Sergey on 15.11.2016.
 */

public class AlphavitAdapter extends RecyclerView.Adapter<AlphavitAdapter.AlphavitViewHolder> {

    private final OnLetterSelected listener;


    public interface OnLetterSelected{
        void onLetterSelected(String letter);
    }


    public AlphavitAdapter(OnLetterSelected listener){
        this.listener = listener;
    }

    @Override
    public AlphavitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alphavit, parent, false);
        AlphavitViewHolder viewHolder = new AlphavitViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlphavitViewHolder holder, int position) {
        holder.bind(String.valueOf((char)(position + 65)), listener);
    }

    @Override
    public int getItemCount() {
        return 26;
    }

    protected class AlphavitViewHolder extends RecyclerView.ViewHolder{

        private TextView letterTextVIew;

        public AlphavitViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            letterTextVIew = (TextView) itemView.findViewById(R.id.letter_text);
        }

        public void bind(final String letter, final OnLetterSelected listener) {
            letterTextVIew.setText(letter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLetterSelected(letter);
                }
            });
        }
    }

}
