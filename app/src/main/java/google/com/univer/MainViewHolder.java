package google.com.univer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sergey on 21.09.2016.
 */
public class MainViewHolder extends RecyclerView.ViewHolder {

    private EditText wordEditText;
    private TextView editTextView;
    private TextView deleteTextView;
    private TextView saveTextView;
    private TextView counterTextView;

    public MainViewHolder(View itemView) {
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

    public void bind(final String word, final MainAdapter.WordListListener listener, int repeat) {
        wordEditText.setEnabled(false);
        wordEditText.setText(word);
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
                editTextView.setEnabled(false);
                editTextView.setVisibility(View.VISIBLE);
                saveTextView.setVisibility(View.GONE);
//                listener.onEditClicked(word);
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onDeleteClicked(word);
            }
        });
        counterTextView.setText(String.valueOf(repeat));
    }
}
