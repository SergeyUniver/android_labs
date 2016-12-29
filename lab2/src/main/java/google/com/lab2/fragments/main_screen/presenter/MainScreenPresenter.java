package google.com.lab2.fragments.main_screen.presenter;

import android.app.Fragment;
import android.view.View;

import google.com.lab2.R;
import google.com.lab2.activity.MainActivity;
import google.com.lab2.fragments.base_fragment.presenter.BasePresenter;
import google.com.lab2.fragments.main_screen.GameNumber;
import google.com.lab2.fragments.main_screen.view.IMainScreenView;
import google.com.lab2.fragments.main_screen.view.MainScreenView;

/**
 * Created by Sergey on 13.11.2016.
 */

public class MainScreenPresenter extends BasePresenter implements IMainScreenPresenter{

    private GameNumber number;
    private IMainScreenView view;
    private int attemptCount = 0;

    public static Fragment createInstance() {
        MainScreenPresenter presenter = new MainScreenPresenter();
        presenter.generateNumber();
        return presenter;
    }

    private void generateNumber() {
        number = new GameNumber();
    }

    @Override
    protected Fragment getMVPView() {
        return null;
    }

    @Override
    protected void onRootInflated(View root) {
        view = new MainScreenView(root, this, getActivity());
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void onGuessButtonClicked(String text) {
        attemptCount ++;
        view.updateMessageStatus(number.getMessageNumber(text));
    }

    @Override
    public void onSuccessDialogAgainClicked() {
        resetData();
    }

    private void resetData() {
        generateNumber();
        attemptCount = 0;
        view.setDefaultView();
    }

    @Override
    public void onSuccessDialogStopClicked() {
        ((MainActivity)getActivity()).finishApp();
    }

    @Override
    public int getAttemptCount() {
        return attemptCount;
    }
}
