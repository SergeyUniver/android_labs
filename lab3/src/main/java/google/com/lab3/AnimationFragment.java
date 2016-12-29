package google.com.lab3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sergey on 12.12.2016.
 */

public class AnimationFragment extends Fragment {

    @BindView(R.id.animation_text_view)
    TextView animationTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_animation, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        ButterKnife.bind(this, root);
    }

    @OnClick(R.id.start_button)
    void onStartButtonClicked(){
        Animation anim = null;
        Random r = new Random();
        int number = r.nextInt(4);
        switch (number) {
            case 0:
                anim = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
                showToast("alpha");
                break;
            case 1:
                anim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale);
                 showToast("scale");
                break;
            case 2:
                anim = AnimationUtils.loadAnimation(getActivity(), R.anim.trans);
                 showToast("trans");
                break;
            case 3:
                anim = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                 showToast("rotate");
                break;
            case 4:
                anim = AnimationUtils.loadAnimation(getActivity(), R.anim.combo);
                 showToast("combo");
                break;
        }
        animationTextView.startAnimation(anim);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
