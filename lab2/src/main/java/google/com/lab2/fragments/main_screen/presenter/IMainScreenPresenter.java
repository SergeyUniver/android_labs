package google.com.lab2.fragments.main_screen.presenter;

import google.com.lab2.fragments.base_fragment.presenter.IBasePresenter;

/**
 * Created by Sergey on 13.11.2016.
 */
public interface IMainScreenPresenter extends IBasePresenter {
    void onGuessButtonClicked(String text);

    void onSuccessDialogAgainClicked();

    void onSuccessDialogStopClicked();

    int getAttemptCount();
}
