package google.com.lab2.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import google.com.lab2.R;

/**
 * Created by Sergey on 13.11.2016.
 */

public class ViewHelper {

    public static void showFirstFragment(FragmentManager fragmentManager, Fragment fragment) {
        showFragmentWithoutAnimation(fragmentManager, fragment, false, null);
    }

    public static void showFragmentWithoutAnimation(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, String backStackKey) {
        FragmentTransaction transaction =
                fragmentManager
                        .beginTransaction()
                        .add(R.id.body_fragment, fragment);
        if (addToBackStack)
            transaction.addToBackStack(backStackKey);
        transaction.commit();
    }

}
