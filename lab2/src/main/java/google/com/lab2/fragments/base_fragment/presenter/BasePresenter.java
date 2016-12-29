package google.com.lab2.fragments.base_fragment.presenter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sergey on 13.11.2016.
 */

public abstract class BasePresenter extends Fragment implements IBasePresenter{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutID(), container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        onRootInflated(root);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getMVPView() != null) {
            getMVPView().onDestroyView();
        }
    }

    protected abstract Fragment getMVPView();

    protected abstract void onRootInflated(View view);

    protected abstract int getLayoutID();

}
