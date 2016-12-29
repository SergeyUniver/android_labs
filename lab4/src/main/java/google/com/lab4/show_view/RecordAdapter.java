package google.com.lab4.show_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import google.com.lab4.GroupMate;
import google.com.lab4.R;

/**
 * Created by Sergey on 12.12.2016.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{

    private ArrayList<GroupMate> listRecord;

    public RecordAdapter(ArrayList<GroupMate> list){
        this.listRecord = list;
    }

    public void setListRecord(ArrayList<GroupMate> listRecord) {
        this.listRecord = listRecord;
    }

    public ArrayList<GroupMate> getListRecord() {
        return listRecord;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.bind(listRecord.get(position));
    }

    @Override
    public int getItemCount() {
        return listRecord.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;

        TextView dateTextView;

        public RecordViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
        }

        public void bind(GroupMate groupMate) {
            nameTextView.setText(groupMate.getSecondName() + " " + groupMate.getName() + " " + groupMate.getPatronymic());
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            dateTextView.setText(df.format(groupMate.getCreateDate()));
        }
    }

}
