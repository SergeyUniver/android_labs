package google.com.lab2.fragments.main_screen.view;

import google.com.lab2.fragments.base_fragment.view.IBaseView;

/**
 * Created by Sergey on 13.11.2016.
 */
public interface IMainScreenView extends IBaseView {
    void updateMessageStatus(int messageNumber);

    void setDefaultView();
}
