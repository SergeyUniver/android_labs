package google.com.univer.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import google.com.univer.R;
import google.com.univer.fragments.word_info.WordInfoFragment;
import google.com.univer.utils.DataUtils;
import google.com.univer.view.ViewHelper;
import io.realm.Realm;

/**
 * Created by Sergey on 14.12.2016.
 */
public class FullTextFragment extends Fragment {

    private View root;
    private TextView fullTextTV;
    private ArrayList<String> listText;
    private int currentPage = 0;
    private ImageView prevButton;
    private ImageView nextButton;
    private String currentText;
    private Realm realm;

    public static FullTextFragment createInstance(ArrayList<String> list){
        FullTextFragment fragment = new FullTextFragment();
        fragment.listText = list;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_full_text, container, false);
        this.root = root;
        initView();
        updateButton();
        showNextText();
        return root;
    }

    private void updateButton() {
        if(currentPage > 0){
            prevButton.setVisibility(View.VISIBLE);
        }else{
            prevButton.setVisibility(View.GONE);
        }
        if(currentPage < listText.size()){
            nextButton.setVisibility(View.VISIBLE);
        }else{
            nextButton.setVisibility(View.GONE);
        }
    }

    private void showPrevText(){
        currentText = getPrevText();
        fullTextTV.setText(currentText);
    }

    private String getPrevText() {
        currentPage --;
        if(currentPage >= 0){
            return listText.get(currentPage);
        }
        return "";
    }

    private void showNextText() {
        currentText = getNextText();
        fullTextTV.setText(currentText);
    }

    private void onPositionClicked(int position) {
        final String word = getWordByPosition(position);
        String tag = DataUtils.getCurrentTagByWord(realm, word.toLowerCase());
        new AlertDialog.Builder(getActivity())
                .setMessage(word + "\nCurrent tag: " + tag)
                .setPositiveButton("OK", null)
                .setNegativeButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showWordInfoFragment(word);
                    }
                })
                .create()
                .show();
    }

    private void showWordInfoFragment(String word) {
        ViewHelper.showFragmentLeftRight(getFragmentManager(), WordInfoFragment.createInstance(word), true, null);

    }

    private String getWordByPosition(int position) {
        if(currentText.charAt(position) == ' '){
            position ++;
        }
        int startPosition = position;
        int endPosition = position;
        while(startPosition >= 0){
            if(currentText.charAt(startPosition) == ' '){
                startPosition ++;
                break;
            }else{
                startPosition --;
            }
        }
        while(endPosition <= currentText.length()){
            if(currentText.charAt(endPosition) == ' '){
                break;
            }else{
                endPosition ++;
            }
        }
        String word = currentText.substring(startPosition, endPosition);
        if(word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == ','
                || word.charAt(word.length()-1) == '!' || word.charAt(word.length()-1) == '?'){
            word = word.substring(0, word.length()-2);
        }
        return word;
    }

    private String getNextText() {
        currentPage ++;
        if(listText.size() > currentPage){
            return listText.get(currentPage);
        }
        return "";
    }


    private void initView() {
        fullTextTV = (TextView) root.findViewById(R.id.full_text_text_view);
        prevButton = (ImageView) root.findViewById(R.id.prev_button);
        nextButton = (ImageView) root.findViewById(R.id.next_button);

        fullTextTV.setOnTouchListener(new View.OnTouchListener() {
            public boolean isMove;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    isMove = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(isMove){
                        isMove = false;
                    }else {
                        int position = fullTextTV.getOffsetForPosition(event.getX(), event.getY());
                        onPositionClicked(position);
                    }
                }
                return true;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClicked();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevButtonClicked();
            }
        });
    }

    private void onPrevButtonClicked() {
        showPrevText();
        updateButton();

    }

    private void onNextButtonClicked() {
        showNextText();
        updateButton();

    }

}
