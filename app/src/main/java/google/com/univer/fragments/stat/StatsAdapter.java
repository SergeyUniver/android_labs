package google.com.univer.fragments.stat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import google.com.univer.R;

/**
 * Created by Sergey on 14.12.2016.
 */

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {

    private final ArrayList<String> list;

    public StatsAdapter(ArrayList<String> list){
        this.list = list;
    }

    @Override
    public StatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        StatsViewHolder viewHolder = new StatsViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(StatsViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    class StatsViewHolder extends RecyclerView.ViewHolder{

        private TextView textTV;

        public StatsViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            textTV = (TextView) itemView.findViewById(R.id.letter_text);
        }

        public void bind(String s) {
            textTV.setText(s);
        }
    }

}
