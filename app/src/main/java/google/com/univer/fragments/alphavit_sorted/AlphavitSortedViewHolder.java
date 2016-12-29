package google.com.univer.fragments.alphavit_sorted;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import google.com.univer.MainAdapter;
import google.com.univer.R;
import google.com.univer.model.WordEntity;

/**
 * Created by Sergey on 09.11.2016.
 */

public class AlphavitSortedViewHolder extends RecyclerView.ViewHolder {

    private EditText wordEditText;
    private TextView editTextView;
    private TextView deleteTextView;
    private TextView saveTextView;
    private TextView counterTextView;

    public AlphavitSortedViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        wordEditText = (EditText) itemView.findViewById(R.id.word_edit_text);
        editTextView = (TextView) itemView.findViewById(R.id.edit_text_view);
        saveTextView = (TextView) itemView.findViewById(R.id.save_text_view);
        counterTextView = (TextView) itemView.findViewById(R.id.repeat_text_view);
        deleteTextView = (TextView) itemView.findViewById(R.id.delete_text_view);
    }

    public void bind(final WordEntity word, final MainAdapter.WordListListener listener) {
        wordEditText.setEnabled(false);
        wordEditText.setClickable(true);
        wordEditText.setText(word.getWord());
        saveTextView.setVisibility(View.GONE);
        editTextView.setVisibility(View.VISIBLE);
        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordEditText.setEnabled(true);
                editTextView.setVisibility(View.GONE);
                saveTextView.setVisibility(View.VISIBLE);
                wordEditText.requestFocus();
            }
        });
        saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordEditText.setEnabled(false);
                editTextView.setVisibility(View.VISIBLE);
                saveTextView.setVisibility(View.GONE);
                listener.onEditClicked(word, wordEditText.getText().toString());
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(word);
            }
        });
        wordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWordClicked(word);
            }
        });
        counterTextView.setText(String.valueOf(word.getRepeatCount()));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWordClicked(word);
            }
        });
    }
}
