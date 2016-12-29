package google.com.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sergey on 12.12.2016.
 */

public class ImageLoaderFragment extends Fragment {

    @BindView(R.id.image_url_tv)
    EditText imageUrlEditText;

    @BindView(R.id.image_view)
    CircleImageView imageView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_image_loader, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        ButterKnife.bind(this, root);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.load_button)
    void onLoadImageClicked(){
        String url = imageUrlEditText.getText().toString();
        if(URLUtil.isValidUrl(url)){
            showProgressBar();
            Picasso.with(getActivity()).load(url).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressBar();

                        }
                    });
                }

                @Override
                public void onError() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressBar();
                            showErrorMessage();
                        }
                    });
                }
            });
            imageUrlEditText.setText("");
        }else{
            imageUrlEditText.setError(getString(R.string.invalid_url));
        }
    }

    private void showErrorMessage() {
        Toast.makeText(getActivity(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar() {
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

}
