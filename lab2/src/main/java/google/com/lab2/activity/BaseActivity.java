package google.com.lab2.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import google.com.lab2.utils.ViewHelper;

/**
 * Created by Sergey on 13.11.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onCreateFinished();
    }

    private void onCreateFinished() {
        showFirstFragment();
    }

    private void showFirstFragment() {
        ViewHelper.showFirstFragment(getFragmentManager(), getFirstFragment());
    }

    protected abstract Fragment getFirstFragment();

    protected abstract int getLayoutId();

}
