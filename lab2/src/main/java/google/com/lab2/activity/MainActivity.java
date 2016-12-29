package google.com.lab2.activity;

import android.app.Fragment;

import google.com.lab2.R;
import google.com.lab2.fragments.main_screen.presenter.MainScreenPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected Fragment getFirstFragment() {
        return MainScreenPresenter.createInstance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void finishApp() {
        this.finishAffinity();
    }
}
