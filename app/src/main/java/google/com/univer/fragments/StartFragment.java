package google.com.univer.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import google.com.univer.MainActivity;
import google.com.univer.R;

/**
 * Created by Sergey on 19.10.2016.
 */

public class StartFragment extends Fragment {

    private View root;
    private ProgressDialog progressDialog;
    private Button alphavitSortedButton;
    private Button descendingSortedButton;
    private Button asscendingSortedButton;
    private Button showTextButton;
    private Button statButton;


    public static StartFragment createInstance(){
        StartFragment fragment = new StartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_start, container, false);
        this.root = root;
        initView();
        setupView();
        return root;
    }

    private void setupView() {
        alphavitSortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showAlphavitFragment();
            }
        });
        descendingSortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showCountSortedFragment(true);
            }
        });
        asscendingSortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showCountSortedFragment(false);
            }
        });
        showTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showText();
            }
        });
        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showStatFragment();

            }
        });
    }

    private MainActivity getMainActivity() {
        return ((MainActivity)getActivity());
    }

    private void initView() {
        alphavitSortedButton = (Button)root.findViewById(R.id.sorted_by_alphavit_button);
        descendingSortedButton = (Button)root.findViewById(R.id.sorted_by_descending_button);
        asscendingSortedButton = (Button)root.findViewById(R.id.sorted_by_ascending_button);
        showTextButton = (Button)root.findViewById(R.id.show_text_button);
        statButton = (Button)root.findViewById(R.id.diagram_button);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(((MainActivity)getActivity()).isWordProcessing){
            showProgressDialog(getString(R.string.dictionary_generated_message), false);
        }
    }


    public void showProgressDialog(String message, boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(message);
            progressDialog.setCancelable(cancelable);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }

    public void onWordMapGenerating() {
        showProgressDialog(getString(R.string.dictionary_generated_message), false);
    }

    public void onWordMapGenerated(){
        hideProgressDialog();
    }
}
